import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        // n*m 크기
        // 벽 또는 빈칸으로 구성
        // 청소 -> 빈칸있으면 좌회전 전진

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer nm = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(nm.nextToken());
        int M = Integer.parseInt(nm.nextToken());


        StringTokenizer xyd = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(xyd.nextToken());
        int x = Integer.parseInt(xyd.nextToken());
        int d = Integer.parseInt(xyd.nextToken());

        Robot robot = new Robot(x, y, d);
        int[][] roomBoard = new int[N][M];
        for (int n = 0; n < N; n++) {
            StringTokenizer line = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                roomBoard[n][m] = Integer.parseInt(line.nextToken());
            }
        }

        Room room = new Room(roomBoard);

        room.check(robot);


    }

    private static class Robot {
        private int x;
        private int y;
        private int d; // 방향

        public Robot(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getD() {
            return d;
        }

        public void back() {
            switch (this.d) {
                case 0:
                    this.y = this.y + 1;
                    break;

                case 1:
                    this.x = this.x - 1;
                    break;
                case 2:
                    this.y = this.y - 1;
                    break;

                case 3:
                    this.x = this.x + 1;
                    break;


            }
        }

        public void go(int d2) {
            this.d = d2;
            switch (this.d) {
                case 0:
                    this.y = this.y - 1;
                    break;

                case 1:
                    this.x = this.x + 1;
                    break;
                case 2:
                    this.y = this.y + 1;
                    break;

                case 3:
                    this.x = this.x - 1;
                    break;


            }
        }
    }

    private static class Room {


        private int[][] board;

        public Room(int[][] board) {

            this.board = board;
        }


        public void clean(Robot robot) {
            int x = robot.getX();
            int y = robot.getY();
            this.board[y][x] = -1;
        }

        public void check(Robot robot) throws Exception {
            boolean end = false;
            while (!end) {
                int x = robot.getX();
                int y = robot.getY();
                int d = robot.getD();
                clean(robot);
                int t;
                for (t = 1; t < 5; t++) {

                    int x2 = x;
                    int y2 = y;
                    try {
                        int d2 = (d + 3 * t) % 4;
                        switch (d2) {
                            case 0:
                                y2--;
                                break;
                            case 1:
                                x2++;
                                break;
                            case 2:
                                y2++;
                                break;
                            case 3:
                                x2--;
                                break;
                        }
                        if (this.board[y2][x2] == 0) {
                            break;
                        }
                    } catch (Exception e) {

                    }
                }
                if (t != 5) {
                    robot.go((d + 3 * t) % 4);
                } else {
                    robot.back();
                    try {
                        if (this.board[robot.getY()][robot.getX()] == 1) {
                            checkCleanTiles();
                            end = true;
                        }
                    } catch (Exception e) {
                        checkCleanTiles();
                        end = true;
                    }

                }

            }


        }


        private void checkCleanTiles() throws Exception {
            long count = Arrays.stream(board)
                    .flatMapToInt(Arrays::stream)
                    .filter(x -> x == -1)
                    .count();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            bw.write(String.valueOf(count));
            bw.newLine();
            bw.flush();
        }


    }
}
