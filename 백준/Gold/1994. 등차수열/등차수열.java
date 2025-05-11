import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Main {
    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numArr = new int[N];
        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(br.readLine());
        }

        // 입력 배열 정렬
        Arrays.sort(numArr);

        // (공차 diff, 해당 공차로 끝나는 등차수열 길이)를 저장하는 DP 테이블
        List<HashMap<Integer, Integer>> dp = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            dp.add(new HashMap<>()); // 안전하게 초기화
        }

        int answer = 1; // 최소 등차수열 길이는 1
        // 그냥 이분탐색으로 2중 for문에 while 문 돌리는 식으로 푸니 시간초과뜸
        // 그래서 지피티햄도움받음
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                int diff = numArr[i] - numArr[j];
                dp.get(i).put(diff, dp.get(j).getOrDefault(diff, 1) + 1); // 이전 길이에 +1
                answer = Math.max(answer, dp.get(i).get(diff)); // 최댓값 갱신
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}