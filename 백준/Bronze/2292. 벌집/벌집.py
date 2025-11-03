n = int(input())

shell_count = 1
max_room_in_previous_shell = 1
increment_size = 6

while n > max_room_in_previous_shell:
    max_room_in_previous_shell += increment_size
    shell_count += 1
    increment_size += 6

print(shell_count)