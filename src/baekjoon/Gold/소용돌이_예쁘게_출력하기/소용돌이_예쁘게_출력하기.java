package baekjoon.소용돌이_예쁘게_출력하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 소용돌이_예쁘게_출력하기 {
    int [][] vortex;
    int maxLength;
    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        int length_r = r2 - r1 + 1;
        int length_c = c2 - c1 + 1;

        // 간단하게 for 문 돌면서 각 자리에 숫자 계산해서 리턴되도록
        // 일단 소용돌이
        vortex = new int[length_r][length_c];
        for (int i = 0; i<length_r ; i++){
            for (int j = 0; j<length_c ; j++){
                vortex[i][j] = calculateValue(j+c1, i+r1);
            }
        }
        print();
    }

    private int calculateValue(int c, int r){
        int val = 0;
        if(Math.abs(r) >= Math.abs(c)){
            int n = (Math.abs(r)*2+1);
            int dc = Math.abs(r) - c;
            if(r >=0){
                val = n*n -dc;
            }else{
                val = n*n - 6*Math.abs(r) + dc;
            }
        }else{
            int n = (Math.abs(c)*2+1);
            int dx = Math.abs(c) + r;
            if(c >=0){
                val = n*n - 6*Math.abs(c) -dx;
            }else{
                val = n*n - 4*Math.abs(c) + dx;
            }

        }
        if (String.valueOf(val).length() >maxLength){
            maxLength = String.valueOf(val).length();
        }
        return val;
    }

    private void print() {
        for (int i = 0; i < vortex.length; i++) {
            for (int j = 0; j < vortex[i].length; j++) {
                for (int k =String.valueOf(vortex[i][j]).length(); k<maxLength ; k++ ){
                    System.out.print(" ");
                }
                System.out.print(vortex[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws IOException {
        new 소용돌이_예쁘게_출력하기().solution();
    }
}
