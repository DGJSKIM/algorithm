import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N;
    int[][] map;
    private static final int[] DR = {-1, 0, 0, 1};
    private static final int[] DC = {0, -1, 1, 0};

    Shark shark;
    List<Fish> fishes = new LinkedList<>();

    private void solution() throws Exception {

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    shark = new Shark(i,j);
                }else if(map[i][j]!=0){
                    fishes.add(new Fish(i,j,map[i][j]));
                }
            }
        }

        int time = 0;
        while(true){
            boolean isEnd = true;
            int R = shark.r;
            int C = shark.c;
            Queue<Fish> targets = new LinkedList<>();
            for (int i = 0; i < fishes.size(); i++) {
                Fish f =fishes.get(i);
                if (fishes.get(i).level<shark.level){
                    targets.add(f);
                    isEnd = false;
                }
            }
            if (isEnd){
                break;
            }
            int addTime = Integer.MAX_VALUE;
            int idx = 0;
            while (!targets.isEmpty()){
                Fish target= targets.poll();
                int newAddTime = bfs(new Point(target.r, target.c));
                if(newAddTime > 0 && newAddTime < addTime){
                    addTime = newAddTime;

                    idx = fishes.indexOf(target);
                }
            }
            if(targets.size() == 0 && addTime ==  Integer.MAX_VALUE){
                break;
            }
            time += addTime;

            Fish food = fishes.get(idx);
            shark.r = food.r;
            shark.c = food.c;
            shark.eat();
            fishes.remove(idx);
            map[shark.r][shark.c] = 0;
        }
        bw.write(String.valueOf(time));
        bw.flush();

    }

    private int bfs(Point point) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int[][] distance = new int[N][N];
        q.add(new Point(shark.r,shark.c));
        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i=0 ; i<4; i++){
                int r = p.r + DR[i];
                int c = p.c + DC[i];
                if(r>=0 && r< N && c >=0 && c <N && !visited[r][c] && map[r][c] <= shark.level){
                    q.add(new Point(r,c));
                    visited[r][c] = true;
                    distance[r][c] = distance[p.r][p.c]+1;
                }
            }
        }

        return distance[point.r][point.c];

    }

    private class Shark{
        int r,c;
        int level =2;
        int exp=0;

        public Shark(int r, int c) {
            this.r = r;
            this.c = c;
            map[r][c] = 0;
        }

        private void eat(){
            exp++;
            if(exp == level){
                level++;
                exp=0;
            }
        }
    }

    private class Fish{
        int r,c,level;

        public Fish(int r, int c, int level) {
            this.r = r;
            this.c = c;
            this.level = level;
        }
    }
    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    private class Point{
        int r,c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
