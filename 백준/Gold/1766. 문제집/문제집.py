import heapq
import sys
from collections import defaultdict

input = sys.stdin.readline
N, M = map(int, input().strip().split())

graph = defaultdict(list)
q = []
in_degree = [0] * (N + 1)

result = []
for i in range(M):
    A, B = map(int, input().strip().split())
    graph[A].append(B)
    in_degree[B] += 1

for i in range(1, N + 1):
    if in_degree[i] == 0:
        heapq.heappush(q, i)

while q:
    now = heapq.heappop(q)
    result.append(now)
    for next in graph[now]:
        in_degree[next] -= 1
        if in_degree[next] == 0:
            heapq.heappush(q, next)

print(*result)