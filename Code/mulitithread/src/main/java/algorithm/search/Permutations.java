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
        List<List<Integer>> res = permutations.permuteUnique(new int[]{1, 1, 3});
        System.out.println(Arrays.toString(res.toArray()));
    }


}
