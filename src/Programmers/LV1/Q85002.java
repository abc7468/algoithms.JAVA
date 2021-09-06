package Programmers.LV1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Q85002 {
    static PriorityQueue<Player> players = new PriorityQueue<>(new Comparator<Player>() {
        @Override
        public int compare(Player o1, Player o2) {
            if(o1.winRate!=o2.winRate){
                return o1.winRate<o2.winRate?1:-1;
            }
            if(o1.countWinMoreHeavy != o2.countWinMoreHeavy){
                return o1.countWinMoreHeavy<o2.countWinMoreHeavy?1:-1;
            }
            if(o1.weight != o2.weight){
                return o1.weight<o2.weight?1:-1;
            }
            return o1.id>o2.id?1:-1;
        }
    });
    static class Player{
        int id;
        int weight;
        float winRate;
        int countWinMoreHeavy;
        public Player(int id, int weight, String head2head, int[] weights){
            this.id = id;
            this.weight = weight;
            calculateWinRate(head2head, weights);
        }
        private void calculateWinRate(String head2head, int[] weights){
            int winCount = 0;
            int count = 0;
            int countWinMoreHeavy = 0;
            int size = head2head.length();
            for(int i =0; i<size; i++){
                if(i==this.id){
                    continue;
                }
                if(head2head.charAt(i)=='W'){

                    if(weights[i]>this.weight){
                        countWinMoreHeavy++;
                    }
                    winCount++;
                    count++;
                }
                else if(head2head.charAt(i)=='L'){
                    count++;
                }
            }
            this.countWinMoreHeavy = countWinMoreHeavy;
            if(count ==0){
                this.winRate = 0;
            }
            else{
                this.winRate = (float)winCount/count;
            }
        }
    }
    public static int[] solution(int[] weights, String[] head2head) {
        int size = weights.length;
        int[] answer = new int[size];
        for(int i =0; i<size; i++){
            Player player = new Player(i,weights[i], head2head[i],weights);
            players.add(player);
        }
        int index = 0;
        while(!players.isEmpty()){
            answer[index++] = players.poll().id+1;

        }
        return answer;
    }
    public static void main(String[] args){
        int[] weights= {50,82,75,120};
        String[] head2head = {"NLWL","WNLL","LWNW","WWLN"};
        solution(weights, head2head);
    }
}
