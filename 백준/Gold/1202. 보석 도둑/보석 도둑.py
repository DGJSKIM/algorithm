import heapq
import sys

input = sys.stdin.readline

N, K = map(int, input().strip().split(' '))
jewels = []
bags = []
pq = [] 
for _ in range(N):
    M, V = map(int, input().strip().split(' '))
    heapq.heappush(jewels, (M, V))
for _ in range(K):
    C = int(input().strip())
    heapq.heappush(bags, C)

result = 0
for _ in range(K):
    C = heapq.heappop(bags)
    
    while jewels and jewels[0][0] <= C: # 무게가 가방 크기보다 큰 보석은 무시
        M, V = heapq.heappop(jewels)
        heapq.heappush(pq, (-V, M))
    if pq:
        result += -(heapq.heappop(pq)[0]) # 금액만 더함

print(result)