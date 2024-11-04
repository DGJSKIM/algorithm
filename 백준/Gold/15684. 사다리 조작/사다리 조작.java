import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private int N, H;

    private class route {
        int a;
        int b;
        public route(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        route[] defaultRoutes = new route[M];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            defaultRoutes[i] = new route(a,b);
        }

        boolean[][] used = new boolean[H+1][N];
        for(route r : defaultRoutes) {
            used[r.a][r.b] = true;
        }

        int possibleCount = 0;
        route[] possibleRoutes = new route[H * N];
        for(int i = 1; i <= H; i++) {
            for(int j = 1; j < N; j++) {
                if(!used[i][j] && (j == 1 || !used[i][j-1]) && (j == N-1 || !used[i][j+1])) {
                    possibleRoutes[possibleCount++] = new route(i, j);
                }
            }
        }

        for(int i = 0; i <= 3; i++) {
            if(combination(0, 0, i, used, possibleRoutes, possibleCount)) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }

    private boolean combination(int start, int count, int target, boolean[][] used, route[] possibleRoutes, int possibleCount) {
        if(count == target) {
            return check(used);
        }
        
        for(int i = start; i < possibleCount; i++) {
            route r = possibleRoutes[i];
            if(!used[r.a][r.b] && (r.b == 1 || !used[r.a][r.b-1]) && (r.b == N-1 || !used[r.a][r.b+1])) {
                used[r.a][r.b] = true;
                if(combination(i+1, count+1, target, used, possibleRoutes, possibleCount)) {
                    return true;
                }
                used[r.a][r.b] = false;
            }
        }
        return false;
    }

    private boolean check(boolean[][] used) {
        for(int start = 1; start < N; start++) {
            int current = start;
            for(int i = 1; i <= H; i++) {
                if(current > 1 && used[i][current-1]) {
                    current--;
                } else if(current < N && used[i][current]) {
                    current++;
                }
            }
            if(current != start) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
