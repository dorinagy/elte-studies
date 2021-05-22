# SAGE használatával #

def indexOfLetter(c):
    return ord(c) - 65

def letterWithIndex(i):
    return chr(i+65)

def shiftChar(c, shift):
    i = indexOfLetter(c)
    new = (i + shift) % 26
    return letterWithIndex(new)


# 1. - Függvény Shift
def shiftCipher(s, enc = True):
    ret = ""
    k = 0
    dir = 1 if enc else -1
    for c in s:
        if (k % 2 == 0):
            shift = ((k+2)^3) % 107
        else:
            shift = ((k+3)^2) % 111

        ret += shiftChar(c, dir*shift)
    return ret

shiftCipher('cryptography')
shiftCipher('hungary')
shiftCipher('letsgobacktoschoolsoon')

#shiftCipher('nmhhlzjvtslmnbfzdwgk', False)


#Eredmény:

#QFMDHCUFODVM
#VIBUOFM
#ZSHGUCPOQYHCGQVCCZGCCB

# 2. - Vigenere2
def vigenere(s, shiftList):
    ret = ""
    pos = 0
    n = len(shiftList)
    for c in s:
        new = shiftChar(c, shiftList[pos])
        ret += new
        pos = (pos + 1) % n
    return ret

def repetitionsInString(s, length = 3):
    n = len(s)
    ret = [(i,j) for i in range(0, n-length) for j in range(i+1, n-length+1) if s[i:(i+length)] == s[j:(j+length)] ]
    return ret

def guessedKeyLength(ciphertext, length = 3):
    differences = [j-i for (i,j) in repetitionsInString(ciphertext, length)]
    return gcd(differences)


# 3. - Randgen2


# 4. - Hash
def char_position(letter):
    return ord(letter) - 97

def pos_to_char(pos):
    return chr(pos + 97)

def L2pol(L):
    n = len(L)
    ret = 0
    for k in range(n):
        ret += char_position(L[k])*x^k
    return ret

f = L2pol('HELLO')
#f = 14*x^4 + 11*x^3 + 11*x^2 + 4*x + 7

R.<x> = PolynomialRing(ZZ)

g = x^12 + 5*x^7 + 2

r = f.quo_rem(g) # maradék

# r(73)

#Eredmény:

# r = (0, 14*x^4 + 11*x^3 + 11*x^2 + 4*x + 7)
