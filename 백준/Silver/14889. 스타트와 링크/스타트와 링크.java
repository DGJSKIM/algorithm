import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int N;

    public static int[][]teamWorks;

    public static boolean[] join;
    public static int minDiff = Integer.MAX_VALUE;
    public static boolean[] start;
    public static boolean[] zelda;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        teamWorks = new int[N][N];
        start = new boolean[N];
        zelda = new boolean[N];
        join = new boolean[N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                teamWorks[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Arrays.fill(join,false);
        join[0]= true;
        dfs(0,1);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(minDiff));
        bw.newLine();
        bw.flush();




    }

    public static void dfs(int s , int n){
        if (n == N/2){
            int startSum = 0;
            int zeldaSum = 0;
            for (int i=0; i<N; i++){
                for (int j=0; j<N; j++){
                    if(i != j){
                        if (join[i] && join[j]){
                            startSum += teamWorks[i][j];
                        }else if(!join[i]&&!join[j]){
                            zeldaSum += teamWorks[i][j];
                        }
                    }

                }
            }
            minDiff = Math.min(minDiff, Math.abs(startSum - zeldaSum));
            return;
        }
        for(int i=s; i<N; i++){
            if(!join[i]){
                join[i] = true;
                dfs(i,n+1);
                join[i] = false;
            }
        }


    }
}
