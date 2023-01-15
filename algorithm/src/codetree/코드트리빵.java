import java.util.*;
import java.io.*;

public class Main {
    static int N,M;
    static int[][] cell;
    static int[] dr = {-1,0,0,1};
    static int[] dc = {0,-1,1,0};
    static class People{
        int r;
        int c;
        int state;

        public People(int r, int c){
            this.r=r;
            this.c=c;
            this.state = 0;
        }
        @Override
        public String toString(){
            return "[r:"+r+", c:"+c+", state:"+state+"]";
        }
    }
    static People[] people;
    static People[] conven;
    static int time = 1;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cell = new int[N+1][N+1];
        for(int i=1 ; i<=N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1 ; j<=N ; j++){
                cell[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        people = new People[M+1];
        conven = new People[M+1];
        for(int i=1 ; i<=M ; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            people[i] = new People(n1,n2);
            conven[i] = new People(n1,n2);
            cell[n1][n2] = 2;
        }

        boolean[][][] v = new boolean[M+1][N+1][N+1];
        Queue<int[]>[] qlist = new LinkedList[M+1];
        for(int i=1 ; i<= M ; i++){
            qlist[i] = new LinkedList<int[]>();
        }


        la:while(true){
            if(time<=M){
                People p = people[time];
                basecamp(p);
                v[time][people[time].r][people[time].c] = true;
                qlist[time].add(new int[]{people[time].r, people[time].c, 0});
            }

            for(int i=1 ; i<=M ; i++){
                if(time >= i && people[i].state == 0){
                    int cnt = qlist[i].size();
                    for(int k = 0 ; k<cnt ; k++){
                        int[] p = qlist[i].poll();
                        if(conven[i].r == p[0] && conven[i].c == p[1]){
                            cell[conven[i].r][conven[i].c] = 3;
                            people[i].state = 1;
                            if(isFinish()) break la;
                            else continue;
                        }

                        for(int d=0 ; d<dr.length ; d++){
                            int nr = p[0]+dr[d];
                            int nc = p[1]+dc[d];
                            if(nr>=1 && nr<=N && nc>=1 && nc<= N && !v[i][nr][nc] && cell[nr][nc]!=3){
                                v[i][nr][nc] = true;
                                qlist[i].add(new int[]{nr,nc,p[2]+1});
                            }
                        }
                    }
                }
            }
            // System.out.println("time = "+time);
            // print();
            time+=1;

        }
        System.out.println(time);
        // print();
    }
    public static boolean isFinish(){
        for(int i=1 ; i<=M ; i++){
            if(people[i].state == 0) return false;
        }

        return true;
    }

    public static void basecamp(People p){
        boolean[][] v = new boolean[N+1][N+1];
        v[p.r][p.c] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{p.r, p.c, 0});
        int[] bc = new int[3];
        int mindest = 10000000;

        while(!q.isEmpty()){
            int[] poll = q.poll();
            if(cell[poll[0]][poll[1]] == 1){
                if(poll[2] < mindest){
                    bc = poll;
                    mindest = poll[2];
                }else if(poll[2] == mindest){
                    if(poll[0] < bc[0]){
                        bc = poll;
                        mindest = poll[2];
                    }else if(poll[0] == bc[0]){
                        if(poll[1] < bc[1]){
                            bc = poll;
                            mindest = poll[2];
                        }
                    }
                }
            }
            for(int d=0 ; d<dr.length ; d++){
                int nr = poll[0]+dr[d];
                int nc = poll[1]+dc[d];
                if(nr>=1 && nr<=N && nc>=1 && nc<=N && !v[nr][nc] && cell[nr][nc] != 3){
                    v[nr][nc] = true;
                    q.add(new int[]{nr, nc, poll[2]+1});
                }
            }
        }

        people[time].r = bc[0];
        people[time].c = bc[1];
        cell[bc[0]][bc[1]] = 3;

    }

    public static void print(){
        for(int i=1 ; i<=N ; i++){
            for(int j=1 ; j<=N ; j++){
                System.out.print(cell[i][j]+" ");
            }System.out.println();
        }
    }
}