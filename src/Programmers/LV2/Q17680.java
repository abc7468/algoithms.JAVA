package Programmers.LV2;



import java.util.LinkedList;
import java.util.Locale;
import java.util.Objects;

public class Q17680 {
    static LinkedList<String> cache = new LinkedList<>();

    static void addCity(String city){
        cache.addFirst(city);
    }
    static void deleteLastCity(){
        cache.removeLast();
    }
    static void deleteHasCity(String city){
        cache.remove(city);
    }
    static boolean hasCity(String city){
        return cache.contains(city);
    }


    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if(cacheSize==0){
            answer+=5*cities.length;
            return answer;
        }
        for(String city : cities){
            city = city.toLowerCase(Locale.ROOT);
            boolean isHas = hasCity(city);
            if(isHas){
                if (!Objects.equals(cache.get(0), city)) {
                    deleteHasCity(city);
                    addCity(city);
                }
                answer+=1;
            }else{
               if(cache.size() == cacheSize){
                   deleteLastCity();
               }
                addCity(city);
                answer+=5;
            }
        }
        return answer;
    }

    public static void main(String[] args){
        //String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        //String[] cities = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
        String[] cities = {	"Jeju", "Pangyo", "NewYork", "newyork"};
        System.out.println(solution(3, cities));

    }
}
