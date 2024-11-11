import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private int[][] map;
    // 상 우 하 좌
    private int[] dr = {-1, 0, 1, 0};
    private int[] dc = {0, 1, 0, -1};
    private Taxi taxi;
    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int taxiRow = Integer.parseInt(st.nextToken()) - 1;
        int taxiCol = Integer.parseInt(st.nextToken()) - 1;

        taxi = new Taxi(taxiRow, taxiCol, fuel, 0);

        ArrayList<Customer> customers = new ArrayList<>();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int startR = Integer.parseInt(st.nextToken()) - 1;
            int startC = Integer.parseInt(st.nextToken()) - 1;
            int endR = Integer.parseInt(st.nextToken()) - 1;
            int endC = Integer.parseInt(st.nextToken()) - 1;
            Point start = new Point(startR, startC);
            Point end = new Point(endR, endC);
            int distance = calculateDistance(start, end);
            if(distance == -1){
                System.out.println(-1);
                return;
            }
            customers.add(new Customer(start, end, distance));
        }
        
        while(taxi.count < M){
            int minIndex = goToNearestCustomer(customers);
           
            if(minIndex == -1 || taxi.fuel < customers.get(minIndex).distance){ // 손님까지 못가거나 연료가 손님의 distance보다 작을경우
                System.out.println(-1);
                return;
            }
            
            Customer selected = customers.get(minIndex);
            taxi.fuel -= selected.distance;
            taxi.fuel += selected.distance * 2;
            taxi.point = selected.end;
            taxi.count++;
            customers.remove(minIndex);
        }
        System.out.println(taxi.fuel);
    }

    private int goToNearestCustomer(ArrayList<Customer> customers) {
        if(customers.isEmpty()) return -1;
        
        int N = map.length;
        boolean[][] visited = new boolean[N][N];
        Queue<Point> q = new LinkedList<>();
        q.add(taxi.point);
        visited[taxi.point.r][taxi.point.c] = true;
        
        int distance = 0;
        ArrayList<Integer> candidates = new ArrayList<>();
        
        while(!q.isEmpty() && candidates.isEmpty()) {
            int size = q.size();
            
            for(int i = 0; i < size; i++) {
                Point now = q.poll();
                
                for(int j = 0; j < customers.size(); j++) {
                    if(customers.get(j).start.r == now.r && customers.get(j).start.c == now.c) {
                        candidates.add(j);
                    }
                }
                
                for(int d = 0; d < 4; d++) {
                    int nr = now.r + dr[d];
                    int nc = now.c + dc[d];
                    
                    if(nr < 0 || nr >= N || nc < 0 || nc >= N || 
                       visited[nr][nc] || map[nr][nc] == 1) continue;
                    
                    visited[nr][nc] = true;
                    q.add(new Point(nr, nc));
                }
            }
            distance++;
        }

        if(candidates.isEmpty()) return -1;
        
        int result = candidates.get(0);
        Point start = customers.get(result).start;
        
        for(int i = 1; i < candidates.size(); i++) {
            Point current = customers.get(candidates.get(i)).start;
            if(current.r < start.r || 
               (current.r == start.r && current.c < start.c)) {
                result = candidates.get(i);
                start = current;
            }
        }
        
        taxi.fuel -= (distance - 1);
        if(taxi.fuel < 0) return -1;
        
        taxi.point = customers.get(result).start;
        
        return result;
    }

    private class Taxi {
        Point point;
        int fuel;
        int count;

        public Taxi(int r, int c, int fuel, int count) {
            this.point = new Point(r, c);
            this.fuel = fuel;
            this.count = count;
        }
    }

    private class Customer {
        Point start;
        Point end;
        int distance;

        public Customer(Point start, Point end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }

    private class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private int calculateDistance(Point start, Point end) { // 각 승객들의 거리 계산
        boolean[][] visited = new boolean[map.length][map[0].length];
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        visited[start.r][start.c] = true;
        int distance = 0;
        if (start.r == end.r && start.c == end.c) {
            return 0;
        }
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                Point now = q.poll();
                
                for (int j = 0; j < 4; j++) {
                    int nr = now.r + dr[j];
                    int nc = now.c + dc[j];
                    if (nr < 0 || nr >= map.length || nc < 0 || nc >= map[0].length 
                        || visited[nr][nc] || map[nr][nc] == 1) {
                        continue;
                    }
                    if (nr == end.r && nc == end.c) {
                        return distance + 1;
                    }
                    visited[nr][nc] = true;
                    q.add(new Point(nr, nc));
                }
            }
            distance++;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}