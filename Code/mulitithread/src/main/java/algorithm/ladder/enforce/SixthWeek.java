package algorithm.ladder.enforce;

import java.util.*;

public class SixthWeek {

    public static void main(String[] args) {
        SixthWeek sixthWeek = new SixthWeek();
//        int[] rea = new int[]{3, 4, 8, 5};
        String[] rea = new String[]{"a", "b", "ba", "babbab", ""};
        List<String> b = sixthWeek.kDistance(rea, "", 5);
//        System.out.println(b);
        System.out.println(Arrays.toString(b.toArray()));
    }

    public boolean firstWillWin(int n) {
        boolean[] dp = new boolean[n + 1];
        boolean[] visited = new boolean[n + 1];

        search(n, dp, visited);
        return dp[n];
    }

    private void search(int j, boolean[] dp, boolean[] visited) {
        if (visited[j]) {
            return;
        }
        if (j == 0) {
            dp[j] = false;
        } else if (j == 1) {
            dp[j] = true;
        } else if (j == 2) {
            dp[j] = true;
        } else if (j == 3) {
            dp[j] = false;
        } else {
            search(j - 2, dp, visited);
            search(j - 3, dp, visited);
            search(j - 4, dp, visited);
            boolean firstTakeOne = dp[j - 2] && dp[j - 3];
            boolean firstTakeTwo = dp[j - 3] && dp[j - 4];
            dp[j] = firstTakeOne || firstTakeTwo;
        }
        visited[j] = true;
    }

    public boolean firstWillWinII(int[] values) {

        int sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        int len = values.length;
        int[] dp = new int[len];

        int firstMaxValue = searchValue(values, dp, len - 1);
        return firstMaxValue > sum / 2;

    }

    private int searchValue(int[] values, int[] dp, int i) {
        int len = values.length;
        if (dp[i] != 0) {
            return dp[i];
        }
        if (i == 0) {
            dp[i] = values[len - 1];
        } else if ((i == 1) || (i == 2)) {
            dp[i] = values[len - 1] + values[len - 2];
        } else {
            searchValue(values, dp, i - 2);
            searchValue(values, dp, i - 3);
            if (i >= 4) {
                searchValue(values, dp, i - 4);
            }
            int left = Math.min(dp[i - 2], dp[i - 3]);//先手拿1个的情况
            int right = i >= 4 ? Math.min(dp[i - 3], dp[i - 4]) : dp[i - 3];//先手拿2个的情况
            dp[i] = Math.max(left + values[len - i + 1], right + values[len - i + 1] + values[len - i + 2]);
        }
        return dp[i];
    }

    public boolean firstWillWin(int[] values) {
        int len = values.length;
        int sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        int[][] dp = new int[len][len];
        searchIntervalValues(dp, values, 0, len - 1);
        return dp[0][len - 1] > sum / 2;
    }

    private void searchIntervalValues(int[][] dp, int[] values, int i, int j) {
        if (i > j || i < 0 || j < 0) {
            return;
        }
        if (dp[i][j] != 0) {
            return;
        }
        if (i == j) {
            dp[i][j] = values[i];
        }
        searchIntervalValues(dp, values, i + 1, j - 1);
        searchIntervalValues(dp, values, i + 2, j);
        searchIntervalValues(dp, values, i, j - 2);
        int left = 0;
        if (i + 2 <= j) {
            left = Math.min(dp[i + 2][j], dp[i + 1][j - 1]);
        }
        int right = 0;
        if (i <= j - 2) {
            right = Math.min(dp[i][j - 2], dp[i + 1][j - 1]);
        }
        dp[i][j] = Math.max(left + values[i], right + values[j]);
    }

