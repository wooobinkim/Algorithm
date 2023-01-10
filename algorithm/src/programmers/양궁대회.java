package programmers;

import java.util.Arrays;

public class 양궁대회 {

	public static void main(String[] args) {
		int[] info = {2,1,1,1,0,0,0,0,0,0,0};
		int n = 5;
		
		boolean[] sel = new boolean[info.length];
        com(sel, 0, 0, n, info);
        

	}

	public static void com(boolean[] sel, int k, int index, int n, int[] info){
        if(sel.length == index){
            int result = gameCal(sel, n, info);
        }
        
        sel[k] = true;
        com(sel, k+1, index+1, n, info);
        sel[k] = false;
        com(sel, k+1, index+1, n, info);
    }
    
    public static int gameCal(boolean[] sel, int n, int[] info){
        int ryan = 0;
        int apeach = 0;
        
        for(int i=0 ; i<sel.length ; i++){
            //라이언이 이겼으면
            if(sel[i]){
                n = n-(info[i]+1);
                if(n < 0) return -1;
                ryan += (10-i);
            }
            //라이언이 졌으면
            else if(!sel[i] && info[i] > 0){
                apeach += (10-i);
            }
        }
        
        if(ryan > apeach) return (ryan - apeach);
        if(ryan <= apeach) return -1;
        
        return -1;
    }

	
}
