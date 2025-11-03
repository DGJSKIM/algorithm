max_value, max_row, max_col = 0, 0, 0

for i in range(9):
    row = input().split(" ")
    for j in range(9):
        if int(row[j]) > max_value:
            max_value = int(row[j])
            max_row = i
            max_col = j

print(max_value)
print(max_row + 1, max_col + 1)