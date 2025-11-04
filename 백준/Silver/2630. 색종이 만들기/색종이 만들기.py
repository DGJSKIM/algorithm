import sys

input = sys.stdin.readline
count = [0, 0]

def solve():
    global count
    try:
        N = int(input().strip())
    except ValueError:
        return

    matrix = []
    for _ in range(N):
        try:
            matrix.append(list(map(int, input().split())))
        except:
            continue
    
    # -----------------------------------------------
    
    def check(m):
        # 현재 부분 배열의 크기
        N_current = len(m)
        
        standard_val = m[0][0]
        is_uniform = True
        
        for i in range(N_current):
            for j in range(N_current):
                if m[i][j] != standard_val:
                    is_uniform = False
                    break
            if not is_uniform:
                break
        
        if is_uniform:
            count[standard_val] += 1
            return
        
        # -----------------------------------------------
        half = N_current // 2

        sub_m1 = [row[:half] for row in m[:half]]
        check(sub_m1)
        sub_m2 = [row[half:] for row in m[:half]]
        check(sub_m2)
        sub_m3 = [row[:half] for row in m[half:]]
        check(sub_m3)
        sub_m4 = [row[half:] for row in m[half:]]
        check(sub_m4)

    check(matrix)
    print(count[0])
    print(count[1])

if __name__ == "__main__":
    solve()