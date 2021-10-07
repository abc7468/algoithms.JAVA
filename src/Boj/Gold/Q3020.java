package Boj.Gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q3020 {
    public static int binSearch(int width, int[] stone, int val){
        int l = 0;
        int r = width/2-1;
        while(l<=r){
            int mid = (l+r)/2;
            if(stone[mid]>=val) r = mid-1;
            else l = mid+1;
        }
        return width/2-l;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] up;
        int[] down;

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        up = new int[N / 2];
        down = new int[N / 2];

        for(int i=0; i<N; i++) {
            int num=Integer.parseInt(br.readLine());
            if(i%2==0) down[i/2]=num;
            else up[i/2]=num;
        }

        Arrays.sort(up);
        Arrays.sort(down);
        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for(int i =1; i<=H; i++){
            int downCrash = binSearch(N,down,i);
            int upCrash = binSearch(N,up,H-i+1);

            if(min>downCrash+upCrash){
                cnt = 1;
                min = downCrash+upCrash;
            }
            else if(min==downCrash+upCrash){
                cnt++;
            }
        }
        System.out.print(min + " " + cnt);
    }
}
