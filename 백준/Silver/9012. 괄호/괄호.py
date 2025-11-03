import sys
n = int(sys.stdin.readline().strip())

for i in range(n):
    stack = []
    a = sys.stdin.readline().strip()
    for j in range(len(a)):
        if a[j] == '(':
            stack.append(a[j])
        elif a[j] == ')':
            if len(stack) == 0:
                print("NO")
                break
            else:
                stack.pop()

    else: 
        if len(stack) == 0:
            print("YES")
        else:
            print("NO")