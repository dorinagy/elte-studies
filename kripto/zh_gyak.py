# KRIPTOGRÁFIA ÉS BIZTONSÁG

# Kriptografia - Caesar cipher
# replace letters by the one 3 behind it - ASCII
# A -> D, B -> E, C -> F, --- Z -> C
# DOG -> GRJ
# use chr, ord

# determine index of letter -> A:0
def indexOfLetter(c):
    return ord(c)-65

def letterWithIndex(i):
    return chr(i+65)

def shiftByThree(c):
    i = indexOfLetter(c)
    new = (i+3)%26
    return letterWithIndex(new)

def caesar(s):
    ret = ""
    for c in s:
        ret+= shiftByThree(c)
    return ret

caesar("DOG")

# generic shift method
def shiftChar(c, shift):
    i = indexOfLetter(c)
    new = (i+shift)%26
    return letterWithIndex(new)

def shi(s):
    ret = ""
    for c in s:
        ret+= shiftByThree(c)
    return ret



# One-time pad
def encrypt_xor(plain, key):
    n = min(len(plain), len(key))
    ascii_codes = [ord(plain[i]) ^^ ord(key[i]) for i in range(n)]
    return ''.join([chr(x) for x in ascii_codes])

# Segédfüggvények:
from binascii import hexlify, unhexlify
# Hexlify converts a sequence of ascii characters to the sequence of two-digit hexa codes (A = hexa 41 etc.)
hexlify("ABCDE")

message01 = "PLAIN001"
key = "BUDAPEST"
cipher01 = encrypt_xor(message01, key)
print(hexlify(cipher01))


# VÉLETLEN SZÁM GENERÁLÓ ALGORITMUS
# értelmezés: az utolsó 7-jegyből kapott szám akár 0-val is kezdődhet. A szám 3. jegye: a tízezres helyiérték
def third_dig(n):
    return floor(n%100000 / 10000)

third_dig(1234567)

# Parameters: seed, length
def rand_seq(seed, N):
    ret = []
    last = seed
    for i in range(N):
        ret.append(third_dig(last) % 2)
        last = (last^2) % 10^7
    return ret

rand_seq(1234567, 20)

def period_of_rand_seq(seed):
    last = seed
    L = []
    N = 0
    while last not in L:
        L.append(last)
        N += 1
        last = (last^2) % 10^7
    return N

period_of_rand_seq(1234567)

[period_of_rand_seq(K) for K in [1..10]]

[period_of_rand_seq(K) for K in [91..100]]

# Periódus nem túl hosszú -> nem 'one-time'. Information leaked.

# kapunk két plaintext-et és egy ciphertextet. Meg kell tippelnünk, 
# melyik plaintext képe a ciphertext. Ha 50%-nál lényegesen jobban teljesítünk: baj.

# Example 1. 
# Plaintexts: "AAAAAAAA", "BBBBBBBB" (or longer versions)
# Method: XOR with pseudorandom function's output
# Key: uniformly random integer between 1 and 10000
# (Pseudo)randomness: take powers of seed, Output 0 if last digit: 0, 1, 2, 3, 4, output 1 if last digit is 5, 6, 7, 8, 9.

# E.g.
def rand_gen_ex1(seed, N):
    return [0 if (seed^k)%10 <5 else 1     for k in range(N)]

rand_gen_ex1(11, 20)

def convert_binary_list_to_str(L):
    ret = ""
    for i in range(floor(len(L) / 8)):
        ret += chr( sum([2^k * L[8*i+k] for k in range(8)] ) )
    return ret

ord("A")
65.digits(2)
L = 65.digits(2) + [0]
convert_binary_list_to_str(L)

def output_random_ciphertext_ex1():
    m0 = "AAAAAAAA"
    m1 = "BBBBBBBB"
    plain = m0 if randint(0,1) == 0 else m1
    seed = randint(1, 10000)
    L = rand_gen_ex1(seed, len(plain)*8)
    k = convert_binary_list_to_str(L)
    return encrypt_xor(plain, k)

output_random_ciphertext_ex1()


from Crypto.Cipher import AES

