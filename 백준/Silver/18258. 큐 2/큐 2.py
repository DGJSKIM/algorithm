from bdb import Breakpoint
from collections import deque
import sys

n = int(sys.stdin.readline().strip())
q = deque([])
for i in range(n):
    line = sys.stdin.readline().strip()
    if line.startswith('push'):
        q.append(line.split()[1])
    elif line.startswith('pop'):
        if q:
            print(q.popleft())
        else:
            print(-1)
    elif line.startswith('size'):
        print(len(q))
    elif line.startswith('empty'):
        if q:
            print(0)
        else:
            print(1)    
    elif line.startswith('front'):
        if q:
            print(q[0])
        else:
            print(-1)
    elif line.startswith('back'):
        if q:
            print(q[-1])
        else:
            print(-1)