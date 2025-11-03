from bdb import Breakpoint
from collections import deque
import sys

n = int(sys.stdin.readline().strip())
s = []
q = deque(map(int, sys.stdin.readline().strip().split(' ')))

for i in range(1, n+1):
    if q and q[0] == i:
        q.popleft()
        continue
    elif s and s[-1] == i:
        s.pop()
        continue
    elif q and q[0] != i:
        flag = True
        len_q = len(q)
        for j in range(len_q):
            p = q.popleft();
            if p == i :
                break
            elif (s and s[-1] > p ) or (not s):
                s.append(p)
            else:
                flag = False
                break
        else : flag = False

        if flag == False:
            print("Sad")
            break
else : print("Nice")