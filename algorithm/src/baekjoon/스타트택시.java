package baekjoon;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class 스타트택시 {
	static int[][] cell;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static Person[] personList;
	static Taxi taxi;
	static int answer = 0;
	static int finish = 0;
	
	static class Person {
		int pr;
		int pc;
		int dr;
		int dc;
		boolean isFinish;
		
		public Person() {
			
		}
		
		public Person(int pr, int pc, int dr, int dc){
			this.pr = pr;
			this.pc = pc;
			this.dr = dr;
			this.dc = dc;
			this.isFinish = false;
		}
		
		@Override
		public String toString() {
			return "person -> [pr : " + pr + ", pc : "+pc+", dr : "+dr+", dc : "+dc+", isFinish : "+isFinish+"]";
		}
		
	}
	
	static class Taxi {
		int tr;
		int tc;
		int fuel;
		
		public Taxi(int tr, int tc, int fuel) {
			this.tr = tr;
			this.tc = tc;
			this.fuel = fuel;
		}
		
		@Override
		public String toString() {
			return "Taxi -> [tr : "+tr+", tc : "+tc+", fuel : "+fuel+"]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("스타트택시.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int startFuel = Integer.parseInt(st.nextToken());
		
		cell = new int[N+1][N+1];
		for(int i=1 ; i<cell.length ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1 ; j<cell.length ; j++) {
				cell[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken());
		int sc = Integer.parseInt(st.nextToken());
		
		taxi = new Taxi(sr, sc, startFuel);
		System.out.println(taxi);
		
		personList = new Person[M];
		for(int i=0 ; i<personList.length ; i++) {
			st = new StringTokenizer(br.readLine());
			personList[i] = new Person(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
					,Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
		}
		
		while(finish != personList.length) {
			
			int minPerson = personDfs();
			
			if(answer == -1) {
				System.out.println(answer);
				break;
			}
			else {
				System.out.println(personList[minPerson]);
				System.out.println(taxi);
				
				dfs(minPerson);
				if(answer == -1) {
					System.out.println(answer);
					break;
				}
			}
			System.out.println(finish);
			System.out.println();
			
			System.out.println(taxi.fuel);
			
		}
		

	}
	public static void dfs(int personIndex) {
		Person person = personList[personIndex];
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] v = new boolean[cell.length+1][cell[0].length+1];
		q.add(new int[] {taxi.tr, taxi.tc, 0, taxi.fuel});
		v[taxi.tr][taxi.tc] = true;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			
			if(p[0] == person.dr && p[1] == person.dc) {
				if(p[3] < 0) answer = -1;
				else {
					finish+=1;
					personList[personIndex].isFinish = true;
					taxi.tr = p[0];
					taxi.tc = p[1];
					taxi.fuel = taxi.fuel + (p[2]-1);
				}
				break;
			}
			
			
			for(int d=0 ; d<dr.length ; d++) {
				int nr = p[0]+dr[d];
				int nc = p[1]+dc[d];
				if(nr>=1 && nr<cell.length && nc>=1 && nc<cell.length && !v[nr][nc] && cell[nr][nc] != 1) {
					v[nr][nc] = true;
					q.add(new int[] {nr,nc,p[2]+1,p[3]-1});
				}
			}
			
		}
		
		return ;
	}
	
	
	public static int personDfs() {
		Person person = new Person();
		int personIndex = -1;
		int minDest = 1000000;
		int taxiFuel = taxi.fuel;
		int useFuel = 0;
		
		for(int i=0 ; i<personList.length ; i++) {
			if(personList[i].isFinish) continue;
			
			Queue<int[]> q = new LinkedList<int[]>();
			boolean[][] v = new boolean[cell.length+1][cell[0].length+1];
			q.add(new int[] {taxi.tr, taxi.tc, 0});
			v[taxi.tr][taxi.tc] = true;
			
			while(!q.isEmpty()) {
				int[] p = q.poll();
				if(p[0] == personList[i].pr && p[1] == personList[i].pc) {
					p[2] += 1;
					if(taxi.fuel - p[2] >= 0) {
						break;
					}
					
					if(p[2] < minDest) {
						person = personList[i];
						personIndex = i;
						useFuel = p[2];
					}else if(p[2] == minDest) {
						if(p[0] < person.dr) {
							person = personList[i];
							personIndex = i;
							useFuel = p[2];
						}else if(p[0] == person.dr) {
							if(p[1] < person.dc) {
								person = personList[i];
								personIndex = i;
								useFuel = p[2];
							}
						}
					}
					
					break;
				}
				
				for(int d=0 ; d<dr.length ; d++) {
					int nr = p[0]+dr[d];
					int nc = p[1]+dc[d];
					
					if(nr>=1 && nr<cell.length && nc>=1 && nc<cell.length && !v[nr][nc] && cell[nr][nc] != 1) {
						v[nr][nc] = true;
						q.add(new int[] {nr,nc,p[2]+1});
					}
				}
				
				
			}
			
			
			
		}
		
		
		if(person.pr == 0 && person.pc == 0) answer = -1;
		else {
			taxi.tr = person.pr;
			taxi.tc = person.pc;
			taxi.fuel -= useFuel;
		}
		
		return personIndex;
	}
	

	private static void print(int[][] cell2) {
		for(int i=1 ; i<cell2.length ; i++) {
			for(int j=1 ; j<cell2[i].length ; j++) {
				System.out.print(cell2[i][j]+" ");
			}System.out.println();
		}
		
	}

}