# KEY length: 16, 24 or 32 BYTE (128, 192, 256 bits)
key = '0123456789abcdef'
# encryption object
enc = AES.new(key)
# BLOCK (no stream yet)
cipher05 = enc.encrypt("A text length 16")
str(cipher05)

cipher05b = enc.encrypt("A text length 16")
cipher05b

enc.encrypt(cipher05b)

enc.encrypt('A text length 16A text length 16')

# Block modes of operation - ECB
text = "This is some new text which contains a bit more letters. It should still be of length which is a multiple of the blocksize."
enc_ecb = AES.new(key, AES.MODE_ECB)
cipher06 = enc_ecb.encrypt(text)

# Simple padding (we add as many 0s as are missing from the block length multiple
def pad_text(s, blength = 16):    
    r = len(s) % blength
    if r == 0:
        return s
    else:
        return s + '0'*(blength-r)

pad_text(text)

cipher06 = enc_ecb.encrypt(pad_text(text))
cipher06

enc_ecb.decrypt(cipher06)

enc.encrypt(pad_text('0000'))

# DO not try to understand what this is
def black_box_enc_image():
    s = ''
    for i in range(10000):
        k0 = 'ababababcdcdcdcd'
        k1 = i%100
        k2 = (i-k1)/50*2/4
        d = (k1/10.0-5)**2 + (k2/10.0-5.0)**2        
        s += str(floor(d/10))*4
    enc_ebc_img = AES.new(k0, AES.MODE_ECB)
    return enc_ebc_img.encrypt(s)

cipher07 = black_box_enc_image()

# Block modes of operation - CBC
text = "This is some new text which contains a bit more letters. It should still be of length which is a multiple of the blocksize. Hey!"
IV = ''.join([chr(randint(0,255)) for i in range(16)])
enc_cbc = AES.new(key, AES.MODE_CBC, IV)
cipher08 = enc_cbc.encrypt(text)
cipher08

text1 = text[:32]
text2 = text[32:]
enc_cbc2 = AES.new(key, AES.MODE_CBC, IV)

# AES object enc_cbc2 knows where it was (it has state)
enc_cbc2.encrypt(text2)

# key: obtained by the two communicating parties via a "key-exchange algorithm" (or whispered into the partner's ear years before).
# IV: generated freshly for each communication round, randomly, can be published
# CBC, OFB, CTR, all the modes have IV (nonce)

cipher09 = '\xb1x\xe9G\x13\xb5r\x06\xde8\xbfq\x947\xedN \x8cd\x84Jt\xf8&\xcdUX\xd9%)\xe2\xfd\x8c]\x91\xf3\t\x19\x86\xdew\x9c@k\xf5/\x1c^'

c_blocks = [cipher07[i:i+16] for i in range(0, len(cipher07), 16)]


uniques = list(Set(c_blocks))
print len(c_blocks), len(uniques)

c_seq = [uniques.index(x) for x in c_blocks]
horiz = 50
vert = 50
matrix_plot(matrix(horiz, vert, c_seq))

matrix(horiz, vert, c_seq)[0:10, 0:10]

# A priori, no information is available on the dimensions of the image. We should try other sizes.
horiz = 25
vert = 100
matrix_plot(matrix(horiz, vert, c_seq))

# A priori, no information is available on the dimensions of the image. We should try other sizes.
horiz = 100
vert = 25
matrix_plot(matrix(horiz, vert, c_seq))

key = "BudapestBudapest"
iv15 = "n7an54k?in%gs67"
enc = AES.new(key, AES.MODE_OFB, iv15+"=")
cipher09 = enc.encrypt("This was very easy!  We solved it in 10 minutes.")
cipher09

# Make list of candidate plaintexts
L = []
for i in range(256):
    enc = AES.new(key, AES.MODE_OFB, iv15+chr(i))
    L += [enc.decrypt(cipher09)]

from sage.probability.random_variable import DiscreteProbabilitySpace
def text_entropy(s):    
    s2 = s.lower()
    letters = Set(list(s2)).intersection(Set(alphabet))
    n = sum([s2.count(l) for l in letters])
    probs = dict([(l, 1.0*s2.count(l)/n) for l in letters])
    ps = DiscreteProbabilitySpace(list(letters), probs)
    return ps.entropy()
