package baekjoon.Bingo;

import java.util.Scanner;

// 이 클래스 복사후 이름 바꿔주시고
class Bingo {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        // 빙고판 제작 (5*5 고정)
        int[][] board = new int[5][5];
        for(int i=0;i<5;i++){ // 행
            for(int j=0;j<5;j++){ // 열
                board[i][j] = sc.nextInt();
            }
        }

        // 빙고 체크
        for(int c=0; c<25 ; c++){
            int m = sc.nextInt();
            for(int i=0; i<5; i++) {
                for(int j=0; j<5; j++) {
                    if(m == board[i][j]) {
                        board[i][j] = 0;
                        break;
                    }
                }
            }
            // 빙고 체크
            if(bingo(board)){
                System.out.println(c+1);
                break;
            }

        }
        


    }

    private static boolean bingo(int[][] board){
        int Bcount = 0;

        // 가로 체크
        for(int i=0;i<5;i++){ // 행
            boolean bingo = true;
            for(int j=0;j<5;j++){ // 열
                if(board[i][j] != 0){
                    bingo = false;
                    break;
                }
            } // 1줄 검사 종료
            if(bingo){
                Bcount++;
            }
        }
        // 세로 체크
        for(int j=0;j<5;j++){ // 행
            boolean bingo = true;
            for(int i=0;i<5;i++){ // 열
                if(board[i][j] != 0){
                    bingo = false;
                    break;
                }
            } // 1줄 검사 종료
            if(bingo){
                Bcount++;
            }
        }
        // 대각선 체크
        boolean crossBingo1 = true;
        boolean crossBingo2 = true;
        for(int i=0;i<5;i++){ // 행
            if(board[i][i]!= 0){
                crossBingo1 = false;
            }
            if(board[4-i][i]!= 0){
                crossBingo2 = false;
            }
            if(!crossBingo1 && !crossBingo2){ // 둘다 false 면 그만
                break;
            }

        }
        if(crossBingo1){
            Bcount++;
        }
        if(crossBingo2){
            Bcount++;
        }
        
        if(Bcount >= 3){
            return true;
        }

        return false;
    }
}
