package programmers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 나무자르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] tree = new int[n];
		for(int i=0 ; i<n ; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(tree);
		int start = 0;
		int end = tree[tree.length-1];
		
		while(start <= end) {
			int mid = (start+end)/2;
			
			int score = cal(mid, tree);
			if(score >= m) {
				start = mid+1;
			}else {
				end = mid-1;
			}
			
		}
		
		System.out.println(end);

	}

	private static int cal(int mid, int[] tree) {
		int score = 0;
		for (int i = 0; i < tree.length; i++) {
			if(tree[i] > mid) {
				score += tree[i]-mid;
			}
		}
		
		return score;
	}

}
