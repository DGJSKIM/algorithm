n = int(input())

#  1, 2, 3 ,4 ,5 
next_count = 1

for i in range(1, n+1):
    if n <= next_count:
        if i % 2 == 1:
            print(f'{next_count - n + 1}/{n}')
        else:
            print(f'{n}/{next_count - n + 1}')
        break
    n -= next_count
    next_count = next_count + 1
