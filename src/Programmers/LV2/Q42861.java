package Programmers.LV2;

import java.util.PriorityQueue;

public class Q42861 {
    public static void main(String[] args){
        int n =4 ;
        int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};
        System.out.print(solution(n, costs));
    }

    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void union(int a,int b) {
        a = find(a);
        b = find(b);
        if(a<b) parent[b] = a;
        else parent[a] = b;

    }
    public static int find(int a) {

        if(parent[a]==a)
            return a;
        else
            return parent[a] = find(parent[a]);
    }
    public static int findParent(int a, int b){
        a = find(a);
        b = find(b);
        if(a == b)
            return 0;
        return 1;
    }
    public static class Edge implements Comparable<Edge>{
        int now;
        int next;
        int weight;
        public Edge(int now, int next, int weight){
            this.now = now;
            this.next = next;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return this.weight-o.weight;
        }
    }


    public static int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];
        for(int i =0; i<n; i++){
            parent[i]=i;
        }
        for(int[] tmp : costs){
            pq.add(new Edge(tmp[0],tmp[1],tmp[2]));
            pq.add(new Edge(tmp[1],tmp[0],tmp[2]));
        }

        while(!pq.isEmpty()){
            Edge tmp = pq.poll();
            if(findParent(tmp.now, tmp.next)==1){
                union(tmp.now,tmp.next);
                answer+=tmp.weight;
            }
        }
        for(int i =0; i<n; i++){
            System.out.println(parent[i]);
        }
        return answer;
    }
}
