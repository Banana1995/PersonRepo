package algorithm.ladder.normal;

import java.util.Arrays;

public class FirstWeek {

    public static void main(String[] args) {
        FirstWeek firstWeek = new FirstWeek();
        int[] data = new int[]{7, 11, 11, 1, 2, 3, 4};
        int i = firstWeek.twoSum6(data, 47);
        System.out.println(i);
    }

    public int strStr(String source, String target) {
        if (source == null || target == null) {
            return -1;
        }
        for (int k = 0; k < source.length() - target.length() + 1; k++) {
            int l = 0;
            for (; l < target.length(); l++) {
                if (source.charAt(k + l) != target.charAt(l)) {
                    break;
                }
            }
            if (l == target.length()) {
                return k;
            }
        }
        return -1;
    }

    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        String lowerCase = s.toLowerCase();
        if (lowerCase.length() <= 1) {
            return true;
        }
        int left = 0;
        int right = lowerCase.length() - 1;
        while (left < right) {
            if ((lowerCase.charAt(left) - 'a' >= 26 || lowerCase.charAt(left) - 'a' < 0) && (lowerCase.charAt(left) - '0' >= 10 || lowerCase.charAt(left) - '0' < 0)) {
                left++;
                continue;
            }
            if ((lowerCase.charAt(right) - 'a' >= 26 || lowerCase.charAt(right) - 'a' < 0) && (lowerCase.charAt(right) - '0' >= 10 || lowerCase.charAt(right) - '0' < 0)) {
                right--;
                continue;
            }
            if (lowerCase.charAt(left) == lowerCase.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;

    }

    class Pair {
        int a;
        int b;

        public Pair(int be, int en) {
            a = be;
            b = en;
        }

        public int hashCode() {
            return a + b;
        }

        @Override
        public boolean equals(Object obj) {
            Pair another = (Pair) obj;
            return (a == another.a && b == another.b) || (a == another.b && b == another.a);
        }

    }

    public int twoSum6(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int count = 0;
        while (left < right) {
            int remain = target - nums[left];

            if (remain == nums[right]) {
                left++;
                right--;
                count++;
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
                while (left < right && nums[right] == nums[right + 1]) {
                    right--;
                }
            } else if (remain < nums[right]) {
                right--;
            } else {
                left++;
            }
        }
        return count;
    }
}
