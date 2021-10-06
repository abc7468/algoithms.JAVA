package Boj.Silv;

import java.io.*;
import java.util.Arrays;

public class Q2343 {

    public static int findSmall(int[] classes){
        int size = classes.length;
        int max = 0;
        for(int i =0; i<size; i++){
            if(max<classes[i]) max = classes[i];
        }
        return max;
    }
    public static int binary(int[] classes, int classCnt, int bluelayCnt){
        int lo = findSmall(classes);
        int hi = 1000000000;
        int mid=Integer.MAX_VALUE;
        while(lo<=hi){
            int cnt=1;
            mid=(lo+hi)/2;
            long sum=0;
            for(int i=0;i<classCnt;i++){
                if(sum+classes[i]>mid){
                    cnt++;
                    sum=0;
                }
                sum+=classes[i];
            }

            if(cnt<=bluelayCnt)
                hi=mid-1;
            else
                lo=mid+1;
        }
        return lo;
    }
    public static void main(String[] args)throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] classes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int answer = binary(classes, nm[0], nm[1]);
        System.out.println(answer);

    }
}
