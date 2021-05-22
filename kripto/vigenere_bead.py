# STEP 1. Repetition detection
# input: string s
# output: all pairs of positions (i,j) s.t. s[i:(i+3)] == s[j:(j+3)]

def repetitionsInString(s, length = 3):
    n = len(s)
    ret = [(i,j) for i in range(0, n-length) for j in range(i+1, n-length+1) if s[i:(i+length)] == s[j:(j+length)] ]
    return ret

# STEP 2. Find out the shiftList length
# best guess: greatest common divisor of all differences (j-i) where (i,j): positions in the repetition

def guessedKeyLength(ciphertext, length = 3):
    differences = [j-i for (i,j) in repetitionsInString(ciphertext, length)]
    return gcd(differences)

# STEP 3.: Megpróbáljuk illuszrálni az adott nyelvre

def getFirstBlock():
    LP1 = [.15, .15, .15, .15, .15]
    dist1 = GeneralDiscreteDistribution(LP1)
    alphabet1 = 'AEIOU'
    textL1 = [alphabet1[dist1.get_random_element()] for _ in range(5)]
    return textL1

def getSecondBlock():
    alphabet2 = ['B','C','D','F','G','H','J','K','L','J','M','N','P','Q','R','S','T','V','W','X','Y','Z']
    textL2 = [alphabet2.get_random_element()] for _ in range(7)]
    return textL2

LP = [.80, .15, .05]
dist = GeneralDiscreteDistribution(LP)
alphabet = [getFirstBlock(), getSecondBlock(), 'KORE']
textL = [alphabet[dist.get_random_element()] for _ in range(200)]
text = "".join(textL)