alphabet = [chr(i) for i in range(97, 123)]
def score_english(s, pen = 0.2):
    penalty = 0
    for c in s:
        c1 = c.lower()
        if not c1 in alphabet and not c1 in ',.?!- ':
            penalty += pen
    return text_entropy(s) + penalty

# Sort candidates by likeliness
sorted(L, key=score_english)[0:3]

# Hash: algo vs. kriptó
# algo: objektum -> szám (deter., de véletlen szerű, rövidít/tömörít)
# kriptó: + inverz nehéz

# Számlista -> polinom
def L2pol(L):
    n = len(L)
    ret = 0
    for k in range(n):
        ret += L[k]*x^k
    return ret

L2pol([5, 7, 2, 3, 0, 0, 7])
# CRC: hash(nem kriptó), üzenet : pol
# pol maradékos osztás

R.<x> = PolynomialRing(ZZ)

f = x^2 + 2*x + 1
g = x + 7

type(f)

f.quo_rem(g)

g*(x-5)

""" 1. A cipher scheme has the following structure for a secret key: 
the key is a string consisting of decimal digits only (0-9), the length of the key 
is either 8 or 9. How large is the key space (=how many different keys are possible)? 
How long should a key consisting of only bits (0 and 1) to offer the same or larger 
size of key space?

[Problem 1: 5 pts] """

# Problem 1 solution:
# Keyspace size: number of all possible keys
# Length 8: 10^8
# Length 9: 10^9
keyspace_size = 10^8 + 10^9
keyspace_size

# Problem 1, second part
# For a binary key of length L, the keyspace size is 2^L
# We need the smallest integer L with 2^L >= keyspace_size
L = ceil(log(keyspace_size, 2))

""" 2. We define the following encryption algorithm. As input, we expect a
 string consisting entirely of lowercase letters of the English alphabet (a-z). 
 The output is defined by using the following function: f(k) = ((k+5)^3) % 113.
  Using f, the letter at position k is shifted by f(k), here k = 0, 1,… (E.g. 
  Shifting ‘a’ by 2 positions gives ‘c’, shifting by 3 positions gives ‘d’ and so on, 
  if the shift is too large, w e wrap around: shifting ‘z’ by 2 positions gives ‘b’, 
  and shifting ‘a’ by 26 positions gives ‘a’ again etc.)


Implement the encryption function and compute the encrypted version of the following 
strings:
“mathematics”
“elte”
“tohellwithcovid”
Implement the decryption function and decrypt the following (the plaintexts won’t 
necessarily make sense):
“hahjjjahhs”
“popasppkkiw” """

def f(k):
    return ((k+5)^3) % 113
    
# We'll borrow the shift functions from earlier worksheets, adapted to LOWERCASE letters
# Note that lowercase letters start at position 97 in the code table
def indexOfLetter(c):    
    return ord(c) - 97
def letterWithIndex(i):
    return chr(i+97)
def shiftChar(c, shift):
    i = indexOfLetter(c)
    new = (i + shift) % 26
    return letterWithIndex(new)
    
# The "main" function implementing encryption and decryption
# Since the behaviour for encrypt-decrypt is similar, we use one function
# and the direction is given as a parmeter. The only change is in the sign of the shift.
# Default behaviour is encrypt
def problem2(s, enc = True):
    ret = ""
    dir = 1 if enc else -1
    for k in range(len(s)):
        ret += shiftChar(s[k], dir*f(k))
    return ret

# Part encrypt
[problem2(s) for s in ["mathematics", "elte", "tohellwithcovid"]]

# Part decrypt
[problem2(s, enc = False) for s in ["hahjjjahhs", "popasppkkiw"]]

