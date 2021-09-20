package Programmers.LV2;

public class Q12951 {
    public static String solution(String s) {
        boolean check = false;
        s = s.toLowerCase();
        char[] tmp = s.toCharArray();
        for(int i =0; i<tmp.length; i++){
            if (tmp[i] == ' '){
                check = false;
                continue;
            }
            if(!check){
                check = true;
                if(tmp[i]>='a' && tmp[i]<='z'){
                    tmp[i] = (char)((int)tmp[i]-'a'+'A');
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(tmp);
        return sb.toString();
    }


    public static void main(String[] args){

        System.out.println(solution("3people unFollowed me"	));
    }
}
