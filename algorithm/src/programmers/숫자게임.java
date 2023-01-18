import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = -1;

        Arrays.sort(A);
        Arrays.sort(B);

        int score = 0;
        int k = 0;
        for (int i = 0; i < B.length; i++) {
            for (int j = k; j < A.length; j++) {
                if (B[i] > A[j]) {
                    score += 1;
                    k = j + 1;
                    break;
                } else {
                    break;
                }

            }
        }

        return score;
    }
}