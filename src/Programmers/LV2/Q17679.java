package Programmers.LV2;

import java.util.ArrayList;

public class Q17679 {
    //터질 블록들의 왼쪽 윗부분을 저장해 놓는다.
    //2x2터트린다
    //빈자리 메꾼다
    static ArrayList<int[]> pangTmp = new ArrayList<>();
    static char[][] map;
    static int[] dy = {0,1,0,1};
    static int[] dx = {0,0,1,1};
    static boolean[] ok;
    static int cnt = 0;
    public static void pang(){
        for(int[] pos : pangTmp){
            for(int k =0; k<4; k++){
                if(map[pos[0]+dy[k]][pos[1]+dx[k]]!='.'){
                    cnt++;
                    map[pos[0]+dy[k]][pos[1]+dx[k]] = '.';
                }
            }
        }
    }
    public static void sortBlock(int m, int n){
        for(int i = m-1; i>=0; i--){
            for(int j =0; j<n; j++){
                if(ok[j]){
                    continue;
                }
                if(map[i][j]=='.'){
                    int row = i;
                    while(true){
                        row--;
                        if(row < 0){
                            ok[j] = true;
                            break;
                        }
                        if(map[row][j]!='.'){
                            map[i][j] = map[row][j];
                            map[row][j] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }
    public static boolean findPang(int row, int col, char std){
        return map[row][col+1] == std && map[row+1][col] == std && map[row+1][col+1]==std;
    }

    public static int solution(int m, int n, String[] board) {
        map = new char[m][n];
        ok = new boolean[n];
        for(int i =0; i<m; i++){
            map[i] = board[i].toCharArray();
        }
        while(true) {
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    char std = map[i][j];
                    if (map[i][j] != '.' && findPang(i, j, std)) {
                        pangTmp.add(new int[]{i, j});
                    }
                }
            }
            if(pangTmp.isEmpty()) break;
            pang();
            sortBlock(m,n);
            for(int i =0; i<n; i++){
                ok[i] = false;
            }
            pangTmp.clear();
        }
        return cnt;
    }

    public static void main(String[] args){
        int m = 6; //row
        int n = 6; //col
        String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        solution(m, n, board);
    }
}
