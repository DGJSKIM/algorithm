import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static int MAX = Integer.MIN_VALUE;	// 최댓값
    public static int MIN = Integer.MAX_VALUE;	// 최솟값
    public static int N;
    public static int[] operator = new int[4];
    static int[] numArr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        numArr = new int[N];
        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i <4; i++){
            operator[i] = Integer.parseInt(st.nextToken());
        }

        dfs(numArr[0],1);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(MAX));
        bw.newLine();
        bw.write(String.valueOf(MIN));
        bw.newLine();
        bw.flush();



    }

    public static void dfs(int num, int idx){
        if(idx == N){ // 마지막 깊이까지 오면 지금까지 저장된 MAX 와 MIN 과 비교하여 저장
            MAX = Math.max(MAX,num);
            MIN = Math.min(MIN,num);
            return;
        }
        for (int i=0; i<4;i++){
            if (operator[i]>0){
                operator[i]--;

                switch (i){
                    case 0: dfs(num + numArr[idx],idx+1); break;
                    case 1: dfs(num - numArr[idx],idx+1); break;
                    case 2: dfs(num * numArr[idx],idx+1); break;
                    case 3: dfs(num / numArr[idx],idx+1); break;
                }
                operator[i]++;

            }
        }

    }
}
