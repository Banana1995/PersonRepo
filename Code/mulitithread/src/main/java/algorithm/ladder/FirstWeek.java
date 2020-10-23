package algorithm.ladder;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class FirstWeek {

    public static void main(String[] args) {
        FirstWeek firstWeek = new FirstWeek();
        int[] A = new int[]{1, 7, 11};
        int[] B = new int[]{2, 4, 6};
        int res = firstWeek.kthSmallestSum(A, B,3);
        System.out.println(res);


    }

    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.val - n2.val;
            }
        });
        int[] dx = new int[]{1, 0};
        int[] dy = new int[]{0, 1};

        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] matrixMark = new boolean[m][n];

        queue.add(new Node(0, 0, matrix[0][0]));
        for (int i = 0; i < k - 1; i++) {
            Node currentNode = queue.poll();

            for (int j = 0; j < 2; j++) {
                int x = currentNode.x + dx[j];
                int y = currentNode.y + dy[j];
                if (x < n && y < m && !matrixMark[x][y]) {
                    Node newNode = new Node(x, y, matrix[x][y]);
                    queue.add(newNode);
                    matrixMark[x][y] = true;
                }
            }
        }
        return queue.peek().val;
    }

    public int minimumSize(int[] nums, int s) {
        int left = 0;

        int res = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            while (sum > s) {
                sum = sum - nums[left];
                left++;
                res = Math.min(res, i - left + 1);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int ans = Integer.MIN_VALUE;
        int[] hash = new int[256];
        int j = 0;
        for (int i = 0; i < len; i++) {
            while (j < len) {
                int index = s.charAt(j);
                if (hash[index] >= 1) {
                    //repeat
                    break;
                } else {
                    ans = Math.max(ans, j - i + 1);
                    j++;
                    hash[index]++;
                }
            }
        }
        return ans;
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {

        int len = s.length();
        Set<Character> intervalCharSet = new HashSet<>();
        int[] hashChar = new int[256];
        int j = 0;
        if (len <= 0) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            while (intervalCharSet.size() <= k && j < len) {
                char jc = s.charAt(j);
                if (intervalCharSet.size() == k && !intervalCharSet.contains(jc)) {
                    break;
                }
                intervalCharSet.add(jc);
                hashChar[jc]++;
                j++;
            }
            ans = Math.max(ans, j - i);
            char ic = s.charAt(i);
            hashChar[ic]--;
            if (hashChar[ic] == 0) {
                intervalCharSet.remove(ic);
            }

        }
        return ans;
    }

    public int kthSmallestSum(int[] A, int[] B, int k) {
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.val - o2.val;
            }
        });
        int[] dx = new int[]{1, 0};
        int[] dy = new int[]{0, 1};

        boolean[][] used = new boolean[B.length][A.length];
        Node smallestNode = new Node(0, 0, A[0] + B[0]);
        queue.add(smallestNode);
        for (int i = 1; i < k; i++) {
            Node currNode = queue.poll();

            for (int j = 0; j < 2; j++) {
                int x = currNode.x + dx[j];
                int y = currNode.y + dy[j];
                if (x < B.length && y < A.length && !used[x][y]) {
                    Node newNode = new Node(x, y, A[y] + B[x]);
                    queue.add(newNode);
                    used[x][y] = true;
                }
            }
        }
        return queue.peek().val;
    }

    class Node {
        private int x;
        private int y;
        private int val;

        Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
}
