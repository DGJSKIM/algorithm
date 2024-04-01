package baekjoon.바이러스;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 바이러스 {

    private int n,m,count;
    private boolean [] corrupted;

    private Route[] routes;
    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        routes = new Route[m];
        corrupted = new boolean[n];//
        for (int i = 0; i <m;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            routes[i] = new Route(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        bfs();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(count));
        bw.newLine();
        bw.flush();
    }

    private void bfs() {
        count = 0;
        Queue<Integer> q = new LinkedList<>();
        corrupted[0] = true;
        q.add(1);
        while (!q.isEmpty()){
            int s = q.poll();
            for (int i =0; i<m;i++){
                Route route = routes[i];
                if(route.isContain(s) && !corrupted[route.returnElse(s)-1]){
                    q.add(route.returnElse(s));
                    corrupted[route.returnElse(s)-1] = true;
                    count++;

                }
            }
        }



    }

    private class Route{
        private int a;
        private int b;

        public Route(int a, int b) {
            this.a = a;
            this.b = b;
        }

        private  boolean isContain(int k){
            return k==a | k==b;
        }

        private int returnElse(int k){
            if(k == a){
                return b;
            }
            return a;
        }
    }

    public static void main(String[] args) throws IOException {
        new 바이러스().solution();
    }
}
