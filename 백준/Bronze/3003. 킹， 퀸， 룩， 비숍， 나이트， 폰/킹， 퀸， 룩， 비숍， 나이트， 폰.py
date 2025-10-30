required = [1, 1, 2, 2, 2, 8]
current = input().split(" ")
needed = []
for i in range(len(required)):
    needed.append(required[i] - int(current[i]))

for i in needed:
    print(i, end=" ")