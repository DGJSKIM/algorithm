import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int T;
    public static int N;
    public static Point[][] Stores; //[i][j] i 번째 테스트 j번째 편의점
    public static boolean[] Visited; //편의점 방문한 곳 체크
    public static int[] StoreCounts; //i 번째 테스트 편의점 수

    public static Point[] Home;
    public static Point[] End;
    public static int TestIndex;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StoreCounts = new int[T];
        Stores = new Point[T][100];
        Home = new Point[T];
        End = new Point[T];
        for (int i = 0; i < T; i++) { // 데이터 정리
            N = Integer.parseInt(br.readLine());
            StoreCounts[i] = N;
            StringTokenizer st = new StringTokenizer(br.readLine());
            Home[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            for (int j = 0; j <N;j++) {
                st = new StringTokenizer(br.readLine());
                Stores[i][j] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            st = new StringTokenizer(br.readLine());
            End[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        br.close();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i=0; i<T; i++){
            TestIndex = i;

            Visited = new boolean[StoreCounts[i]];

            if(dfs(Home[i])){
                bw.write("happy");
            }else{
                bw.write("sad");
            }
            bw.newLine();
        }
        bw.flush();


    }

    private static boolean dfs( Point a) { // i 번째 테스트
        if(canGoEnd(a)){
            return true;
        }
        for (int i=0; i<StoreCounts[TestIndex]; i++){
            if(!Visited[i] && canGo(a, Stores[TestIndex][i])){ // 방문한 적 없고 갈 수 있다면
                Visited[i] = true;
                if (dfs(Stores[TestIndex][i])){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean canGo(Point a, Point b) {
        return Math.abs(a.r-b.r)+Math.abs(a.c-b.c) <= 1000;
    }

    public static boolean canGoEnd(Point a){
        return Math.abs(a.r-End[TestIndex].r)+Math.abs(a.c-End[TestIndex].c) <= 1000;
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
