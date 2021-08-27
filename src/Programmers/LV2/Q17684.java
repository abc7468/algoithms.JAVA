package Programmers.LV2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Q17684 {

    static public ArrayList<Integer> solution(String msg) {
        ArrayList<Integer> answerTmp = new ArrayList<>();
        HashMap<String, Integer> dic = new HashMap<>();
        int len = msg.length();
        for(int i =1; i<=26; i++){
            dic.put(Character.toString((char)('A'+i-1)),i);
        }
        int index = 27;
        for(int i =0; i<len; i++){
            for(int j =i+1; j<=len; j++){
                if(j==len){
                    answerTmp.add(dic.get(msg.substring(i,j)));
                    dic.put(msg.substring(i,j), index++);
                    i = j-1;
                    break;
                }
                if(!dic.containsKey(msg.substring(i,j+1))) {
                    answerTmp.add(dic.get(msg.substring(i,j)));
                    dic.put(msg.substring(i,j+1), index++);
                    i = j-1;
                    break;
                }
            }
        }
        System.out.println(answerTmp);
        return answerTmp;
    }
    public static void main(String[] args){
        System.out.println(solution("ABABABABABABABAB"));
    }

}
