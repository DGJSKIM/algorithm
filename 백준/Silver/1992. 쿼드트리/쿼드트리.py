import sys

input = sys.stdin.readline
count = [0, 0]

N = int(input().strip())

matrix = []
for _ in range(N):
    matrix.append(list(map(int, input().strip())))

def check(r, c, n):
    # 현재 부분 배열의 크기
    count = 0
    for i in range(r, r + n):
        for j in range(c, c + n):
            try:
                count += matrix[i][j]
            except:
                print(f"Error: i: {i}, j: {j}, c: {c}, r: {r}, n: {n} ")
    if count == 0:
        return str(0)
    elif count == n * n:
        return str(1)
    
    half = n // 2

    result = '('
    result += check(r, c, half)
    result += check(r, c + half, half)
    result += check(r + half, c, half)
    result += check(r + half, c + half, half)
    result += ')'

    return result

print(check(0,0,N))

