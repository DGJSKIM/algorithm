import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n][3];
        final int R = 0;
        final int G = 1;
        final int B = 2;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(i == 0){
                dp[i][R] = r;
                dp[i][G] = g;
                dp[i][B] = b;
            }else{
                dp[i][R] = Math.min(dp[i-1][G],dp[i-1][B]) + r;
                dp[i][G] = Math.min(dp[i-1][R],dp[i-1][B]) + g;
                dp[i][B] = Math.min(dp[i-1][R],dp[i-1][G]) + b;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(Math.min(dp[n-1][R],Math.min(dp[n-1][G],dp[n-1][B]))));
        bw.newLine();
        bw.flush();
    }


}
