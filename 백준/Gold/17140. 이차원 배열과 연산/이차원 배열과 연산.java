import java.io.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    private int R;
    private int C;
    private int K;
    private int time = 0;
    int[][] A = new int[100][100];
    int[][] B = new int[100][100];
    int[] count = new int[100];
    int maxR = 3;
    int maxC = 3;


    private void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st =  new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i=0 ; i<3;i++){
            st =  new StringTokenizer(br.readLine());
            for (int j=0; j<3;j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        calculate();


    }

    private void calculate() throws IOException {
        if(A[R-1][C-1] == K){
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            bw.write(String.valueOf(time));
            bw.newLine();
            bw.flush();
        }else if(time >= 100){
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            bw.write("-1");
            bw.newLine();
            bw.flush();
        }else{
            B = new int[100][100];
            //R 연산
            if (maxR >= maxC){
                for (int i = 0; i < maxR; i++) {
                    count = new int[100];
                    LinkedList<NumCount> numCounts= new LinkedList<NumCount>();
                    for (int a : A[i]) {
                        if(a != 0 ){ // 1->0 100 -> 99에 저장
                            count[a-1]++;
                        }
                    }
                    for (int j = 0; j<100 ; j++){
                        if(count[j] > 0){
                            numCounts.add(new NumCount(j+1,count[j]));
                        }
                    }
                    List<NumCount> sortedNumCounts = numCounts.stream()
                            .sorted(Comparator.comparingInt((NumCount nc) -> nc.count)
                                    .thenComparingInt(nc -> nc.num)).collect(Collectors.toList());
                    for (int j=0; j<sortedNumCounts.size();j++){
                        for (int k=0; k<2;k++){
                            if(2*j+k <100){
                                if(k==0){
                                    B[i][2*j+k] = sortedNumCounts.get(j).num;
                                }else{

                                    B[i][2*j+k] = sortedNumCounts.get(j).count;
                                }
                            }
                        }
                    }
                    maxC = Math.max(maxC, Math.min(sortedNumCounts.size()*2,100));
                }
            }else{
                //C연산
                for (int i = 0; i < maxC; i++) {
                    count = new int[100];
                    LinkedList<NumCount> numCounts= new LinkedList<NumCount>();

                    for (int j=0; j<maxR; j++){
                        if( A[j][i]!= 0 ){
                            count[A[j][i]-1]++;
                        }
                    }

                    for (int j = 0; j<100 ; j++){
                        if(count[j] > 0){
                            numCounts.add(new NumCount(j+1,count[j]));
                        }
                    }
                    List<NumCount> sortedNumCounts = numCounts.stream()
                            .sorted(Comparator.comparingInt((NumCount nc) -> nc.count)
                                    .thenComparingInt(nc -> nc.num)).collect(Collectors.toList());
                    for (int j=0; j<sortedNumCounts.size();j++){
                        for (int k=0; k<2;k++){
                            if(2*j+k <100){
                                if(k==0){
                                    B[2*j+k][i] = sortedNumCounts.get(j).num;
                                }else{
                                    B[2*j+k][i] = sortedNumCounts.get(j).count;
                                }
                            }
                        }
                    }
                    maxR = Math.max(maxR, Math.min(sortedNumCounts.size()*2,100));
                }
            }
            for (int i=0; i<maxR; i++){
                A[i] = B[i].clone();
            }

            time++;
            calculate();
        }
    }

    private class NumCount{
        int num;
        int count;

        public NumCount(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
