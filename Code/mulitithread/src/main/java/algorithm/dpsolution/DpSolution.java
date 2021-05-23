package algorithm.dpsolution;

import algorithm.AlgorithmUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class DpSolution {

    public static void main(String[] args) {
//        String[] dict = new String[]{"de","ding","co","code","lint"};
//        Set<String> dictSet = new HashSet<String>(Arrays.asList(dict));
        DpSolution dpSolution = new DpSolution();
        int[][] data = AlgorithmUtil.praseMatrix(
                "[[0,0,0,0,1,0,0],[0,0,1,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,1],[0,1,0,0,0,0,0]," +
                        "[0,0,0,1,0,0,0],[0,0,0,0,0,0,0],[0,0,1,0,0,0,1],[0,0,0,0,1,0,0]]");
        int dis = dpSolution.shortestDistance(data,new int[]{0,0}, new int[]{8,6});
        System.out.println(dis);
    }
    class Node{
        int x;
        int y;
        int step;
        public Node(int i, int j, int step){
            this.x = i;
            this.y = j;
            this.step = step;
        }
    }

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        Node root = new Node(start[0], start[1], 0);
        Queue<Node> bfsQueue = new PriorityQueue<Node>((n1, n2) -> n1.step - n2.step);
        bfsQueue.offer(root);

        boolean[][] visited = new boolean[m][n];
        visited[root.x][root.y] = true;

        int[] dx = new int[]{1,0,-1,0};
        int[] dy = new int[]{0,1,0,-1};

        while(!bfsQueue.isEmpty()){
            Node next = bfsQueue.poll();

            log.info("curx:{},cury:{}",next.x,next.y);
            if(next.x == destination[0] && next.y == destination[1]){
                return next.step;
            }
            for(int i = 0; i < 4; i++){

                int count = 0;
                int nx = next.x + dx[i];
                int ny = next.y + dy[i];
                count++;
                while(!touchWall(nx, ny, maze)){
                    nx = nx + dx[i];
                    ny = ny + dy[i];
                    count++;
                }
                if(!inBound(nx, ny, maze)){
                    continue;
                }
                if(maze[nx][ny] == 1){
                    nx = nx - dx[i];
                    ny = ny - dy[i];
                    count--;
                }
                if(!visited[nx][ny]){
                    bfsQueue.offer(new Node(nx, ny, next.step + count));
                    visited[nx][ny] = true;
                    log.info("nx:{},ny:{}",nx,ny);
                }

            }

        }
        return -1;
    }

    private boolean inBound(int x, int y, int[][] maze){
        if(x >= 0 && y >=0 && x < maze.length && y < maze[0].length){
            return true;
        }
        return false;
    }


    private boolean touchWall(int x, int y , int[][] maze){
        if(x > 0 && y > 0 && x < maze.length - 1 && y < maze[0].length - 1 && maze[x][y] != 1){
            return false;
        }
        return true;
    }





    public int totalOccurrence(int[] A, int target) {
        if (A == null || A.length == 0){
            return 0;
        }
        int index = findLeftTar(A,target);
        int count = 0;
        while(index < A.length && A[index] == target){
            count++;
        }
        return count;
    }

    private int findLeftTar(int[] A, int target){
        int left = 0;
        int right = A.length - 1;
        while(left + 1 < right){
            int mid = (left + right) / 2;
            if(A[mid] == target){
                right = mid;
            } else if(A[mid] <  target){
                left = mid;
            }else{
                right = mid;
            }
        }
        if(A[left] == target){
            return left;
        }

        return right;

    }


    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null || nums1.length == 0|| nums2.length == 0){
            return new int[0];
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int len = nums1.length;
        Set<Integer> res = new HashSet<>();
        for(int i = 0; i < len; i++){
            if(findinAnotherNums(nums1[i],nums2)){
                res.add(nums1[i]);
            }
        }
        int[] ans = new int[res.size()];
        int i = 0;
        for(Integer ele: res){
            ans[i] =ele;
            i++;
        }
        return ans;
    }

    private boolean findinAnotherNums(int target, int[] nums){
        int left = 0;
        int right = nums.length - 1;

        while(left + 1 < right){
            int mid = (left + right) / 2;
            if(nums[mid] == target){
                return true;
            }else if(nums[mid] < target){
                left = mid;
            }else{
                right = mid;
            }
        }
        if(nums[left] == target || nums[right] == target){
            return true;
        }

        return false;
    }

    public int fastPower(int a, int b, int n) {
        long ans = 1, tmp = a;

        while (n != 0) {
            if (n % 2 == 1) {
                ans = (ans * tmp) % b;
            }
            tmp = (tmp * tmp) % b;
            n = n / 2;
        }

        return (int) ans % b;
    }
    public int fastPower2(int a, int b, int n) {
        long ans = 1, tmp = a;
        while (n != 0) {
            if (n % 2 == 1) {
                ans = (ans * tmp) ;
            }
            tmp = (tmp * tmp) ;
            n = n / 2;
        }
        return (int) ans % b;
    }
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        if(n==1){
            return x;
        }
        return getPow(x,n);
    }

    public static  double getPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        long beta = ((n>>31)^n)-(n>>31);
        long p = beta / 2;
        double value = 1;
        if((beta&1)!=0){
            value=  x*getPow(x*x,p);
        }else{
            value = getPow(x*x,p);
        }
        return n >> 31 == 0 ? value : 1 / value;
    }
    class Pair {
        private int w;
        private int h;

        public Pair(int width, int high) {
            w = width;
            h = high;
        }

    }

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        int len = envelopes.length;
        Pair[] pairArray = new Pair[len];
        for (int i = 0; i < len; i++) {
            Pair pair = new Pair(envelopes[i][0], envelopes[i][1]);
            pairArray[i] = pair;
        }

        Arrays.sort(pairArray, new Comparator<Pair>() {
            public int compare(Pair p1, Pair p2) {
                int diff = p1.w - p2.w;
                if (diff == 0) {
                    diff = p1.h - p2.h;
                }
                return diff;
            }
        });
        System.out.println(Arrays.toString(pairArray));
        int[] dp = new int[len];

        for (int i = 0; i < len; i++) {
            dp[i] = 1;
        }
        int res = 0;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (pairArray[i].w > pairArray[j].w && pairArray[i].h > pairArray[j].h) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    Arrays.binarySearch(dp, 0, len, pairArray[i].h);
                }
            }
        }
        return dp[len - 1];
    }

    public int deduplication(int[] nums) {
        Set<Integer> hash = new HashSet<>();
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int j = len - 1;
        for (int i = 0; i < len; i++) {
            if (hash.contains(nums[i])) {
                nums[i] = nums[j];
                j--;
                i--;
            } else {
                hash.add(nums[i]);
            }
        }
        return hash.size();

    }

    int[][] dp;
    int n, m;


    public int characterReplacement(String s, int k) {
        if (s == null) {
            return 0;
        }
        int l = 0;
        int r = 0;
        int len = s.length();
        int count = 0;
        int[] charArray = new int[26];
        int maxLen = 0;

        for (; l < len; l++) {
            while (r < len & count <= k + 1) {
                charArray[s.charAt(r) - 'A']++;
                if (charArray[s.charAt(r) - 'A'] == 1) {
                    count++;
                }
                r++;
            }
            if (count < k + 1) {
                maxLen = Math.max(maxLen, r - l);
            } else {
                maxLen = Math.max(maxLen, r - l - 1);
            }
            charArray[s.charAt(l) - 'A']--;
            if (charArray[s.charAt(l) - 'A'] == 0) {
                count--;
            }
        }

        return maxLen;

    }

    //下，右，上，左
    private static int[] dx = new int[]{1, 0, -1, 0};
    private static int[] dy = new int[]{0, 1, 0, -1};


    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return result;
        }
        return dfsFind(s, wordDict, new HashMap<>());
    }

    List<String> dfsFind(String str, Set<String> dict, Map<String, List<String>> memo) {
        if (memo.containsKey(str)) {
            return memo.get(str);
        }

        List<String> tempRes = new ArrayList<>();
        if (dict.contains(str)) {
            tempRes.add(str);
        }
        for (int i = 0; i <= str.length(); i++) {
            String prefix = str.substring(0, i);
            String sufix = str.substring(i, str.length());
            if (!dict.contains(prefix)) {
                continue;
            }
            List<String> subList = dfsFind(sufix, dict, memo);
            for (String suf : subList) {
                tempRes.add(prefix + " " + suf);
            }

        }
        memo.put(str, tempRes);
        return tempRes;

    }

    public int wordBreak3(String s, Set<String> dict) {
        if (s == null) {
            return 0;
        }
        int len = s.length();
        int count = 0;
        Set<String> lowerdict = new HashSet<>();
        for (String di : dict) {
            lowerdict.add(di.toLowerCase());
        }
        if (dict.contains(s)) {
            count += 1;
        }

        for (int i = 1; i < len; i++) {
            String leftstr = s.substring(0, i);
            String rigtstr = s.substring(i, len);
            int left = wordBreak3(leftstr, dict);
            int right = wordBreak3(rigtstr, dict);
            count += left * right;
        }
        return count;
    }

    public int wordBreak4(String s, Set<String> dict) {
        int n = s.length();
        String lowerS = s.toLowerCase();

        Set<String> lowerDict = new HashSet<String>();
        for (String str : dict) {
            lowerDict.add(str.toLowerCase());
        }
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String sub = lowerS.substring(i, j + 1);
                if (lowerDict.contains(sub)) {
                    dp[i][j] = 1;
                }
            }
        }
        for (int[] inn : dp) {
            log.info("init:{}", Arrays.toString(inn));
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                for (int k = i; k < j; k++) {
                    dp[i][j] += (dp[i][k] * dp[k + 1][j]);
                }
            }
        }
        log.info("dict:{}", Arrays.toString(dict.toArray()));
        for (int[] inn : dp) {
            log.info("inn:{}", Arrays.toString(inn));
        }
        return dp[0][n - 1];
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
