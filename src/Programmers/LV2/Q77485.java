package Programmers.LV2;

public class Q77485 {
    public static void main(String[] args){
        int rows = 3;
        int columns = 3;
//        int[][] queries = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};
        int[][] queries = 	{{1,1,2,2},{1,2,2,3},{2,1,3,2},{2,2,3,3}};
        int[] result = solution(rows, columns, queries);
        for(int i : result){
            System.out.println(i);
        }
    }

    static int[][] map;
    static int min = 987654321;
    public static int rightPush(int startWidth, int endWidth, int height){
        int tmp = map[height][endWidth];
        for(int i = endWidth-1; i>=startWidth; i--){
            map[height][i+1] = map[height][i];
            if(map[height][i+1]<min){
                min = map[height][i+1];
            }
        }
        if(tmp<min){
            min = tmp;
        }
        return tmp;

    }
    public static int leftPush(int startWidth, int endWidth, int height, int val){
        int tmp = map[height][startWidth];
        for(int i = startWidth; i<=endWidth-2; i++){
            map[height][i] = map[height][i+1];
            if(map[height][i+1]<min){
                min = map[height][i+1];
            }
        }
        map[height][endWidth-1] = val;
        if(tmp<min){
            min = tmp;
        }
        return tmp;

    }
    public static int downPush(int width, int startHeight, int endHeight, int val){
        int tmp = map[endHeight][width];
        for(int i = endHeight-1; i>=startHeight+1; i--){
            map[i+1][width] = map[i][width];
            if(map[i+1][width]<min){
                min = map[i+1][width];
            }
        }
        map[startHeight+1][width] = val;
        if(tmp<min){
            min = tmp;
        }
        return tmp;

    }
    public static void upPush(int width, int startHeight, int endHeight, int val){
        for(int i = startHeight; i<=endHeight-2; i++){
            map[i][width] = map[i+1][width];
            if(map[i+1][width]<min){
                min = map[i+1][width];
            }
        }
        map[endHeight-1][width] = val;
    }

    public static int rotation(int startWidth, int startHeight, int endWidth, int endHeight){
        min = 987654321;
        int piece1 = rightPush(startWidth, endWidth, startHeight);
        int piece2 = downPush(endWidth, startHeight, endHeight, piece1);
        int piece3 = leftPush(startWidth, endWidth, endHeight, piece2);
        upPush(startWidth, startHeight, endHeight, piece3);
        return min;
    }

    public static int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows+1][columns+1];
        int val = 1;
        for(int y = 1; y<=rows; y++){
            for(int x = 1; x<=columns; x++){
                map[y][x] = val++;
            }
        }




        for(int y = 1; y<=rows; y++){
            for(int x = 1; x<=columns; x++){
                System.out.print(map[y][x]);
            }
            System.out.println("");
        }

        int qSize = queries.length;
        int[] answer = new int[qSize];

        for(int i =0; i<qSize; i++){
            int tmp = rotation(queries[i][1],queries[i][0],queries[i][3],queries[i][2]);
            answer[i] = tmp;
        }



        return answer;
    }


}
