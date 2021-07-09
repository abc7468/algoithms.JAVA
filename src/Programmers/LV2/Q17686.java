package Programmers.LV2;
import java.util.*;
public class Q17686 {
    public static void main(String[] args){
        String[] arg1 = {"img12", "img10.png", "img02.png", "img1.png", "IMG01.png", "img2.JPG", "img010bar020.zip"};
        String[] arg2 = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        String[] result = solution(arg1);
        for(String ans: result){
            System.out.println(ans);
        }
    }


    public static class File{
        int order;
        String head;
        int num;
        String tail;
        public File(int order, String head, int num, String tail){
            this.order = order;
            this.head = head;
            this.num = num;
            this.tail = tail;
        }
        public File(int order, String head, int num){
            this.order = order;
            this.head = head;
            this.num = num;
            this.tail = "";
        }
    }

    public static String[] solution(String[] files) {
        Comparator<File> comparator = new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int headOrder = o1.head.toLowerCase().compareTo(o2.head.toLowerCase());
                if(headOrder==0){
                    int numOrder = o1.num-o2.num;
                    if(numOrder==0){
                        return o1.order-o2.order;
                    }
                    else{
                        return numOrder;
                    }

                }
                else{
                    return headOrder;
                }
            }
        };
        ArrayList<File> tmp = new ArrayList<>();
        int fileSize = files.length;
        for(int i =0; i<fileSize; i++){
            int fileNameSize = files[i].length();
            int numStart = 0;
            int numEnd = 0;
            for(int j =0; j<fileNameSize;j++){
                if(files[i].charAt(j)-'0'>=0 && files[i].charAt(j)-'0'<=9){
                    numStart = j;
                    break;
                }
            }
            for(int j =numStart; j<fileNameSize; j++){
                if(j == fileNameSize-1){
                    numEnd = j+1;
                }
                if(!(files[i].charAt(j)-'0'>=0 && files[i].charAt(j)-'0'<=9)){
                    numEnd = j;
                    break;
                }

            }

            tmp.add(new File(i, files[i].substring(0,numStart), Integer.parseInt(files[i].substring(numStart,numEnd)), files[i].substring(numEnd,fileNameSize)));
        }
        tmp.sort(comparator);
        String[] answer = new String[fileSize];
        int i =0;
        for(File file : tmp){
            answer[i++] = files[file.order];
        }
        return answer;
    }
}
