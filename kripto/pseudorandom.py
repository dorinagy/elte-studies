import matplotlib.pyplot as plt 
import numpy as np

# Linear Congruential Generators

def random_gen(a, seed, c, m, n): 
    for i in range(n):
        seed = (a * seed + c) % m 
        yield seed

for i in random_gen(2, 1, 3, 5, 20): 
    print(i)


# Spectral test

def plot_2d_spectral_test(seq):
    fig = plt.figure(figsize=(10, 10)) 
    ax = fig.add_subplot(111) 
    ax.set_xlabel('x')ű

    ax.set_ylabel('y')
    ax.scatter(seq[:-1], seq[1:], marker='o') 
    return fig, ax


def plot_3d_spectral_test(seq, elev, azim):
    fig = plt.figure(figsize=(10, 10))
    ax = fig.add_subplot(111, projection='3d') 
    ax.scatter(seq[:-2], seq[1:-1], seq[2:], marker='o') 
    ax.set_xlabel('x')
    ax.set_ylabel('y') 
    ax.set_zlabel('z') 
    ax.view_init(elev=elev, azim=azim) 
    return fig, ax

randu = random_gen(65539, 1, 0, 2**31, 10) 
for i in random_gen(65539, 1, 0, 2**31, 10):
    print(i)

seq = [i for i in random_gen(65539, 1, 0, 2**31, 10000)] 
plot_2d_spectral_test(seq)