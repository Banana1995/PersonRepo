package algorithm.ladder;

import algorithm.AlgorithmUtil;

import java.util.*;

public class SeventhWeek {
    public static void main(String[] args) {
        SeventhWeek seventhWeek = new SeventhWeek();
//        int[] data = new int[]{-3, 1, 3, -3, 4};
//        int[] data = new int[]{1, 2, 3, 4};
//        int[] data = new int[]{3, 6, 9, 1};
//        int integers = seventhWeek.subarraySumII(data, 1, 3);
        int[][] data = AlgorithmUtil.praseMatrix("[[0,1,0],[1,1,1],[0,1,0]]");
        int integers = seventhWeek.shortestDistance(data);
        System.out.println(integers);
//        System.out.println(Arrays.toString(integers.toArray()));
    }

    public class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public void flatten(TreeNode root) {

        if (root == null) {
            return;
        }
        flatten(root.left);
        TreeNode left = root.left;

        TreeNode right = root.right;
        flatten(right);

        if (left != null) {
            System.out.println("root:" + root.val + "left:" + left.val + "right:" + right.val);
            root.right = left;
            TreeNode tempNode = left;
            while (tempNode.right != null) {
                tempNode = tempNode.right;
            }
            tempNode.right = right;
            tempNode.left = null;
            root.left = null;
        }
    }

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer,
        // rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds,
        // if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds,
        // if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public List<Integer> flatten(List<NestedInteger> nestedList) {
        List<Integer> ans = new ArrayList<>();
        Stack nestedStack = new Stack();
        pushListStack(nestedList, nestedStack);
        while (!nestedStack.isEmpty()) {
            NestedInteger nestedInteger = (NestedInteger) nestedStack.pop();
            if (nestedInteger.isInteger()) {
                ans.add(nestedInteger.getInteger());
            } else {
                List<NestedInteger> sonListNested = nestedInteger.getList();
                pushListStack(sonListNested, nestedStack);
            }
        }
        return ans;
    }

    public void pushListStack(List<NestedInteger> nestedList, Stack res) {
        Stack tempSta = new Stack();
        for (NestedInteger nestedInteger : nestedList) {
            tempSta.push(nestedInteger);
        }
        while (!tempSta.isEmpty()) {
            res.push(tempSta.pop());
        }
    }

    public List<Integer> subarraySum(int[] nums) {
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        sumMap.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (!sumMap.containsKey(sum)) {
                sumMap.put(sum, i);
            } else {
                Integer start = sumMap.get(sum);
                ans.add(start + 1);
                ans.add(i);
                break;
            }
        }
        return ans;
    }

    private Stack data = new Stack();

    public void pushToStack(List<NestedInteger> nestedList) {
        Stack temp = new Stack();
        for (NestedInteger nestedInteger : nestedList) {
            temp.push(nestedInteger);
        }
        while (!temp.isEmpty()) {
            data.push(temp.pop());
        }
    }

    public Integer next() {
        int res = 0;
        if (hasNext()) {
            res = (Integer) data.pop();
        }
        return res;
    }

    public boolean hasNext() {
        if (!data.isEmpty()) {
            NestedInteger peekObj = (NestedInteger) data.peek();
            if (peekObj.isInteger()) {
                return true;
            } else {
                List<NestedInteger> list = peekObj.getList();
                pushToStack(list);
                data.pop();
                return true;
            }
        }
        return false;
    }

    public List<Integer> continuousSubarraySumII(int[] A) {
        int len = A.length;
        int[] presum = new int[len + 1];
        int minsumIndex = 0;
        int maxSum = Integer.MIN_VALUE;
        List<Integer> ans = new ArrayList<>();
        int left = 0;
        int right = 0;
        //第一遍循环时找到最大的中间区间
        for (int i = 1; i <= len; i++) {
            presum[i] = presum[i - 1] + A[i - 1];
            //区间和 = 前缀和 -  最小的前缀和
            if (presum[i] - presum[minsumIndex] > maxSum) {
                maxSum = presum[i] - presum[minsumIndex];
                left = minsumIndex;
                right = i - 1;
            }
            if (presum[i] < presum[minsumIndex]) {
                minsumIndex = i;
            }
        }
        //第二遍循环时找到最小的中间区间，最大区间为两侧的环接区间
        int maxsumIndex = 0;
        for (int i = 1; i <= len; i++) {
            //区间和 = 前缀和 - 最大前缀和
            int tempsum = presum[i] - presum[maxsumIndex];
            if (maxsumIndex == 0 && i == len) {
                continue;
            }
            if (presum[len] - tempsum > maxSum) {
                maxSum = presum[len] - tempsum;
                left = i;
                right = maxsumIndex - 1;
            }
            if (presum[i] > presum[maxsumIndex]) {
                maxsumIndex = i;
            }
        }
        ans.add(left);
        ans.add(right);
        return ans;
    }

    public List<Integer> continuousSubarraySum(int[] A) {
        int len = A.length;
        int presum = 0;
        int minsum = Integer.MAX_VALUE;
        int maxsum = Integer.MIN_VALUE;
        int minPresumIndex = 0;
        List<Integer> ans = new ArrayList<>();
        int left = 0;
        int right = 0;
        for (int i = 1; i < len + 1; i++) {
            presum += A[i - 1];
            if (presum - minsum > maxsum) {
                left = minPresumIndex + 1;
                right = i - 1;
            }
            if (presum < minsum) {
                minsum = presum;
                minPresumIndex = i - 1;
            }
        }
        ans.add(left);
        ans.add(right);
        return ans;
    }

    public int subarraySumII(int[] A, int start, int end) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int len = A.length;
        int count = 0;
        int[] presum = new int[len + 1];

        for (int i = 1; i < len + 1; i++) {
            presum[i] = presum[i - 1] + A[i - 1];
        }
        int rightStart = 1;
        int rightEnd = 1;

        for (int left = 0; left <= len; left++) {
            if (rightStart <= left) {
                rightStart = left + 1;
            }
            if (rightEnd <= left) {
                rightEnd = left + 1;
            }
            while (rightStart <= len && presum[rightStart] - presum[left] < start) {
                rightStart++;
            }
            while (rightEnd <= len && presum[rightEnd] - presum[left] <= end) {
                rightEnd++;
            }
            count += rightEnd - rightStart;
        }
        return count;
    }

    public int[][] submatrixSum(int[][] matrix) {
        int[][] res = new int[2][2];
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] sum = new int[m + 1][n + 1];
        //sum[i][j]定义为从(0,0)到(i,j)点的和
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }

        for (int l = 0; l < m; l++) {
            for (int h = l + 1; h <= m; h++) {
                Map<Integer, Integer> hash = new HashMap<>();
                for (int i = 0; i <= n; i++) {
                    int diff = sum[h][i] - sum[l][i];
                    if (hash.containsKey(diff)) {
                        int k = hash.get(diff);
                        res[0][0] = l;
                        res[0][1] = k;
                        res[1][0] = h - 1;
                        res[1][1] = i - 1;
                        return res;
                    } else {
                        hash.put(diff, i);
                    }
                }
            }
        }
        return res;
    }

    class Pair {
        //前index个数的总和为sum
        int sum;
        int index;

        public Pair(int sum, int index) {
            this.sum = sum;
            this.index = index;
        }
    }

    public int[] subarraySumClosest(int[] nums) {
        int len = nums.length;
        int[] res = new int[2];

        Pair[] prefixSum = new Pair[len + 1];
        prefixSum[0] = new Pair(0, 0);

        for (int i = 1; i < len + 1; i++) {
            int sum = prefixSum[i - 1].sum + nums[i - 1];
            prefixSum[i] = new Pair(sum, i);
        }

        Arrays.sort(prefixSum, Comparator.comparingInt(o -> o.sum));
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < len + 1; i++) {
            int diff = prefixSum[i].sum - prefixSum[i - 1].sum;
            if (diff < minDiff) {
                minDiff = diff;
                int a = prefixSum[i].index - 1;
                int b = prefixSum[i - 1].index - 1;
                res[0] = Math.min(a, b) + 1;
                res[1] = Math.max(a, b);
            }
        }
        return res;
    }

    public int findPeak(int[] A) {
        int len = A.length;
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) {
                return mid;
            } else if (A[mid] < A[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }
        return left;
    }

    public int maximumGap(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }
        List[] tempnum = new ArrayList[10];
        int max = nums[0];
        for (int i = 0; i < len; i++) {
            max = Math.max(nums[i], max);
        }
        int exp = 1;
        while (max > 0) {
            //清楚每个list内的数据
            for (int i = 0; i < 10; i++) {
                if (tempnum[i] == null) {
                    tempnum[i] = new ArrayList();
                }
                tempnum[i].clear();
            }
            //将数字放入list中
            for (int i = 0; i < len; i++) {
                int si = nums[i] / exp % 10;
                tempnum[si].add(nums[i]);
            }
            //将数字拿出到nums数组中
            int j = 0;
            for (List list : tempnum) {
                if (list.isEmpty()) {
                    continue;
                }
                for (int i = 0; i < list.size(); i++) {
                    nums[j] = (int) list.get(i);
                    j++;
                }
            }
            max /= 10;
            exp *= 10;
        }
        int maxgap = 0;
        for (int i = 1; i < len; i++) {
            int gap = nums[i] - nums[i - 1];
            maxgap = Math.max(gap, maxgap);
        }
        return maxgap;
    }

    public int maxSlidingMatrix(int[][] matrix, int k) {
        if (matrix == null || matrix[0] == null || matrix.length < k || matrix[0].length < k) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] summatrix = new int[n + 1][m + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                //原数组当前点 + 前缀和左边点 + 前缀和上面点 - 前缀和左上点
                summatrix[i][j] = matrix[i - 1][j - 1] + summatrix[i][j - 1] + summatrix[i - 1][j] - summatrix[i - 1][j - 1];
            }
        }
        int maxksum = Integer.MIN_VALUE;
        for (int i = k; i <= n; i++) {
            for (int j = k; j <= m; j++) {
                int sumk = summatrix[i][j] - summatrix[i][j - k] - summatrix[i - k][j] + summatrix[i - k][j - k];
                maxksum = Math.max(maxksum, sumk);
            }
        }

        return maxksum;
    }

    public int shortestDistance(int[][] grid) {
        if (grid == null || grid[0] == null || grid[0].length == 0) {
            return -1;
        }
        int n = grid.length;
        int m = grid[0].length;
        int[][] sumhouse = new int[n + 1][m + 1];//房子数量累计
        int[][] sumx = new int[n + 1][m + 1];//横左标累计
        int[][] sumy = new int[n + 1][m + 1];//纵坐标累计
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sumhouse[i][j] = grid[i - 1][j - 1] + sumhouse[i - 1][j] + sumhouse[i][j - 1] - sumhouse[i - 1][j - 1];
                sumx[i][j] = grid[i - 1][j - 1] * (i - 1) + sumx[i - 1][j] + sumx[i][j - 1] - sumx[i - 1][j - 1];
                sumy[i][j] = grid[i - 1][j - 1] * (j - 1) + sumy[i - 1][j] + sumy[i][j - 1] - sumy[i - 1][j - 1];
            }
        }
        int minDis = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (grid[i - 1][j - 1] == 0) {
                    //左上区域距离
                    int ans = getDis(sumhouse, sumx, sumy, 1, 1, i, j, i - 1, j - 1);
                    if (ans > minDis) {
                        continue;
                    }
                    //右上区域
                    ans += getDis(sumhouse, sumx, sumy, 1, j + 1, i, m, i - 1, j - 1);
                    if (ans > minDis) {
                        continue;
                    }
                    //左下区域
                    ans += getDis(sumhouse, sumx, sumy, i + 1, 1, n, j, i - 1, j - 1);
                    if (ans > minDis) {
                        continue;
                    }
                    //右下
                    ans += getDis(sumhouse, sumx, sumy, i + 1, j + 1, n, m, i - 1, j - 1);
                    minDis = Math.min(ans, minDis);
                }
            }
        }
        return minDis == Integer.MAX_VALUE ? -1 : minDis;
    }

    private int getDis(int[][] sumhouse, int[][] sumx, int[][] sumy, int lx, int ly, int rx, int ry, int targetx, int targety) {
        int khouse = sumhouse[rx][ry] - sumhouse[lx - 1][ry] - sumhouse[rx][ly - 1] + sumhouse[lx - 1][ly - 1];
        int x = sumx[rx][ry] - sumx[lx - 1][ry] - sumx[rx][ly - 1] + sumx[lx - 1][ly - 1];
        int y = sumy[rx][ry] - sumy[lx - 1][ry] - sumy[rx][ly - 1] + sumy[lx - 1][ly - 1];
        int dis = Math.abs(khouse * targetx - x) + Math.abs(khouse * targety - y);
        return dis;
    }

    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        Map<Character, Integer> charmap = new HashMap<>();
        int count = 0;
        for (char aChar : chars) {
            if (charmap.containsKey(aChar)) {
                Integer acharcount = charmap.get(aChar);
                acharcount--;
                if (acharcount == 0) {
                    charmap.remove(aChar);
                }
                count += 2;
            } else {
                charmap.put(aChar, 1);
            }
        }
        if (charmap.size() > 0) {
            count++;
        }
        return count;
    }
}
