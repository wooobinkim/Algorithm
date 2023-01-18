import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};

        PriorityQueue<Integer> q = new PriorityQueue<>();
        PriorityQueue<Integer> rq = new PriorityQueue<>(Collections.reverseOrder());

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < operations.length; i++) {
            String s = operations[i];
            if (s.charAt(0) == 'I') {
                q.add(Integer.parseInt(s.split(" ")[1]));
                rq.add(Integer.parseInt(s.split(" ")[1]));
            } else if (s.equals("D -1")) {
                rq.remove(q.peek());
                if (!q.isEmpty())
                    q.poll();
            } else if (s.equals("D 1")) {
                if (!q.isEmpty())
                    q.remove(rq.poll());
            }

        }

        answer = new int[2];
        answer[1] = q.peek() == null ? 0 : q.peek();
        answer[0] = rq.peek() == null ? 0 : rq.peek();
        // System.out.println(q.poll());

        return answer;
    }
}