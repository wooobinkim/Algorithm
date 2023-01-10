package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class 감시 {
	static int N,M;
	static int answer;
	static int[][][] dr = {
			{},
			{{-1},{1},{0},{0}},
			{{-1,1},{0,0}},
			{{-1,0},{-1,0},{1,0},{1,0}},
			{{-1,0,0},{1,0,0},{-1,1,0},{-1,1,0}},
			{{-1,1,0,0}}
		};
	static int[][][] dc = {
			{},
			{{0},{0},{-1},{1}},
			{{0,0},{-1,1}},
			{{0,-1},{0,1},{0,-1},{0,1}},
			{{0,-1,1},{0,-1,1},{0,0,-1},{0,0,1}},
			{{0,0,-1,1}}
		};
	
	public static void main(String[] args) throws Exception{
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer = 100000000;
		
		int[][] cell = new int[N][M];
		ArrayList<int[]> list = new ArrayList<>();
		
		for(int i=0 ; i<cell.length ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0 ; j<cell[i].length ; j++) {
				cell[i][j] = Integer.parseInt(st.nextToken());
				if(cell[i][j] != 0 && cell[i][j] != 6) list.add(new int[] {i,j});
			}
		}
		
		recursive(0,0,list,cell);
		System.out.println(answer);

	}
	
	public static void recursive(int sharp, int idx, ArrayList<int[]> list, int[][] cell) {
		if(idx == list.size()) {
			
			int site = 0;
			for(int i=0 ; i<cell.length ; i++) {
				for(int j=0 ; j<cell[i].length ; j++) {
					if(cell[i][j] == 0) site+=1;
				}
			}
			
			if(site < answer) answer = site;
			
			return;
		}
		
		int r = list.get(idx)[0];
		int c = list.get(idx)[1];
		int cctv = cell[r][c];
		
		for(int k=0 ; k<dr[cctv].length ; k++) {
			
			for(int d=0 ; d<dr[cctv][k].length ; d++) {
				r=list.get(idx)[0];
				c=list.get(idx)[1];
				while(true) {
					int nr = r+dr[cctv][k][d];
					int nc = c+dc[cctv][k][d];
					if(nr>=0 && nr<N && nc>=0 && nc<M) {
						if(cell[nr][nc] != 6) {
							r = nr;
							c = nc;
							if(cell[nr][nc] >= 7) cell[nr][nc]+=1;
							if(cell[nr][nc] == 0) cell[nr][nc] = 7;
							
						}
						else if(cell[nr][nc] == 6) {
							break;
						}
					}else {
						break;
					}
				}
				
			}
			
			recursive(sharp, idx+1, list , cell);
			cell = back(list, idx, cell, k);
		}
			
		
	}
	
	private static int[][] back(ArrayList<int[]> list, int idx, int[][] cell, int k) {
		int r = list.get(idx)[0];
		int c = list.get(idx)[1];
		int cctv = cell[r][c];
		
			for(int d=0 ; d<dr[cctv][k].length ; d++) {
				r=list.get(idx)[0];
				c=list.get(idx)[1];
				while(true) {
					int nr = r+dr[cctv][k][d];
					int nc = c+dc[cctv][k][d];
					if(nr>=0 && nr<N && nc>=0 && nc<M) {
						if(cell[nr][nc] == 7) {
							r = nr;
							c = nc;
							cell[nr][nc] = 0;
							
						}
						else if(cell[nr][nc] >= 8) {
							cell[nr][nc] -= 1;
							r = nr;
							c = nc;
						}
						else if(cell[nr][nc] == 6) {
							break;
						}else {
							r = nr;
							c = nc;
						}
					}else {
						break;
					}
				}
			}
		
		return cell;
	}

	public static void print(int[][] cell){
		for(int i=0 ; i<cell.length ; i++) {
			for(int j=0 ; j<cell[i].length ; j++) {
				System.out.print(cell[i][j]+" ");
			}System.out.println();
		}
	}

}
