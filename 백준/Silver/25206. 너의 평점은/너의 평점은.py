data = []
grade = {'A+': 4.5, 'A0': 4.0, 'B+': 3.5, 'B0': 3.0, 'C+': 2.5, 'C0': 2.0, 'D+': 1.5, 'D0': 1.0, 'F': 0.0}
for i in range(20):
    i = input().split(" ")
    data.append([float(i[1]), i[2]])
    
p_count = 0
total_score = 0
total_credit = 0
for i in data:
    if i[1] == "P":
        p_count += 1
        continue
    total_score += i[0] * grade[i[1]]
    total_credit += i[0]

print(f"{total_score / total_credit:.6f}")