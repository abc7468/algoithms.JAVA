package Boj.Silv;

import java.util.Scanner;

public class Q6236 {
    public static int binSearch(int max, int check, int[] money){
        int l = max-1;
        int r = 1000000001;
        while(l+1<r){
            int mid = (l+r)/2;
            int cnt = 0;
            int tmp = 0;
            for (int j : money) {
                tmp += j;
                if (tmp > mid) {
                    cnt++;
                    tmp = j;
                }

            }
            if (cnt >= check) {
                l = mid;
            } else r = mid;

        }
        return r;

    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n, m;
        n = sc.nextInt();
        m = sc.nextInt();
        int max = 0;
        int[] money = new int[n];
        for(int i =0; i<n; i++){
            money[i] = sc.nextInt();
            if(max<money[i])max = money[i];
        }
        System.out.println(binSearch(max, m,money));

    }
}
