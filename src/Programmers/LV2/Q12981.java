package Programmers.LV2;

import java.util.ArrayList;

public class Q12981 {

    public static void main(String[] args){
        int n = 3;
//        String[] words = {"hello", "one", "even", "never", "now", "world", "draw"};
        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        int[] result = solution(n, words);
        for(int a : result){
            System.out.println(a);
        }
    }
    public static int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        int wordsSize = words.length;
        ArrayList<String> used = new ArrayList<>();
        int check=-1;
        used.add(words[0]);
        for(int i =1; i<wordsSize; i++){
            String checkStr = used.get(used.size()-1);
            if(used.contains(words[i])){
                check = i;
                break;
            }
            else if(checkStr.charAt(checkStr.length()-1)!=words[i].charAt(0)){
                check = i;
                break;
            }
            else{
                used.add(words[i]);
            }
        }



        if(check == -1){
            return answer;
        }
        else{  //5번째 2명
            answer[0] = (check%n)+1;
            answer[1] = (check/n)+1;
        }
        return answer;
    }
}
