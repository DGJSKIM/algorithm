import sys
n = int(sys.stdin.readline().strip())

stack = []
for i in range(n):
    a = sys.stdin.readline().strip().split(' ')
    if a[0] == '1':
        stack.append(a[1])
    elif a[0] == '2':
        print(stack.pop() if len(stack) > 0 else -1)
    elif a[0] == '3':
        print(len(stack))
    elif a[0] == '4':
        print(1 if len(stack) == 0 else 0)
    elif a[0] == '5':
        print(stack[-1] if len(stack) > 0 else -1)