import sys
n = int(sys.stdin.readline().strip())
result = []

for i in range(n):
    a = int(sys.stdin.readline().strip())
    if a != 0:
        result.append(a)
    else:
        result.pop()

print(sum(result))