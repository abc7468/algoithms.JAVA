package Boj.Gold;

import java.util.Scanner;

public class Q9251 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String str1 = scan.nextLine();
        String str2 = scan.nextLine();
        int str1Size = str1.length();
        int str2Size = str2.length();
        int[][] dp = new int[str1Size+1][str2Size+1];
        //LCS
        for(int i =1; i<=str1Size;i++){
            for(int j =1; j<=str2Size;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        System.out.println(dp[str1Size][str2Size]);

    }
}
