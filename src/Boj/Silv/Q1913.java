package Boj.Silv;

import java.util.Scanner;

public class Q1913 {
    static int[][] map;
    static int value = 2;
    static int left;
    static int right;
    static int up;
    static int down;
    static int row;
    static int col;
    static int val;
    public static void goUp(){
        up--;
        if(up == -1) up = 0;
        for(int i =down-1; i>=up; i--){
            if(val == value){
                row = i;
                col = left;
            }
            map[i][left] = value++;
        }
    }
    public static void goRight(){
        right++;
        for(int i = left+1; i<=right; i++){
            if(val == value){
                row = up;
                col = i;
            }
            map[up][i] = value++;
        }
    }
    public static void goDown(){
        down++;
        for(int i = up+1; i<=down; i++){
            if(val == value){
                row = i;
                col = right;
            }
            map[i][right] = value++;
        }
    }
    public static void goLeft(){
        left--;
        for(int i = right-1; i>=left; i--){
            if(val == value){
                row = down;
                col = i;
            }
            map[down][i] = value++;

        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        val = sc.nextInt();
        map = new int[size][size];
        left = right = up = down = size/2;
        if(val==1){
            row = size/2;
            col = size/2;
        }
        map[left][left] = 1;
        goUp();
        for(int i=0; i<size/2; i++){
            goRight();
            goDown();
            goLeft();
            goUp();
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        sb.append(row + 1).append(" ").append(col + 1);
        System.out.println(sb.toString());
    }
}
