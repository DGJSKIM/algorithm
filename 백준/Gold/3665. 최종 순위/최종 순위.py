import sys
from collections import deque, defaultdict

input = sys.stdin.readline
C = int(input().strip())
for i in range(C):
    N = int(input().strip())
    graph = defaultdict(list)
    in_degree = [0] * (N + 1)

    last_year_rank = list(map(int, input().strip().split()))

    for i in range(N):
        for j in range(i + 1, N):
            graph[last_year_rank[i]].append(last_year_rank[j])
            in_degree[last_year_rank[j]] += 1

    M = int(input().strip())
    for _ in range(M):
        A, B = map(int, input().strip().split())
        if B in graph[A]:
            graph[A].remove(B)
            graph[B].append(A)
            in_degree[A] += 1
            in_degree[B] -= 1
        else:
            graph[B].remove(A)
            graph[A].append(B)
            in_degree[A] -= 1
            in_degree[B] += 1

    q = deque()
    for i in range(1, N + 1):
        if in_degree[i] == 0:
            q.append(i)

    result = []
    is_good = True
    while q:
        if len(q) > 1:
            is_good = False

        now = q.popleft()
        result.append(now)

        for next in graph[now]:
            in_degree[next] -= 1
            if in_degree[next] == 0:
                q.append(next)

    if not is_good:
        print("?")
    elif len(result) != N:
        print("IMPOSSIBLE")
    else:
        print(*result)
  
