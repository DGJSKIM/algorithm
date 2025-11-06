import sys

input = sys.stdin.readline

N = int(input().strip())
A = list(map(int, input().strip().split()))
set_A = set(A)

M = int(input().strip())
list_m = list(map(int, input().strip().split()))

for m in list_m:
    if m in set_A:
        print(1)
    else:
        print(0)