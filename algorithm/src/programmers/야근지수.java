import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < works.length; i++) {
            pq.add(works[i]);
        }

        boolean flag = false;
        while (n > 0) {
            int num = pq.poll() - 1;
            if (num < 0) {
                flag = true;
                break;
            }
            pq.add(num);
            n -= 1;
        }

        if (flag)
            return 0;
        else {
            while (!pq.isEmpty()) {
                int num = pq.poll();
                answer += (num * num);
            }

            return answer;
        }
    }
}