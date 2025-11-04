from collections import deque
import sys

[N, K] = map(int, sys.stdin.readline().strip().split())
dq = deque(list(range(1, N+1)))

for i in range(N):
    dq.append(int(sys.stdin.readline().strip()))

needed = K
count = 0    
while needed != 0:
    p = dq.pop()
    if(p > needed):
        continue
    else:
        c = needed // p
        count += c
        needed -= p * c

print(count)