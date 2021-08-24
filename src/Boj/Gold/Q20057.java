package Boj.Gold;

import java.util.Arrays;
import java.util.Scanner;

public class Q20057 {
    static int N;
    static int[] inOrder;
    static int[] postOrder;
    static void findPre(int[] in, int[] post){
        if (post.length==1){
            System.out.printf("%d ", post[0]);
            return;
        }
        if(post.length==0){
            return;
        }
        System.out.printf("%d ", post[post.length-1]);
        int len = post.length;
        int pivot = post[len-1];
        int i;
        for(i =0; i<len; i++){
            if(in[i]==pivot){
                break;
            }
        }
        int[] leftIn;
        int[] rightIn;
        int[] leftPost;
        int[] rightPost;
        leftIn = Arrays.copyOfRange(in,0, i);
        rightIn = Arrays.copyOfRange(in,i+1, len);
        leftPost = Arrays.copyOfRange(post,0,i);
        rightPost = Arrays.copyOfRange(post,i,len-1);
        findPre(leftIn, leftPost);
        findPre(rightIn, rightPost);
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
        findPre(inOrder, postOrder);
    }
}
