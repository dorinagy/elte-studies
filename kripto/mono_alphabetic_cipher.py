def mono_alphabetic_gen():
    abc = [i for i in ascii_lowercase]
    perm = sample(ascii_uppercase, k=26) 
    return {k: v for k, v in zip(abc, perm)}

def mono_alphabetic_enc(key, plaintext):
    return ''.join(key[char] for char in plaintext)

def mono_alphabetic_dec(key, ciphertext):
    key_inv = {v: k for k, v in key.items()}
    return ''.join(key_inv[char] for char in ciphertext)





## Törés

ciphertext = 'JGRMQOYGHMVBJWRWQFPWHGFFDQGFPFZRKBEEBJIZQQOCIBZKLFAFGQVFZFWWEOGWOPFGFHWOLPHLRLOLFDMFGQWBLWBWQOLKFWBYLBLYLFSFLJGRMQBOLWJVFPFWQVHQWFFPQOQVFPQOCFPOGFWFJIGFQVHLHLROQVFGWJVFPFOLFHGQVQVFILEOGQILHQFQGIQVVOSFAFGBWQVHQWIJVWJVFPFWHGFIWIHZZRQGBABHZQOCGFHX'

# angol betűk gyakorisága

 # forrás: http://pi.math.cornell.edu/~mec/2003-2004/cryptography/subs/frequencies.html
freq = {'a': .0812, 'b': .0149, 'c': .0271, 'd': .0432, 'e': .1202, 'f': .0230, 'g': .0203, 'h': .0592, 'i': .0731, 'j': .0010, 'k': .0069, 'l': .0398, 'm': .0261, 'n': .0695, 'o': .0768, 'p': .0182, 'q': .0011, 'r': .0602, 's': .0628, 't': .0910, 'u': .0288, 'v': .0111, 'w': .0209, 'x': .0017, 'y': .0211, 'z': .0007}


obs = Counter(ciphertext) 
obs


obs_freq = {}

for i in ascii_uppercase:
    try:
        obs_freq[i] = obs[i] / len(ciphertext)
    except KeyError: obs_freq[i] = 0.000

sorted(freq.items(), key=lambda item: item[1], reverse=True)

sorted(obs_freq.items(), key=lambda item: item[1], reverse=True)

