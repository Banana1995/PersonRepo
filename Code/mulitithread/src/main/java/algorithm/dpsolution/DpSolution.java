package algorithm.dpsolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DpSolution {

    public static void main(String[] args) {
        int[] cost = new int[]{2, 2, 2, 2, 2};
        DpSolution dpSolution = new DpSolution();
        int res = dpSolution.findNumberOfLIS(cost);
        System.out.println(res);

    }

    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] sum = new int[2000];
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < len; i++) {
            int temp = 1;
            for (int j = 0; j < i; j++) {
                int jthTemp = nums[i] > nums[j] ? dp[j] + 1 : 1;
                temp = Math.max(temp, jthTemp);
            }
            sum[temp]++;
            dp[i] = temp;
            res = Math.max(dp[i], res);
        }
        System.out.println(res);
        return sum[res]+1;

    }

    public int minCostClimbingStairs(int[] cost) {
//            dp[i] = Min(dp[i-1],dp[i-2]) + A[i];


        int step0Cost = minCostClimbingStairs(0, cost);
        int step1Cost = minCostClimbingStairs(1, cost);

        return Math.min(step0Cost, step1Cost);


    }

    public int minCostClimbingStairs(int start, int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[start] = cost[start];//第一步必须使用start
        if (start == cost.length) {
            return dp[start];
        }
        dp[start + 1] = dp[start] + cost[start + 1];
        for (int i = start + 2; i <= cost.length; i++) {
            int costTemp = i == cost.length ? 0 : cost[i];
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + costTemp;
        }
        return dp[cost.length];
    }


    public int minimumTotal(List<List<Integer>> triangle) {
//        dp[i][j] : minmumize path total at i,j
//        dp[i][j] = Min(dp[i - 1][j], dp[i - 1][j - 1]) + A[i][j];
        int m = triangle.size();
        int n = triangle.get(m - 1).size();
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (i == 0) {
                    dp[i][j] = triangle.get(i).get(j);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                }
            }
        }
        Arrays.sort(dp[m - 1]);

        return dp[m - 1][0];

    }

    public int numSpecial(int[][] mat) {
        int res = 0;
        int m = mat.length;
        int n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1 && checkSpe(i, j, mat)) {
                    res++;
                }
            }
        }
        return res;

    }

    private boolean checkSpe(int i, int j, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        for (int k = 0; k < m; k++) {
            if (k == i) {
                continue;
            }
            if (mat[k][j] == 1) {
                return false;
            }
        }

        for (int k = 0; k < n; k++) {
            if (k == j) {
                continue;
            }
            if (mat[i][k] == 1) {
                return false;
            }
        }
        return true;
    }

    public class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

//    public int minCostConnectPoints(int[][] points) {
//        List<Point> pointList = new ArrayList<>();
//        for (int i = 0; i < points.length; i++) {
//            Point p = new Point(points[i][0], points[i][1]);
//            pointList.add(p);
//        }
//
//    }
//
//    private int dfsFind(List<Point> pointList, int dep,int start,LinkedList<Point> cur) {
//
//    }

}
