package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj4485_녹색_옷_입은_애가_젤다지 {
    private int N;
    private int[][] cave;
    private int[][] min;
    private boolean[][] visited;
    private int[] dr = {0,1,0,-1};
    private int[] dc = {1,0,-1,0};
    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int count =0;
        while(true) {
            N = Integer.parseInt(br.readLine());
            if (N==0){
                br.close();
                bw.flush();
                return;
            }
            StringTokenizer st;
            cave = new int[N][N];
            min = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(min[i], Integer.MAX_VALUE);
                Arrays.fill(visited[i], false);

            }
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.min, o2.min));
            pq.add(new Point(0, 0, 0));
            min[0][0] = cave[0][0];

            while (!pq.isEmpty()) {
                Point p = pq.poll();
                if (visited[p.r][p.c]) {
                    continue;
                }
                visited[p.r][p.c] = true;
                for (int i = 0; i < 4; i++) {
                    int r2 = p.r + dr[i];
                    int c2 = p.c + dc[i];
                    if (r2 >= 0 && r2 < N && c2 >= 0 && c2 < N && !visited[r2][c2]) {
                        min[r2][c2] = Math.min(min[r2][c2], min[p.r][p.c] + cave[r2][c2]);
                        pq.add(new Point(r2, c2, min[r2][c2]));
                    }
                }
            }
            count++;
            bw.write("Problem "+ String.valueOf(count)+ ": " + String.valueOf(min[N - 1][N - 1]));
            bw.newLine();
        }


    }

    private class Point{
        int r;
        int c;
        int min;

        public Point(int r, int c, int min) {
            this.r = r;
            this.c = c;
            this.min = min;
        }

    }
    public static void main(String[] args) throws Exception {
        new boj4485_녹색_옷_입은_애가_젤다지().solution();
    }
}
