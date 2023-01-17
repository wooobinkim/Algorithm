import java.util.*;
import java.io.*;


public class Main
{
    static int N,M;
    static char[][] cell;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int sr = 0;
        int sc = 0;

        cell = new char[N][M];
        for(int i=0 ; i<N ; i++){
            String s = br.readLine();
            for(int j=0 ; j<M ; j++){
                cell[i][j] = s.charAt(j);
                if(cell[i][j]=='W') {
                    sr = i;
                    sc = j;
                }
            }
        }

        dfs(sr, sc);
    }

    public static void dfs(int sr, int sc){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] v = new boolean[N][M];
        q.add(new int[]{sr,sc,0});

        while(!q.isEmpty()){
            rain();
            int size = q.size();
            for(int k=0 ; k<size ; k++){
                int[] p = q.poll();

                if(cell[p[0]][p[1]] == 'H'){
                    System.out.print(p[2]);
                    return;
                }

                for(int d=0 ; d<dr.length ; d++){
                    int nr = p[0]+dr[d];
                    int nc = p[1]+dc[d];
                    if(nr>=0 && nr<N && nc>=0 && nc<M && !v[nr][nc] && cell[nr][nc] != 'X'
                            && cell[nr][nc] != '*'){
                        v[nr][nc] = true;
                        q.add(new int[]{nr,nc,p[2]+1});
                    }
                }
            }

        }

        System.out.print("FAIL");
    }

    public static void rain(){
        boolean[][] v = new boolean[N][M];
        for(int i=0 ; i<N ; i++){
            for(int j=0 ; j<M ; j++){
                if(!v[i][j] && cell[i][j] == '*'){
                    v[i][j] = true;
                    for(int d=0 ; d<dr.length ; d++){
                        int nr = i+dr[d];
                        int nc = j+dc[d];
                        if(nr>=0 && nr<N && nc>=0 && nc<M && !v[nr][nc] && cell[nr][nc] == '.'){
                            v[nr][nc] = true;
                            cell[nr][nc] ='*';
                        }
                    }
                }
            }
        }
    }

    public static void print(){
        for(int i=0 ; i<N ; i++){
            for(int j=0 ; j<M ; j++){
                System.out.print(cell[i][j]);
            }System.out.println();
        }
    }
}