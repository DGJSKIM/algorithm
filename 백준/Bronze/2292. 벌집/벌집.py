n = int(input())

# 첫번째 외곽 2 ~ 7 -> 6개
# 두번째 외곽 8 ~ 19 -> 12개
# 세번째 외곽 20 ~ 37 -> 18개
if n == 1:
    print(1)
    exit()

n -= 1

for i in range(1, n+1):
    if n - (6 * i) <= 0:
        print(i + 1)
        break
    n -= 6 * i
