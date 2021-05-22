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



##################################
#         BEAD                   #
##################################


def rand_gen_ex1(N):
    LP = [.55, .45]
    dist = GeneralDiscreteDistribution(LP1)
    data = [0, 1]
    return [data[dist.get_random_element()] for _ in range(N)]

def convert_binary_list_to_str(L):
    ret = ""
    for i in range(floor(len(L) / 8)):
        ret += chr( sum([2^k * L[8*i+k] for k in range(8)] ) )
    return ret

# One-time pad
def encrypt_xor(plain, key):
    n = min(len(plain), len(key))
    ascii_codes = [ord(plain[i]) ^^ ord(key[i]) for i in range(n)]
    return ''.join([chr(x) for x in ascii_codes])
    
def output_random_ciphertext_ex1():
    m0 = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
    m1 = "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
    plain = m0 if randint(0,1) == 0 else m1
    seed = randint(1, 10000)
    # length = 200 byte -> len(plain)*8 = 1600 bit
    L = rand_gen_ex1(len(plain)*8)
    k = convert_binary_list_to_str(L)
    return encrypt_xor(plain, k)