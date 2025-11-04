import heapq
import sys

input = sys.stdin.readline
max_heap = []
min_heap = []

def add_num(num):
    if not max_heap or num <= -max_heap[0]:
        heapq.heappush(max_heap, -num)
    else:
        heapq.heappush(min_heap, num)
        
    if len(max_heap) > len(min_heap) + 1:
        heapq.heappush(min_heap, -heapq.heappop(max_heap))
    elif len(min_heap) > len(max_heap):
        heapq.heappush(max_heap, -heapq.heappop(min_heap))

def find_median():
    return -max_heap[0]


N = int(input().strip())
for _ in range(N):
    n = int(input().strip())
    k = n // 10 + 1
    max_heap = []
    min_heap = []
    result = []
    for i in range(k):
        numbers = list(map(int, input().strip().split(' ')))
        for c in range(len(numbers)):
            add_num(numbers[c])
            if c % 2 == 0:
                result.append(find_median())
    print(len(result))
    for i in range(len(result)):
        if i > 0 and i % 10 == 0:
            print()
        print(result[i], end=' ')
    print()