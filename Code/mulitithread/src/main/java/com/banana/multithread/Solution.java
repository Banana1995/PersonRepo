package com.banana.multithread;

import java.util.*;

public class Solution {
    public String findLongestWord(String s, List<String> d) {
        if (s == null || d.size() <= 0) {
            return "";
        }
        Collections.sort(d, new compareSpecial());
        for (String word : d) {
            if (isSubStr(s, word)) {
                return word;
            }
        }

        return "";
    }

    private boolean isSubStr(String s, String word) {
        int j = 0;
        for (int i = 0; i < word.length(); i++) {
            while (!(s.charAt(j) == word.charAt(i))) {
                j++;
                if (j >= s.length()) {
                    return false;
                }
            }
        }
        return true;
    }

    class compareSpecial implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            if (o1.length() > o2.length()) {
                return 1;
            } else if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }
            return -1;
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        int[] res = solution.intersection(nums1, nums2);
        System.out.println(Arrays.toString(res));
    }


//    private int binarySearch(int[] nums, int s, int e, int target) {
//        if (s > e) {
//            return -1;
//        }
//        if (s == e) {
//            return nums[s] == target ? s : -1;
//        }
//        int mid = (s + e) / 2;
//        if (nums[mid] == target) {
//            return mid;
//        } else if (nums[mid] < target) {
//            return binarySearch(nums, mid + 1, e, target);
//        } else {
//            return binarySearch(nums, s, mid - 1, target);
//        }
//
//    }

    public int divide(int dividend, int divisor) {
        //edge case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int a = Math.abs(dividend);
        int b = Math.abs(divisor);
//        int a = ((dividend >> 31) ^ dividend) - (dividend >> 31);
//        int b = ((divisor >> 31) ^ divisor) - (divisor >> 31);
        int res = 0;
        while (a - b >= 0) {
            int x = 0;
            while (a - (b << 1 << x) >= 0) {
                x++;
            }
            a -= b << x;
            res += 1 << x;
        }
        return ((dividend >> 31) ^ (divisor >> 31)) == 0 ? res : (-res);
    }

    public int[] findRightInterval(int[][] intervals) {
        if (intervals.length <= 1) {
            int[] res = new int[1];
            res[0] = -1;
            return res;
        }
        int[] res = new int[intervals.length];
        //interval start point & array index
        TreeMap<Integer, Integer> intervalStartIndexMap = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++) {
            intervalStartIndexMap.put(intervals[i][0], i);
        }
        for (int i = 0; i < intervals.length; i++) {
            Integer key = intervalStartIndexMap.ceilingKey(intervals[i][1]);
            res[i] = intervalStartIndexMap.get(key) == null ? -1 : intervalStartIndexMap.get(key);
        }
        return res;
    }


//    public int hIndex(int[] citations) {
//        if (citations.length < 1) {
//            return 0;
//        }
//        return binarySearch(citations, 0, citations.length - 1);
//    }

//    int binarySearch(int[] citations, int s, int e) {
//        int len = citations.length;
//        while (s <= e) {
//            int midIndex = (s + e) >> 1;
//            if (midIndex < 1) {
//                if (citations[midIndex] >= len - midIndex) {
//                    return len - midIndex;
//                } else {
//                    s = midIndex + 1;
//                }
//            } else {
//                if (citations[midIndex] >= (len - midIndex) && citations[midIndex - 1] < (len - midIndex + 1)) {
//                    return len - midIndex;
//                } else if (citations[midIndex] < len - midIndex) {
//                    s = midIndex + 1;
//                } else {
//                    e = midIndex - 1;
//                }
//            }
//        }
//        return 0;
//    }

    public int[] intersection(int[] nums1, int[] nums2) {
        return nums1.length <= nums2.length ? handleData(nums1, nums2) : handleData(nums2, nums1);
    }

    int[] handleData(int[] shortNums, int[] longNums) {
        Set<Integer> res = new HashSet<>();
        Arrays.sort(shortNums);
        Arrays.sort(longNums);
        for (int i = 0; i < shortNums.length; i++) {
            if (recusiveBinarySearch(longNums, 0, longNums.length - 1, shortNums[i])) {
                res.add(shortNums[i]);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    boolean binarySearch(int[] num, int s, int e, int target) {
        while (s <= e) {
            int mid = (s + e) >> 1;
            if (num[mid] == target) {
                return true;
            }
            if (num[mid] > target) {
                e = mid - 1;
            }
            if (num[mid] < target) {
                s = mid + 1;
            }
        }
        return false;
    }

    boolean recusiveBinarySearch(int[] num, int s, int e, int target) {
        if (s > e) {
            return false;
        }
        int mid = (s + e) >> 1;
        if (num[mid] == target) {
            return true;
        } else if (num[mid] > target) {
            return recusiveBinarySearch(num, s, mid - 1, target);
        } else {
            return recusiveBinarySearch(num, mid + 1, e, target);
        }
    }

}


