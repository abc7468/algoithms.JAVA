package Boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

//1. 편의점 알바를 하던 죠르디는 알파벳 문자로 이루어진 상품코드가 너무 길어 별도의 규칙을 만들어
//        인코딩하려고 합니다. 죠르디는 문자열 안에 연속적으로 반복되는 문자를 "반복되는횟수 [문자]"형태로
//        인코딩할 계획입니다. 예를들어 aaaz는 3[a]z로 인코딩되고 accccaccccacccc는 a4[c]a4[c]a4[c]로 1회
//        인코딩 되고 다시3[a4[c]]로 인코딩 됩니다. 인코딩에 너무 집중한 죠르디는 인코딩한 문자열을 다시
//        원래 문자열로 만드는 디코딩 프로그램 작성을 깜빡했습니다. 죠르디 대신 인코딩된 문자열이
//        들어왔을 때, 디코딩된 문자열을 반환하는 프로그램을 작성해 주세요.
//        규칙
//        - 입력되는 문자열은 숫자, 문자, 괄호로만 이루어져 있습니다. - 숫자는 양의 정수이며, 문자에는 숫자가 포함되지 않습니다. 예를 들어 3a, 2[4]와 같은 입력은
//        존재하지 않습니다.
//        - 입력되는 문자열의 길이는 L은 0 < L < 128 입니다.
//
//        테케 입출력
//        입력 : 3[a]z
//        출력 : aaaz
//        입력 : 3[a4[c]]
//        출력 : accccaccccacccc
//
//        입력 : 3[a10[c]]f2[z]
//
//        출력 : accccccccccaccccccccccaccccccccccfzz

public class Q1 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Q1 q1 = new Q1();
        System.out.println(q1.solution(br.readLine()));
        br.close();
    }

    public String solution(String str) {

        Map<StringBuilder, StringBuilder> dict = new HashMap<>();
        str = "1[" + str + ']';

        dfs(str.toCharArray(), dict, 1, 0, 2);

        return dict.get(new StringBuilder(str)).toString();
    }

    public int dfs(char[] chars, Map<StringBuilder, StringBuilder> dict, int rpCnt, int cntSrtIdx, int idx) {

        StringBuilder sb = new StringBuilder();

        while (idx < chars.length && chars[idx] != ']') {

            if (!Character.isDigit(chars[idx])) {
                sb.append(chars[idx++]);
                continue;
            }

            /*
                - 현재 문자가 숫자일 경우, dfs 실행
                - 반복할 횟수, 숫자의 시작 인덱스, 열기괄호 다음 인덱스 값을 매개변수로 넘김
                - 닫기괄호가 끝난 후 다음 인덱스 값 받아옴
             */
            int innerRpCnt = 0;
            int innerIdx = idx;
            while (innerIdx < chars.length && chars[innerIdx] != '[') {
                innerRpCnt = innerRpCnt * 10 + chars[innerIdx++] - '0';
            }

            int nextIdx = dfs(chars, dict, innerRpCnt, idx, innerIdx + 1);
            StringBuilder test = new StringBuilder();
            test = chars2String(chars, idx, nextIdx);
            sb.append(dict.get(test));
            idx = nextIdx;
        }

        /*
            - (key, value) = (number[content], number * content) 형태로 dict에 저장
            - ex) (3[a], aaa)
         */
        dict.put(chars2String(chars, cntSrtIdx, idx + 1), repeatString(sb, rpCnt));
        return idx + 1;
    }

    public static StringBuilder chars2String(char[] chars, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; ++i) {
            sb.append(chars[i]);
        }
        return sb;
    }

    public static StringBuilder repeatString(StringBuilder inputSb, int cnt) {
        StringBuilder sb = new StringBuilder();
        while (cnt-- > 0) {
            sb.append(inputSb);
        }
        return sb;
    }
}