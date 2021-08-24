package Boj.Gold;

import java.util.Scanner;

public class Q13460 {
    static int N,M;
    static int[][] map;
    public static void main(String[] Args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        for(int i =0; i<N; i++){
            String str = sc.nextLine();
            for(int j=0; j<M; j++){
                switch(str.charAt(j)){
                    case '#':
                        map[i][j] = 10;
                        break;
                    case '.':
                        map[i][j] = 0;
                        break;
                    case 'R':
                        map[i][j] = 1;
                        break;
                    case 'B':
                        map[i][j] = 2;
                        break;
                    case '0':
                        map[i][j] = 9;
                        break;
                }
            }
        }
        System.out.print(map);



    }
}
