package Programmers.LV3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Q72413 {
    static final int MAX = Integer.MAX_VALUE;

    static class Node{

        int to;
        int charge;
        public Node(int to, int charge){

            this.to = to;
            this.charge = charge;
        }
    }
    static int[][] map;
    static int[][] dij;
    static boolean[] visited;
    static ArrayList<ArrayList<Node>> nodes = new ArrayList<>();
    static void dijk(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return (o1.charge > o2.charge) ? 1 : -1;
            }
        });
        int[] dp = map[start];
        int size = map.length;
        for(int i =1; i<size; i++){
            visited[i] = false;
        }
        visited[start] = true;
        for(int i =1; i< size; i++){
            if(dp[i]!=0 &&dp[i]!= MAX){
                pq.add(new Node(i, dp[i]));
            }
        }
        while(!pq.isEmpty()){
            Node nowNode = pq.poll();
            int now = nowNode.to;;
            int charge = nowNode.charge;
            if(charge>dp[now]) continue;
            visited[now] = true;

            for(Node node : nodes.get(now)){
                if(!visited[node.to] && map[now][node.to]!=MAX && map[now][node.to]!=0 ){
                    if(dp[now]+map[now][node.to]<dp[node.to]){
                        dp[node.to] = dp[now]+map[now][node.to];
                        pq.add(new Node(node.to, dp[node.to]));
                    }
                }
            }
        }
        dij[start] = dp;

    }
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        visited = new boolean[n+1];
        map = new int[n+1][n+1];
        dij = new int[n+1][n+1];
        for(int i =1; i<=n; i++){
            for(int j =1; j<=n; j++){
                if(i==j) {
                    map[i][j] = 0;
                    continue;
                }
                map[i][j] = MAX;
            }
        }
        for(int i =0 ; i<=n; i++){
            nodes.add(new ArrayList<>());
        }
        for(int[] fare : fares){
            int from = fare[0];
            int to = fare[1];
            int charge = fare[2];
            map[from][to] = charge;
            map[to][from] = charge;
            nodes.get(from).add(new Node(to, charge));
            nodes.get(to).add(new Node(from, charge));
        }
        for(int i =1; i<=n; i++){
            dijk(i);
        }
        int min = MAX;
        for(int i =1; i<=n; i++){
            int tmp = dij[s][i];
            tmp+=dij[i][a]+dij[i][b];
            if(tmp<min){
                min = tmp;
            }
        }

        return min;
    }


    public static void main(String[] args){
        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
//        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50},{2, 4, 66},{2, 3, 22},{1, 6, 25}}
        int[][] fares = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}};
        int answer = solution(7,3,4,1,fares);
        System.out.println(answer);
    }


}
