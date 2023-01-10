package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class 순위검색 {
	static HashMap<String, List<Integer>> map;

	public static void main(String[] args) {
		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		solution(info, query);

	}
	
	public static int[] solution(String[] info, String[] query) {
		int[] answer = {};
		map = new HashMap<>();
		
		for (int i = 0; i < info.length; i++) {
			String[] p = info[i].split(" ");
			
			com(p, 0, "");
		}
		
		for( String key:map.keySet()) {
			Collections.sort(map.get(key));
		}
		print();
		
		for (int i = 0; i < query.length; i++) {
			query[i] = query[i].replace(" and ", "");
			String[] q = query[i].split(" ");
			
			if(map.containsKey(q[0])) {
				answer[i] = binarySearch(q[0], Integer.parseInt(q[1]));
			}else {
				answer[i] = 0;
			}
		}
		
		
		return answer;
	}

	private static int binarySearch(String key, int score) {
		ArrayList<Integer> list = (ArrayList<Integer>) map.get(key);
		int start = 0;
		int end = list.size()-1;
		
		while(start <= end) {
			int mid = (start+end)/2;
			if(list.get(mid) < score) {
				start = mid+1;
			}else {
				end = mid-1;
			}
		}
		
		return list.size() - start;
		
		
	}

	private static void print() {
		for (String key : map.keySet()) {
			ArrayList<Integer> value = (ArrayList<Integer>)map.get(key);
			System.out.print("key : " + key);
			System.out.print(", value : ");
			for (int i : value) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
		
	}

	private static void com(String[] p, int k, String s) {
		if(k==4) {
			if(!map.containsKey(s)) {
				map.put(s, new ArrayList<Integer>());
			}
			
			map.get(s).add(Integer.parseInt(p[4]));
			
			return;
		}
		
		com(p, k+1, s+p[k]);
		com(p, k+1, s+"-");
	}

}
