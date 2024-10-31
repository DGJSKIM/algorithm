package baekjoon.Gold.버블_소트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 버블_소트_구현 {
    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt( br.readLine());
        }
        boolean change = false;
        for(int i = 0; i < N-1; i++){
            change = false;
            for(int j = 0; j < N-1; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    change = true;
                }
            }
            if(!change){
                System.out.println(i+1);
                return ;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new 버블_소트_구현().solution();
    }
}
