import sys

input = sys.stdin.readline
a, b, c, d, e, f = map(int, input().strip().split())

for x in range(-999, 1000): # x는 -999부터 999까지
    for y in range(-999, 1000): # y는 -999부터 999까지
        if a * x + b * y == c and d * x + e * y == f:
            print(x, y)
            break