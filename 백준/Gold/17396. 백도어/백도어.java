import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private int N,M;
    private long[] distance;
    private boolean[] see;
    private boolean[] visited;
    static ArrayList<ArrayList<Route>> list =new ArrayList<>();
    private void solution() throws Exception {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        see = new boolean[N];
        visited = new boolean[N];
        distance = new long[N];
        Arrays.fill(distance,Long.MAX_VALUE);
        for (int i = 0; i < N-1; i++) {
            see[i] = Integer.parseInt(st.nextToken()) != 0;
        }
        see[N-1] = false; // 귀찮으니 넥서스도 몰래 백도 가능하다고 가정 (아군 녹턴이 적절하게 궁극기를 사용해주었다)


        for (int i = 0; i < N ; i++) {
            list.add(new ArrayList<>());
        }
        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s1 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
             if(!see[s1] && !see[s2]){ // 출발지나 도착지가 보이는 곳에 있다면 제외하고 생각
                list.get(s1).add(new Route(s1,s2,d));
                list.get(s2).add(new Route(s2,s1,d));
            }
        }
        PriorityQueue<Integer> indexPq = new PriorityQueue<Integer>(((o1, o2) -> Long.compare(distance[o1.intValue()], distance[o2.intValue()])));
        distance[0] =0;
        indexPq.add(0);

        while(!indexPq.isEmpty()){
            int i = indexPq.poll();
            if(!visited[i]){
                ArrayList<Route> routes = list.get(i);
                visited[i] = true;
                for (Route route : routes){
                    if(!visited[route.s2] && route.d!=0 && distance[route.s2]> distance[i]+ route.d){
                        distance[route.s2] =  distance[i]+route.d;
                        indexPq.add(route.s2);
                    }
                }
            }

        }
        long answer = (distance[N-1]==Long.MAX_VALUE)?-1:distance[N-1];
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
    }

    class Route{
        int s1;
        int s2;
        int d;

        public Route(int s1, int s2, int d) {
            this.s1 = s1;
            this.s2 = s2;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
