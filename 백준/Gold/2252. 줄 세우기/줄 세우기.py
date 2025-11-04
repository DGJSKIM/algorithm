import sys
from collections import deque, defaultdict

input = sys.stdin.readline

N, M = map(int, input().strip().split())

graph = defaultdict(list) 
in_degree = [0] * (N + 1)
result = []
for _ in range(M): 
    A, B = map(int, input().strip().split())
    graph[A].append(B)
    in_degree[B] += 1

q = deque()
for i in range(1, N + 1):
    if in_degree[i] == 0:
        q.append(i)

while q:
    now = q.popleft()
    result.append(now)

    for next in graph[now]:
        in_degree[next] -= 1
        if in_degree[next] == 0:
            q.append(next)

print(*result)