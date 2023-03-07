package baekjoon.AnagramGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class AnagramGroup {

    /**
     * 1. 입력으로 들어온 문자열들에 대해 '같은 단어는 한 번만 출력'해야 한다.
     * 반면에 그룹의 크기를 구할 때는 동일한 문자라도 세줘야 한다.
     * 즉, 입력으로 "aaaa"가 5번 들어왔다면 출력은 "Group of size 5: aaaa ."와 같이 이루어져야 한다.
     * 왜 이것부터 쓰냐면 난 이것때문에 엄청 틀렸다 ㅠㅠ 문제 예시에 동일한거 하나만 좀 넣어주지.. ㅂㄷㅂㄷ
     *
     * 이거 때문에 어디가 틀렸는지 몇시간을 점검했는데 너무 억울하고 눈물이 난다.
     */

    public static void main(String[] args) throws IOException {


        //일단 EOF 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        ArrayList<char[]> charArr = new ArrayList<>();
        ArrayList<String> stringArr = new ArrayList<>();
        HashMap<char[], ArrayList<String>> charString = new HashMap<>();
        // null 나올때까지 계속 받는다asd
        while ((input = br.readLine())!=null) {

            char[] inputChar = input.toCharArray();
            Arrays.sort(inputChar);
            // 기존 저장된 값들과 비교
            boolean charSame = false;
            for (char[] charList : charArr) {
                if (Arrays.equals(inputChar, charList)) { // 같은게 있다면
                    ArrayList<String> strArr = charString.get(charList);

                    if (!strArr.contains(input)) {
                        strArr.add(input);
                        strArr.sort(String.CASE_INSENSITIVE_ORDER);
                    }
                    charSame = true;
                }
            }
            if(!charSame) {// 동일 애너그램 그룹 존재x
                charArr.add(inputChar);
                ArrayList<String> strArr = new ArrayList<>();
                strArr.add(input);
                strArr.sort(String.CASE_INSENSITIVE_ORDER);
                charString.put(inputChar, strArr);

            }




        }// end of while((input = br.readLine())!= null) {
            // 출력파트
            // 일단 HashMap 배열 순서를 변경해줘야 한다.

            // 조건 1 - > 그룹의 크기 내림차순
            // 조건 2 -> 같은 크기면 그룹내 첫 단어 sort 내림차순
            //charString.entrySet().stream().sorted( new Comparator<>())

            // entrySet() -> HashMap 은 순서가 없는 자료구조이므로 정렬을 위해 List 로 변환
            // List<Map.Entry<char[], ArrayList<String>>> entryList = new ArrayList<>(charString.entrySet());
        Comparator<ArrayList<String>> comparator = new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> list1, ArrayList<String> list2) {
                int sizeCompare = Integer.compare(list2.size(),list1.size());// 크기 비교
                if(sizeCompare!=0){
                    return sizeCompare;
                }
                return list1.get(0).compareTo(list2.get(0));
            }
        };
        List<Map.Entry<char[], ArrayList<String>>> entryList = charString.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(comparator))
                .collect(Collectors.toList());
// comparingByValue 메소드는 매개변수로 받은 Comparator 객체를 사용해서 Map의 값을 비교하고, 그 결과에 따라 Map.Entry들을 정렬합니다.
// Comparator 객체의 compare 메소드는 내부적으로 사용되며, comparingByValue 메소드는 이 Comparator 객체를 정렬에 적용하기 위해 사용됩니다.

        for (int i = 0; i < Math.min(5, entryList.size()); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < entryList.get(i).getValue().size(); j++) {
                sb.append(entryList.get(i).getValue().get(j) + " ");
            }


            System.out.println("Group of size " + entryList.get(i).getValue().size() + ": " + sb.toString() + ".");
        }


    }

}

