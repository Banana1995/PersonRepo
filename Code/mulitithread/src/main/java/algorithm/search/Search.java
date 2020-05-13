package algorithm.search;

import java.util.ArrayList;
import java.util.List;

public class Search {

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        dfsgetLetterCombine(digits, 0, null, res);
        return res;
    }

    private void dfsgetLetterCombine(String digits, int index, String curStr, List<String> beforeRes) {
        if (index == digits.length()) {
            if (curStr != null) {
                beforeRes.add(curStr);
            }
            return;
        }
        Integer curr = Integer.parseInt(String.valueOf(digits.charAt(index)));
        String[] numberStr = getNumberStr(curr);
        for (String numstr : numberStr) {
            dfsgetLetterCombine(digits, index + 1, curStr == null ? numstr : curStr + numstr, beforeRes);
        }
    }

    private String[] getNumberStr(int digit) {
        switch (digit) {
            case 2:
                return new String[]{"a", "b", "c"};
            case 3:
                return new String[]{"d", "e", "f"};
            case 4:
                return new String[]{"g", "h", "i"};
            case 5:
                return new String[]{"j", "k", "l"};
            case 6:
                return new String[]{"m", "n", "o"};
            case 7:
                return new String[]{"p", "q", "r", "s"};
            case 8:
                return new String[]{"t", "u", "v"};
            case 9:
                return new String[]{"w", "x", "y", "z"};
            default:
                return new String[0];
        }
    }


}
