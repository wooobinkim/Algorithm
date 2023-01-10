import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1] - o2[1];
            }
            
        });
     
        int min = -30000000;
        
        for(int i=0 ; i<routes.length ; i++){
            if(routes[i][0] > min){
                answer+=1;
                min = routes[i][1];
            }
        }
        
        
        return answer;
    }
}