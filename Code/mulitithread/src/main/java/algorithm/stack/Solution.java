package algorithm.stack;

import java.util.*;

public class Solution {

    public int largestRectangleArea(int[] heights) {
        //maining a monotony increase stack whick save the index of array
        Stack<Integer> stashStack = new Stack<>();
        int maxRectangle = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!stashStack.isEmpty()) {
                if (heights[i] >= heights[stashStack.peek()]) {
                    stashStack.push(i);
                    break;
                } else {
                    int high = heights[stashStack.pop()];
                    int weith = i - stashStack.peek() - 1;
                    maxRectangle = high * weith > maxRectangle ? high * weith : maxRectangle;
                }
            }
            if (stashStack.isEmpty()) {
                stashStack.push(i);
            }
        }
        return maxRectangle;
    }

    public int findKthPositive(int[] arr, int k) {
        int[] ans = new int[k];
        int val = 0;
        int ansIndex = 0;
        int arrIndex = 0;
        while (val <= arr[arr.length - 1] + k && ansIndex < k) {
            val++;
            if (arrIndex >= arr.length || arr[arrIndex] != val) {
                ans[ansIndex] = val;
                ansIndex++;
            } else {
                arrIndex++;
            }

        }
        return ans[k - 1];
    }

    public boolean canConvertString(String s, String t, int k) {
        if (s.length() != t.length()) {
            return false;
        }
        List<Integer> stringChangeArray = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char chars = s.charAt(i);
            char chart = t.charAt(i);
            int dis = chart - chars;
            if (dis != 0) {
                stringChangeArray.add(dis < 0 ? 26 + dis : dis);
            }
        }
        Integer[] disArray = new Integer[stringChangeArray.size()];
        stringChangeArray.toArray(disArray);
        Arrays.sort(disArray);
        long sum = 0;
        int mi = 0; //幂次数
        for (int i = 0; i < disArray.length; i++) {
            if (disArray[i] + 26 * mi > sum) {
                sum = disArray[i] + 26 * mi;
            } else {
                mi++;
                sum = disArray[i] + 26 * mi;
            }
            if (sum > k) {
                return false;
            }
        }
        return true;
    }

    public String makeGood(String s) {
        ArrayDeque<Character> sDeque = new ArrayDeque<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!sDeque.isEmpty() && (sDeque.getLast() == chars[i] + 32 || sDeque.getLast() + 32 == chars[i])) {
                sDeque.removeLast();
                continue;
            }
            sDeque.addLast(chars[i]);
        }
        StringBuilder sb = new StringBuilder();
        while (!sDeque.isEmpty()) {
            sb.append(sDeque.removeFirst());
        }

        return sb.toString();
    }

    public char findKthBit(int n, int k) {
        String s1 = "0";
        StringBuilder sTemp = new StringBuilder(s1);
        for (int i = 2; i <= n; i++) {
            char[] tempChars = sTemp.toString().toCharArray();
            Stack<Character> tempStack = new Stack<>();
            for (int j = 0; j < tempChars.length; j++) {
                tempStack.push(tempChars[j]);
            }
            sTemp.append("1");
            while (!tempStack.isEmpty()) {
                sTemp.append(tempStack.pop() == '1' ? "0" : "1");
            }
        }
        return sTemp.charAt(k - 1);
    }

    public class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    private String split = ",";
    private String emptyNode = "#";

    public int findKthLargest(int[] nums, int k) {
        int l = 0;
        int r = nums.length - 1;
        int target = nums.length - k;
        partition(nums, l, r);
//        while (true) {
//            int index =
//            if (index == target) {
//                return nums[index];
//            } else if (index < target) {
//                l = index + 1;
//            } else {
//                r = index - 1;
//            }
//        }
//        partition(nums,0,nums.length-1);
        return 1;
    }

    private int partition(int[] nums, int l, int r) {
        int pivotIndex = (l + r) / 2;
        swap(nums, l, pivotIndex);
        int pivot = nums[l];
        int left = l;
        int right = r + 1;
        while (left < right) {
            while (nums[++left] < pivot) {
                if (left == r) break;//[l,left] < pivot
            }
            while (nums[--right] > pivot) {
                if (right == l) break;//[right,r] > pivot
            }
            if (left < right) {
                swap(nums, left, right);
            }
        }
        //将pivot换到right下标位置
        swap(nums, l, right);
        return right;
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

//    private void partition(int[] nums, int l, int r) {
//        if (l==r){
//            return;
//        }
//        int left = l;
//        int right = r;
//        int pivot = nums[(left + right) / 2];
//        while (left <= right) {
//            while (left <= right && nums[left] < pivot) {
//                //保证[l,left-1] < pivot
//                left++;
//            }
//            while (left <= right && nums[right] > pivot) {
//                //保证[right+1,r] > pivot
//                right--;
//            }
//            if (left <= right) {
//                int temp = nums[left];
//                nums[left] = nums[right];
//                nums[right] = temp;
//                left++;
//                right--;
//            }
//        }
////        if (left == nums.length) {
////            return right;
////        }
////        if (right == -1) {
////            return left;
////        }
////        return nums[left] == pivot ? left : right;
//        partition(nums,l,right);
//        partition(nums,left,r);
//    }

    public static void main(String[] args) {
        int[] data = new int[]{3, 2, 1, 5, 6, 4};
        Solution so = new Solution();
        so.findKthLargest(data, 2);
    }
}
