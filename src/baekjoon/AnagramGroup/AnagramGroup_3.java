package baekjoon.AnagramGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class AnagramGroup_3 {

    /**
     * 인간적으로 속도차이가 심하다. 줄일 수 있는 부분을 줄여보자.
     * 1. key 값을 char[] 로 잡는건 좀 심했던 거 같기도 하다. 근데 toString 하는게 더 시간 오래 걸리네
     *
     */

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        ArrayList<char[]> charArr = new ArrayList<>();
        ArrayList<String> stringArr = new ArrayList<>();
        HashMap<String, ArrayList<String>> charString = new HashMap<>();
        HashMap<String, Integer > countMap = new HashMap<>();
        //일단 EOF 처리 ctrl D
        while ((input = br.readLine())!=null) {
            char[] inputChar = input.toCharArray();
            Arrays.sort(inputChar);
            // 기존 저장된 값들과 비교
            boolean charSame = false;
            for (char[] charList : charArr) {
                if (Arrays.equals(inputChar, charList)) { // 같은게 있다면
                    ArrayList<String> strArr = charString.get(charList.toString());

                    if (!strArr.contains(input)) {
                        strArr.add(input);
                        strArr.sort(String.CASE_INSENSITIVE_ORDER);
                    }
                    charSame = true;
                    // 카운트 ++
                    countMap.put(charList.toString(), countMap.get(charList.toString())+1);

                }
            }
            if(!charSame) {// 동일 애너그램 그룹 존재x
                charArr.add(inputChar);
                ArrayList<String> strArr = new ArrayList<>();
                strArr.add(input);
                strArr.sort(String.CASE_INSENSITIVE_ORDER);
                charString.put(inputChar.toString(), strArr);
                // 카운트 따로 저장
                countMap.put(inputChar.toString(), 1);
            }

            
        }// end of while((input = br.readLine())!= null) {
        // 출력파트

        List<Map.Entry<String, ArrayList<String>>> entryList = charString.entrySet()
                .stream()
                .sorted((entry1, entry2)->{
                    int sizeCompare = Integer.compare(countMap.get(entry2.getKey()),countMap.get(entry1.getKey()));

                    if(sizeCompare!=0){
                        return sizeCompare;
                    }
                    return entry1.getValue().get(0).compareTo(entry2.getValue().get(0));
                })
                .collect(Collectors.toList());
// comparingByValue 메소드는 매개변수로 받은 Comparator 객체를 사용해서 Map의 값을 비교하고, 그 결과에 따라 Map.Entry들을 정렬합니다.
// Comparator 객체의 compare 메소드는 내부적으로 사용되며, comparingByValue 메소드는 이 Comparator 객체를 정렬에 적용하기 위해 사용됩니다.

        for (int i = 0; i < Math.min(5, entryList.size()); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < entryList.get(i).getValue().size(); j++) {
                sb.append(entryList.get(i).getValue().get(j) + " ");
            }


            System.out.println("Group of size " + countMap.get(entryList.get(i).getKey()) + ": " + sb.toString() + ".");
        }


    }

}

