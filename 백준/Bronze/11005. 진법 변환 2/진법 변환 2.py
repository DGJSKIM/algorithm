a, b = map(int, input().split(" "))
reversed_result = ""
while True:
    if a == 0:
        break
    x = a // b
    y = a % b
    
    if y >= 10:
        y = chr(y + 55)
    reversed_result += str(y)
    a = x

print(reversed_result[::-1])