""" 3. Consider the following “language” rules:

Valid messages contain only capital English letters (A-Z).
The letter ‘K’ is always followed by a letter ‘R’
We know that there is a 5-letter sequence consisting only of the letters 
‘A’, ‘B’ and ‘C’ that occurs frequently (no further information available), 
so several repetitions of length at least 5 are expected in the plaintext.

A text in this language was encrypted using a Vigenere-like cipher, where the 
characters get shifted by a periodically repeating amount. The key is a list of 
this shifts. E.g. if the key is [1, 2, 3], then “AAAAAAA” gets encrypted as “BCDBCDB”, 
and if the key is [1, 2, 3, 4, 5], then “BBBBBKRR” gets encrypted as “CDEFGLTU”.

Using the method seen in class (guessedKeyLength etc.) or otherwise, decrypt the
following ciphertext, if the only thing you know is that the plainext obeys the 
above rules and the key is either of length 7 or length 5. The answer to give is the key.

c = “XOBRBIEIOBIEIONPCVBXSWMNCJCJIYSTIODHDVJODUBLJFUWBBIEIODHDKNCQNCVXHDKNCXCJPBIYNNCJCJU
RRTBNCJCJETSTIODHDDVKTCKEZCAIWBIEIOBHDKNCBHTGBIEIOBLECBZJWDSBIEIORHVGNCJCJNCJCJLDTEZFOYGS
EMHDKNCCPXNCJCJNCJCJNCJCJWCFQCCOSCJPBICEWNMVGYISJUUFHLOOUECJPBICJPBISLVLYLXBBIEIOXECJPBIT
IODHDPCLYCJPBIWAEBUHVNCJCJNMPCJPBIZLBHEVIODHDIODHDNULYOXALYOKLFHDKNCHDKNCWOVGSBCJPBI” 

[Problem 3: 9 points] """

# We'll borrow the methods for breaking Vigenere from past worksheets
# The main ingredients are: finding repetitions, greatest common divisor for guessing the shift period length and then a bit of brute force
def indexOfLetter(c):
    return ord(c) - 65
def letterWithIndex(i):
    return chr(i+65)
def shiftChar(c, shift):
    i = indexOfLetter(c)
    new = (i + shift) % 26
    return letterWithIndex(new)
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

def repetitionsInString(s, length = 3):
    n = len(s)
    ret = [(i,j) for i in range(0, n-length) for j in range(i+1, n-length+1) if s[i:(i+length)] == s[j:(j+length)] ]
    return ret
def guessedKeyLength(ciphertext, length = 3):
    differences = [j-i for (i,j) in repetitionsInString(ciphertext, length)]
    return gcd(differences)

c = "XOBRBIEIOBIEIONPCVBXSWMNCJCJIYSTIODHDVJODUBLJFUWBBIEIODHDKNCQNCVXHDKNCXCJPBIYNNCJCJURRTBNCJCJETSTIODHDDVKTCKEZCAIWBIEIOBHDKNCBHTGBIEIOBLECBZJWDSBIEIORHVGNCJCJNCJCJLDTEZFOYGSEMHDKNCCPXNCJCJNCJCJNCJCJWCFQCCOSCJPBICEWNMVGYISJUUFHLOOUECJPBICJPBISLVLYLXBBIEIOXECJPBITIODHDPCLYCJPBIWAEBUHVNCJCJNMPCJPBIZLBHEVIODHDIODHDNULYOXALYOKLFHDKNCHDKNCWOVGSBCJPBI"

guessedKeyLength(c, 5)

# Looks as the shift period is of length 5.
# Now we try to guess the shift amounts.
# Our best guess is that repetitions are caused by the repeating pattern in the plaintext
# That repeating pattern is unknown, though we know that it contains only characters A, B and C. 
# In what form is it repeated in the ciphertext?
L = repetitionsInString(c, 5)
L[:15]

# Okay, it looks that we have the same thing in the ciphertext at positions 5, 115, 130
[c[k:k+5] for k in [5, 115, 130]]

# If the Vigenere period length is 5, and the (yet unknown) shift amounts are [x0, x1, x2, x3, x4], then
# positions 0, 5, 10, ..., 115, ..., 130 get all shifted by x0
# positions 1, 6, 11, ..., 116, ..., 131 get all shifted by x1 and so on.
# We assume (hopefully riht) that the plaintext contains only characters 
# A, B, C at positions 5 to 9, 115 to 119 adn 130 to 134.
# Since these 5-character parts get shifted to "IEIOB", we can restrict our attention to shifts with the following properties
# A shift of x0 takes A or B or C to I -> x0 is 8, 7 or 6.
# A shift of x1 takes A or B or C to E -> x1 is 4, 3 or 2.
# A shift of x2 takes A or B or C to I -> x2 is 8, 7 or 6.
# A shift of x3 takes A or B or C to O -> x3 is 14, 13 or 12.
# A shift of x4 takes A or B or C to B -> x4 is 1, 0 or 25.
# Now the keyspace is down to 3^5 possibilities, and we have yet one condition to use: the R after K rule.
def is_KR_rule_okay(s):
    for k in range(len(s) - 1):
        if s[k] == 'K' and s[k+1] != 'R':
            return False
    return True

