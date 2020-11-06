package algorithm.ladder;

import java.util.Arrays;
import java.util.HashMap;

public class FifthWeek {

    public static void main(String[] args) {
        FifthWeek fifthWeek = new FifthWeek();
//        int[] te = new int[]{2, 4, 8, 4, 0, 7, 8, 9, 1, 2, 4, 7, 1, 7, 3};
        int i = fifthWeek.numDecodings("**********1");
        int b = fifthWeek.numDecodings2("**********1");
        System.out.println("i" + i);
        System.out.println("b" + b);
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public int maxSubArray(int[] nums) {
        //令dp[i]为以第i位为结尾的最大和 ， 则dp[i]= dp[i-1] + (nums[i]>0?nums[i]:0)
        int presum = 0;
        int sum = Integer.MIN_VALUE;
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum = Math.max(nums[i], presum + nums[i]);
            max = Math.max(max, sum);
            System.out.print("max:" + max);
            System.out.print("sum:" + sum);
            presum = sum;
        }
        return max;

    }

//    public int numDecodings(String s) {
//        int len = s.length();
//        if (len == 0) {
//            return 0;
//        }
//        int[] dp = new int[len];
//        dp[0] = (s.charAt(0) - '0') == 0 ? 0 : 1;
//        for (int i = 1; i < len; i++) {
//            int digit = s.charAt(i) - '0';
//            int predigit = s.charAt(i - 1) - '0';
//            int combineDigit = predigit * 10 + digit;
//            if (digit == 0) {
//                //当第i位为0时，它只能与前一位结合
//                if (combineDigit == 10 || combineDigit == 20) {
//                    dp[i] = dp[i - 2];
//                } else {
//                    dp[i] = 0;
//                }
//            } else if (digit > 0 && combineDigit > 10 && combineDigit <= 26) {
//                //第i为可以与前一位结合
//                dp[i] = (i >= 2 ? dp[i - 2] : 0) + dp[i - 1];
//            } else if (combineDigit < 10 || combineDigit > 26) {
//                //第i为不可以与前一位结合,同时需要处理00001的情况
//                dp[i] = (dp[i - 1] == 0) ? 1 : dp[i - 1];
//            }
//        }
//        return dp[len - 1];
//    }

