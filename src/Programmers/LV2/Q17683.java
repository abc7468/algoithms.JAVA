package Programmers.LV2;

import java.util.ArrayList;
public class Q17683 {
    static class Music{
        int runningTime;
        String name;
        String infos;
        public Music(int runningTime, String name, String infos){
            this.runningTime = runningTime;
            this.name = name;
            this.infos = infos;
        }
    }
    static ArrayList<Music> musicList = new ArrayList<>(); // 모든 음악 리스트
    static ArrayList<Music> candi = new ArrayList<>(); // 답이 될 수 있는 후보 음악 리스트

    // startTime과 EndTime으로 RunningTime 도출
    static int getRunningTime(String startTime, String endTime){
        int time = 0;
        String[] sTime = startTime.split(":");
        String[] eTime = endTime.split(":");
        int sHour = Integer.parseInt(sTime[0]);
        int eHour = Integer.parseInt(eTime[0]);
        int sMinute = Integer.parseInt(sTime[1]);
        int eMinute = Integer.parseInt(eTime[1]);
        if(eMinute-sMinute>=0){
            time+=(eHour-sHour)*60 + eMinute-sMinute;
        }
        else{
            eHour--;
            time+=(eHour-sHour)*60 + 60-(sMinute-eMinute);
        }
        return time;
    }

    // 음계의 일관성을 위해 from -> to 로 문자 변경
    static String replaceChar(String str){
        String[] from = {"C#","D#","F#","G#","A#"};
        String[] to = {"H","I","J","K","L"};
        for(int i =0; i<5; i++){
            str = str.replaceAll(from[i],to[i]);
        }
        return str;
    }

    public static String solution(String m, String[] musicinfos) {
        String answer = "";
        m = replaceChar(m);
        for(String music : musicinfos){
            String[] musicStatus = music.split(",");
            StringBuilder sb = new StringBuilder();
            musicStatus[3] = replaceChar(musicStatus[3]);
            int runningTime = getRunningTime(musicStatus[0],musicStatus[1]);
            int infosLen = musicStatus[3].length();
            for(int i =0; i<runningTime; i++){
                sb.append(musicStatus[3].charAt(i%infosLen));
            }
            musicList.add(new Music(runningTime,musicStatus[2],sb.toString()));
        }
        int max = 0;
        for (Music music : musicList) {
            if (music.infos.contains(m)) {
                candi.add(music);
                if (max < music.runningTime) {
                    max = music.runningTime;
                }
            }
        }
        if(candi.size()==0){
            answer = "(None)";
        }
        // 후보곡이 여러가지라면 먼저 나온곡 출력
        else{
            for (Music music : candi) {
                if (music.runningTime == max) {
                    answer = music.name;
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args){
        String m = "ABC";
        String[] musicinfos = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        System.out.println(solution(m, musicinfos));
    }
}