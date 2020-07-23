package algorithm.twopoint;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TwoPointFollowup {

    String ans = "";

    int start = 0;
    int end = 0;
    int minLen = Integer.MAX_VALUE;

    public String minWindow(String s, String t) {

        findWindow(s, t);
        return ans;
    }


    private void findWindow(String s, String t) {

        int[] map = new int[128];
        char[] tarChars = t.toCharArray();
        for (char tarChar : tarChars) {
            map[tarChar]++;
        }
        int len = s.length();
        int j = 0;
        int match = 0;
        for (int i = 0; i < len; i++) {
            while (j <= len) {

                if (match == t.length()) {
                    int size = j - i;
                    if (size < minLen) {
                        minLen = size;
                        start = i;
                        end = j;
                    }
                    break;
                } else {
                    j++;
                    if (j <= len) {
                        map[s.charAt(j - 1)]--;
                        if (map[s.charAt(j - 1)] >= 0) {
                            match++;
                        }
                    }
                }
            }
            map[s.charAt(i)]++;
            if (map[s.charAt(i)] > 0) {
                match--;
            }
        }
        ans = s.substring(start, end);
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int maxLen = 0;
        int[] map = new int[256];
        int len = s.length();
        int j = 0;
        for (int i = 0; i < len; i++) {
            while (j < len) {
                if (map[s.charAt(j)] > 0) {
                    //有重复
                    break;
                } else {
                    map[s.charAt(j)]++;
                    j++;
                    maxLen = j - i > maxLen ? (j - i) : maxLen;
                }
            }
            map[s.charAt(i)]--;
        }
        return maxLen;
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int slen = s.length();
        int maxLen = 0;
        int j = 0;
//        Set<Character> temp = new HashSet<>();
        int[] map = new int[256];
        int distincLetter = 0;
        for (int i = 0; i < slen; i++) {
            while (j < slen) {
                if (map[s.charAt(j)] == 0) {
                    distincLetter++;
                }
                map[s.charAt(j)]++;
                j++;
                if (distincLetter <= k) {
                    maxLen = j - i > maxLen ? j - i : maxLen;
                    //包含字符数量小于等于k
                } else {
                    break;
                }
            }
            map[s.charAt(i)]--;
            if (map[s.charAt(i)] == 0) {
                distincLetter--;
            }
        }
        return maxLen;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode i = dummy;
        ListNode j = dummy;
        for (int k = n; k >=0; k--) {
            j = j.next;
        }
        while ( j.next != null) {
            j = j.next;
            i = i.next;
        }
        i.next = i.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        String s = "ababffeezzz";
        TwoPointFollowup twoPointFollowup = new TwoPointFollowup();
        int sna = twoPointFollowup.lengthOfLongestSubstringKDistinct(s, 2);
        System.out.println(sna);

    }
}
