package Boj.Gold;

import java.io.*;
import java.util.Arrays;

public class Q2470 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.valueOf(br.readLine());
        int[] mix = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int ans1= 0;
        int ans2 = 0;
        Arrays.sort(mix);
        int left = 0;
        int right = n-1;
        int gap=2000000000;
        int tmp;
        while(left<right){
            tmp = mix[left]+mix[right];
            if(gap>Math.abs(tmp)) {
                gap = Math.abs(tmp);
                ans1 = mix[left];
                ans2 = mix[right];
            }
            if(tmp >0){
                right--;
            }else{
                left++;
            }
        }
        System.out.println(ans1+" "+ans2);


        bw.flush();
        bw.close();
    }

}
