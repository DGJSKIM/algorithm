package baekjoon.ColoredPaper;

import java.util.Scanner;

// 이 클래스 복사후 이름 바꿔주시고
class ColoredPaper {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int pn = sc.nextInt();// 색종이 갯수
        int[][] board = new int[100][100]; // 100*100 칸의 모눈종이로 생각

        for(int i=0 ; i<pn ; i++){
            int px = sc.nextInt(); // x좌표
            int py = sc.nextInt(); // y좌표
            for(int x = px; x<px+10;x++){
                for(int y =py;y<py+10;y++){
                    board[x][y] = 1;
                }
            }
        }
        int sum =0;
        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                sum +=board[i][j];
            }
        }
        System.out.println(sum);

    }
}
