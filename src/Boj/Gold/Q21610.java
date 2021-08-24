package Boj.Gold;

import java.util.ArrayList;
import java.util.Scanner;

public class Q21610 {
    /*
    1. 구름 이동하기
    2. 물 뿌리기
    3. 구름 없애기s
    4. 물복사 버그
    5. 구름 만들기
     */

    static class Cloud{
        int row,col;
        Cloud(int row, int col){
            this.row = row;
            this.col = col;
        }
        public void move(int d, int s){
            this.row = checkRange(this.row + dr[d]*s);
            this.col = checkRange(this.col + dc[d]*s);
            sprinkle();
        }
        private int checkRange(int val){
            if(val>N){
                val = val-N;
            }
            if(val<=0){
                val = val+N;
            }
            return val;
        }

        public void sprinkle(){
            mmap[this.row][this.col].amount++;
            mmap[this.row][this.col].delCloud = true;
        }
    }
    static int[] cloudDr = {-1,-1,1,1};
    static int[] cloudDc = {-1,1,-1,1};
    static class Field{
        int amount;
        boolean delCloud;
        public Field(){
            this.amount = 0;
            this.delCloud = false;
        }


        public void makeCloud(int row, int col){
            if(!delCloud && this.amount>=2){
                clouds.add(new Cloud(row, col));
                this.amount -=2;
            }
            if(delCloud){
                delCloud = false;
            }
        }
        public void copyWater(int row, int col){
            for(int k=0; k<4; k++){
                int nextRow = row+cloudDr[k];
                int nextCol = col+cloudDc[k];
                if(checkRange(nextRow, nextCol) && mmap[nextRow][nextCol].amount!=0){
                    this.amount++;
                }
            }
        }
        private boolean checkRange(int row, int col){
            return row <= N && row > 0 && col <= N && col > 0;
        }
    }

    static ArrayList<Cloud> clouds = new ArrayList<>();
    static Field[][] mmap;
    static int[] dr = {0,-1,-1,-1,0,1,1,1};
    static int[] dc = {-1,-1,0,1,1,1,0,-1};
    static int N,M;



    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        clouds.add(new Cloud(N,1));
        clouds.add(new Cloud(N,2));
        clouds.add(new Cloud(N-1,1));
        clouds.add(new Cloud(N-1,2));
        mmap = new Field[N+1][N+1];
        for(int i=1; i<=N; i++){
            for(int j =1; j<=N; j++){
                mmap[i][j] = new Field();
            }
        }
        for(int r =1; r<=N; r++){
            for(int c=1; c<=N; c++){
                mmap[r][c].amount = sc.nextInt();
            }
        }
        for(int t =0; t<M; t++){
            int d, s;
            d = sc.nextInt()-1;
            s = sc.nextInt()%N;
            for (Cloud cloud : clouds) {
                cloud.move(d, s);
            }
            clouds.clear();
            for(int r = 1; r<=N; r++){
                for(int c = 1; c<=N; c++){
                    if (mmap[r][c].delCloud){
                        mmap[r][c].copyWater(r,c);
                    }
                }
            }
            for(int r = 1; r<=N; r++){
                for(int c = 1; c<=N; c++){
                    mmap[r][c].makeCloud(r,c);
                }
            }

        }
        int answer = 0;
        for(int r = 1; r<=N; r++){
            for(int c = 1; c<=N; c++){
                answer += mmap[r][c].amount;
            }
        }
        System.out.println(answer);
    }
}
