matrix = []
result = '';
for i in range(5):
    matrix.append(list(input()))

for i in range(15):
    for j in range(5):
        if len(matrix[j]) > i:
            result += matrix[j][i]
print(result)