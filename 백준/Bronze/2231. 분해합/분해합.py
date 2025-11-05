import sys

input = sys.stdin.readline
N = input().strip()

min = max(int(N) - len(N) * 9, 0)
for i in range(min, int(N)):
    sum = int(i)
    for j in range(len(str(i))):
        sum += int(str(i)[j])
    
    if sum == int(N):
        print(i)
        break
else:
    print(0)