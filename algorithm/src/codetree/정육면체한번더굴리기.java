import java.util.*;
import java.io.*;

public class Main {
    static class Dice {
        int r;
        int c;
        int dir;
        int bnum;

        public Dice(int r, int c, int dir, int bnum){
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.bnum = bnum;
        }

        @Override
        public String toString(){
            return "r : "+r+", c : "+c+", dir : "+dir+", bnum : "+bnum;
        }
    }
    static int N;
    static int M;
    static int[][] cell;
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static Dice dice;
    static int[] dicecell = {3,2,4,5};
    static int ans = 0;
    static int cnt = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cell = new int[N][N];
        for(int i=0 ; i<cell.length ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0 ; j<cell[i].length ; j++){
                cell[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dice = new Dice(0,0,0,6);
        for(int i=0 ; i<M ; i++){
            Roll();

            cnt = 0;
            boolean[][] v = new boolean[N][N];
            bfs(dice.r,dice.c,v);

            ans = ans+(cnt*cell[dice.r][dice.c]);
        }

        System.out.println(ans);

    }
    public static void bfs(int r, int c, boolean[][] v){
        v[r][c] = true;
        cnt+=1;
        for(int d=0 ; d<dr.length ; d++){
            int nr = r+dr[d];
            int nc = c+dc[d];
            if(nr>=0 && nr<N && nc>=0 && nc<N && !v[nr][nc] && cell[nr][nc] == cell[dice.r][dice.c]){
                bfs(nr,nc,v);
            }
        }

    }

    public static void setting(int bnum){
        if(dice.dir == 0){
            dicecell[0] = 7-bnum;
            dicecell[2] = bnum;
        }else if(dice.dir == 1){
            dicecell[1] = 7-bnum;
            dicecell[3] = bnum;
        }else if(dice.dir == 2){
            dicecell[0] = bnum;
            dicecell[2] = 7-bnum;
        }else if(dice.dir == 3){
            dicecell[1] = bnum;
            dicecell[3] = 7-bnum;
        }
    }

    public static void Roll(){
        int nr = dice.r+dr[dice.dir];
        int nc = dice.c+dc[dice.dir];
        if(nr>=0 && nr<N && nc>=0 && nc<N){
            dice.r = nr;
            dice.c = nc;
            int bnum = dice.bnum;
            dice.bnum = dicecell[dice.dir];
            setting(bnum);

            if(cell[nr][nc] < dice.bnum){
                dice.dir = (dice.dir+1)%4;
            }else if(cell[nr][nc] > dice.bnum){
                dice.dir = (dice.dir+3)%4;
            }

        }else{
            nr = dice.r+dr[(dice.dir+2)%4];
            nc = dice.c+dc[(dice.dir+2)%4];
            dice.r = nr;
            dice.c = nc;
            dice.dir = (dice.dir+2)%4;
            int bnum = dice.bnum;
            dice.bnum = dicecell[dice.dir];
            setting(bnum);

            if(cell[nr][nc] < dice.bnum){
                dice.dir = (dice.dir+1)%4;
            }else if(cell[nr][nc] > dice.bnum){
                dice.dir = (dice.dir+3)%4;
            }
        }
    }
}