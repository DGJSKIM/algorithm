import sys

input = sys.stdin.readline

N, M = map(int, input().strip().split())
board = [input().strip() for _ in range(N)]

min_count = 64
for i in range(N-7):
    for j in range(M-7):
        count = 0
        base = board[i][j]
        for k in range(8):
            for l in range(8):
                is_even = (k + l) % 2 == 0
                if (is_even and board[i+k][j+l] != base) or (not is_even and board[i+k][j+l] == base):
                    count += 1

        min_count = min(min_count, count, 64 - count)
print(min_count)