import sys

input = sys.stdin.readline

N = int(input().strip())

min = 5000

a = N // 5

for i in range(a, -1, -1):
    if (N - i * 5) % 3 == 0:
        print(i + (N - i * 5) // 3)
        break
else:
    print(-1)