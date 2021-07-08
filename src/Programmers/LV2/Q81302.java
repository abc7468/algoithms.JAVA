package Programmers.LV2;

import java.util.*;
public class Q81302 {

    public static void main(String[] args) {

        String[][] str1 =
                {
                        {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                        {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                        {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                        {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                        {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
                };


        int[] result = solution(str1);
        for(int ans : result) {
            System.out.println(ans);
        }
    }



    static final int MAX = 5;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static Queue<Pos> posArray = new LinkedList<>();
    //좌표를 저장할 class
    public static class Pos{
        int x,y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    //bfs를 통해 맨해튼거리가 2이하인 상태의 강의실을 찾음
    public static int bfs(Pos pos, String[] map) {
        int[][] visited = new int[5][5];
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(pos.x, pos.y));
        visited[pos.x][pos.y] = 1;
        while(!q.isEmpty()) {
            Pos nowPos = q.poll();
            int nowX = nowPos.x;
            int nowY = nowPos.y;

            for(int k =0; k<4; k++) {
                int nextX = nowX+dx[k];
                int nextY = nowY+dy[k];

                if(nextX>=0 && nextX<5 && nextY>=0 && nextY<5) {
                    char state = map[nextY].charAt(nextX);
                    if(visited[nextX][nextY]==0 && state=='O' ) {
                        if(visited[nowX][nowY]<4) {
                            q.add(new Pos(nextX, nextY));
                            visited[nextX][nextY]=visited[nowX][nowY]+1;
                        }
                    }
                    else if(visited[nextX][nextY]==0 && state=='P' ) {
                        if(visited[nowX][nowY]<3) {
                            return 0;
                        }
                    }
                }
            }
        }
        return 1;

    }


    public static int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int i=0; i<5; i++) {
            for(int j =0; j<5; j++) {
                for(int k =0; k<5; k++) {
                    if(places[i][j].charAt(k)=='P') {
                        posArray.add(new Pos(k,j));
                    }
                }
            }
            if(posArray.isEmpty()) {
                answer[i] = 1;
                continue;
            }
            while(!posArray.isEmpty()) {
                if(bfs(posArray.poll() ,places[i])==1) {
                    answer[i] = 1;
                    continue;
                }
                else {
                    answer[i] = 0;
                    break;
                }
            }

            while(!posArray.isEmpty()) {
                posArray.poll();
            }
        }

        return answer;
    }


}
