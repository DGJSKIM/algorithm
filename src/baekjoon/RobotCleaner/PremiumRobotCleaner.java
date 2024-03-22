package baekjoon.RobotCleaner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PremiumRobotCleaner {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1 << 16);

    public static void main(String[] args) throws Exception {
        new PremiumRobotCleaner().solution();
    }

    private static final int[] DR = {-1, 0, 1, 0};
    private static final int[] DC = {0, 1, 0, -1};

    private static final int BLOCKED = -1;
    private static final int BLANK = 0;
    private static final int CLEAN = 1;

    private int answer = 0;
    private int[][] v;

    // 의문의 알고리즘 장인이 푼 해답은 다음과 같다.
    // 개인적으로 가져가고 싶은 부분은
    /*
    예외처리 안하도록 그냥 board 를 상하좌우 1씩더 키워서 하는것


    private static final int[] DR = {-1, 0, 1, 0};
    private static final int[] DC = {0, 1, 0, -1};

    위처럼 지정하여 상태값 하나로(이 문제에서는 d 값이 될 것이다)
    변경할 값을 미리 지정해주는 것 -> 이런 유형의 문제에서는 자주 쓸 듯

    ++
    for (int i = 0; i < 4; i++) {
            d += 3;   //3.1
            d %= 4;
            if (v[cr + DR[d]][cc + DC[d]] == BLANK) {
                simulation(cr + DR[d], cc + DC[d], d);    //3.2, 3.3
                return;
            }
        }

        -1 로 구현해야 하나 싶은 것을 +3 으로 구현한 부분

       그 외에도
        private static final int BLOCKED = -1;
    private static final int BLANK = 0;
    private static final int CLEAN = 1;

    이런 식으로 저장하여 코드 읽기 편하게 한 부분도 나중에 써봐야 겠다.

     */


    public void solution() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        v = new int[r + 2][c + 2];
        for (int[] row : v) Arrays.fill(row, BLOCKED);

        for (int i = 1; i <= r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= c; j++) {
                if (st.nextToken().charAt(0) == '0')
                    v[i][j] = BLANK;
            }
        }

        simulation(sr + 1, sc + 1, d);

        System.out.println(answer);
    }

    private void simulation(int cr, int cc, int d) {
        if (v[cr][cc] == BLOCKED) return;   //2.2

        if (v[cr][cc] == BLANK) {   //1
            answer++;
            v[cr][cc] = CLEAN;
        }

        for (int i = 0; i < 4; i++) {
            d += 3;   //3.1
            d %= 4;
            if (v[cr + DR[d]][cc + DC[d]] == BLANK) {
                simulation(cr + DR[d], cc + DC[d], d);    //3.2, 3.3
                return;
            }
        }

        simulation(cr + DR[(d + 2) % 4], cc + DC[(d + 2) % 4], d);    // 2.1, 2.2
    }

}
