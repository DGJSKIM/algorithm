import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

class Main {

    /**
     * 구조를 바꿔보자
     * chatGpt 에게 내 코드를 더 빠르게 작동시킬 방법을 물어봤다
     * 1. HashMap 사용하여 중복체크 과정을 줄일것
     */

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        ArrayList<ang> angArr = new ArrayList<>();
        HashMap<String, ang> angMap = new HashMap<>();
        //일단 EOF 처리 ctrl D
        while ((input = br.readLine())!=null) {
            char[] inputChar = input.toCharArray();
            Arrays.sort(inputChar);
            // 기존 저장된 값들과 비교
            String sortedInput = new String(inputChar);

            if(angMap.containsKey(sortedInput)){
                ang an = angMap.get(sortedInput);
                if (!an.strArr.contains(input)) { // 만족하는 값이 없다면
                    an.strArr.add(input);
                    an.strArr.sort(String.CASE_INSENSITIVE_ORDER);
                }
                an.count++;
            }
            else{
                ang an = new ang();
                an.charArr = inputChar;
                an.strArr = new ArrayList<String>();
                an.strArr.add(input);
                an.strArr.sort(String.CASE_INSENSITIVE_ORDER);
                an.count=1;
                angMap.put(sortedInput,an);

                angArr.add(an);
            }
        }// end of while((input = br.readLine())!= null) {
        // 출력파트

        angArr.sort(Comparator.comparing((ang an) -> -an.count)
                .thenComparing((ang an) -> an.strArr.get(0).toLowerCase()));

        for (int i = 0; i < Math.min(5, angArr.size()); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < angArr.get(i).strArr.size(); j++) {
                sb.append(angArr.get(i).strArr.get(j) + " ");
            }


            System.out.println("Group of size " + angArr.get(i).count + ": " + sb.toString() + ".");
        }


    }
    private static class ang{ // 애너그램 그룹
        char[] charArr;
        ArrayList<String> strArr;
        int count;

    }

}



