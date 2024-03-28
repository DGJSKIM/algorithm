package baekjoon.맥주_마시면서_걸어가기;

import java.io.*;
import java.util.StringTokenizer;

public class 맥주_마시면서_걸어가기 {

    public static int T;
    public static int N;
    public static Point[][] stores; //[i][j] i 번째 테스트 j번째 편의점
    public static boolean[] visited; //편의점 방문한 곳 체크
    public static int[] storeCounts; //i 번째 테스트 편의점 수

    public static Point[] home;
    public static Point[] end;
    public static int testIndex;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        storeCounts = new int[T];
        stores = new Point[T][100];
        home = new Point[T];
        end = new Point[T];


        for (int i = 0; i < T; i++) { // 데이터 정리
            N = Integer.parseInt(br.readLine());
            storeCounts[i] = N;
            StringTokenizer st = new StringTokenizer(br.readLine());
            home[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            for (int j = 0; j <N;j++) {
                st = new StringTokenizer(br.readLine());
                stores[i][j] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            st = new StringTokenizer(br.readLine());
            end[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        br.close();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i=0; i<T; i++){
            testIndex = i;
            visited = new boolean[storeCounts[i]];
            if(dfs(home[i])){
                bw.write("happy");
            }else{
                bw.write("sad");
            }
            bw.newLine();
        }
        bw.flush();
    }
    public static void main(String[] args) throws Exception {
        new 맥주_마시면서_걸어가기().solution();
    }

    private boolean dfs(Point a) { // i 번째 테스트
        if(canGoEnd(a)){
            return true;
        }
        for (int i=0; i<storeCounts[testIndex]; i++){
            if(!visited[i] && canGo(a, stores[testIndex][i])){ // 방문한 적 없고 갈 수 있다면
                visited[i] = true;
                if (dfs(stores[testIndex][i])){
                    return true;
                }
                // Visited[i] = flase; 다른 루트로 i번째 가는 경우를 따로 체크할 때만 해제해주면 된다
                // 습관성으로 방문기록 삭제할 필요 X
                // 이번 문제에서는 방문기록을 지워서 지속적으로 i 를 다른 루트에서도
            }
        }
        return false;
    }

    private boolean canGo(Point a, Point b) {
        return Math.abs(a.r-b.r)+Math.abs(a.c-b.c) <= 1000;
    }

    public boolean canGoEnd(Point a){
        return Math.abs(a.r-end[testIndex].r)+Math.abs(a.c-end[testIndex].c) <= 1000;
    }

    public class Point{
        int r;
        int c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
