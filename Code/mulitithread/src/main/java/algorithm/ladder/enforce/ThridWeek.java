package algorithm.ladder.enforce;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ThridWeek {


    public String expressionExpand(String s) {

        Stack<Object> objectStack = new Stack<>();

        int len = s.length();
        int num = 0;
        for (int i = 0; i < len; i++) {
            char schar = s.charAt(i);
            if (Character.isDigit(schar)) {
                num = num * 10 + schar - '0';
            } else if (schar == '[') {
                objectStack.push(num);
                num = 0;
            } else if (schar == ']') {
                Stack<String> reverStack = new Stack<>();
                while (!objectStack.isEmpty() && (objectStack.peek() instanceof String)) {
                    reverStack.push((String) objectStack.pop());
                }
                StringBuilder tempsb = new StringBuilder();
                while (!reverStack.isEmpty()) {
                    tempsb.append(reverStack.pop());
                }

                Integer count = (Integer) objectStack.pop();
                for (int j = 1; j < count; j++) {
                    tempsb.append(tempsb);
                }
                objectStack.push(tempsb.toString());
            } else {
                objectStack.push(String.valueOf(schar));
            }
        }

        Stack<String> reveTempStack = new Stack<>();
        StringBuilder resTempBuilder = new StringBuilder();
        while (!objectStack.isEmpty()) {
            reveTempStack.push((String) objectStack.pop());
        }
        while (!reveTempStack.isEmpty()) {
            resTempBuilder.append(reveTempStack.pop());
        }
        return resTempBuilder.toString();
    }

    public int trapRainWater(int[] heights) {
        if (heights == null || heights.length <= 0) {
            return 0;
        }
        int left = 0;
        int right = heights.length - 1;
        int sum = 0;
        int leftHeight = heights[left];
        int rightHeight = heights[right];
        while (left < right) {
            if (leftHeight <= rightHeight) {
                //移动左指针
                left++;
                //累积当前指针位可以trap 的 water
                if (heights[left] >= leftHeight) {
                    leftHeight = heights[left];
                } else {
                    sum += leftHeight - heights[left];
                }
            } else {
                //移动右指针
                right--;
                if (heights[right] >= rightHeight) {
                    rightHeight = heights[right];
                } else {
                    sum += rightHeight - heights[right];
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        ThridWeek thridWeek = new ThridWeek();
//        String data = "[[12,13,0,12],[13,4,13,12],[13,8,10,12],[12,13,12,12],[13,13,13,13]]";
//        int[][] test = AlgorithmUtil.praseMatrix(data);
        int[] ta = new int[]{2, 5, 6, 0, 3, 1};
        TreeNode i = thridWeek.maxTree(ta);
        System.out.println(i);
    }

    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();//存储大的那半段数组
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());//存储小的那半段数组
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length <= 0) {
            return ans;
        }
        for (int i = 0; i < nums.length; i++) {
            maxQueue.offer(nums[i]);
            minQueue.add(maxQueue.poll());
            //balance maxqueue and minqueue
            balance(minQueue, maxQueue);
            if (i < k - 1) {
                continue;
            }
            ans.add(maxQueue.peek());
            if (nums[i - k + 1] > maxQueue.peek()) {
                minQueue.remove(nums[i - k + 1]);
            } else {
                maxQueue.remove(nums[i - k + 1]);
            }
        }
        return ans;
    }

    private static void balance(PriorityQueue<Integer> minQueue, PriorityQueue<Integer> maxQueue) {
        if (minQueue.size() > maxQueue.size()) {
            maxQueue.add(minQueue.poll());
        }
    }

    private PriorityQueue<Node> minQueue = new PriorityQueue<Node>();

    class Node implements Comparable<Node> {
        int x;
        int y;
        int height;

        public Node(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        @Override
        public int compareTo(@NotNull Node other) {
            return this.height - other.height;
        }
    }

    private int[] dx = new int[]{1, 0, -1, 0};
    private int[] dy = new int[]{0, 1, 0, -1};

    public int trapRainWater(int[][] heights) {
        if (heights == null) {
            return 0;
        }
        int m = heights.length;
        if (m <= 0) {
            return 0;
        }
        int n = heights[0].length;
        boolean[][] visited = new boolean[m][n];
        int sum = 0;
        for (int i = 0; i < m; i++) {
            minQueue.offer(new Node(i, 0, heights[i][0]));
            visited[i][0] = true;
            minQueue.offer(new Node(i, n - 1, heights[i][n - 1]));
            visited[i][n - 1] = true;
        }
        for (int j = 1; j < n - 1; j++) {
            minQueue.offer(new Node(0, j, heights[0][j]));
            visited[0][j] = true;
            minQueue.offer(new Node(m - 1, j, heights[m - 1][j]));
            visited[m - 1][j] = true;
        }

        while (!minQueue.isEmpty()) {
            Node curNode = minQueue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curNode.x + dx[i];
                int ny = curNode.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < m && ny < n && !visited[nx][ny]) {
                    minQueue.offer(new Node(nx, ny, Math.max(curNode.height, heights[nx][ny])));
                    visited[nx][ny] = true;
                    if (curNode.height > heights[nx][ny]) {
                        sum += curNode.height - heights[nx][ny];
                    }
                }
            }
        }
        return sum;
    }

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> mononicStack = new Stack<>();
        if (heights == null || heights.length <= 0) {
            return 0;
        }
        int area = 0;
        for (int i = 0; i <= heights.length; i++) {
            int cutHeigh = i == heights.length ? -1 : heights[i];
            while (!mononicStack.isEmpty() && cutHeigh <= heights[mononicStack.peek()]) {
                int topIndex = mononicStack.pop();
                int with = mononicStack.isEmpty() ? i : i - mononicStack.peek() - 1;
                int hei = heights[topIndex];
                area = Math.max(area, hei * with);
            }
            mononicStack.push(i);
        }
        return area;
    }

    public int maximalRectangle(boolean[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[] height = new int[columns];//每行的高度
        int area = 0;
        for (boolean[] booleans : matrix) {

            for (int j = 0; j < columns; j++) {
                height[j] = booleans[j] ? height[j] + 1 : 0;//当前点为true时，则该点高度为原先的高度+1
            }
            Stack<Integer> monoStack = new Stack<>();
            for (int j = 0; j <= height.length; j++) {
                int curHeight = j == height.length ? -1 : height[j];
                while (!monoStack.isEmpty() && curHeight <= height[monoStack.peek()]) {
                    int topIndex = monoStack.pop();//栈顶元素的数组下标
                    int weith = monoStack.isEmpty() ? j : j - monoStack.peek() - 1; //宽度为:栈顶元素的左边界到右边界(stack.peek,j),即j-stack.peek-1
                    int heig = height[topIndex];//高度为栈顶元素的高度
                    area = Math.max(area, weith * heig);
                }
                monoStack.add(j);
            }
        }
        return area;
    }

    public class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public TreeNode maxTree(int[] A) {
        if (A == null || A.length == 0) {
            return null;
        }
        Stack<TreeNode> monotonicStack = new Stack<>();
        //2, 5, 6, 0, 3, 1
        for (int i = 0; i < A.length; i++) {
            TreeNode cur = new TreeNode(A[i]);
            while (!monotonicStack.isEmpty() && A[i] > monotonicStack.peek().val) {
                TreeNode lastNode = monotonicStack.pop();
                if (!monotonicStack.isEmpty() && A[i] > monotonicStack.peek().val) {
                    monotonicStack.peek().right = lastNode;
                } else {
                    cur.left = lastNode;
                }
            }
            monotonicStack.add(cur);
        }
        TreeNode root = monotonicStack.peek();
        while (!monotonicStack.isEmpty()) {
            root = monotonicStack.pop();
            if (!monotonicStack.isEmpty()) {
                monotonicStack.peek().right = root;
                root = monotonicStack.peek();
            }
        }
        return root;

    }

    public int[] medianII(int[] nums) {
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return null;
        }
        for (int i = 0; i < nums.length; i++) {
            maxQueue.offer(nums[i]);
            minQueue.offer(maxQueue.poll());
            if (maxQueue.size() < minQueue.size()) {
                maxQueue.offer(minQueue.poll());
            }
            res.add(maxQueue.peek());
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

}
