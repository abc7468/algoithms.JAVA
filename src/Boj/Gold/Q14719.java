package Boj.Gold;

import java.util.Scanner;

public class Q14719 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int height = sc.nextInt();
        int width = sc.nextInt();
        int[] map = new int[width+2];
        int[] left = new int[width+2];
        int[] right= new int[width+2];
        left[0] = 0;
        right[width+1]=0;
        for(int i =1; i<=width; i++){
            map[i] = sc.nextInt();
        }
        for(int i =1; i<=width+1; i++){
            left[i] = Math.max(map[i], left[i-1]);
        }
        for(int i = width; i>=0; i--){
            right[i] = Math.max(map[i], right[i+1]);
        }
        int answer = 0;
        for(int i =1; i<width+1; i++){
            int standard = Math.min(left[i],right[i]);
            answer += standard-map[i];
        }
        System.out.println(answer);
    }

}
