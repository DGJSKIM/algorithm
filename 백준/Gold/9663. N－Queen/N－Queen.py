import sys

input = sys.stdin.readline

N = int(input().strip())

count = 0
is_col_used = [False] * N
is_diag_used = [False] * (2 * N - 1)
is_diag2_used = [False] * (2 * N - 1)
def dfs(d):
    if d == N:
        global count
        count += 1
        return

    for i in range(N):
        if not is_col_used[i] and not is_diag_used[d + i] and not is_diag2_used[d - i + N - 1]:
            is_col_used[i] = True
            is_diag_used[d + i] = True
            is_diag2_used[d - i + N - 1] = True
            dfs(d + 1)
            is_col_used[i] = False
            is_diag_used[d + i] = False
            is_diag2_used[d - i + N - 1] = False

dfs(0)
print(count)