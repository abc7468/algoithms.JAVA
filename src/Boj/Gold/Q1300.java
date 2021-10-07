package Boj.Gold;

import java.util.Scanner;

public class Q1300 {
    public static int binSearch(int n, int m){
        int l =1;
        int r = m;
        int val = 0;
        while(l<=r){
            int mid = (l+r)/2;
            long cnt = 0;
            for(int i =1; i<=n; i++){
                cnt += Math.min(mid/i ,n);
            }
            if(cnt>=m){
                val = mid;
                r = mid-1;
            }else{
                l = mid+1;
            }
        }
        return val;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n,m;
        n = sc.nextInt();
        m = sc.nextInt();

        int answer = binSearch(n, m);
        System.out.println(answer);
    }
}
