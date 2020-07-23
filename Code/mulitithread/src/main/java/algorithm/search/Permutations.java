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
//        int i = permutations.numSquarefulPerms(new int[]{65, 44, 5, 11});
//        System.out.println(re2s);
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
        dfsLetterPermutation(res, start + 1, length, S, currentString);
        if (!Character.isDigit(cur)) {
            currentString.setLength(start);
            currentString.append(Character.isUpperCase(cur) ? Character.toLowerCase(cur) : Character.toUpperCase(cur));
            dfsLetterPermutation(res, start + 1, length, S, currentString);
        }
    }

    private int[][] gatherLen;
    private int[] bestPath;
    private int bestLen;
    int[] path;

    public String shortestSuperstring(String[] A) {
        gatherLen = new int[A.length][A.length];
        bestPath = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                gatherLen[i][j] = A[j].length();
                for (int k = 0; k <= Math.min(A[i].length(), A[j].length()); k++) {
                    if (A[i].substring(A[i].length() - k).equals(A[j].substring(0, k))) {
                        gatherLen[i][j] = A[j].length() - k;
                    }
                }
            }
        }
        path = new int[A.length];
        bestLen = Integer.MAX_VALUE;
        dfsShortestString(A, 0, path, 0, new boolean[A.length]);
        StringBuilder ans = new StringBuilder(A[bestPath[0]]);
        for (int i = 1; i < bestPath.length; i++) {
            int a = bestPath[i - 1];
            int b = bestPath[i];
            ans.append(A[b].substring(A[b].length() - gatherLen[a][b]));
        }
        return ans.toString();
    }

    private void dfsShortestString(String[] A, int depth, int[] path, int curLen, boolean[] used) {
        if (curLen >= bestLen) {
            return;//剪枝
        }
        if (depth == A.length) {
            bestLen = curLen;
            bestPath = path.clone();
            return;
        }
        for (int i = 0; i < A.length; i++) {
            path[depth] = i;
            if (used[i]) continue;
            used[i] = true;
            dfsShortestString(A, depth + 1, path, depth == 0 ? A[i].length() : curLen + gatherLen[path[depth - 1]][i], used);
            used[i] = false;
        }
    }
//
//    private int ans = 0;
//
//    public int numSquarefulPerms(int[] A) {
//        ans=0;
//        Arrays.sort(A);
//        dfsFindSquareFulPerms(A, 0, new LinkedList<>(), new boolean[A.length]);
//        return ans;
//    }


    private void dfsFindSquareFulPerms(int[] A, int de, LinkedList<Integer> cur, boolean[] used) {
        if (de == A.length) {
            ans++;
            return;
        }
        for (int i = 0; i < A.length; i++) {
            if (used[i]) continue;
            if (i > 0 && !used[i - 1] && A[i] == A[i - 1]) continue;
            if (cur.size() > 0 && !isSquareFul(cur.peek(), A[i])) {
                continue;
            }
            cur.push(A[i]);
            used[i] = true;
            dfsFindSquareFulPerms(A, de + 1, cur, used);
            used[i] = false;
            cur.pop();
        }
    }

    private boolean isSquareFul(int a, int b) {
        int c = (int) Math.sqrt(a + b);
        return c * c == a + b;

    }

    private int appendSb(String curStr, StringBuilder stringBuilder) {
        int index = stringBuilder.length();
        int start = stringBuilder.length() > curStr.length() ? curStr.length() : stringBuilder.length();
        int j = index - 1;
        for (int i = start - 1; i >= 0; i--) {
            if (stringBuilder.charAt(j) == curStr.charAt(i)) {
                j--;
            } else {
                j = index - 1;
                if (stringBuilder.charAt(j) == curStr.charAt(i)) {
                    j--;
                }
            }
        }
        int sameLen = 0;
        if (j != index - 1) {
            sameLen = index - 1 - j;
        }
        stringBuilder.append(curStr.substring(sameLen, curStr.length()));
        return index;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxSum(root);
        return ans;
    }

    private int maxSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(0, maxSum(root.left));
        int right = Math.max(0, maxSum(root.right));
        int cur = left + right + root.val;
        ans = cur > ans ? cur : ans;
        return Math.max(left, right) + root.val;
    }

}
