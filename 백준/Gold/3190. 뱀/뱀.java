import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            board[x][y] = 1; // 사과 = 1
        }
        int L = Integer.parseInt(br.readLine());
        Move[] moves = new Move[L];
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            moves[i] = new Move(time, direction);
        }

        // 뱀은 queue로 구현
        Queue<Body> snake = new LinkedList<>();
        snake.add(new Body(0, 0, 1));
        int time = 0;
        int moveIdx = 0;
        int[] dx = {-1, 0, 1, 0}; // 상우하좌
        int[] dy = {0, 1, 0, -1};
        
        while (true) {
            time++;
            
            // 뱀의 머리 위치와 방향
            Body head = ((LinkedList<Body>)snake).getLast();
            int nx = head.x + dx[head.dir];
            int ny = head.y + dy[head.dir];
            // 벽에 부딪히는 경우
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                break;
            }
            
            // 자기 자신과 부딪히는지 확인
            boolean collision = false;
            for (Body body : snake) {
                if (body.x == nx && body.y == ny) {
                    collision = true;
                    break;
                }
            }
            if (collision) break;
            
            // 새로운 위치로 이동
            snake.add(new Body(nx, ny, head.dir));
            
            // 사과가 없다면 꼬리 제거
            if (board[nx][ny] != 1) {
                snake.poll();
            } else {
                board[nx][ny] = 0; // 사과 제거
            }
            
            // 방향 전환 확인
            //  if (moveIdx < L && moves[moveIdx].time == time) {  -> 런타임 에러 (ArrayIndexOutOfBounds)
            if (moveIdx < L && moves[moveIdx].time == time) {
                Body newHead = ((LinkedList<Body>)snake).getLast();
                if (moves[moveIdx].direction.equals("L")) { // 왼쪽 으로
                    newHead.dir = (newHead.dir + 3) % 4;
                } else { // 오른쪽 으로
                    newHead.dir = (newHead.dir + 1) % 4;
                }
                moveIdx++;
            }
        }
        
        System.out.println(time);
    }

    private class Body {
        int x;
        int y;
        int dir; // 0:상, 1:우, 2:하, 3:좌
        public Body(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    private class Move {
        int time;
        String direction;

        public Move(int time, String direction) {
            this.time = time;
            this.direction = direction;
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();   
    }
}
