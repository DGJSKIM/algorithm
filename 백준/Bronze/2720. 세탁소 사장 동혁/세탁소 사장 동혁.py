n = int(input())

change_list = []
for i in range(n):
    change_list.append(int(input()))

for change in change_list:
    coins = [25, 10, 5, 1]
    result = '';
    for coin in coins:
        count = change // coin
        change %= coin
        result += str(int(count)) + " "
    print(result)