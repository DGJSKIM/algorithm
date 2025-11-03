import sys

while True:
    stack = []
    a = sys.stdin.readline().rstrip()
    if a == '.':
        break

    for j in range(len(a)):
        if a[j] == '(':
            stack.append(a[j])
        elif a[j] == ')':
            if len(stack) == 0 or stack[-1] != '(':
                print("no")
                break
            else:
                stack.pop()
        if a[j] == '[':
            stack.append(a[j])
        elif a[j] == ']':
            if len(stack) == 0 or stack[-1] != '[':
                print("no")
                break
            else:
                stack.pop()

    else: 
        if len(stack) == 0:
            print("yes")
        else:
            print("no")