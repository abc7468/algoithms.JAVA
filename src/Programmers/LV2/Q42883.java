package Programmers.LV2;

import java.util.*;

public class Q42883 {
    public static void main(String[] args){
        String number = "51515157";
        int k = 3;
        String result = solution(number, k);
        System.out.println(result);
    }
    public static String solution(String number, int k) {
        String answer = "";
        Stack<Character> s = new Stack<Character>();
        int delCount = 0;
        int numSize = number.length();
        boolean isEnd = false;
        if(number.charAt(0) == '0') return "0";
        s.add(number.charAt(0));
        for(int i =1; i<numSize; i++){
            if(s.peek()<number.charAt(i) && !isEnd){
                while(true){
                    s.pop();
                    delCount++;
                    if(delCount==k){
                        isEnd = true;
                        s.add(number.charAt(i));
                        break;
                    }
                    if(s.isEmpty()){
                        s.add(number.charAt(i));

                        break;
                    }
                    if(s.peek()>=number.charAt(i)){
                        s.add(number.charAt(i));
                        break;
                    }

                }
            }
            else{
                s.add(number.charAt(i));
            }
        }
        StringBuffer sb = new StringBuffer();
        if(!isEnd){
            while(k!=delCount){
                delCount++;
                s.pop();
            }
        }
        while(!s.isEmpty()){
            sb.append(s.pop());
        }


        sb.reverse();


        return sb.toString();
    }
}
