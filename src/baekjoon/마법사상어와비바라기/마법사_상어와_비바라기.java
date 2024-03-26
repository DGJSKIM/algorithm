package baekjoon.마법사상어와비바라기;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 마법사_상어와_비바라기 {

    public static int N;
    public static int M;
    public static int[][] Board;

    public static int[] DR = {0,-1,-1,-1,0,1,1,1};
    public static int[] DC = {-1,-1,0,1,1,1,0,-1};
    public static int[] D;
    public static int[] S;
    public static int[][] Cloud; // 이동할 구름 위치
    public static int[][] Cloud2;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Board = new int[N][N];
        Cloud = new int[N][N];
        Cloud2 = new int[N][N];



        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j<N;j++){
                Board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        D = new int[M];
        S = new int[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            D[i] = Integer.parseInt(st.nextToken());
            S[i] = Integer.parseInt(st.nextToken())%N;
        }
        Cloud[N-1][0] = 1;
        Cloud[N-1][1] = 1;
        Cloud[N-2][0] = 1;
        Cloud[N-2][1] = 1;
                          for (int m=0;m<M;m++){

            for (int i = 0; i < N; i++) { // 구름 이동
                for (int j = 0;j<N;j++){
                    if(Cloud[i][j] == 1){
                        int i2 = ((i+(DR[D[m]-1]*S[m]))<0 )?(i+(DR[D[m]-1]*S[m])+N)%N  :(i+(DR[D[m]-1]*S[m]))%N;

                        int j2 = ((j+(DC[D[m]-1]*S[m]))<0 )?(j+(DC[D[m]-1]*S[m])+N)%N :(j+(DC[D[m]-1]*S[m]))%N;

                        Cloud2[i2][j2] = 2;
                    }
                }
            }
            for (int i = 0; i < N; i++) { // 비 뿌리기
                for (int j = 0; j < N; j++) {
                    if(Cloud2[i][j] == 2){
                        Board[i][j]++;
                        Cloud2[i][j] = -1; // 사라진 구름 표기
                    }
                }
            }
            int count;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(Cloud2[i][j] == -1){ // 비내린 칸에 물복사


                            count = 0;
                            for (int k=0;k<4;k++){
                                int i2 = i+DR[2*k+1];
                                int j2 = j+DC[2*k+1];

                                if(i2>=0 && i2 < N && j2>=0 && j2 <N && Board[i+DR[2*k+1]][j+DC[2*k+1]]>0 ){
                                    count ++;
                                }
                            }

                            Board[i][j] = Board[i][j] + count;


                    }


                }
            }
            Cloud = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 구름 생성
                    if (Board[i][j] >= 2 && Cloud2[i][j] != -1) {
                        Board[i][j] = Board[i][j] - 2;
                        Cloud[i][j] = 1;

                    } else {
                        Cloud[i][j] = 0;
                    }
                }
            }
            Cloud2 = new int[N][N];
        }


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int sum = Arrays.stream(Board).flatMapToInt(Arrays::stream).sum();
        bw.write(String.valueOf(sum));
        bw.newLine();
        bw.flush();




    }


}
