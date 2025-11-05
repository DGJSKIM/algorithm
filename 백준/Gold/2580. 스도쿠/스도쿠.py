import sys

input = sys.stdin.readline

row_used = [[False] * 10 for _ in range(9)]
col_used = [[False] * 10 for _ in range(9)]
box_used = [[False] * 10 for _ in range(9)]
zero_list = []
board = []

for i in range(9):
    board.append(list(input().strip().split(' ')))

for i in range(9):
    for j in range(9):
        if board[i][j] != '0':
            row_used[i][int(board[i][j])] = True
            col_used[j][int(board[i][j])] = True
            box_used[i // 3 * 3 + j // 3][int(board[i][j])] = True
        else:
            zero_list.append((i, j))

def dfs(d):
    if d == len(zero_list):
        for i in range(9):
            for j in range(9):
                print(board[i][j], end=' ')
            print()
        exit()
    for i in range(1, 10):
        if not row_used[zero_list[d][0]][i] and not col_used[zero_list[d][1]][i] and not box_used[zero_list[d][0] // 3 * 3 + zero_list[d][1] // 3][i]:
            row_used[zero_list[d][0]][i] = True
            col_used[zero_list[d][1]][i] = True
            box_used[zero_list[d][0] // 3 * 3 + zero_list[d][1] // 3][i] = True
            board[zero_list[d][0]][zero_list[d][1]] = str(i)
            dfs(d + 1)
            row_used[zero_list[d][0]][i] = False
            col_used[zero_list[d][1]][i] = False
            box_used[zero_list[d][0] // 3 * 3 + zero_list[d][1] // 3][i] = False
            board[zero_list[d][0]][zero_list[d][1]] = '0'

dfs(0)            