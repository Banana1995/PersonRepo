package algorithm.ladder.normal;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class ThirdWeek {
    public static void main(String[] args) {
        ThirdWeek thirdWeek = new ThirdWeek();
        int[] data = new int[]{1, 2, 3, 4};
        int target = 5;
        int k = 2;
        List<List<Integer>> res = thirdWeek.kSumII(data, k, target);
        log.info("当前结果为：{}", Arrays.toString(res.toArray()));
    }

    public class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public int closestValue(TreeNode root, double target) {

        if (root == null) {
            return -1;
        }
        TreeNode smallerOne = findFirstSmallerNode(root, target);
        TreeNode biggerOne = findRightBiggerNode(root, target);
        if (smallerOne != null && biggerOne != null) {
            if (target - smallerOne.val < biggerOne.val - target) {
                return smallerOne.val;
            } else {
                return biggerOne.val;
            }
        }
        if (smallerOne == null) {
            return biggerOne.val;
        }
        return smallerOne.val;

    }

    public TreeNode findFirstSmallerNode(TreeNode root, double target) {
        if (root == null) {
            return null;
        }
        if (root.val > target) {
            return findFirstSmallerNode(root.left, target);
        }
        TreeNode res = findFirstSmallerNode(root.right, target);
        return res == null ? root : res;
    }

    public TreeNode findRightBiggerNode(TreeNode root, double target) {
        if (root == null) {
            return null;
        }
        if (root.val < target) {
            return findRightBiggerNode(root.right, target);
        }
        TreeNode rightBiggerNode = findRightBiggerNode(root.left, target);

        return rightBiggerNode == null ? root : rightBiggerNode;

    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        char[] chars = digits.toCharArray();
        int[] nums = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            nums[i] = chars[i] - '0';
        }
        List<String> answer = new ArrayList<>();
        findCombineStrings(nums, 0, answer, "");
        return answer;
    }

    private void findCombineStrings(int[] nums, int depth, List<String> ans, String strb) {
        if (depth == nums.length) {
            ans.add(strb);
            return;
        }
        int dig = nums[depth];
        for (int i = 0; i < 3; i++) {
            char curChar = (char) ((dig - 2) * 3 + 'a' + i);
//            strb.append(curChar);
            findCombineStrings(nums, depth + 1, ans, strb + curChar);
        }
    }

    public List<String> stringPermutation2(String str) {
        List<String> ans = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return ans;
        }
        boolean[] visited = new boolean[str.length()];
//        Set<String> tempSet = new HashSet<>();
        dfsPer(str.toCharArray(), 0, visited, ans, new StringBuilder());
//        ans.addAll(tempSet);
        return ans;
    }

    public void dfsPer(char[] str, int depth, boolean[] visited, List<String> ans, StringBuilder strbuilder) {
        if (depth == str.length) {
            ans.add(strbuilder.toString());
            return;
        }
        for (int i = 0; i < str.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i > 0 && str[i] == str[i - 1] && visited[i - 1]) {
                continue;
            }
            visited[i] = true;
            strbuilder.append(str[i]);
            dfsPer(str, depth + 1, visited, ans, strbuilder);
            strbuilder.deleteCharAt(depth);
            visited[i] = false;
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        dfsFindCombination(candidates, 0, 0, 0, target, ans, new LinkedList<>());

        return ans;
    }

    private void dfsFindCombination(int[] candidates, int start, int depth, int combinSum, int target, List<List<Integer>> ans, LinkedList<Integer> combinate) {
        if (depth == candidates.length) {
            return;
        }
        if (combinSum == target) {
            List<Integer> tempList = new ArrayList<>(combinate);
            Collections.sort(tempList);
            ans.add(tempList);
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] + combinSum > target) {
                continue;
            }
            combinate.push(candidates[i]);
            dfsFindCombination(candidates, i, depth + 1, candidates[i] + combinSum, target, ans, combinate);
            combinate.pop();
        }
    }

    public List<List<Integer>> kSumII(int[] A, int k, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfsCombineSum(A, 0, 0, k, 0, target, res, new LinkedList<>());
        return res;
    }

    private void dfsCombineSum(int[] A, int start, int sum, int k, int depth, int target, List<List<Integer>> res, LinkedList<Integer> combine) {
        if (depth > k) {
            return;
        }
        if (k == depth && sum == target) {
            List<Integer> newCopy = new ArrayList<>(combine);
            res.add(newCopy);
            return;
        }
        for (int i = start; i < A.length; i++) {
            if (A[i] + sum > target) {
                continue;
            }
            combine.push(A[i]);
            dfsCombineSum(A, i + 1, A[i] + sum, k, depth + 1, target, res, combine);
            combine.pop();
        }
    }


}
