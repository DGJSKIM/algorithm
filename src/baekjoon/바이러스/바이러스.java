package baekjoon.바이러스;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 바이러스 {

    private int n,m,count;
    private boolean [] corrupted;

    private Root[] roots;
    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        roots = new Root[m];
        corrupted = new boolean[n];//
        for (int i = 0; i <m;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            roots[i] = new Root(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
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
                Root root = roots[i];
                if(root.isContain(s) && !corrupted[root.returnElse(s)-1]){
                    q.add(root.returnElse(s));
                    corrupted[root.returnElse(s)-1] = true;
                    count++;
                }
            }
        }



    }

    private class Root{
        private int a;
        private int b;

        public Root(int a, int b) {
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
