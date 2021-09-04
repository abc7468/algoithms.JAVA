package Programmers.LV1;

public class Q17681 {
    static String toBinary(int intVal, int n){
        StringBuilder sb = new StringBuilder();
        while(true){
            if(intVal ==0){
                break;
            }
            if(intVal==1){
                sb.append('1');
                break;
            }
            if(intVal%2==0){
                sb.append('0');
            }
            if(intVal%2==1){
                sb.append('1');
            }
            intVal = intVal/2;

        }
        while(sb.length()<n){
            sb.append('0');
        }
        return sb.reverse().toString();
    }
    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for(int i =0; i<n; i++){
            String tmp1 = toBinary(arr1[i],n);
            String tmp2 = toBinary(arr2[i],n);
            StringBuilder sb = new StringBuilder();
            for(int j =0; j<n; j++){
                if(tmp1.charAt(j)=='0' && tmp2.charAt(j)=='0'){
                    sb.append(' ');
                    continue;
                }
                sb.append('#');
            }
            answer[i] = sb.toString();
        }
        return answer;
    }
    public static void main(String[] args){
        int[] a = {9, 20, 28, 18, 11};
        int[] b = {30, 1, 21, 17, 28};
        solution(5, a,b);
    }
}