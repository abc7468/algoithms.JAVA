package Boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1208 {
    static HashMap<Integer,Integer> map1 = new HashMap<>();
    static HashMap<Integer,Integer> map2 = new HashMap<>();
    static int val = 0;
    public static void go(int[] numbers, int index, int end, HashMap<Integer,Integer> map){
        if(map.containsKey(val)){
            map.put(val, map.get(val)+1);
        }else{
            map.put(val, 1);
        }
        for(int i = index; i<end; i++){
            val+=numbers[i];
            go(numbers, i+1, end, map);
            val-=numbers[i];
        }
    }

    public static void main(String[] args)throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int half = nm[0]/2;
        go(numbers, 0, half, map1);
        go(numbers, half, nm[0], map2);

        ArrayList<Integer> list1 = new ArrayList<>(map1.keySet());
        long answer = 0;
        for(Integer a : list1){
            if(map2.containsKey(nm[1]-a)){
                answer+= (long) map1.get(a) *map2.get(nm[1]-a);
            }
        }
        if(nm[1]==0){
            answer--;
        }
        System.out.println(answer);
    }
}
