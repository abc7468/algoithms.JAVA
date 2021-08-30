package Boj.Gold;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Test
{
    static ArrayList<ArrayList<Integer>> subwayHo;
    static boolean[] visited;
    static HashMap<Integer, HashSet<Integer>> changeSubway = new HashMap<>();
    public static void main(String[] args){
        String[] tmp = {"1 2 3 4 5 6 7 8","2 11","0 11 10","3 17 19 12 13 9 14 15 10","20 2 21"};
        int answer = solution(tmp, 1, 9);
        System.out.println(answer);
    }
    public static int solution(String[] subway, int start, int end) {
        int answer = 0;
        int lineLong = subway.length;
        visited = new boolean[lineLong];
        subwayHo = new ArrayList<>();
        for(int i =0; i<lineLong; i++){
            subwayHo.add(new ArrayList<>());
        }
        for(int i =0; i<lineLong; i++){
            for(String subwayNum : subway[i].split(" ")){
                int eachSubway = Integer.parseInt(subwayNum);
                subwayHo.get(i).add(eachSubway);
                if (changeSubway.containsKey(eachSubway)){
                    changeSubway.get(eachSubway).add(i);
                }else{
                    HashSet<Integer> tmp = new HashSet<>();
                    tmp.add(i);
                    changeSubway.put(eachSubway, tmp);
                }
            }
        }

        for(Integer go : changeSubway.get(start)){
            if(visited[go] == false) {
                if (findDes(go, end)) {
                    return answer;
                }
            }
            for(Integer eachSubway : subwayHo.get(go)){
                answer++;
                for(Integer ho : changeSubway.get(eachSubway)){
                    if(visited[ho] == false) {
                        if (findDes(ho, end)) {
                            return answer;
                        }
                    }
                }
            }
        }


        return answer;
    }


    //도착 지점 찾기
    static boolean findDes(int hoNum, int des){
        visited[hoNum] = true;
        if(subwayHo.get(hoNum).contains(des)==true){
            return true;
        }
        return false;
    }





}