    public int stoneGame(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int len = A.length;
        int[][] dp = new int[len][len];
        int[][] sum = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                sum[i][j] = j == i ? A[j] : sum[i][j - 1] + A[j];
            }
        }
        searchStone(A, 0, len - 1, dp, sum);
        return dp[0][len - 1];
    }

    private int searchStone(int[] A, int i, int j, int[][] dp, int[][] sum) {
        if (i > j || i < 0) {
            return 0;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        if (i == j) {
            dp[i][j] = 0;
            return dp[i][j];
        }
        int temp = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            temp = Math.min(temp, searchStone(A, i, k, dp, sum) + searchStone(A, k + 1, j, dp, sum) + sum[i][j]);
        }
        dp[i][j] = temp;
        return dp[i][j];
    }

    public int stoneGame2(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int len = A.length;
        //dp[i][j]:从第i位到第j位合并所需要的代价
        int[][] dp = new int[2 * len][2 * len];
        int[] sum = new int[2 * len];
        for (int i = 0; i < 2 * len; i++) {
            sum[i] = i == 0 ? A[i] : sum[i - 1] + A[(i) % len];
        }
        searchStone(sum, dp, A, 0, 2 * len - 1);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            int j = i + len - 1;
            ans = Math.min(ans, dp[i][j]);
        }
        return ans;
    }

    private void searchStone(int[] sum, int[][] dp, int[] A, int i, int j) {
        if (j < i || i < 0) {
            return;
        }
        if (i == j) {
            dp[i][j] = 0;
            return;
        }
        if (dp[i][j] != 0) {
            return;
        }
        int minTemp = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            searchStone(sum, dp, A, i, k);
            searchStone(sum, dp, A, k + 1, j);
            minTemp = Math.min(minTemp, dp[i][k] + dp[k + 1][j] + sum[j] - (i == 0 ? 0 : sum[i - 1]));
        }
        dp[i][j] = minTemp;
    }

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[2][m + 1];
        for (int i = 0; i < 2; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < m + 1; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (i == 0) {
                    dp[i % 2][j] = j;
                } else if (j == 0) {
                    dp[i % 2][j] = i;
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i % 2][j] = Math.min(dp[(i - 1) % 2][j - 1], Math.min(dp[(i - 1) % 2][j] + 1, dp[i % 2][j - 1] + 1));
                } else {
                    dp[i % 2][j] = Math.min(dp[(i - 1) % 2][j - 1], Math.min(dp[(i - 1) % 2][j], dp[i % 2][j - 1])) + 1;
                }
            }
        }
        return dp[n % 2][m];
    }

    public int backPack(int m, int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int len = A.length;
        boolean[][] dp = new boolean[len][m + 1];
        dp[0][0] = true;
        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 && j - A[i] >= 0) {
                    dp[i][j] = j == A[i];
                } else if (i >= 1 && j - A[i] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else if (i >= 1 && j - A[i] >= 0) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - A[i]];
                }
            }
        }
        int res = 0;
        for (int i = m - 1; i >= 0; i--) {
            if (dp[len - 1][i]) {
                return i;
            }
        }
        return res;
    }

    public int backPackII(int m, int[] A, int[] V) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int len = A.length;
        int[][] dp = new int[len + 1][m + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= m; j++) {
                if (j - A[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else if (j - A[i - 1] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - A[i - 1]] + V[i - 1]);
                }
            }
        }
        return dp[len][m];
    }

    public int longestCommonSubsequence(String A, String B) {
        if (A == null || A.length() == 0 || B == null || B.length() == 0) {
            return 0;
        }
        int m = A.length();
        int n = B.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    if (A.charAt(i - 1) == B.charAt(j - 1)) {
                        dp[i][j] = Math.max(Math.max(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
                    } else {
                        dp[i][j] = Math.max(Math.max(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]);
                    }
                }
            }
        }
        return dp[m][n];

    }

    public List<String> kDistance(String[] words, String target, int k) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String word1 = words[i];
            insert(word1);
        }
        int len = target.length();
        int[] dp = new int[len + 1];
        for (int i = 0; i < len + 1; i++) {
            dp[i] = i;
        }
        dfs(root, dp, len, k, target, ans);
        return ans;
    }

    private void dfs(Node cur, int[] dp, int len, int k, String target, List<String> ans) {
        if (cur.isword && dp[len] <= k) {
            ans.add(cur.word);
            return;
        }
        int[] newDp = new int[len + 1];
        newDp[0] = dp[0] + 1;
        for (Map.Entry<Character, Node> childEntry : cur.child.entrySet()) {
            Character childChar = childEntry.getKey();
            Node childNode = childEntry.getValue();
            for (int i = 1; i < len + 1; i++) {
                if (childChar == target.charAt(i - 1)) {
                    newDp[i] = Math.min(Math.min(dp[i - 1], dp[i] + 1), newDp[i - 1] + 1);
                } else {
                    newDp[i] = Math.min(Math.min(dp[i - 1], dp[i]), newDp[i - 1]) + 1;
                }
            }
            dfs(childNode, newDp, len, k, target, ans);
        }
    }

    private Node root = new Node();

    class Node {
        private Map<Character, Node> child = new HashMap<>();
        private boolean isword;
        private char ach;
        private String word;
    }

    private void insert(String word) {
        char[] chars = word.toCharArray();
        Node cur = root;
        for (char aChar : chars) {
            if (!cur.child.containsKey(aChar)) {
                Node newNode = new Node();
                newNode.ach = aChar;
                cur.child.put(aChar, newNode);
                cur = newNode;
            } else {
                cur = cur.child.get(aChar);
            }
        }
        cur.isword = true;
        cur.word = word;
    }

    public int kSum(int[] A, int k, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int len = A.length;

        //dp[i][j][k]:前i个里选出j个数字，组成和为k的个数
        int[][][] dp = new int[len + 1][len + 1][target + 1];

        for (int i = 0; i < len + 1; i++) {
            dp[i][0][0] = 1;
        }

        for (int i = 1; i < len + 1; i++) {
            for (int j = 1; j <= i && j <= k; j++) {
                for (int l = 1; l <= target; l++) {
                    if (l - A[i - 1] >= 0) {
                        //选择第i个数
                        dp[i][j][l] = dp[i - 1][j - 1][l - A[i - 1]];
                    }
                    //不选择第i个数
                    dp[i][j][l] += dp[i - 1][j][l];
                }
            }
        }
        return dp[len][k][target];
    }

}
