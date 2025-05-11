import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 그냥 홀수 갯수 세면 끝아닌가 갯수 세고 1개 빼면 끝아인교 이미 1개면 바로 끝이고
        // 아 기존 수열 위치 못바꾸네
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        int[][]dp = new int[N][N];
        for (int length = 2; length <= N; length++) {
            // 최소 길이 2인 부분 수열부터 시작
            for (int i = 0; i <= N - length; i++) {
                int j = i + length - 1;
                if (numbers[i] == numbers[j]) {
                    dp[i][j] = (length == 2) ? 0 : dp[i + 1][j - 1];
                }
                else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        System.out.println(dp[0][N - 1]);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
