package Programmers.LV2;

import java.util.Stack;

public class Q12909 {
    static boolean solution(String s) {
        int cnt = 0;
        int size = s.length();
        for(int i =0; i<size; i++){
            if(s.charAt(i)=='('){
                cnt++;
            }
            else{
                cnt--;
            }
            if(cnt<0){
                return false;
            }

        }

        if(cnt !=0){
            return false;
        }
        return true;
    }


    public static void main(String[] args){
        if(solution(")()("	)){
            System.out.println("true");
        }else{
            System.out.println("false");

        }
    }


}
