package Programmers.LV2;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Q12978 {
    public static void main(String[] args){
        //5, {{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}}, 3
        int N = 5;
        int[][] road = {{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}};
        int K = 3;
        int result = solution(N, road, K);

        System.out.println(result);
    }

    static ArrayList<ArrayList<Edge>> adj;
    static PriorityQueue<Edge> pq;
    static int[] dist;
    public static class Edge implements Comparable<Edge>{
        int to;
        int weight;
        public Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return this.weight-o.weight;
        }
    }

    public static void dijk(){
        while(!pq.isEmpty()){
            Edge nowEdge = pq.poll();
            for(Edge nextEdge : adj.get(nowEdge.to)){
                if(dist[nextEdge.to]>dist[nowEdge.to]+ nextEdge.weight){
                    dist[nextEdge.to] = dist[nowEdge.to]+ nextEdge.weight;
                    pq.add(nextEdge);
                }
            }
        }
    }

    public static int solution(int N, int[][] road, int K) {
        int answer = 0;
        pq = new PriorityQueue<>();
        adj = new ArrayList<>();
        dist = new int[51];
        for(int i =1; i<=N; i++){
            dist[i] = 987654321;
        }
        for(int i =0; i<51; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] tmp : road){
            int now = tmp[0];
            int to = tmp[1];
            int weight = tmp[2];
            adj.get(now).add(new Edge(to, weight));
            adj.get(to).add(new Edge(now, weight));
        }
        dist[1] = 0;
        pq.add(new Edge(1,0));
        dijk();
        for(int i=1; i<=N; i++){
            System.out.println(dist[3]);
            if(dist[i]<=K) answer++;
        }

        return answer;
    }
}
