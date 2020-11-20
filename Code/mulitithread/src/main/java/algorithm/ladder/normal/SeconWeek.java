package algorithm.ladder.normal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SeconWeek {


    public static void main(String[] args) {
        SeconWeek seconWeek = new SeconWeek();
        int[] data = new int[]{1, 2, 3};
//        List<List<Integer>> lists = seconWeek.threeSum(data);
        int[] i = seconWeek.kClosestNumbers(data, 2, 3);
//        System.out.println(Arrays.toString(lists.toArray()));
        System.out.println(Arrays.toString(i));
    }

    public List<List<Integer>> threeSum(int[] numbers) {
        int len = numbers.length;
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(numbers);
        for (int i = 0; i < len; i++) {
            int firstnum = numbers[i];
            int twosumTarget = -firstnum;
            List<List<Integer>> twosumAns = twosum(numbers, twosumTarget, i + 1, len - 1, firstnum);
            ans.addAll(twosumAns);
        }
        return ans;
    }

    private List<List<Integer>> twosum(int[] numbers, int target, int begin, int end, int firstnum) {
        int left = begin;
        int right = end;
        List<List<Integer>> twosumAns = new ArrayList<>();
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                List<Integer> temp = new ArrayList<>();
                temp.add(firstnum);
                temp.add(numbers[left]);
                temp.add(numbers[right]);
                twosumAns.add(temp);
                left++;
                right--;
                while (left < right && numbers[left] == numbers[left - 1]) {
                    left++;
                }
                while (left < right && numbers[right] == numbers[right + 1]) {
                    right--;
                }
            } else if (numbers[left] + numbers[right] < target) {
                left++;
            } else if (numbers[left] + numbers[right] > target) {
                right--;
            }
        }
        return twosumAns;
    }

    public int partitionArray(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        int tempStash = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= k) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] < k) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = tempStash;
        return tempStash >= k ? left : left + 1;
    }

    public int[] kClosestNumbers(int[] A, int target, int k) {
        int len = A.length;
        // bisect to find closest to target number index
        int left = 0;
        int right = len - 1;
        int mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (A[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        //跳出循环时right==left 指向的是大于等于mid的第一个值
        left = right - 1;

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            if (left >= 0 && right < len) {
                if (Math.abs(A[left] - target) <= Math.abs((A[right] - target))) {
                    res[i] = A[left];
                    left--;
                } else {
                    res[i] = A[right];
                    right++;
                }
            } else if (left > 0) {
                res[i] = A[left];
                left--;
            } else {
                res[i] = A[right];
                right++;
            }
        }
        return res;
    }

    public int findMin(int[] nums) {
        int len = nums.length;
        LinkedList
        int left = 0;
        int right = len - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1] && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[right];

    }
}
