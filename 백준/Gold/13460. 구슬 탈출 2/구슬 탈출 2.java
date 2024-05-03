import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    int N,M;
    String[][]map;
    private int[] dc= {1,0,-1,0};
    private int[] dr= {0,1,0,-1};

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    private void solution() throws Exception {
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new String[N][M];
        String str;
        Ball R = null;
        Ball B = null;
        for (int i = 0; i < N; i++){
            str = br.readLine();
            for (int j = 0; j < M; j++){
                map[i][j] = String.valueOf(str.charAt(j));
                if(map[i][j].equals("B")){
                    B = new Ball(i,j);
                    map[i][j] = ".";
                }else if (map[i][j].equals("R")){
                    R = new Ball(i,j);
                    map[i][j] = ".";
                }
            }
        }



        Queue<Ball[]> q = new LinkedList<>();
        Queue<Integer> depth = new LinkedList<>();
        Ball[] arr = {R,B};

        q.add(arr);
        depth.add(1);

        int answer = -1;
        while (!q.isEmpty() && answer <0){
            boolean R_Hole = false;
            boolean B_Hole = false;
            Ball[] poll = q.poll();
            int d = depth.poll();
            if(d >10){
                break;
            }
            Ball R0 = poll[0];
            Ball B0 = poll[1];
            for (int i = 0; i < 4; i++){
                R_Hole = false;
                B_Hole = false;
                int Rcount=1;
                while (true) {
                    str = map[R0.r+dr[i]*Rcount][R0.c+dc[i]*Rcount];
                    if(!str.equals(".")){
                        if(str.equals("O")){
                            R_Hole = true;
                        }else{
                            Rcount --;
                        }
                        break;
                    }
                    Rcount++;
                }
                int Bcount=1;
                while (true) {
                    str = map[B0.r+dr[i]*Bcount][B0.c+dc[i]*Bcount];
                    if(!str.equals(".")){
                        if(str.equals("O")){
                            B_Hole = true;
                        }else{
                            Bcount --;
                        }
                        break;
                    }
                    Bcount++;
                }

                // 위치 겹쳤다면 덜 움직인 쪽이 먼저온 거니 많이 움직인 쪽 count -1
                if(R0.r+dr[i]*Rcount == B0.r+dr[i]*Bcount && R0.c+dc[i]*Rcount == B0.c+dc[i]*Bcount){
                    if (Rcount > Bcount){
                        Rcount --;

                    }else if(Bcount > Rcount){
                        Bcount --;

                    }
                }
                if(Rcount == 0 && Bcount == 0){
                    continue;
                }
                if(R_Hole && !B_Hole){
                    answer = d;
                    break;
                }
                if(B_Hole){
                    continue;
                }

                // 움직였고 구멍에 들어가지 않았다면 depth 를 1 더한후 각각 큐에 추가
                Ball[] b = {new Ball(R0.r+dr[i]*Rcount,R0.c+dc[i]*Rcount) ,new Ball(B0.r+dr[i]*Bcount,B0.c+dc[i]*Bcount)};
                q.add(b);
                depth.add(d+1);
            }

        }

        System.out.println(answer);
    }

    class Ball{
        int r,c;

        public Ball(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
