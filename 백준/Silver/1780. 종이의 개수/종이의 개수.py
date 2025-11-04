import sys

input = sys.stdin.readline
count = [0, 0]

N = int(input().strip())

matrix = []
for _ in range(N):
    matrix.append(list(map(int, input().strip().split(' '))))

counts = [0, 0, 0]

def check(r, c, n):
    # 현재 부분 배열의 크기
    is_uniform = True
    base = matrix[r][c]
    for i in range(r, r + n):
        for j in range(c, c + n):
                if matrix[i][j] != base:
                    is_uniform = False
                    break
    if is_uniform and base == 0:
        counts[1] += 1
        return
    elif is_uniform and base == 1:
        counts[2] += 1
        return
    elif is_uniform and base == -1:
        counts[0] += 1
        return

    x = n // 3
    check(r, c, x)
    check(r, c + 2 * x, x)
    check(r, c + x, x)
    check(r + x, c, x)
    check(r + x, c + x, x)
    check(r + x, c + 2 * x, x)
    check(r + 2 * x, c, x)
    check(r + 2 * x, c + x, x)
    check(r + 2 * x, c + 2 * x, x)

check(0,0,N)
print(counts[0])
print(counts[1])
print(counts[2])
