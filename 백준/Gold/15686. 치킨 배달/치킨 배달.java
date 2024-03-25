import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
    public static int N,M;
    public static ArrayList<Point> chickenArr, homeArr;
    public static boolean[] alive;
    public static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        homeArr = new ArrayList<>();
        chickenArr = new ArrayList<>();
        int num=0;
        for (int i=0; i<N;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++){
                num = Integer.parseInt(st.nextToken());
                if( num == 1){
                    homeArr.add(new Point(i,j));
                }else if( num == 2){
                    chickenArr.add(new Point(i,j));
                }
            }
        }
        alive = new boolean[chickenArr.size()];
        br.close();

        answer = Integer.MAX_VALUE;
        setChickenStore(0,0);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
    }

    public static void setChickenStore(int start, int n) {
        if(n == M){
            count();
            return;
        }

        for (int i=start; i<chickenArr.size();i++){
            alive[i] = true;
            setChickenStore(i+1,n+1);
            alive[i] = false;
        }
    }

    public static void count(){
        int temp = 0;
        for (int i=0; i<homeArr.size();i++){
            int min = Integer.MAX_VALUE;

            for(int j=0;j<chickenArr.size();j++){
                if(alive[j]){
                    min = Math.min(min,Math.abs(homeArr.get(i).r-chickenArr.get(j).r) + Math.abs(homeArr.get(i).c-chickenArr.get(j).c));
                }
            }
            temp += min;
        }
        answer = Math.min(answer,temp);
    }

    public static class Point {
        int r;
        int c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}
