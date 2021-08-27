package Programmers.LV3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Q1830 {

    static public int[] solution(String msg) {
        int[] answer = {};
        ArrayList<Integer> answerTmp = new ArrayList<>();
        HashMap<String, Integer> dic = new HashMap<>();
        int len = msg.length();
        for(int i =1; i<=26; i++){
            dic.put(Character.toString((char)('A'+i-1)),i);
        }
        int index = 27;
        for(int i =0; i<len; i++){
            for(int j =i+1; j<len; j++){
                if(dic.containsKey(msg.substring(i,j))){
                    if(dic.containsKey(msg.substring(i,j+1))){
                        continue;
                    }
                    answerTmp.add(dic.get(msg.substring(i,j)));
                }else{
                    dic.put(msg.substring(i,j),index++);
                    System.out.println(msg.substring(i,j));
                    break;
                }
            }
        }
        answerTmp.add(dic.get(msg.substring(len-1,len)));
        System.out.println(answerTmp);
        return answer;
    }
    public static void main(String[] args){
        System.out.println(solution("KAKAO"));
    }

}
