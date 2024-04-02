package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class boj1446_지름길 {
    private int N,D;
    ShortCut [] scs;
    int [] distance;

    private void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());


        int[] distance = IntStream.rangeClosed(0, D+1).toArray();
        scs = new ShortCut[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if(d< e-s) { // 지름길 의미가 있는 경우만 채택
                scs[i] = new ShortCut(s,e,d);
            }else{
                i--;
                N--;
            }
        }
        scs = Arrays.copyOfRange(scs,0,N);

        int totalDistance = 0;
        while (totalDistance <=D){
            for (int i = 0; i <N ; i++){
                if (scs[i].start == totalDistance && scs[i].end <=D){
                    distance[scs[i].end] =  Math.min(distance[totalDistance]+scs[i].distance,distance[scs[i].end]);
                }
            }
            distance[totalDistance +1 ] =  Math.min(distance[totalDistance]+1,distance[totalDistance+1]);
            totalDistance ++;
        }

        System.out.println(distance[D]);





    }
    private class ShortCut {
        int start;
        int end;
        int distance;

        public ShortCut(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        new boj1446_지름길().solution();
    }
}
