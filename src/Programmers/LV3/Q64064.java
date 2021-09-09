package Programmers.LV3;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Q64064 {
    static boolean[] visited;

    static HashMap<String, Integer> id = new HashMap<>();
    static ArrayList<ArrayList<Integer>> candi = new ArrayList<>();
    static ArrayList<String> ans = new ArrayList<>();

    static ArrayList<Integer> tmp = new ArrayList<>();
    static void back(int banSize, int banNum){
        if(banNum==banSize){
            StringBuilder sb = new StringBuilder();
            ArrayList<Integer> tmp2 = new ArrayList<>(tmp);

            Collections.sort(tmp2);
            for(int num : tmp2){
                sb.append(num);
            }
            if(!ans.contains(sb.toString())){
                ans.add(sb.toString());
            }
            return;
        }

        for(int i =0; i<candi.get(banNum).size(); i++){
            if(!visited[candi.get(banNum).get(i)]){
                visited[candi.get(banNum).get(i)] = true;
                tmp.add(candi.get(banNum).get(i));
                back(banSize, banNum+1);
                tmp.remove(tmp.size()-1);
                visited[candi.get(banNum).get(i)] = false;
            }


        }
    }


    public static int solution(String[] user_id, String[] banned_id) {
        StringBuilder sb = new StringBuilder();
        int idSize = user_id.length;
        int banSize = banned_id.length;
        visited = new boolean[idSize];
        // user dic 만들기
        for(int i =0; i<idSize;i++){
            id.put(user_id[i], i);
        }
        for(int i =0; i<banSize; i++){
            candi.add(new ArrayList<>());
        }
        // ban 후보자 만들기
        for(int b = 0; b<banSize; b++){
            String banId = banned_id[b];
            int size = banId.length();
            String[] candiTmp;
            for(int i =0; i<size; i++){
                if(banId.charAt(i)=='*'){
                    sb.append(".{1}");
                    continue;
                }
                sb.append(banId.charAt(i));
            }
            String re = sb.toString();
            for (String s : user_id) {
                if (s.matches(re)) {
                    candi.get(b).add(id.get(s));
                }
            }
            sb.delete(0, sb.length());
        }
        back(banSize, 0);


        return ans.size();
    }
    public static void main(String[] args){
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "*rodo", "******", "******"};
        int answer = solution(user_id, banned_id);
        System.out.println(answer);
    }
}
