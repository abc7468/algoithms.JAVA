package Programmers.LV1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Q84325 {

    static HashMap<String, Integer> languagePreference = new HashMap<>();
    static HashMap<String, Integer> totalScoreTable = new HashMap<>();
    static int max = 0;
    public static String solution(String[] table, String[] languages, int[] preference) {
        String answer = "";
        int len = languages.length;
        for(int i =0; i<len; i++){
            languagePreference.put(languages[i], preference[i]);
        }
        for(String t : table){
            String[] tmp = t.split(" ");
            int tmpLen = tmp.length;
            int tmpScore = 0;
            for(int i =1; i<tmpLen; i++){
                if(languagePreference.containsKey(tmp[i])){
                    tmpScore+=(6-i)*languagePreference.get(tmp[i]);
                }
            }
            totalScoreTable.put(tmp[0],tmpScore);
            if(max<totalScoreTable.get(tmp[0])){
                max = totalScoreTable.get(tmp[0]);
            }
        }
        ArrayList<String> answerTmp = new ArrayList<>();
        for(String key : totalScoreTable.keySet()){
            if(totalScoreTable.get(key)==max){
                answerTmp.add(key);
            }
        }
        Collections.sort(answerTmp);



        return answerTmp.get(0);
    }
    public static void main(String[] args){

        String[] table = {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"};
        String[] language = {"PYTHON", "C++", "SQL"};
        int[] preference = {7,5,5};
        String answer = solution(table, language, preference);

        System.out.println(answer);
    }
}
