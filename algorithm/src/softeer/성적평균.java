import java.util.*;
import java.io.*;


public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] score = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1 ; i<score.length ; i++){
            score[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0 ; i<M ; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            double sum = 0;
            for(int k=s ; k<=e ; k++){
                sum+=(double)score[k];
            }

            System.out.println(String.format("%.2f",sum / (double)(e-s+1)) );
        }
    }
}