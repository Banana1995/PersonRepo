package algorithm.search;

import java.util.*;

public class CombinationSubset {

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


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfsCombination(candidates, target, 0, new LinkedList<>(), res);
        return res;
    }

    private void dfsCombination(int[] candidates, int target, int start, LinkedList<Integer> current, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(current));//current对象需要不断被修改，因此需要复制一份
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            int currValue = candidates[i];
            if (currValue > target) {
                continue;//由于前面未对数组进行排序，所以此处当发现值大于目标时直接跳过。 剪枝处理
            }
            current.push(currValue);
            //由于可以使用重复的数字所以下次的循环起点可以从i开始
            dfsCombination(candidates, target - currValue, i, current, res);
            current.pop();
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        dfsCombin2(candidates, target, 0, new LinkedList<>(), res);

        return res;
    }

    private void dfsCombin2(int[] candidates, int target, int startIndex, LinkedList<Integer> current, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(current));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            //此处的额大于起点值判断条件很重要，因为起点值可能和上一个深度的值相等，这是允许的
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (candidates[i] > target) {
                break; //剪枝操作
            }
            current.push(candidates[i]);
            dfsCombin2(candidates, target - candidates[i], i + 1, current, res);
            current.pop();
        }
    }

    private static int[] source = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfsGetCombination(0, k, n, 0, new LinkedList<>(), res);
        return res;

    }

    private void dfsGetCombination(int depth, int k, int target, int startIndex, LinkedList<Integer> currRes, List<List<Integer>> res) {
        if (k == depth) {
            if (target == 0) {
                res.add(new ArrayList<>(currRes));
            }
            return;
        }
        for (int i = startIndex; i < source.length; i++) {
            if (source[i] > target) {
                break;
            }
            currRes.addLast(source[i]);
            dfsGetCombination(depth + 1, k, target - source[i], i + 1, currRes, res);
            currRes.removeLast();
        }
    }


    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfsCombine(0, k, 0, n, res, new LinkedList<>());
        return res;
    }


    private void dfsCombine(int depth, int k, int start, int n, List<List<Integer>> res, LinkedList<Integer> cur) {
        if (depth == k) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = start; i <= n; i++) {
            cur.push(i);
            dfsCombine(depth + 1, k, i + 1, n, res, cur);
            cur.pop();
        }

    }


    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfsSubsets(nums,0,0,res,new LinkedList<>());
        return res;
    }


    private void dfsSubsets(int[] nums, int depth, int start, List<List<Integer>> res, LinkedList<Integer> cur) {
        if (depth == nums.length) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i == 0) {
                res.add(new ArrayList<>(cur));
            }
            cur.push(nums[i]);
            res.add(new ArrayList<>(cur));
            dfsSubsets(nums, depth + 1, i+1, res, cur);
            cur.pop();
        }
    }

}
