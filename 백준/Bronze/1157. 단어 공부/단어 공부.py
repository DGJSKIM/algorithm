text = input().upper()

set = set(text)
set_count = {}
for i in set:
    set_count[i] = text.count(i)

max_count = max(set_count.values())
max_count_char = [k for k, v in set_count.items() if v == max_count]

if len(max_count_char) > 1:
    print("?")
else:
    print(max_count_char[0])