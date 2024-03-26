package baekjoon.바닥_장식;

import java.io.*;
import java.util.StringTokenizer;

public class 바닥_장식 {

    public static int [][] board;
    public static int N;
    public static int M;
    public static int count = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N+1][M+1];

        String str;
        for (int i = 0; i < N; i++){
            str = br.readLine();
            for (int j = 0;j < M ; j++){
                switch (String.valueOf(str.charAt(j))){
                    case "-" :
                        board[i][j] = 1;
                        break;
                    case "|" :
                        board[i][j] = 2;
                        break;
                }
            }
        }

        dfs(board[0][0],0,0,0);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(count));
        bw.newLine();
        bw.flush();
    }

    public  static void dfs(int d, int r, int c,int idx){
        // 체크 된 곳은 0 넣고 스킵하는게 나읗듯
        if(board[r][c] == 0 && idx == 0){
            if(c<M-1){
                dfs(board[r][c+1],r,c+1,idx);
            }else if(r<N-1){
                dfs(board[r+1][0],r+1,0,idx);
            }else{
                return;
            }
            return;
        }

        if(d == board[r][c]){
            //방향 같으면
            board[r][c] = 0;
            switch (d){
                case 1:
                        dfs(d, r, c+1, idx+1);
                    break;
                case 2:
                        dfs(d, r+1, c, idx+1);
                    break;
            }
        }else{
            // 방향 다르거나 이미 체크된 애면
            count++;
        }

        if(idx == 0){
            dfs(board[r][c+1],r,c+1,0);
        }





    }
}
