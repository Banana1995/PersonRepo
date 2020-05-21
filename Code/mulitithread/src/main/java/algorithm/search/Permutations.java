package algorithm.search;

import java.util.*;

public class Permutations {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);//排序是回溯去重的前提
        dfsPermute(nums, 0, new boolean[nums.length], new LinkedList<>(), res);
        return res;
    }

    private void dfsPermute(int[] nums, int depth, boolean[] used, LinkedList<Integer> cur, List<List<Integer>> res) {
        if (depth == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            //used数组表示在每层中被使用的数组index
            //这里用！used[i-1] 是指在当前层i-1刚刚被使用过，回溯撤销了选择。因此当遍历到第i个值的时候可以直接跳过。
            if (i > 0 && !used[i - 1] && nums[i] == nums[i - 1]) continue;
            cur.push(nums[i]);
            used[i] = true;
            dfsPermute(nums, depth + 1, used, cur, res);
            used[i] = false;
            cur.pop();
        }
    }

    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        List<String> res = permutations.letterCasePermutation("C");
        System.out.println(Arrays.toString(res.toArray()));
    }

    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        dfsLetterPermutation(res, 0, S.length(), S, new StringBuilder());
        return res;
    }

    private void dfsLetterPermutation(List<String> res, int start, int length, String S, StringBuilder currentString) {
        if (start == length) {
            res.add(currentString.toString());
            return;
        }
        char cur = S.charAt(start);
        currentString.append(cur);
//        if (currentString.length() == length) {
//            res.add(currentString.toString());
//            return;
//        }
        dfsLetterPermutation(res, start + 1, length, S, currentString);
        if (!Character.isDigit(cur)) {
//            currentString = new StringBuilder(currentString.substring(0, start));
            currentString.setLength(start);
            currentString.append(Character.isUpperCase(cur) ? Character.toLowerCase(cur) : Character.toUpperCase(cur));
            dfsLetterPermutation(res, start + 1, length, S, currentString);
        }

    }

}
