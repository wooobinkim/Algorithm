package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class 올바른괄호 {

	public static void main(String[] args) {
		String s = "(()()";
		
		Queue<Character> q = new LinkedList<Character>();
		for(int i=0 ; i<s.length(); i++) {
			q.add(s.charAt(i));
		}
		
		boolean answer = false;
		
		for (int i = 0; i < q.size(); i++) {
			char c = q.poll();
			
			if(i==0 && c==')') break;
			
			
		}
		
		

	}

}
