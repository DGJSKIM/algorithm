import sys
import heapq

input = sys.stdin.readline
pq = []

N = int(input().strip())

for _ in range(N):
    row = map(int, input().strip().split(' '))
    for r in row:
        heapq.heappush(pq, r)
        if len(pq) > N:
            heapq.heappop(pq)

print(heapq.heappop(pq))