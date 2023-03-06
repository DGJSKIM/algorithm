package baekjoon.Five;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Five {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 10*10 바둑판 생성
        char[][] board = new char[10][10];
        for(int i=0;i<10;i++){
            String s = br.readLine();
            for(int j=0;j<10;j++){
                board[i][j] = s.charAt(j);
            }
        }
        // BufferedReader 사용법 인터넷에서 긁어옴(황세영 과장님 블로그)
        int win = 0;
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(board[i][j] == 'X'){
                    if(checkWin(board,i,j)){
                        win = 1;
                        break;
                    }
                }
            }
        }
        System.out.println(win);

    }

    private static boolean checkWin(char[][] board, int i, int j) throws Exception{

        // 가로로 4개가 깔리고 양쪽중 한칸은 .인 경우 승리
        int xcount=0;
        int ycount=0;

        for(int x = i-3;x<i+4;x++){ // 기준 좌우 3칸가져옴
            if(i-3<0){x=0;}
            if(board[x][j] == 'X'){
                xcount++;
            }
            else{
                xcount=0;
            }

            if(xcount == 4 && x-4>= 0 && board[x - 4][j] == '.') {
                return true;
            }
            if(xcount ==4 && x+1<10 && board[x+1][j]=='.'){
                return true;
            }



        }
        // 세로로  4개가 깔리고 양쪽중 한칸은 .인 경우 승리
        for(int y = j-3;y<j+4;y++){ // 기준 좌우 3칸가져옴
            if(i-3<0){y=0;}
            if(board[y][j] == 'X'){
                ycount++;
            }
            else{
                ycount=0;
            }
            if(ycount ==4 && y-4>=0 && board[i][y-4]=='.'){
                return true;
            }
            if(ycount ==4 && y+1<10 && board[i][y+1]=='.'){
                return true;
            }

        }

        int ccount1 = 0;
        int ccount2 = 0;
        // 대각선으로 4개가 깔리고 양쪽중 한칸은 .인 경우 승리
        // 우상향 대각선
        for(int z = -3; z<4;z++){
            if(board[i+z][j+z] == 'X'){
                ccount1++;
            }
            else{
                ccount1=0;
            }

            if(board[i+z][j-z] == 'X'){
                ccount2++;
            }
            else{
                ccount2=0;
            }

            if(ccount1 ==4 && i+z+1<10 && j+z+1<10  && board[i+z+1][j+z+1]=='.'){
                return true;
            }
            if(ccount1 ==4 && i+z-4>=0 && j+z-4>= 0 && board[i+z-4][j+z-4]=='.'){
                return true;
            }

            if(ccount2 ==4 && i+z+1<10 && j-z-1>=0  && board[i+z+1][j-z-1]=='.'){
                return true;
            }
            if(ccount2 ==4 && i+z-4>=0 && j-z+4<10 && board[i+z-4][j-z+4]=='.'){
                return true;
            }
        }


        return false;
    }
    
    
    //////// 경우의수 체크 실패 
    // 중간에 하나만 비어있는 경우는 파악하지 못함
    // 새로운 방법 접근 필요
}
