package baekjoon.퇴사;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 퇴사 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[]dp = new int[N+1];// n 일차에 마무리 귀찮으니까 [0] 버리고 [1] 부터 기입


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            if(i>1){
                dp[i] = Math.max(dp[i-1], dp[i]); // 전날과 비교해서 더 큰게 오늘부터 우리팀이다
            }
            if(i+t <= N){
                dp[i + t] = Math.max(dp[i + t],dp[i] + p);
            }
        }
        String max = String.valueOf(Arrays.stream(dp).max().getAsInt());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(max));
        bw.newLine();
        bw.flush();


    }
}
