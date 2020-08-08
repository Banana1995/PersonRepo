package algorithm.trietree;

import java.util.*;

public class Solution {

    class TrieTreeNode {
        private char c;
        private String word;
        private Map<Character, TrieTreeNode> childern = new HashMap<>();
    }

    private TrieTreeNode root = new TrieTreeNode();

    private void insert(String word) {
        char[] chars = word.toCharArray();
        TrieTreeNode cur = root;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (!cur.childern.containsKey(c)) {
                cur.childern.put(c, new TrieTreeNode());
            }
            cur = cur.childern.get(c);
        }
        cur.word = word;
    }

    private String searchRootWord(String word) {
        char[] chars = word.toCharArray();
        TrieTreeNode cur = root;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (cur.childern.containsKey(c)) {
                cur = cur.childern.get(c);
            } else {
                return null;
            }
            if (cur.word != null) {
                return cur.word;
            }
        }
        return null;
    }

    public String replaceWords(List<String> dict, String sentence) {
        String[] sentenceArray = sentence.split(" ");
        for (String s : dict) {
            insert(s);
        }
        for (int i = 0; i < sentenceArray.length; i++) {
            String con = sentenceArray[i];
            String root = searchRootWord(con);
            if (root != null) {
                sentenceArray[i] = root;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < sentenceArray.length; i++) {
            stringBuilder.append(sentenceArray[i]);
            if (i != sentenceArray.length - 1) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        int maxIndex = 0;
        for (int i = 0; i < k; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        ans[0] = nums[maxIndex];
        for (int i = k; i < nums.length; i++) {

            if (maxIndex < i - k + 1) {
                //refind maxIndex
                maxIndex = i - k + 1;
                for (int j = i - k + 1; j <= i; j++) {
                    if (nums[j] > nums[maxIndex]) {
                        maxIndex = j;
                    }
                }
            } else {
                if (nums[i] > nums[maxIndex]) {
                    maxIndex = i;
                }
            }
            ans[i - k + 1] = nums[maxIndex];
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution a = new Solution();
        int[] nums = new int[]{1, -1};
        int[] maximumXOR = a.maxSlidingWindow(nums, 1);
        System.out.println(Arrays.toString(maximumXOR));
    }
}
