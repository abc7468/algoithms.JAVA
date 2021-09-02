package Programmers.LV2;

public class Q82612 {
    public static void main(String[] args){
        System.out.println(solution(3,20,4));
    }
    public static long solution(int price, int money, int count) {
        long hasMoney=money;
        for(int i =1; i<=count; i++){
            hasMoney-= (long) price *i;
        }
        if(hasMoney<0){
            return -hasMoney;
        }
        return 0;
    }
}
