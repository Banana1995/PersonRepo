package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlgorithmUtil {




    public static int[][] praseMatrix(String matrixInput) {
        matrixInput = matrixInput.replace(" ", "");
        matrixInput = matrixInput.replace("]\n", "],");
        matrixInput = matrixInput.replace("\n", "");
        if (matrixInput.startsWith("[[")) {
            matrixInput = matrixInput.substring(1, matrixInput.length() - 1);
        }
        char[] charArray = matrixInput.toCharArray();
        List<Integer> arrTemp = new ArrayList<>();
        List<int[]> res = new ArrayList<>();

        int start = 0;
        int end = 0;
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (c == '[') {
                start = i + 1;
                arrTemp = new ArrayList<>();
            }

            if (c == ']') {
                end = i;
                String teArrStr = matrixInput.substring(start, end);
                String[] integers = teArrStr.split(",");
                for (String integer : integers) {
                    arrTemp.add(Integer.valueOf(integer));
                }
                int[] te = new int[arrTemp.size()];
                for (int j = 0; j < arrTemp.size(); j++) {
                    te[j] = arrTemp.get(j);
                }
                res.add(te);
            }

        }
        int[][] ans = new int[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
            System.out.println(Arrays.toString(ans[i]));
        }
        return ans;

    }


}
