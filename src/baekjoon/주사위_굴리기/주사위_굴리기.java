package baekjoon.주사위_굴리기;

import java.io.*;
import java.util.StringTokenizer;

public class 주사위_굴리기 {

    public static int[] dice;
    public static int[][] board;
    public static int[] dr = new int[]{0,0,-1,1};
    public static int[] dc = new int[]{1,-1,0,0};

    public static void main(String[] args) throws IOException {

        dice = new int[6];


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i <N;i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <M;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        st = new StringTokenizer(br.readLine());
        int d;
        int[] nextDice = new int[6];
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int k=0; k<K;k++){

            d = Integer.parseInt(st.nextToken());
            if(R+dr[d-1] >=0 && R+dr[d-1] <N && C+dc[d-1] >=0 && C+dc[d-1]<M){ // 밖으로 이동 X
                R = R+dr[d-1];
                C = C+dc[d-1];
                switch (d){
                    case 1:
                        nextDice = new int[]{dice[3], dice[1], dice[0], dice[5], dice[4], dice[2]};
                        break;
                    case 2:
                        nextDice = new int[]{dice[2], dice[1], dice[5], dice[0], dice[4], dice[3]};
                        break;
                    case 3:
                        nextDice = new int[]{dice[4], dice[0], dice[2], dice[3], dice[5], dice[1]};
                        break;
                    case 4:
                        nextDice = new int[]{dice[1], dice[5], dice[2], dice[3], dice[0], dice[4]};
                        break;
                }
                dice = nextDice.clone();
                if(board[R][C] == 0){
                    board[R][C] = dice[5];
                }else{
                    dice[5] = board[R][C];
                    board[R][C] = 0;
                }
                bw.write(String.valueOf(dice[0]));
                bw.newLine();
            }
        }
        bw.flush();
    }
}
