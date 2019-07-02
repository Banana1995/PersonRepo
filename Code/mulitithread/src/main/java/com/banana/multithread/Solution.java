package com.banana.multithread;

import java.util.*;

public class Solution {
    public String findLongestWord(String s, List<String> d) {
        if (s == null || d.size() <= 0) {
            return "";
        }
        Collections.sort(d, new compareSpecial());
        for (String word : d) {
            if (isSubStr(s,word)) {
                return word;
            }
        }

        return "";
    }

    private boolean isSubStr(String s, String word) {
        int j = 0;
        for (int i = 0; i < word.length(); i++) {
            while (!(s.charAt(j) == word.charAt(i))) {
                j++;
                if (j >= s.length()) {
                    return false;
                }
            }
        }
        return true;
    }

    class compareSpecial implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            if (o1.length() > o2.length()) {
                return 1;
            } else if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }
            return -1;
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String a = "aab";
        String b = solution.findLongestWord(a, new ArrayList<String>(2));
        System.out.println(b);
    }
}


