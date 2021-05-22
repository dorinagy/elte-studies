# VIGENERE cipher:
# Idea: shift amount is not the same, but varies with the position of the letter in the text.
# Az alapötlet: mindig változatjuk az eltolás mértékét - periodikusan ismételve egy eltolási sorozat elemeit.
# [3, 7, 3, 7, 3, 7, ...]
# KEY: list of integers e.g. L = [3,7].
# A kulcs egy véges lista egészekből

# múltkori függvények betűk ábécébeli pozíciójára stb.
def indexOfLetter(c):
    return ord(c) - 65

def letterWithIndex(i):
    return chr(i+65)
    
def shiftChar(c, shift):
    i = indexOfLetter(c)
    new = (i + shift) % 26
    return letterWithIndex(new)

# Vigenere titkosítás törése
def vigenere(s, shiftList):
    ret = ""
    # Current position in the shiftList
    pos = 0
    n = len(shiftList)
    for c in s:
        new = shiftChar(c, shiftList[pos])
        ret += new
        # Add one, restart if list is over
        pos = (pos + 1) % n
    return ret

L = [3, 3, 3]
vigenere("DOG", L)




# Breaking Vigenere:    
# STEP 1. have to detect repetitions. 
#(Ismétlődő részek keresése.)
# STEP 2. detect the length of shiftList
#(Ismétlésekből az eltolási periódus megállapítása.)
# STEP 3. guess the shift amounts based on English letter statistics etc.
#(Az egyes eltolások megtippelése a bemeneti szöveg tulajdonságai alapján.)

# STEP 1. Repetition detection (admittedly inefficient)
# input: string s
# output: all pairs of positions (i,j) s.t. s[i:(i+3)] == s[j:(j+3)]

i = 10
s = "ABCDEFGHIJKLMNO"
s[10:13]

def repetitionsInString(s, length = 3):
    n = len(s)
    ret = [(i,j) for i in range(0, n-length) for j in range(i+1, n-length+1) if s[i:(i+length)] == s[j:(j+length)] ]
    return ret

s = "ABCjahskjhjhakjdhkjadhkhkABChsadkjhkajhdkahskjdaABC"
repetitionsInString(s)

# STEP 2. Find out the shiftList length
# best guess: greatest common divisor of all differences (j-i) where (i,j): positions in the repetition
def guessedKeyLength(ciphertext, length = 3):
    differences = [j-i for (i,j) in repetitionsInString(ciphertext, length)]
    return gcd(differences)

m = "THEHTRWFVSTHNZJKANNNBZHBATGOKNMQEWDFANTHEHAGBZBHAGVBSHTHENNANATHENNAJNSHJAOMNBVHTHEATHEHHHHGTHAGHSGTHHTHEVVCDCSNXTHE"
[i for i in range(len(m)-2) if m[i:(i+3)] == "THE" ]

c = vigenere(m, [2, 3, 10])
repetitionsInString(c)

guessedKeyLength(c)

# STEP 3. To guess the values in the shiftList (in the exmaple: 2, 3, 10)
# Difficult not really crypto: we have to use the fact about plaintext (it's English)

# STEP 3.: Megpróbáljuk illuszrálni egy "kamu" nyelvre, melyben csak A, B, C, D betűk vannak.
# Letter stats: [58, 25, 12, 5]
LP = [.58, .25, .12, .05]
dist = GeneralDiscreteDistribution(LP)
alphabet = 'ABCD'
textL = [alphabet[dist.get_random_element()] for _ in range(300)]
text = "".join(textL)

k = [2, 3, 1, 2]

#ciphertext = vigenere(text, k)
text2 = 'DCDBFCEEDCBEFFEEFCEBFCECCDEEFFCECCECCFBCCFBECCEECCBBDCBEDCEBFFECCFCBDCD\
BDEEECFBCFFEBEFEDCFEECDBECEEBCCBBFFEEFCBBCDEBEDBBEDDECCEBCDCECFBECEDDFDB\
BCCBECCBCDCBEFFCBFCBECDBBFDBBCFECCCBBCCCBFFBDFFDBCCEBCDEECFEDCFCEDCBDCDB\
ECFBBFEECCFBDCCBBFDEEFCECDFCEFCBCDFBBFFBECFBBDEDEFFBECCCBFFBDCDBBCCBECDE\
BDCBCCFECDFCB'


#repetitionsInString(ciphertext)[:20]
repetitionsInString(text2)[:20]

guessedKeyLength(text2)

guessedKeyLength(text2, 6)