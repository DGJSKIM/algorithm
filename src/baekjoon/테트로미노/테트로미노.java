package baekjoon.테트로미노;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class 테트로미노 {
    public static int N;
    public static int M;
    public static int[][] Board;
    static Stack<Point> stackCheked = new Stack<Point>();;
    public static boolean[][] CheckBoard;
    public static int MaxValue = Integer.MIN_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Board = new int[N][M];
        CheckBoard = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++){
                Board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j<M;j++){
                Point p = new Point(i,j);
                CheckBoard[i][j] = true;
                stackCheked.add(p);
                dfs(p,1);
                CheckBoard[i][j] = false;
                stackCheked.pop();
            }
        }
        Point p2 = new Point(1,1);


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(MaxValue));
        bw.newLine();
        bw.flush();

    }
    public static void dfs(Point p, int n){
        if(n == 4){
            int total = 0;
            for (int i = 0; i <4 ; i++){
                Point point = stackCheked.get(i);
                total += Board[point.r][point.c];
            }

            MaxValue = Math.max(MaxValue, total);
            return;
        }
        // 기본적으로 오른쪽, 아래로만 이동

        Point point;
        for (int k = 0; k <4;k++){ // 한쪽만
            int r2 = p.r;
            int c2 = p.c;
            switch (k){
                case 0:r2++;break;
                case 1:c2++;break;
                case 2:r2--;break;
                case 3:c2--;break;

            }

            if(r2<N && c2 <M){
                if(k != 3 && r2 >=0 && r2 <N && c2 >=0 && c2 <M && !CheckBoard[r2][c2]){
                    point = new Point(r2,c2);
                    if (n==2) {
                        // 현재기준(2번째 칸 기준) 으로 한번더 탐색
                        stackCheked.add(point);
                        CheckBoard[r2][c2] = true;
                        dfs(p,n+1); // 현재 기준에서 p,3 으로 탐색한번 더해줌
                        CheckBoard[r2][c2] = false;
                        stackCheked.pop();
                    }

                    stackCheked.add(point);
                    CheckBoard[r2][c2] = true;
                    dfs(new Point(r2,c2),n+1);
                    CheckBoard[r2][c2] = false;
                    stackCheked.pop();
                }

            }
        }

    }

    public static class Point{
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
