n = int(input())
count = 0
for i in range(n):
    text = input()
    set_text = set(text)
    for j in range(len(text)):
        if (j is len(text) -  1 or text[j] != text[j+1]) and text[j] in set_text:
            set_text.remove(text[j])
        elif (j is len(text) - 1 or text[j] != text[j+1])  and text[j] not in set_text:
            break
    else:
        count += 1
print(count)