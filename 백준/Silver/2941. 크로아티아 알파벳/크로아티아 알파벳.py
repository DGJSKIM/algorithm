text = list(input())

count = 0
i = len(text) - 1

while i >= 0:
    if i >= 1 and text[i] == '-' and (text[i-1] == 'c' or text[i-1] == 'd'):
        count += 1
        i -= 2
        
    elif i >= 1 and text[i] == 'j' and (text[i-1] == 'l' or text[i-1] == 'n'):
        count += 1
        i -= 2
        
    elif i >= 2 and text[i] == '=' and text[i-1] == 'z' and text[i-2] == 'd':
        count += 1
        i -= 3
        
    elif i >= 1 and text[i] == '=' and (text[i-1] == 'z' or text[i-1] == 's' or text[i-1] == 'c'):
        count += 1
        i -= 2
        
    else:
        count += 1
        i -= 1
        
print(count)