package Programmers.LV2;

public class Q12985 {
    public static int solution(int n, int a, int b)
    {
        int answer = 0;
        if(a>b){
            int tmp = a;
            a = b;
            b = tmp;
        }
        for(int i = n/2; i>0; i/=2){
            a -= i;
            b -= i;
            if(a<0 && b<=0){
                a+=i;
                b+=i;
            }
            if(a<=0 &&b>0){
                answer = (int)(Math.log(i)/Math.log(2))+1;
                break;
            }
        }

        return answer;
    }


    public static void main(String[] args){
        System.out.println(solution(16,4, 7));
    }
}
