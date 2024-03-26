package baekjoon.시험감독;

import java.io.*;
import java.util.StringTokenizer;

public class 시험감독 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] rooms = new int[N];
        for (int i = 0; i < N; i++) {
            rooms[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long count = 0;
        for (int i = 0; i < N; i++) {
            count++;
            if(rooms[i]-B > 0){
                count = count +(rooms[i]-B)/C;
                if((rooms[i]-B)%C != 0){
                    count++;
                }
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(count));
        bw.flush();
    }
}
