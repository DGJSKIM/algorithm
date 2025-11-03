import math

[A, B, V] = list(map(int, input().split()))

if V - A <= 0:
    print(1)
else:
    print(math.ceil((V - A) / (A - B)) + 1)