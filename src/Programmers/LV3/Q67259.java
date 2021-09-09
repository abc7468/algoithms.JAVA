package Programmers.LV3;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Q67259 {
    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,1,0,-1};
    static Board[][] distanceBoard;
    static final int MAX = Integer.MAX_VALUE;


    static class Board{
        int r;
        int c;
        int[] dis = new int[2];
        int dir; // dir : 1 가로 2 세로 3 상관없음
        public Board(int r, int c, int dis, int dir){
            this.r = r;
            this.c = c;
            this.dis[0] = dis;
            this.dis[1] = dis;
            this.dir = dir;
        }

    }
    static void bfs(int size, int[][] board){
        PriorityQueue<Board> pq = new PriorityQueue<>(new Comparator<Board>() {
            @Override
            public int compare(Board o1, Board o2) {
                return Math.min(o1.dis[0],o1.dis[1])<Math.min(o2.dis[0],o2.dis[1])?1:-1;
            }
        });
        distanceBoard[0][0].dir = 3;
        distanceBoard[0][0].dis[0] = 0;
        distanceBoard[0][0].dis[1] = 0;
        pq.add(distanceBoard[0][0]);

        while(!pq.isEmpty()){
            Board now = pq.peek();
            pq.poll();
            int nowR = now.r;
            int nowC = now.c;
            for(int k = 0; k<4; k++){
                int nextR = nowR + dr[k];
                int nextC = nowC + dc[k];
                if(isCorrectRange(nextR, nextC, size, board)){
                    if(isCheaperThenChangeValue(nextR, nextC, now.dis[0], now.dis[1] ,now.dir, k)){
                        pq.add(distanceBoard[nextR][nextC]);
                    }
                }
            }
        }
    }



    static boolean isCheaperThenChangeValue(int r, int c, int dis1, int dis2, int dir, int k){
        boolean isChanged = false;
        if(k%2==1){
            dis1+=100;
            if(distanceBoard[r][c].dis[0]>dis1){
                distanceBoard[r][c].dis[0]=dis1;
                isChanged=true;
            }
            dis2+=600;
            if(distanceBoard[r][c].dis[1]>dis2){
                distanceBoard[r][c].dis[1] = dis2;
                isChanged=true;
            }
        }else{
            dis1+=600;
            if(distanceBoard[r][c].dis[0]>dis1){
                distanceBoard[r][c].dis[0]=dis1;
                isChanged=true;
            }
            dis2+=100;
            if(distanceBoard[r][c].dis[1]>dis2){
                distanceBoard[r][c].dis[1] = dis2;
                isChanged=true;
            }

        }
        if(isChanged){
            return true;
        }
        return false;
    }

    static boolean isCorrectRange(int r, int c, int size, int[][] board){
        return r>=0 && r<size && c>=0 && c<size && board[r][c]==0;
    }

    public static int solution(int[][] board) {
        int answer = 0;
        int size = board.length;
        distanceBoard = new Board[size][size];
        for(int i =0; i<size; i++){
            for(int j =0; j<size; j++){
                distanceBoard[i][j] = new Board(i, j, MAX, 0);
            }
        }
        bfs(size, board);
        return Math.min(distanceBoard[size-1][size-1].dis[0],distanceBoard[size-1][size-1].dis[1]);
    }
    public static void main(String[] args){
        int board[][] = {{0,0,0},{0,0,0},{0,0,0}};
        solution(board);
    }
}
