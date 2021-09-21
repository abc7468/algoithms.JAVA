package Programmers.LV2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Q72412 {
    static String[][] infos;
    public static int binarySearch(int std){
        int left = 0;
        int right = infos.length;
        int mid;
        while(left<=right){
            mid = (left+right)/2;
            if(Integer.parseInt(infos[mid][4])>=std){
                right = mid -1;
            }else{
                left = mid+1;
            }
        }
        return left;
    }
    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        infos = new String[info.length][];
        String[][] queries = new String[query.length][5];
        int index = 0;

        for(String i : info){
            infos[index++] = i.split(" ");
        }
        index = 0;
        for(String q : query){
            String[] tmp = q.split(" and ");
            queries[index][0] = tmp[0];
            queries[index][1] = tmp[1];
            queries[index][2] = tmp[2];
            tmp = tmp[3].split(" ");
            queries[index][3] = tmp[0];
            queries[index][4] = tmp[1];
            index++;
        }
        Arrays.sort(infos,(a,b)-> Integer.parseInt(a[4])-Integer.parseInt(b[4]));
        int idx = 0;

        for(String[] q : queries){
            int cnt = 0;
            int startIndex = binarySearch(Integer.parseInt(q[4]));
            for(int i = startIndex; i< infos.length; i++){
                for(int j = 0; j<4; j++){
                    if(q[j].equals("-")){
                        if(j==q.length-2){
                            cnt++;
                        }
                        continue;
                    }
                    if(!q[j].equals(infos[i][j])){
                        break;
                    }
                    if(j== q.length-2 && q[j].equals(infos[i][j])){
                        cnt++;
                    }
                }
            }
            answer[idx++]=cnt;
        }





        return answer;
    }

    public static void main(String[] args){
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        solution(info, query);

    }
}
