package Boj.Silv;

import java.util.Scanner;

public class Q13702 {
    public static long binSearch(int[] al, int maxVal, int want){
        long l = 0;
        long r = maxVal+1;
        int size = al.length;
        while(l+1<r){
            long tmp = 0;
            long mid = (l+r)/2;
            for(int i=0; i<size; i++){
                tmp+=al[i]/mid;
            }
            if(tmp>=want){
                l = mid;
            }else{
                r = mid;
            }
        }
        return l;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] al;
        int maxVal = 0;
        int n, m;
        n = sc.nextInt();
        m = sc.nextInt();
        al = new int[n];
        long total = 0;
        for(int i =0; i<n; i++){
            int a = sc.nextInt();
            if(maxVal<a){
                maxVal = a;
            }
            total+=a;
            al[i] = a;
        }
        if(total==0){
            System.out.println("0");
            return;
        }
        long answer = binSearch(al, maxVal, m);

        System.out.println(answer);
    }
}