    public int maxSquare(int[][] matrix) {
        //dp[i][j]:以i,j为右下角的正方形边长
        //dp[i][j] = Min(dp[i-1][j-1],dp[i-1][j],dp[i][j-1]) + 1
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j];
                } else {
                    dp[i][j] = matrix[i][j] == 1 ? 1 + Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) : 0;
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max * max;

    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int len = s.length();
        int longest = 1;
        String res = null;
        boolean[][] dp = new boolean[len][len];
        //dp[i][j]:代表从第i位开始至第j位是否为回文字符串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;//单字符本身是回文字符串
            if (longest <= 1) {
                res = s.substring(i, i + 1);
            }
            if (i < len - 1 && (s.charAt(i + 1) == s.charAt(i))) {
                dp[i][i + 1] = true;//字符与旁边的字符相同也是回文字符串
                longest = 2;
                res = s.substring(i, i + 2);
            }
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 2; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                    if (dp[i][j] && (j - i + 1 > longest)) {
                        longest = j - i + 1;
                        res = s.substring(i, j + 1);//左闭右开
                    }
                }
            }
        }
        return res;
    }

    public long houseRobber(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        long[] dp = new long[A.length];
        //dp[i] : 截止当前为止，可以抢到的最大金额
        //dp[i] = Max(dp[i-1],dp[i-2]+dp[i]);
        dp[0] = A[0];
        dp[1] = Math.max(A[0], A[1]);
        for (int i = 2; i < A.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + A[i]);
        }
        return dp[A.length - 1];
    }

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //dp[i]:以i位为结尾的连续子序列中乘积最大的
        int[] maxdp = new int[nums.length];
        int[] mindp = new int[nums.length];
        maxdp[0] = nums[0];
        mindp[0] = nums[0];
        int maxres = maxdp[0];
        for (int i = 1; i < nums.length; i++) {
            maxdp[i] = Math.max(nums[i], maxdp[i - 1] * nums[i]);
            mindp[i] = Math.min(nums[i], mindp[i - 1] * nums[i]);
            if (nums[i] < 0) {
                maxdp[i] = Math.max(mindp[i - 1] * nums[i], maxdp[i]);
            } else {
                mindp[i] = Math.min(maxdp[i - 1] * nums[i], mindp[i]);
            }
            maxres = Math.max(maxres, maxdp[i]);
        }
        return maxres;
    }

    public int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] dp = new int[len];
        //dp[i]为以i为终点的LIS长度
        dp[0] = 1;
        int res = dp[0];
        for (int i = 1; i < len; i++) {
            int maxlen = dp[0];
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxlen = Math.max(maxlen, dp[j] + 1);
                }
            }
            dp[i] = maxlen;
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    private int[] dx = new int[]{1, 0, -1, 0};
    private int[] dy = new int[]{0, 1, 0, -1};

    public int longestContinuousIncreasingSubsequence2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(matrix, m, n, i, j, dp);
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

    private void dfs(int[][] matrix, int m, int n, int x, int y, int[][] dp) {
        if (dp[x][y] != 0) {
            return;
        }
        dp[x][y] = 1;
        int maxlen = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!isvalid(nx, ny, m, n)) {
                continue;
            }
            if (matrix[x][y] > matrix[nx][ny]) {
                dfs(matrix, m, n, nx, ny, dp);
                maxlen = Math.max(maxlen, dp[nx][ny] + 1);
            }
        }
        dp[x][y] = maxlen;
    }

    private boolean isvalid(int x, int y, int m, int n) {
        if (x < m && x >= 0 && y >= 0 && y < n) {
            return true;
        }
        return false;
    }


    private HashMap<String, Boolean> scrambleMap = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (scrambleMap.containsKey(s1 + "#" + s2)) {
            return scrambleMap.get(s1 + "#" + s2);
        }
        int len = s1.length();
        if (len == 1) {
            return s1.equals(s2);
        }
        for (int i = 1; i < len; i++) {
            //i代表的是划分的子串长度
            if ((isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i, len), s2.substring(i, len)))
                    || (isScramble(s1.substring(0, i), s2.substring(len - i, len)) && isScramble(s1.substring(len - i, len), s2.substring(0, i)))
            ) {
                scrambleMap.put(s1 + "#" + s2, true);
                return true;
            }

        }
        scrambleMap.put(s1 + "#" + s2, false);
        return false;
    }

    class Node {
        private int left;
        private int right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Node)) {
                return false;
            }
            Node node = (Node) obj;
            return left == node.left && right == node.right;
        }

        @Override
        public int hashCode() {
            return this.left * 1000 + this.right;
        }
    }

    private HashMap<Node, Integer> nodeMap = new HashMap<>();

    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] extendNums = new int[len + 2];
        extendNums[0] = 1;
        extendNums[len + 1] = 1;
        for (int i = 0; i < len; i++) {
            extendNums[i + 1] = nums[i];
        }
        return searchCoin(0, extendNums.length - 1, extendNums);

    }

    private int searchCoin(int left, int right, int[] nums) {
        if (left == right) return 0;
        Node node = new Node(left, right);
        if (nodeMap.containsKey(node)) {
            return nodeMap.get(node);
        }
        int max = 0;
        for (int i = left + 1; i < right; i++) {
            int leftCoins = searchCoin(left, i, nums);
            int rightCoins = searchCoin(i, right, nums);
            max = Math.max(max, leftCoins + rightCoins + nums[left] * nums[right] * nums[i]);
        }
        nodeMap.put(node, max);
        return max;
    }

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] memo = new int[s.length()];
        int res = ways(s, s.length() - 1, memo);
        return res;
    }

    private int ways(String s, int i, int[] memo) {
        if (i < 0) {
            return 1;
        }
        if (memo[i] != 0) {
            return memo[i];
        }
        final int mod = 1000000007;
        long temp = 0;
        if (s.charAt(i) == '*') {
            //第i位单独作为一个字符
            temp = ((temp + 9L * ways(s, i - 1, memo)) % mod);
            //第i位与前一位结合
            if (i > 0 && s.charAt(i - 1) == '1') {
                temp = ((temp + 9L * ways(s, i - 2, memo)) % mod);
            } else if (i > 0 && s.charAt(i - 1) == '2') {
                temp = ((temp + 6L * ways(s, i - 2, memo)) % mod);
            } else if (i > 0 && s.charAt(i - 1) == '*') {
                temp = ((temp + 15L * ways(s, i - 2, memo)) % mod);
            }
        } else if (s.charAt(i) == '0') {
            if (i > 0 && (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2')) {
                temp = (temp + ways(s, i - 2, memo)) % mod;
            } else if (i > 0 && s.charAt(i - 1) == '*') {
                temp = ((temp + 2L * ways(s, i - 2, memo)) % mod);
            }
        } else if (s.charAt(i) != '0') {
            temp = (temp + ways(s, i - 1, memo)) % mod;
            if (i > 0 && s.charAt(i - 1) == '1') {
                temp = (temp + ways(s, i - 2, memo)) % mod;
            } else if (i > 0 && (s.charAt(i - 1) == '2') && (s.charAt(i) - '0') <= 6) {
                temp = (temp + ways(s, i - 2, memo)) % mod;
            } else if (i > 0 && s.charAt(i - 1) == '*') {
                if ((s.charAt(i) - '0') <= 6) {
                    temp = ((temp + 2L * ways(s, i - 2, memo))) % mod;
                } else {
                    temp = (temp + ways(s, i - 2, memo)) % mod;
                }
            }
        }
        memo[i] = (int) temp;
        return memo[i];
    }

    public int numDecodings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        final int mod = 1000000007;
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            f[i] = 0;
            if (s.charAt(i - 1) == '*') {
                f[i] = (int) ((f[i] + 9L * f[i - 1]) % mod);
                if (i >= 2) {
                    if (s.charAt(i - 2) == '*') {
                        f[i] = (int) ((f[i] + 15L * f[i - 2]) % mod);
                    } else if (s.charAt(i - 2) == '1') {
                        f[i] = (int) ((f[i] + 9L * f[i - 2]) % mod);
                    } else if (s.charAt(i - 2) == '2') {
                        f[i] = (int) ((f[i] + 6L * f[i - 2]) % mod);
                    }
                }
            } else {
                if (s.charAt(i - 1) != '0') {
                    f[i] = (f[i] + f[i - 1]) % mod;
                }
                if (i >= 2) {
                    if (s.charAt(i - 2) == '*') {
                        if (s.charAt(i - 1) <= '6') {
                            f[i] = (int) ((f[i] + 2L * f[i - 2]) % mod);
                        } else {
                            f[i] = (f[i] + f[i - 2]) % mod;
                        }
                    } else {
                        int twoDigits = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0';
                        if (twoDigits >= 10 && twoDigits <= 26) {
                            f[i] = (f[i] + f[i - 2]) % mod;
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.toString(f));
        return f[n];
    }
}
