package algorithm.ladder;

import algorithm.AlgorithmUtil;

import java.util.*;

public class FourthWeek {

    public static void main(String[] args) {
        FourthWeek fourthWeek = new FourthWeek();
        int[] nums = new int[]{232, 124, 456};
//        int[][] nums = AlgorithmUtil.praseMatrix("[[1,2,1,2,1,2,1],[2,17,13,6,5,17,2],[1,15,8,10,8,15,1],[2,14,12,11,12,14,2],[1,2,1,2,1,2,1]]");
//        double sqrt = fourthWeek.copyBooksII(4, nums);
        int peakII = fourthWeek.woodCut(nums, 7);
        System.out.println(peakII);
    }

    public double sqrt(double x) {
        double left = 0;
        double right = Math.max(1.0, x);
        double eps = 1e-12;
        while (left + eps <= right) {
            double mid = left + (right - left) / 2;
            double temp = mid * mid;
            if (temp < x) {
                left = mid;
            } else if (temp == x) {
                return mid;
            } else if (temp > x) {
                right = mid;
            }
        }
        return right;
    }

    public class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public int minMeetingRooms(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(o -> o.start));
        int count = 0;
        PriorityQueue<Integer> endMinQueue = new PriorityQueue<>();
        for (int i = 0; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            int start = interval.start;
            int end = interval.end;
            count++;
            if (!endMinQueue.isEmpty() && start > endMinQueue.peek()) {
                count--;
                endMinQueue.poll();
            }
            endMinQueue.offer(end);
        }
        return count;
    }


    private boolean canfind(int[] nums, int k, double avg) {
        double[] presum = new double[nums.length + 1];
        for (int i = 0; i <= nums.length; i++) {
            if (i == 0) {
                presum[i] = nums[i] - avg;
            } else if (i == nums.length) {
                presum[i] = presum[i - 1];
            } else {
                presum[i] = presum[i - 1] + nums[i] - avg;//通过每个数均减去avg后，将原先的平均数计算需要除k的操作规避了，简化了代码

            }
        }
        double minPreValue = 0;
        for (int i = k; i <= nums.length; i++) {
            //通过不断计算距离当前点k位之前的最小前缀和，拿当前点的前缀和减去最小的那个就可获得>=k的范围的累积和
            if (i < nums.length) {
                minPreValue = Math.min(minPreValue, presum[i - k]);
            }
            if (presum[i] - minPreValue >= 0) {//>=k范围的累积和若>=0则说明存在一个平均值比avg更大
                return true;
            }
        }
        return false;
    }

    public double maxAverage(int[] nums, int k) {
        double left = nums[0], right = nums[0];
        for (int i = 0; i < nums.length; i++) {
            left = Math.min(left, nums[i]);
            right = Math.max(right, nums[i]);
        }
        double diff = 1e-5;
        while (left + diff <= right) {
            double mid = left + (right - left) / 2;
            if (canfind(nums, k, mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public int countOfAirplanes(List<Interval> airplanes) {
        airplanes.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        PriorityQueue<Integer> endPrioQueue = new PriorityQueue<>();
        int count = 0;
        int res = 0;
        for (Interval airplane : airplanes) {
            int start = airplane.start;
            count++;
            int end = airplane.end;
            if (!endPrioQueue.isEmpty() && start >= endPrioQueue.peek()) {
                count--;
                endPrioQueue.poll();
            }
            res = Math.max(count, res);
            endPrioQueue.offer(end);
        }
        return res;
    }

    public int copyBooks(int[] pages, int k) {
        if (pages == null || pages.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        for (int page : pages) {
            right += page;
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;
            //判断最慢的人复制完成耗时是否会>=mid
            if (lowestCanlower(pages, mid, k)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }
        return left;
    }

    private boolean lowestCanlower(int[] pages, int limit, int k) {
        int presum = 0;
        int count = 0;
        for (int j = 0; j <= pages.length; j++) {
            if (j < pages.length && pages[j] > limit) {
                count = Integer.MAX_VALUE;
                break;
            } else {
                if (j < pages.length) {
                    presum += pages[j];
                }
                if (presum > limit) {
                    count++;
                    presum = pages[j];
                } else if (presum == limit) {
                    count++;
                    presum = 0;
                }
            }
        }
        if (count == k) {
            return presum == 0;
        }
        return count <= k;
    }


    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : -dividend;
        }
        int sign = ((divisor > 0 && dividend > 0) || (divisor < 0 && dividend < 0)) ? 1 : 0;
        long lgdivisor = Math.abs((long) divisor);
        long lgdividend = Math.abs((long) dividend);
        int ans = div(lgdividend, lgdivisor);
        return sign == 1 ? ans : -ans;
    }

    private int div(long lgdividend, long lgdivisor) {
        if (lgdivisor > lgdividend) return 0;
        int ans = 1;
        long tempdivisor = lgdivisor;
        while (lgdividend - tempdivisor > tempdivisor) {
            tempdivisor += tempdivisor;
            ans += ans;
        }
        return ans + div(lgdividend - tempdivisor, lgdivisor);
    }

    public int findDuplicate(int[] nums) {
        int left = 1;
        int right = nums.length - 1;
//        1,2,3,3,4,5
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (lowermidcount(mid, nums) <= mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private int lowermidcount(int mid, int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= mid) {
                count++;
            }
        }
        return count;
    }

    public int copyBooksII(int n, int[] times) {
        int left = 0;
        int maxtime = times[0];
        for (int time : times) {
            maxtime = Math.max(time, maxtime);
        }
        int right = n * maxtime;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (midtimecopybooks(mid, times) < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private int midtimecopybooks(int mid, int[] times) {
        int count = 0;
        for (int i = 0; i < times.length; i++) {
            count += mid / times[i];
        }
        return count;
    }

    public List<Integer> findPeakII(int[][] A) {
        List<Integer> ans = new ArrayList<>();
        int m = A.length;
        int n = A[0].length;
        int left = 1;
        int right = m - 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int maxValueIndex = findMaxValueIndexOfMid(A, mid);//当前mid行的最大值
            if (A[mid][maxValueIndex] > A[mid - 1][maxValueIndex] && A[mid][maxValueIndex] > A[mid + 1][maxValueIndex]) {
                ans.add(mid);
                ans.add(maxValueIndex);
                return ans;
            } else if (A[mid][maxValueIndex] < A[mid - 1][maxValueIndex]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private int findMaxValueIndexOfMid(int[][] A, int mid) {
        int[] midarray = A[mid];
        int maxvalue = midarray[0];
        int index = 0;
        for (int i = 0; i < midarray.length; i++) {
            if (midarray[i] > maxvalue) {
                maxvalue = midarray[i];
                index = i;
            }
        }
        return index;
    }

    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            inQueue(deque, i, nums);
            if (i < k - 1) {
                continue;
            }
            ans.add(deque.peekFirst());
            outDeque(deque, i - k + 1);
        }
        return ans;
    }

    private void inQueue(Deque<Integer> deque, int pos, int[] nums) {
        while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[pos]) {
            deque.pollLast();
        }
        deque.offerLast(pos);
    }

    private void outDeque(Deque<Integer> deque, int pos) {
        if (!deque.isEmpty() && deque.peekFirst() == pos) {
            deque.pollFirst();
        }
    }

    public int woodCut(int[] L, int k) {
        if (L == null || L.length == 0) {
            return 0;
        }
        int left = 1;
        int right = L[0];
        for (int i = 0; i < L.length; i++) {
            right = Math.max(right, L[i]);
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (cutMidCount(L, mid) >= k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    private int cutMidCount(int[] L, int limit) {
        int count = 0;
        for (int i = 0; i < L.length; i++) {
            count += L[i] / limit;
        }
        return count;
    }

}

