# titkos kulcs generálás
def shift_cipher_gen(): 
  return randint(0, 25)

# titkosítás
def shift_cipher_enc(key, plaintext): 
    def shift(char):
        i = ascii_lowercase.find(char)
        return ascii_uppercase[(i + key) % 26]
    return ''.join(shift(char) for char in plaintext)

# decrypt
def shift_cipher_dec(key, ciphertext): 
    def shift(char):
        i = ascii_uppercase.find(char)
        return ascii_lowercase[(i - key) % 26]
    return ''.join(shift(char) for char in ciphertext)



key = shift_cipher_gen()

ciphertext = shift_cipher_enc(key, plaintext)

shift_cipher_dec(key, ciphertext)