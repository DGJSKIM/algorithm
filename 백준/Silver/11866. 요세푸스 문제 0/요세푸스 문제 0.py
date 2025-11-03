from collections import deque
import sys

[N, K] = map(int, sys.stdin.readline().strip().split())
dq = deque(list(range(1, N+1)))
result = []

while dq:
    dq.rotate(-K+1)
    result.append(dq.popleft())

print("<", end="")
for i in range(len(result)):
    if i == len(result)-1:
        print(result[i], end="")
    else:
        print(result[i], end=", ")
print(">")