import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] stepCount = new int[N + 1];
        int[][] dp = new int[N + 1][2]; // [각 층별][전계단밟고 지금도 밟] / [각 층별][전계단안밟 지금밟]
        int answer = 0;
        for (int i = 1; i < N + 1; i++) {
            stepCount[i] = Integer.parseInt(br.readLine());
        }
        if (N == 1){
            bw.write(String.valueOf(stepCount[1]));
            bw.newLine();
            bw.flush();
            return;
        }
        // 첫 계단
        dp[1][1] = stepCount[1];

        // 두번째 계단
        dp[2][0] = stepCount[1] + stepCount[2];
        dp[2][1] = stepCount[2];

        for (int i = 3; i < N + 1; i++) {
            dp[i][0] = dp[i-1][1] + stepCount[i]; // 3연속은 안되니까 무조건 저번에는 dp[i-1][1] 이어야함
            dp[i][1] = Math.max(dp[i-2][0], dp[i-2][1]) + stepCount[i]; // 2계단 전이 밟든 안밟든 신경 안쓰므로 최댓값에서 + 점수
        }
        answer = Math.max(dp[N][0], dp[N][1]);

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
    }

}
