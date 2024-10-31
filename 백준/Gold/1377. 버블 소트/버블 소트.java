import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
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
        new Main().solution();
    }
}
