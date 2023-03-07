package baekjoon.Five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Five_2 {
    // 저번엔 4개가 이어진 상태만 체크하다보니 XX.XX 같은 경우는 찾지 못함
    // .을 X로 바꿀시 오목완성을 조건으로 할 경우

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 10*10 바둑판 생성
        char[][] board = new char[10][10];
        for (int i = 0; i < 10; i++) {
            String s = br.readLine();
            for (int j = 0; j < 10; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        int win = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == '.') {
                    if (checkWin(board, i, j)) {
                        win = 1;
                        break;
                    }
                }
            }
        }
        System.out.println(win);

    }

    private static boolean checkWin(char[][] board, int i, int j) throws Exception {

        // . 기준 왼쪽으로 쭉 X갯수 세보고 멈추는 지점에서 다시 오른쪽 세기 그 후 총 카운트가 4이면 return true;
        int L = 1;
        int R = 1;
        while(true){
            if(j-L<0 ||board[i][j-L]!='X'){
                break;
            }
            L++;
        }
        while(true){
            if(j+R>=10 || board[i][j+R]!='X'){
                break;
            }
            R++;
        }
        if(L+R>=6){
            // 원래는 (L-1)+(R-1)=4 인데 편의상 이렇게 표기
            return true;
        }
        int U = 1;
        int D = 1;
        // . 기준 위 U 아래 D
        while(true){
            if(i-U<0 || board[i-U][j]!='X'){
                break;
            }
            U++;
        }
        while(true){
            if(i+D>=10 || board[i+D][j]!='X'){
                break;
            }
            D++;
        }
        if(U+D>=6){ // 6목도 승리
            return true;
        }

        // 우상향 대각선 체크
        int Z = 1;
        int ZZ = 1;
        while(true){
            if(i-Z<0 || j-Z<0 || board[i-Z][j-Z]!='X'){
                break;
            }
            Z++;
        }
        while(true){
            if(i+ZZ>=10 || j+ZZ>=10 || board[i+ZZ][j+ZZ]!='X'){
                break;
            }
            ZZ++;
        }
        if(Z+ZZ>=6){
            return true;
        }


        // 우하향 대각선 체크
        Z=1; ZZ=1;
        while(true){
            if(i-Z<0 || j+Z>=10 || board[i-Z][j+Z]!='X'){
                break;
            }
            Z++;
        }
        while(true){
            if(i+ZZ>=10 || j-ZZ<0 ||board[i+ZZ][j-ZZ]!='X'){
                break;
            }
            ZZ++;
        }
        if(Z+ZZ>=6){
            return true;
        }

        return false;
    }
}