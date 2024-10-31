package baekjoon.Gold.버블_소트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 버블_소트 {
    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 뒷 숫자가 앞으로 올때는 한번에 1칸 밖에 못온다
        // 즉 정렬이 완료된 이후 앞으로 온 횟수가 가장 큰 값이 버븥 소트가 일어난 횟수가 되고 거기에 1더해준 것이 답이 된다.(더이상 안 일어나는 회차니까)
        // 값과 인덱스를 함께 저장할 클래스
        class Number implements Comparable<Number> {
            int value, index;
            Number(int value, int index) {
                this.value = value;
                this.index = index;
            }
            
            @Override
            public int compareTo(Number o) {
                return this.value - o.value;
            }
        }
        
        Number[] numbers = new Number[N];
        for(int i = 0; i < N; i++) {
            numbers[i] = new Number(Integer.parseInt(br.readLine()), i);
        }
        
        // 값 기준으로 정렬
        Arrays.sort(numbers);
        
        int max = 0;
        // 정렬 후 위치(i)와 원래 위치(index) 비교
        for(int i = 0; i < N; i++) {
            max = Math.max(max, numbers[i].index - i);
        }
        
        System.out.println(max + 1);
    }

    public static void main(String[] args) throws IOException {
        new 버블_소트().solution();
    }
}
