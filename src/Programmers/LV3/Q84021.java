package Programmers.LV3;

import java.util.ArrayList;
import java.util.List;

public class Q84021 {
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static boolean[][] boardVisited;
    static boolean[][] tableVisited;

    static int upR;
    static int downR;
    static int leftC;
    static int rightC;
    static int cnt;
    static int size;
    static ArrayList<Pos> place = new ArrayList<>();
    static ArrayList<ArrayList<Block>> boardPlaces = new ArrayList<>();
    static ArrayList<ArrayList<Block>> tablePlaces = new ArrayList<>();
    static class Pos{
        int r,c;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static class Block{
        boolean isUse;
        int size;
        int[][][] blocks;
        public Block(int size, int[][] block){
            this.isUse = false;
            this.size = size;
            this.blocks = new int[4][size][size];
            for(int r =0; r<size; r++){
                for(int c =0; c<size; c++) {
                    this.blocks[0][r][c] = block[r][c];
                }
            }
        }
        //돌리기
        public void rotate(){
            for(int i =1; i<4; i++){
                for(int r = 0; r<this.size; r++){
                    for(int c = 0; c<this.size; c++){
                        this.blocks[i][r][c] = this.blocks[i-1][this.size-1-c][r];
                    }
                }
            }
        }
        //빈틈 제거
        public void clean(){
            for(int i =1; i<=3; i++){
                int deleteLine = 0;
                point : for(int j =0; j<this.size; j++){
                    for(int k = 0; k<this.size; k++){
                        if(this.blocks[i][j][k]==1){
                            break point;
                        }
                    }
                    deleteLine++;
                }
                for(int j = deleteLine; j<this.size; j++){
                    for(int k =0; k<this.size; k++){
                        this.blocks[i][j-deleteLine][k] = this.blocks[i][j][k];
                    }
                }
                for(int j = this.size-deleteLine; j<this.size; j++){
                    for(int k =0; k<this.size; k++){
                        this.blocks[i][j][k] = 0;
                    }
                }

            }
            for(int i =1; i<=3; i++){
                int deleteLine = 0;
                point : for(int j =0; j<this.size; j++){
                    for(int k = 0; k<this.size; k++){
                        if(this.blocks[i][k][j]==1){
                            break point;
                        }
                    }
                    deleteLine++;
                }
                for(int j = deleteLine; j<this.size; j++){
                    for(int k =0; k<this.size; k++){
                        this.blocks[i][k][j-deleteLine] = this.blocks[i][k][j];
                    }
                }
                for(int j = this.size-deleteLine; j<this.size; j++){
                    for(int k =0; k<this.size; k++){
                        this.blocks[i][k][j] = 0;
                    }
                }
            }
        }


    }
    static void dfsForBoard(int r, int c, int[][] table){
        cnt++;
        place.add(new Pos(r, c));
        boardVisited[r][c] = true;
        if(upR>r){
            upR = r;
        }
        if(downR<r){
            downR = r;
        }
        if(leftC>c){
            leftC=c;
        }
        if(rightC<c){
            rightC=c;
        }
        int nowR = r;
        int nowC = c;
        for(int k =0; k<4; k++){
            int nextR = nowR+dr[k];
            int nextC = nowC+dc[k];
            if(rangeCheck(nextR, nextC) && !boardVisited[nextR][nextC] && table[nextR][nextC]==0){
                dfsForBoard(nextR,nextC,table);
            }

        }
    }

    static void dfsForTable(int r, int c, int[][] table){
        cnt++;
        place.add(new Pos(r, c));
        tableVisited[r][c] = true;
        if(upR>r){
            upR = r;
        }
        if(downR<r){
            downR = r;
        }
        if(leftC>c){
            leftC=c;
        }
        if(rightC<c){
            rightC=c;
        }
        int nowR = r;
        int nowC = c;
        for(int k =0; k<4; k++){
            int nextR = nowR+dr[k];
            int nextC = nowC+dc[k];
            if(rangeCheck(nextR, nextC) && !tableVisited[nextR][nextC] && table[nextR][nextC]==1){
                dfsForTable(nextR,nextC,table);
            }
        }
    }

    static boolean rangeCheck(int r, int c){
        return r>=0 && r<size && c>=0 && c<size;
    }


    static void init(){
        upR = 50;
        downR = -1;
        leftC = 50;
        rightC = -1;
        cnt = 0;
        place.clear();
    }
    public static int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        size = table.length;
        boardVisited = new boolean[size][size];
        tableVisited = new boolean[size][size];
        for(int i =0; i<7; i++){
            boardPlaces.add(new ArrayList<>());
            tablePlaces.add(new ArrayList<>());
        }

        for(int r=0; r<size; r++){
            for(int c=0; c<size; c++){
                init();
                if(!boardVisited[r][c] && game_board[r][c]==0){
                    dfsForBoard(r,c, game_board);
                    int tmpSize = Math.max(downR-upR, rightC-leftC)+1;
                    int[][] tmp = new int[tmpSize][tmpSize];
                    for(Pos pos : place){
                        tmp[pos.r-upR][pos.c-leftC] = 1;
                    }
                    boardPlaces.get(cnt).add(new Block(tmpSize, tmp));
                }
            }
        }

        for(int r=0; r<size; r++){
            for(int c=0; c<size; c++){
                init();
                if(!tableVisited[r][c] && table[r][c]==1){
                    dfsForTable(r,c, table);
                    int tmpSize = Math.max(downR-upR, rightC-leftC)+1;
                    int[][] tmp = new int[tmpSize][tmpSize];
                    for(Pos pos : place){
                        tmp[pos.r-upR][pos.c-leftC] = 1;
                    }
                    tablePlaces.get(cnt).add(new Block(tmpSize, tmp));
                    for(int i =1; i<7; i++){
                        int tableSize = tablePlaces.get(i).size();
                        for(int j =0; j<tableSize; j++){
                            tablePlaces.get(i).get(j).rotate();
                        }
                    }
                    for(int i =1; i<7; i++){
                        int tableSize = tablePlaces.get(i).size();
                        for(int j =0; j<tableSize; j++){
                            tablePlaces.get(i).get(j).clean();
                        }
                    }
                }
            }
        }

        for(int i =1; i<7; i++){
            for(Block boardBlock : boardPlaces.get(i)){
                int blockSize = boardBlock.size;
                candiTable : for(Block tableBlock : tablePlaces.get(i)){
                    if(blockSize != tableBlock.size || tableBlock.isUse) continue;
                    else{
                        for(int k =0; k<4; k++){
                            int count = 0;
                            candi : for(int r =0; r<blockSize; r++){
                                for(int c =0; c<blockSize; c++){
                                    if(boardBlock.blocks[0][r][c]==tableBlock.blocks[k][r][c] && tableBlock.blocks[k][r][c]==1){
                                        count++;
                                    }else if(boardBlock.blocks[0][r][c]==tableBlock.blocks[k][r][c]){
                                        continue;
                                    }
                                    else{
                                        break candi;
                                    }
                                }
                            }
                            if(count == i){
                                tableBlock.isUse=true;
                                answer = answer+i;
                                break candiTable;

                            }

                        }
                    }
                }
            }
        }


        return answer;
    }








    public static void main(String[] args){
        int[][] game_board = {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}};
        int[][] table = {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}};
//        int[][] game_board = {{0, 0, 0}, {1, 1, 0}, {1, 1, 1}};
//        int[][] table = {{1, 1, 1}, {1, 0, 0}, {0, 0, 0}};
        System.out.println(solution(game_board, table));
    }
}
