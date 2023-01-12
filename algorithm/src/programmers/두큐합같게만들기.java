package programmers;

import java.util.*;

public class 두큐합같게만들기 {
	static class Node implements Comparable<Node>{

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}

	public static void main(String[] args) {
		
		String s = "ABC";
		s.toLowerCase();
		char c = 'A';
		System.out.println(s.toLowerCase());
		
		int[][] arr = {
				{1,1,0},
				{0,1,0},
				{0,1,1}
		};
		
		
		print(arr);
		
		int[][] arr2 = new int[arr.length][arr[0].length];
		for(int i=0 ; i<arr2.length ; i++) {
			for(int j=0 ; j<arr2[i].length ; j++) {
				arr2[i][j] = arr[ arr.length-1-j ][i];
			}
		}
		System.out.println();
		print(arr2);
		
	}

	private static void print(int[][] arr) {
		for(int i=0 ; i<arr.length ; i++) {
			for(int j=0 ; j<arr[i].length ; j++) {
				System.out.print(arr[i][j]+" ");
			}System.out.println();
		}
		
	}

}
