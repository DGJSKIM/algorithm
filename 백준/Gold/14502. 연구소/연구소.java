import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private int n,m;
    private int[][]room;
    private int max= Integer.MIN_VALUE;
    private int[]dr = {-1,0,1,0};
    private int[]dc = {0,1,0,-1};
    private void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        room = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <m;j++){
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0,0);

        System.out.println(max);


    }

    private void dfs(int depth, int r, int c) {
        if(depth == 3){
            bfs();
            return;
        }

        for (int i = r; i < n; i++) {
            int startColumn = 0;
            if(i == r){
                startColumn = c;
            }
            for (int j = startColumn; j <m;j++){
                if(room[i][j] == 0){
                    room[i][j] = 1;
                    dfs(depth+1, i, j);
                    room[i][j] = 0;
                }
            }
        }

    }

    private void bfs() {
        int[][] virusRoom = new int[n][m];
        Queue<Point> q = new LinkedList<>();
        for (int i=0; i<n;i++){
            virusRoom[i] = room[i].clone();
            for (int j=0; j<m;j++) {
                if(virusRoom[i][j] == 2){
                    q.add(new Point(i,j));
                }
            }
        };// 맵복제, 큐 생성

        while (!q.isEmpty()){
            Point p = q.poll();

            for (int k=0; k<4;k++){
                if(p.r+dr[k] < n && p.r+dr[k] >= 0 && p.c+dc[k]<m && p.c+dc[k]>=0&&virusRoom[p.r+dr[k]][p.c+dc[k]] == 0){
                    virusRoom[p.r+dr[k]][p.c+dc[k]] = 2;
                    q.add(new Point(p.r+dr[k],p.c+dc[k]));
                }
            }

        }
        int tempMax = (int) Arrays.stream(virusRoom).flatMapToInt(Arrays::stream).filter(a -> a==0).count();
        max = Math.max(max, tempMax);
    }
    public static class Point {
        int r;
        int c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
