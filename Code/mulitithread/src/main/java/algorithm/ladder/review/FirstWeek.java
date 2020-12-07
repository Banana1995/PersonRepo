package algorithm.ladder.review;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class FirstWeek {

    public static void main(String[] args) {
        FirstWeek firstWeek = new FirstWeek();
        List<String> words = new ArrayList<>();
        words.add("dog");
        words.add("dad");
        words.add("dgdg");
        words.add("can");
        char[][] board = new char[3][4];
        board[0] = "doaf".toCharArray();
        board[1] = "agai".toCharArray();
        board[2] = "dcan".toCharArray();
        List<String> res = firstWeek.wordSearchII(board, words);
        log.info("结果为：{}", Arrays.toString(res.toArray()));

    }

    class TreeNode implements Comparator {

        private String word;
        private Map<Character, TreeNode> children = new HashMap<>();

        @Override
        public int compare(Object o1, Object o2) {
            return 0;
        }
    }

    class TrieTree {
        private TreeNode root = new TreeNode();//dummy node

        public void insert(String wor) {
            TreeNode cur = root;
            for (int i = 0; i < wor.length(); i++) {
                char ac = wor.charAt(i);
                if (cur.children.containsKey(ac)) {
                    cur = cur.children.get(ac);
                } else {
                    TreeNode child = new TreeNode();
                    cur.children.put(ac, child);
                    cur = child;
                }
            }
            cur.word = wor;
        }
    }

    class Tweet {
        public int id;
        public int user_id;
        public String text;

    }

    class Node {
        private int time;
        private Tweet tweet;

        public Node(int time, Tweet tweet) {
            this.time = time;
            this.tweet = tweet;
        }
    }

    public List<String> wordSearchII(char[][] board, List<String> words) {
        List<String> result = new ArrayList<>();
        if (words == null || words.size() == 0 || board == null || board[0].length == 0) {
            return result;
        }

        TrieTree tree = new TrieTree();
        for (String word : words) {
            tree.insert(word);
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {
                // visited[i][j] = true;
                dfsFind(i, j, visited, result, board, tree.root);
                // visited[i][j] = false;
            }
        }

        return result;

    }

    private static int[] dx = new int[]{0, 1, 0, -1};
    private static int[] dy = new int[]{1, 0, -1, 0};


    public void dfsFind(int x,
                        int y,
                        boolean[][] visited,
                        List<String> result,
                        char[][] board,
                        TreeNode node
    ) {
        char curchar = board[x][y];
        if (!node.children.containsKey(curchar)) {
            return;
        }
        TreeNode curNode = node.children.get(curchar);
        if (curNode.word != null && !result.contains(curNode.word)) {//往左和往右可能是同个字母，这样会形成相同的排列字符串，所以这里需要去重
            result.add(node.word);
        }

        for (int k = 0; k < 4; k++) {
            int nextx = x + dx[k];
            int nexty = y + dy[k];
            if (!inbound(nextx, nexty, board.length, board[0].length) || visited[nextx][nexty]) {
                continue;
            }
            LinkedList<Integer> list = new LinkedList<>();
            dfsFind(nextx, nexty, visited, result, board, curNode);
        }
        visited[x][y] = false;
    }

    private boolean inbound(int x, int y, int rows, int columns) {
        if (x < rows && y < columns && x >= 0 && y >= 0) {
            return true;
        }
        return false;
    }

    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        int l = 0;
        int r = s.length() - 1;
        String lowers = s.toLowerCase();
        while (l < r) {
            if (!Character.isDigit(lowers.charAt(l)) || !Character.isLetter(lowers.charAt(l))) {
                l++;
                continue;
            }
            if (!Character.isDigit(lowers.charAt(r)) || !Character.isLetter(lowers.charAt(r))) {
                r--;
                continue;
            }
            if (lowers.charAt(l) != lowers.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int len = s.length();
        //dp[i][j]表示s的第[i,j]位是否为回文字符串
        boolean[][] dp = new boolean[len][len];
        //初始化第i位
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
            if (i + 1 < len && s.charAt(i) == s.charAt(i + 1)) {//中间两位相等也是回文字符串
                dp[i][i + 1] = true;
            }
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 2; j < len; j++) {//指针向两端的方向往外扩
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                }
            }
        }
        //取出最长的结果
        int maxLen = 0;
        String res = null;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    public String RotateString2(String str, int left, int right) {
        if (str == null || str.length() == 0) {
            return "";
        }
        int len = str.length();
        int startIndex = left - right;
        startIndex = startIndex < 0 ? len + startIndex % len : startIndex % len;//需要讨论偏移量是否会超出字符串长度
        String pre = str.substring(0, startIndex);
        String sufix = str.substring(startIndex, len);
        return sufix + pre;
    }

    public int longestPalindromeSubseq(String s) {
        if (s == null) {
            return 0;
        }
        //dp[i][j]: dp[i+1][j-1]+2  s(i)==s(j)
        //dp[i][j]: max(dp[i+1][j],dp[i][j-1])  s(i)！=s(j)
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        //i > j情况下的子序列不存在，均为0，由于dp[i][j]需要有它左，左下，下边三个数据推导出来，因此选择逆序遍历，从小往上，从左往右进行
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][len - 1];
    }

    private boolean inbound(int len, int i, int j) {
        if (i >= 0 && j >= 0 && i <= len - 1 && j <= len - 1) {
            return true;
        }
        return false;
    }

    private boolean theOtherSetContains(char otherchar, Map<Character, Integer> otherMap) {
        if (otherMap.containsKey(otherchar)) {
            Integer time = otherMap.get(otherchar);
            if (time == 1) {
                otherMap.remove(otherchar);
            } else {
                otherMap.put(otherchar, time - 1);
            }
            return true;
        }
        return false;
    }

    static class Color {
        static int red = 0;
        static int white = 1;
        static int blue = 2;
    }

    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int pivot = Color.white;
        int l = 0;
        int r = nums.length;
        int temp = 0;
        //[0,l) < pivot
        //（temp r） = pivot
        // (r,len-1] >pivot
        while (temp <= r) {
            if (nums[temp] < pivot) {
                swap(nums, temp, l);
                temp++;
                l++;
            } else if (nums[temp] == pivot) {
                temp++;
            } else {
                r--;
                swap(nums, temp, r);
                temp++;
            }
        }

    }

    public void patition(int[] A, int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start, right = end;
        // key point 1: pivot is the value, not the index
        int midIndex = (start + end) / 2;
        int pivot = A[midIndex];
        // key point 2: every time you compare left & right, it should be
        // left <= right not left < right
//        [0,left) <= pivot
//        (right,len-1] > pivot
        while (left <= right) {
            if (A[left] <= pivot) {
                left++;
            }
            if (A[right] > pivot) {
                right--;
            }
            if (A[left] > pivot) {

            }
        }
        patition(A, 0, midIndex - 1);
        patition(A, midIndex + 1, end);
    }

    public int kthSmallest(int k, int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        return quickselect(0, nums.length - 1, k, nums);
    }

    public int quickselect(int start, int end, int k, int[] nums) {
        if (start == end) {
            return nums[start];
        }
        int l = start;
        int r = end;
        int pivot = nums[(l + r) / 2];
        while (l <= r) {
            while (l <= r && nums[l] > pivot) {
                l++;
            }
            while (l <= r && nums[r] < pivot) {
                r--;
            }
            if (l <= r) {
                swap(nums, l, r);
                l++;
                r--;
            }
        }
        if (start + k - 1 <= r) {
            return quickselect(start, r, k, nums);
        }
        if (start + k - 1 >= l) {
            return quickselect(l, end, start + k - l, nums);
        }
        return nums[r + 1];

    }

    private void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    public int triangleCount(int[] S) {
        if (S == null || S.length == 0) {
            return 0;
        }
        int count = 0;
        Arrays.sort(S);
        for (int i = 0; i < S.length - 1; i++) {

            for (int j = i + 1; j < S.length; j++) {
                int k = j + 1;
                while (k < S.length && S[i] + S[j] > S[k]) {
                    k++;
                }
                count = count + k - j - 1;
            }
        }
        return count;
    }


    public boolean sequenceReconstruction(int[] org, int[][] seqs) {

        Map<Integer, Set<Integer>> seqMap = new HashMap<>();
        Map<Integer, Integer> degreeMap = new HashMap<>();

        // int seqlen = seqs.length;
        int orgLen = org.length;

        for (int i = 0; i < orgLen; i++) {
            degreeMap.put(org[i], 0);
            seqMap.put(org[i], new HashSet<>());
        }

        for (int[] seq : seqs) {

            for (int i = 1; i < seq.length; i++) {
                if (!degreeMap.containsKey(seq[i])) {
                    return false;
                }
                //统计依赖该数字的集合
                boolean addSuccess = seqMap.get(seq[i - 1]).add(seq[i]);
                //统计每隔数字的入度
                if (addSuccess) {
                    degreeMap.put(seq[i], degreeMap.get(seq[i]) + 1);
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < orgLen; i++) {
            if (degreeMap.get(org[i]) == 0) {
                queue.offer(org[i]);
            }
        }
        int count = 0;
        while (queue.size() == 1) {
            Integer next = queue.poll();
            if (next != org[count]) {
                return false;
            }
            count++;
            Set<Integer> depSet = seqMap.get(next);
            if (depSet.size() == 0) {
                break;
            }
            for (Integer num : depSet) {
                degreeMap.put(num, degreeMap.get(num) - 1);
                if (degreeMap.get(num) == 0) {
                    queue.offer(num);
                }
            }
        }
        return count == orgLen;
    }

//    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
//        if (nodes == null || nodes.size() == 0){
//            return new ArrayList<>();
//        }
//        List<List<Integer>>  res = new ArrayList<>();
//
//        Set<UndirectedGraphNode> visited = new HashSet<>();
//        for(UndirectedGraphNode node: nodes){
//            if(visited.contains(node)){
//                continue;
//            }
//            List<Integer> connectList = new ArrayList<>();
//            res.addAll(connectList);
//        }
//        return res;
//    }
//
//    public List<Integer> bfsFind(UndirectedGraphNode node, Set<UndirectedGraphNode> visited){
//        List<Integer> connect = new ArrayList<>();
//        Queue<UndirectedGraphNode> queue = new LinkedList<>();
//        queue.offer(node);
//        visited.add(node);
//        connect.add(node.label);
//
//        while (!queue.isEmpty()){
//            UndirectedGraphNode nextNode = queue.poll();
//            for(UndirectedGraphNode nei: nextNode.neighbors){
//                if(visited.contains(nei)){
//                    continue;
//                }
//                queue.offer(nei);
//                connect.add(nei.label);
//                visited.add(nei);
//            }
//        }
//
//        return connect;
//    }
}