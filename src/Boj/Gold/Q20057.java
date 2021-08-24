package Boj.Gold;

import java.util.Arrays;
import java.util.Scanner;

public class Q20057 {
    static int N;
    static int[] inOrder;
    static int[] postOrder;
    static void findPre(int leftIn, int rightIn, int leftPost, int rightPost){
        if (rightPost==leftPost){
            System.out.printf("%d ", postOrder[rightPost]);
            return;
        }
        if(rightPost<leftPost){
            return;
        }
        int pivot = postOrder[rightPost];
        System.out.printf("%d ", pivot);
        int i;
        for(i =leftIn; i<rightIn; i++){
            if(inOrder[i]==pivot){
                break;
            }
        }

        findPre(leftIn, i-1, leftPost, leftPost + (i-leftIn-1));
        findPre(i+1, rightIn, leftPost + (i-leftIn), rightPost-1);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        inOrder = new int[N];
        postOrder = new int[N];
        for(int i =0; i<N; i++){
            inOrder[i] = sc.nextInt();
        }
        for(int i =0; i<N; i++){
            postOrder[i] = sc.nextInt();
        }
        findPre(0,N-1,0, N-1);
    }
}
