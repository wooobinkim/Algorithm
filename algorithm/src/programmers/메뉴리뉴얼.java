package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class 메뉴리뉴얼 {
	static HashMap<String, Integer> map;

	public static void main(String[] args) {
		String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
		int[] course = {2,3,5};
		
		solution(orders, course);
		
		
	}

	private static void solution(String[] orders, int[] course) {
		map = new HashMap<String, Integer>();
		
		for(int c : course) {
			for(String s : orders) {
				com(s.split(""), new String[c], 0, 0);
			}
		}
		
		int[] max = new int[11];
		for(int c : course) {
			for(String key : map.keySet()) {
				int value = map.get(key);
				if(key.length() == c && value > max[c]) {
					max[c] = value;
				}
			}
		}
		
		ArrayList<String> list = new ArrayList<>();
		for(int c : course) {
			for(String key : map.keySet()) {
				int value = map.get(key);
				if(key.length() == c && value == max[c]) {
					list.add(key);
				}
			}
		}
		
		String[] answer = new String[list.size()];
		for(int i=0 ; i<answer.length ; i++) {
			answer[i] = list.get(i);
		}
		Arrays.sort(answer);
		System.out.println(Arrays.toString(answer));
		
		
		
		
		
	}

	private static void com(String[] arr, String[] sel, int k, int idx) {
		if(k == sel.length) {
			String[] temp = new String[sel.length];
			for(int i=0 ; i<sel.length ; i++) {
				temp[i] = sel[i];
			}
			Arrays.sort(temp);
			
			String s = "";
			for(String ts : temp) {
				s+=ts;
			}
			
			if(!map.containsKey(s)) {
				map.put(s, 1);
			}else {
				map.put(s, map.get(s)+1);
			}
			
			return;
		}
		
		for(int i=idx ; i<arr.length ; i++) {
			sel[k] = arr[i];
			com(arr, sel, k+1, i+1);
		}
		
	}

}
