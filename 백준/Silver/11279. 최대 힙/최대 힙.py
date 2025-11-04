import sys
import heapq

input = sys.stdin.readline
pq = []

N = int(input().strip())

for _ in range(N):
    c = input().strip()
    if c != '0':
        heapq.heappush(pq, - int(c))
    elif c == '0':
        if pq:
            print(-heapq.heappop(pq))
        else:
            print(0)
