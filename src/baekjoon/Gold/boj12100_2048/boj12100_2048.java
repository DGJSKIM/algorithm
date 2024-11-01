package baekjoon.Gold.boj12100_2048;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj12100_2048 {

    private int maxNum = 0;

    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] dir = {0,1,2,3}; // 0: 좌, 1: 상, 2: 우, 3: 하
        
        for(int i = 0; i < 4; i++){
            dfs(map, dir[i], 0);
        }

        System.out.println(maxNum);
    }

    private void dfs(int[][] map, int dir, int cnt) {
        if(cnt == 5) {
            maxNum = Math.max(maxNum, getMaxNum(map));
            return;
        }
        
        // 맵 복사
        int[][] copyMap = new int[map.length][map.length];
        for(int i = 0; i < map.length; i++) {
            copyMap[i] = map[i].clone();
        }
        
        // dir만큼 회전
        for(int i = 0; i < dir; i++) {
            copyMap = rotate(copyMap);
        }
        
        // 왼쪽으로 밀기
        moveLeft(copyMap);
        
        // 원래 방향으로 되돌리기
        for(int i = 0; i < (4 - dir) % 4; i++) {
            copyMap = rotate(copyMap);
        }
        
        // 다음 단계 진행
        for(int i = 0; i < 4; i++) {
            dfs(copyMap, i, cnt + 1);
        }
    }

    private int[][] rotate(int[][] map) {
        int N = map.length;
        int[][] rotated = new int[N][N];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                rotated[i][j] = map[N-1-j][i];
            }
        }
        return rotated;
    }

    private void moveLeft(int[][] map) {
        int N = map.length;
        for(int i = 0; i < N; i++) {
            // 0이 아닌 숫자들을 임시 배열에 저장
            int[] temp = new int[N];
            int idx = 0;
            for(int j = 0; j < N; j++) {
                if(map[i][j] != 0) {
                    temp[idx++] = map[i][j];
                }
            }
            
            // 같은 숫자 합치기
            for(int j = 0; j < idx-1; j++) {
                if(temp[j] == temp[j+1]) {
                    temp[j] *= 2;
                    temp[j+1] = 0;
                }
            }
            
            // 결과를 다시 원래 배열에 채우기
            Arrays.fill(map[i], 0);  // 현재 행을 0으로 초기화
            idx = 0;
            for(int j = 0; j < N; j++) {
                if(temp[j] != 0) {
                    map[i][idx++] = temp[j];
                }
            }
        }
    }

    private int getMaxNum(int[][] map){
        int max = 0;
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                max = Math.max(max, map[i][j]);
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        new boj12100_2048().solution();
    }
}
