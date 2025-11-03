from collections import deque
import sys

n = int(sys.stdin.readline().strip())
q = deque(list(range(1, n+1)))

discard = True
while len(q) != 1:
    if discard:
        q.popleft()
        discard = False
    else:
        q.append(q.popleft())
        discard = True
print(q[0])