# Try all remaining keys and check if the decrypted text obeys the "R after K" rule:
good_keys = []
for x0 in [6, 7, 8]:
    for x1 in [2, 3, 4]:
        for x2 in [6, 7, 8]:
            for x3 in [12, 13, 14]:
                for x4 in [0, 1, 25]:
                    if is_KR_rule_okay(vigenere(c, [-x0, -x1, -x2, -x3, -x4])):
                        good_keys.append([x0, x1, x2, x3, x4])

# Turns out there are 3 possible values for the key that fulfil our conditions. Any of these would be accepted in a live exam.
# In a real-life atack getting the possible keys down to 3 is fine enough:)

""" 4. Consider the following (poorly designed) random number generator:

the seed is a 7-digit integer S. Let x = S
[LOOP]:
consider the set of positive divisors of x: denote it by H
Let H1, H3, H7 and H9 denote the 4 subsets of H that contain those elements whose final 
digit is 1, 3, 7 or 9, respectively. Example: for x = 2469138, we have
H = {1, 2, 3, 6, 7, 14, 21, 42, 58789, 117578, 176367, 352734, 411523, 823046, 1234569, 
2469138},
H1 = {1, 21}
H3 = {3, 411523}
H7 = {7, 176367}
H9 = {58789, 1234569}
(Here comes the random generation)
Output two bits:

first compare the size of H1 and H3, and output a 1 if
|H1| > |H3|, otherwise output 0.
then output a 1 if |H9| > |H7|, otherwise output 0.
Let x = x+1 and continue with new iteration of loop until desired length is reached.
Set S = 1726188, and compute the first 100 elements of the stream as a string of 0s and 1s.     

[Problem 4: 6 points]

 

BONUS (5 pts): build a distinguisher to problem 4.

 

Input: two 100-long 0-1 sequences as strings, one a true random sequence, the other one 
generated as above.

Output: a guess about which one was obtained by true random generation """

# This function gives H1, H3, H7 and H9 for input n.
def h_sets(n):
    L = divisors(n)
    return [[x for x in L if x%10 == k] for k in [1, 3, 7, 9] ]

# Test in on the example input:
h_sets(2469138)

# for the generation of a single pair of bits (True/False type)
def rand_gen(n):
    h1, h3, h7, h9 = tuple(h_sets(n))
    return (len(h1) > len(h3), len(h9) > len(h7))

# For the generation of L bits (as 0-1 values in a list):
# Seed n is used, L bits are produced
def generate_rand(n, L):
    ret = []
    for x in [n..floor(n+L/2)]:
        a, b = rand_gen(x)
        ret.append(int(a))
        ret.append(int(b))
    return ret[:L]

L = generate_rand(1726188, 100)
# Since we are asked to produce s atring
p4_sol = "".join([str(x) for x in L])
p4_sol

len(p4_sol)

# For breaking the scheme
# Check if 0 and 1 are not equally likely (this is a biased generator)
p4_sol.count('0')

# Check some other seeds and lengths
L = generate_rand(172111288, 1000)
L.count(0)

L = generate_rand(8171128, 1000)
L.count(0)

# Looks as if 0 is more frequent (it is, we won't prove it, but empirical observation is good now)
# We will simply base our distinguisher on this observation 
def disting4(s0, s1):
     # Output 0 if s0 is better random, otherwise 1
     if s0.count('0') < s1.count('0'):
         return 0
     else:
         return 1

# Lets try it
rand_seq = ''.join([ '01'[randint(0,1)] for _ in range(100)])
# Should be 1
disting4(p4_sol, rand_seq)
