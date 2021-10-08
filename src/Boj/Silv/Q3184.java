package Boj.Silv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q3184 {
    static int R,C,totalWolf,totalSheep;
    static boolean visited[][];
    static char map[][];
    static int[] dr = {0,-1,0,1};
    static int[] dc = {-1,0,1,0};
    public static class Pos{
        int r;
        int c;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void bfs(int row, int col){
        visited[row][col] = true;
        Queue<Pos> q = new LinkedList<>();
        int[] total = {0,0};
        q.add(new Pos(row, col));
        while(!q.isEmpty()){
            Pos nowP = q.poll();
            int nowR = nowP.r;
            int nowC = nowP.c;
            if(map[nowR][nowC] == 'o') total[0]++;
            else if(map[nowR][nowC] == 'v') total[1]++;
            for(int k = 0; k<4; k++){
                int nextRow = nowR+dr[k];
                int nextCol = nowC+dc[k];
                if(isRange(nextRow,nextCol)&&!visited[nextRow][nextCol]&&map[nextRow][nextCol]!='#'){
                    visited[nextRow][nextCol] = true;
                    q.add(new Pos(nextRow, nextCol));
                }
            }
        }
        if(total[0]>total[1]){
            totalSheep += total[0];
        }
        else{
            totalWolf += total[1];
        }


    }
    public static boolean isRange(int row, int col){
        return row<R && row>=0&& col<C && col>=0;
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());


        visited = new boolean[R][C];
        totalSheep = 0;
        totalWolf = 0;
        map = new char[R][C];
        for (int i = 0; i < R; i++)
            map[i] = br.readLine().toCharArray();
        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(!visited[r][c] && map[r][c] != '#') {
                    bfs(r,c);
                }
            }
        }
        System.out.printf("%d %d", totalSheep, totalWolf);

    }

}
