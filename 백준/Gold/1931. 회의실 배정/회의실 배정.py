from collections import deque
import sys

N = int(sys.stdin.readline().strip())
meetings = []
for i in range(N):
    start, end = map(int, sys.stdin.readline().strip().split(" "))
    meetings.append((start, end))
sorted_meetings = sorted(meetings, key=lambda x: (x[1], x[0]))

current_time = 0
count = 0
for meeting in sorted_meetings:
    if meeting[0] >= current_time:
        current_time = meeting[1]
        count += 1

print(count)