from collections import Counter 
from functools import reduce
from math import gcd
from random import randint, sample 
import re
from string import ascii_lowercase, ascii_uppercase

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



def caesar_dec(ciphertext): 
    def shift(char):
        i = ascii_uppercase.find(char) 
        return ascii_lowercase[(i - 3) % 26]
    return ''.join(shift(char) for char in ciphertext)

