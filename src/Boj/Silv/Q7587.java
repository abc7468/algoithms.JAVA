package Boj.Silv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Q7587 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(true){
            HashMap<String, Integer> map = new HashMap<>();

            int max = 0;

            int t = scanner.nextInt();
            String[] strTmp;
            strTmp = new String[t];
            if(t == 0){
                break;
            }
            for(int i =0; i<t; i++){
                String str = scanner.next();
                strTmp[i] = str;
            }
            for(int i =0; i<t; i++){
                char[] charArray = strTmp[i].toCharArray();
                Arrays.sort(charArray);
                String sortStr = String.valueOf(charArray);
                if(!map.containsKey(sortStr)){
                    map.put(sortStr,1);
                }else{
                    map.replace(sortStr,map.get(sortStr)+1);
                }
                if(max<map.get(sortStr)){
                    max = map.get(sortStr);
                }
            }
            for(String tmp : strTmp){

                char[] cArray = tmp.toCharArray();
                Arrays.sort(cArray);
                String sStr = String.valueOf(cArray);

                if(map.containsKey(sStr)&&map.get(sStr)==max){
                    System.out.printf("%s %d\n",tmp, max-1);
                    break;
                }
            }
        }


    }
}
