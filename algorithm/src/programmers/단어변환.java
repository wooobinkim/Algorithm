class Solution {
    static int answer;

    public int solution(String begin, String target, String[] words) {
        answer = 10000000;

        boolean b = false;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(target)) {
                b = true;
                break;
            }
        }
        if (!b)
            return 0;

        trans(begin, target, words, new boolean[words.length], 0);

        return answer;
    }

    public void trans(String begin, String target, String[] words, boolean[] v, int count) {
        if (begin.equals(target)) {
            if (count < answer) {
                answer = count;
            }

            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (!v[i] && isPossible(begin, words[i])) {
                v[i] = true;
                begin = words[i];
                trans(begin, target, words, v, count + 1);
            }
        }

    }

    public boolean isPossible(String begin, String word) {
        int count = 0;
        boolean b = false;

        if (begin.equals(word))
            return false;

        for (int i = 0; i < begin.length(); i++) {
            if (begin.charAt(i) != word.charAt(i))
                count += 1;

            if (count > 1)
                return false;
        }

        return true;
    }
}