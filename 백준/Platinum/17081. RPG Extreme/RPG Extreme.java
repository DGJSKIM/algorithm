import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private String[][] dungeon;
    private Event[][] events;
    private int N,M;
    private int passedTurn =0;
    boolean endGame = false;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private void solution() throws Exception {


        StringTokenizer st = new StringTokenizer(br.readLine());
        String str;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dungeon = new String[N][M];
        events = new Event[N][M];
        Player player = null;

        int itemCount = 0;
        int monsterCount = 0;
        for (int i = 0; i < N; i++) {
            str = br.readLine();
            for (int j = 0; j < M; j++) {
                String s = String.valueOf(str.charAt(j));
                dungeon[i][j] = s;
                switch (s){
                    case "B":
                        itemCount++;
                        break;
                    case "&","M":
                        monsterCount++;
                        break;
                    case "@":
                        player = new Player(i,j);
                        break;
                }
            }
        }
        str = br.readLine();
        String[] destiny = new String[str.length()];

        for (int i=0; i<str.length(); i++) {
            destiny[i] = String.valueOf(str.charAt(i));
        }
        for (int i=0; i<monsterCount;i++){
            st  = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            String name = st.nextToken();
            int w = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            events[r-1][c-1] = new Monster(w,a,h,e,name,dungeon[r-1][c-1].equals("M"));
        }

        for (int i=0; i<itemCount; i++){
            st  = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            String type = st.nextToken();
            Event event = new Event();
            String a = st.nextToken();
            switch (type){
                case "W":
                    event = new Weapon(Integer.parseInt(a));
                    break;
                case "A":
                    event = new Armor(Integer.parseInt(a));
                    break;
                case "O":
                    event = new Ring(a);
                    break;
            }
            events[r-1][c-1] = event;
        }

        for (passedTurn=0; passedTurn<destiny.length; passedTurn++){
            player.move(destiny[passedTurn]);
            switch (dungeon[player.r][player.c]){
                case "B":
                    player.getItem(events[player.r][player.c]);
                    break;
                case "&", "M":
                    player.fight((Monster) events[player.r][player.c]);
                    break;
                case "^":
                    player.stepOnTrap();
            }
            if (endGame){
                break;
            }
        }

        if(!endGame){
            passedTurn--;
            player.showDungeon();
            player.showState();
            bw.write("Press any key to continue.");
        }

        bw.flush();
    }

    private class Player {
        int r, c ,r0, c0;
        int LEVEL = 1;//레벨
        int H_CUR = 20;
        int H_MAX = 20;
        int ATT = 2;
        int E_CUR = 0;
        int E_MAX = 5;
        int ARM = 2;
        private Weapon weapon = new Weapon(0);
        private Armor armor = new Armor(0);
        private List<Ring> rings = new ArrayList<>();

        public Player(int r, int c) {
            this.r = r;
            this.r0 = r;
            this.c = c;
            this.c0 = c;
        }

        private boolean HR= false;
        private boolean RE= false;
        private boolean CO= false;
        private boolean EX= false;
        private boolean DX= false;
        private boolean HU= false;
        private boolean CU= false;
        private String deathSign = "YOU HAVE BEEN KILLED BY ";

        private void move(String d){
            int r2 =r;
            int c2 =c;

            switch (d) {
                case "R":
                    c2++;
                    break;
                case "L":
                    c2--;
                    break;
                case "U":
                    r2--;
                    break;
                case "D":
                    r2++;
                    break;
            }
            if( r2>=0 && c2 >=0 && r2<N && c2<M && !dungeon[r2][c2].equals("#")){
                this.r = r2;
                this.c = c2;
            }

        }

        private void getItem(Event item){
            switch (item.getClass().getSimpleName()){
                case "Weapon":
                    getWeapon((Weapon) item);
                    break;
                case "Armor":
                    getArmor((Armor) item);
                    break;
                case "Ring":
                    getRing((Ring) item);
                    break;
            }
            dungeon[this.r][this.c] = ".";

        }

        private void getWeapon(Weapon w) {
            this.weapon = w;

        }

        private void getArmor(Armor a) {
            this.armor = a;
        }

        private void getRing(Ring r){
            if(rings.size()<4 && !rings.stream().anyMatch(a -> a.type.equals(r.type))){
                rings.add(r);
                switch (r.type){
                    case "HR":
                        this.HR = true;
                        break;
                    case "RE":
                        this.RE = true;
                        break;
                    case "CO":
                        this.CO = true;
                        break;
                    case "EX":
                        this.EX = true;
                        break;
                    case "DX":
                        this.DX = true;
                        break;
                    case "HU":
                        this.HU = true;
                        break;
                    case "CU":
                        this.CU = true;
                        break;
                }
            }
        }



        private void levelUp() {
            LEVEL++;
            E_MAX += 5;
            E_CUR = 0;
            H_MAX += 5;
            H_CUR = H_MAX;
            ATT += 2;
            ARM += 2;
        }

        private void fight(Monster m0) throws IOException {

            Monster m = m0.copyMonster();
            boolean victory = true;
            boolean first = true;
            int dmg =0;
            while (H_CUR > 0 && m.h > 0) {
                dmg = this.ATT + this.weapon.a;
                if(first && this.CO && this.DX){
                    dmg = dmg*3;
                }else if(first && this.CO){
                    dmg = dmg*2;
                }
                if(first && HU && m.isBoss){
                    H_CUR = H_MAX;
                }

                m.h -= Math.max(1,dmg - m.a);
                if (m.h <= 0) {
                    victory = true;
                    break;
                }
                // 맞는턴
                if(!(first && HU && m.isBoss)){
                    this.H_CUR -= Math.max(1, m.w - (this.ARM+ this.armor.a));
                }

                if (this.H_CUR <= 0) {
                    victory = false;
                    break;
                }

                if(first){
                    first = false;
                }
            }
            if (victory) {
                killMonster(m);

            } else {
                this.deathSign += (m.name + "..");
                gameOver();
            }

        }
        private void killMonster(Monster m) throws IOException {

            int exp = (this.EX)?(int)Math.floor(m.e*1.2):m.e;
            if (this.E_MAX - this.E_CUR <= exp) {
                levelUp();
            } else {

                E_CUR += exp;
            }
            if(HR){
                H_CUR = Math.min(H_CUR+3, H_MAX);
            }
            dungeon[this.r][this.c] =".";
            if(m.isBoss){
                win();
            }

        }



        private void stepOnTrap() throws IOException {
            this.H_CUR -= DX?1:5;

            if (this.H_CUR <= 0) {
                this.deathSign += ("SPIKE TRAP" + "..");
                gameOver();
            }

        }

        private void gameOver() throws IOException {
            if(RE){
                RE = false;
                for (int i = 0; i <rings.size(); i++) {
                    if (rings.get(i).type.equals("RE")){
                        rings.remove(i);
                        break;
                    }
                }
                this.r = r0;
                this.c = c0;
                this.H_CUR = H_MAX;
                this.deathSign = "YOU HAVE BEEN KILLED BY ";
                return;
            }
            showDungeon();
            showState();
            bw.write(this.deathSign);
            endGame = true;
        }
        private void win() throws IOException {
            showDungeon();
            showState();

            bw.write("YOU WIN!");
            endGame = true;
        }
        private void showDungeon() throws IOException {
            dungeon[this.r0][this.c0] = ".";
            if((dungeon[this.r][this.c].equals(".") || dungeon[this.r][this.c].equals("^"))&&this.H_CUR >0){
                dungeon[this.r][this.c] = "@";
            }

            for (int i=0; i<N;i++){
                for (int j=0;j<M;j++){
                    bw.write(dungeon[i][j]);
                }
                bw.newLine();
            }
        }
        private void showState() throws IOException {
            StringBuilder sb = new StringBuilder();
            sb.append("Passed Turns : ").append(++passedTurn).append("\n");
            sb.append("LV : ").append(this.LEVEL).append("\n");
            sb.append("HP : ").append(Math.max(0,this.H_CUR) + "/" + this.H_MAX).append("\n");
            sb.append("ATT : ").append(this.ATT + "+" + this.weapon.a).append("\n");
            sb.append("DEF : ").append(this.ARM + "+" + this.armor.a).append("\n");
            sb.append("EXP : ").append(this.E_CUR + "/" + this.E_MAX).append("\n");
            bw.write(sb.toString());
        }
    }

    private class Event{

    }

    private class Weapon extends Event {
        int a;

        public Weapon(int a) {

            this.a = a;
        }
    }

    private class Armor extends Event {
        int a;

        public Armor( int a) {

            this.a = a;
        }
    }

    private class Ring extends Event {
        String type;

        public Ring(String type) {

            this.type = type;
        }
    }

    private class Monster extends Event {
        int w, a, h, e;
        String name;
        boolean isBoss;

        public Monster( int w, int a, int h, int e, String name, boolean isBoss) {
            this.w = w;
            this.a = a;
            this.h = h;
            this.e = e;
            this.name = name;
            this.isBoss = isBoss;
        }
        private Monster copyMonster(){
            return new Monster(this.w, this.a, this.h,this.e, this.name, this.isBoss);
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
