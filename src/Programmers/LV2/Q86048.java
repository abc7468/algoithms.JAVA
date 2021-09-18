package Programmers.LV2;

import java.util.ArrayList;

public class Q86048 {
    static int[] cnt;
    static ArrayList<Integer> room = new ArrayList<>();
    public static int[] solution(int[] enter, int[] leave) {
        int size = enter.length;
        cnt = new int[size];
        int enterIndex = 0;
        for(int i =0; i<size; i++){
            int leavePerson = leave[i];
            while(!room.contains(leavePerson)){
                room.add(enter[enterIndex++]);
            }
            room.remove(room.indexOf(leavePerson));
            for(int person : room){
                cnt[leavePerson-1]++;
                cnt[person-1]++;
            }
        }
        return cnt;
    }

    public static void main(String[] args){
        int[] enter = {1,4,2,3};
        int[] leave = {2,1,3,4};
        solution(enter, leave);
    }

}
