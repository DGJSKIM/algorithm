package baekjoon.Gold;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj15683_감시 {
    private int N, M;
    private int min = Integer.MAX_VALUE;
    private int[][] room;
    private int[][] copyRoom;
    private CCTV[] cctvs = new CCTV[8];
    private int K =0;
    private int[] dc= {1,0,-1,0};
    private int[] dr= {0,1,0,-1};


    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        room = new int[N][M];
        copyRoom = new int[N][M];

        K = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int type = Integer.parseInt(st.nextToken());
                room[i][j] = type;
                if(type >=1 && type <6){
                    cctvs[K++] = new CCTV(i,j,type);
                }
            }
        }

        dfs(0);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(min));
        bw.newLine();
        bw.flush();
    }

    private void dfs(int depth) {
        if (depth == K){
            for (int i=0;i<N;i++){
                copyRoom[i] = room[i].clone();
            }
            for (int i = 0; i <K ; i++){
                turnOn(cctvs[i]);
            }
            int tempZero = (int) Arrays.stream(copyRoom).flatMapToInt(Arrays::stream).filter(a -> a==0).count();
            min = Math.min(min, tempZero);

            return;
        }
        int c =0;
        switch (cctvs[depth].type){
            case 1,3,4 :c=4; break;
            case 2: c=2; break;
            case 5: c=1; break;
        }

        for (int i=0;i<c;i++){
            cctvs[depth].d = i;
            dfs(depth+1);
        }
    }

    private void turnOn(CCTV cctv){

        int r= cctv.r;
        int c= cctv.c;
        int d= cctv.d;


        switch (cctv.type){

            case 1:// 한방향
                see(r,c,d);
                break;
            case 2: // 양방향
                see(r,c,d);
                see(r,c,d+2%4);
                break;
            case 3: // 방향 +1 방향
                see(r,c,d);
                see(r,c,d+1%4);
                break;
            case 4: // 방향 +1, 방향+2 방향
                see(r,c,d);
                see(r,c,d+1%4);
                see(r,c,d+2%4);
                break;
            case 5:
                see(r,c,d);
                see(r,c,d+1%4);
                see(r,c,d+2%4);
                see(r,c,d+3%4);
                break;
        }
    }

    private void see(int r, int c, int d) {
        int r2 = r+dr[d];
        int c2 = c+dc[d];
        if(r2>=0 && r2 <N && c2>=0 && c2 <M &&copyRoom[r2][c2] != 6 ){
            if(copyRoom[r2][c2] == 0){
                copyRoom[r2][c2] = -1;
            }
            see(r2,c2,d);
        }
    }

    private class CCTV{
        int r,c,type,d;

        public CCTV(int r, int c, int type) {
            this.r = r;
            this.c = c;
            this.type = type;
        }

    }
    public static void main(String[] args) throws IOException {
        new boj15683_감시().solution();
    }


}
