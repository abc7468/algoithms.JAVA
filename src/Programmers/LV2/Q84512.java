package Programmers.LV2;

import java.util.HashMap;
import java.util.List;

public class Q84512 {
    static HashMap<Character, Integer> map = new HashMap<>();
    static int[] list = {781, 156, 31, 6, 1};
    static public int solution(String word) {
        int answer = 0;

        map.put('A',0);
        map.put('E',1);
        map.put('I',2);
        map.put('O',3);
        map.put('U',4);
        int len = word.length();
        for(int i =0; i<len; i++){
            answer += map.get(word.charAt(i))*list[i]+1;
        }
        return answer;
    }
    public static void main(String[] args){
        int answer = solution("AAAE");
        System.out.println(answer);
    }
}
