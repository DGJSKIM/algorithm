package baekjoon.Snail;

import java.util.Scanner;

// 이 클래스 복사후 이름 바꿔주시고
class Snail {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);



        int n = sc.nextInt();

        int[][] Snail = new int[n][n];
        // 위로1 오1 아2 왼2 위3 오3 아4 왼4 ...... 언제까지 -> 이동할 칸수가 n 이 될때까지 이동할 칸수가 n 이 된다면 n-1칸 이동하고 종료
        // 시작지점 -> n 이 홀수일 경우 (n/2) 몫 +1 3->(2,2) 5->(3,3) 7-> (4,4) n-> (n/2+1,n/2+1)
        // n이 짝수일 경우 시작지점 2->(2,1) 4->(3,2) 6->(4,3)  n ->(n/2+1, n/2)
        
        // 배열은 0 부터 시작이니 그거 생각하고 짤것

        int direction = 8; // 키보드 숫자키패드 기준 2468 아래 왼 오른 윗 처음은 무조건 윗방향
        int distance = 1;

        int x = n/2;
        int y = n/2;


        int count = 0; // 한방향으로 진행될때마다 +1;
        for(int i=1; i<=n*n; i++){ // i



            Snail[x][y] = i; // 값 저장

            if(count == distance) {
                switch (direction){ // 방향 전환 4 6 다음에서 1칸씩 더 이동
                    case 2:direction = 4;
                        break;
                    case 4:direction = 8;
                        distance = distance+1;
                        break;
                    case 6:direction = 2;
                        distance = distance+1;
                        break;
                    case 8:direction = 6;
                        break;
                }
                count = 0;

            }

            switch (direction){
                case 2:x++;
                    break;
                case 4:y--;
                    break;
                case 6:y++;
                    break;
                case 8:x--;
                    break;

            }
            count ++;

        } // 값 저장 완료

        // 달팽이 보여주기
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {

                sb.append(Snail[i][j] +" ");
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());

        // 찾기
        int m = sc.nextInt();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(m == Snail[i][j]) {
                    System.out.print((i+1)+" "+(j+1));
                }
            }
        }

    }

    
}
