package com.banana.multithread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class UEHLogger implements Thread.UncaughtExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("1231312313");
        logger.error("未捕获异常处理器处理中");
    }

    public int triangleNumber(int[] nums) {

        if (nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int twoSideSum = nums[i] + nums[j];
                //find last less than twoSideSum index
                int index = binarySearch(nums, j + 1, nums.length, twoSideSum);
                res += index - j;
            }
        }
        return res;
    }

    private int binarySearch(int[] nums, int s, int e, int target) {
        while (s < e) {
            int mid = (s + e) >> 1;
            if (nums[mid] <= target) {
                s = mid + 1;
            } else {
                e = mid;
            }
        }
        return e - 1;


    }

//    public boolean isAnagram(String s, String t) {
//        if ((s.isEmpty() && !t.isEmpty()) || s.length() != t.length()) {
//            return false;
//        }
//        char[] tchars = t.toCharArray();
//        char[] schars = s.toCharArray();
//        Arrays.sort(tchars);
//        Arrays.sort(schars);
//        for (int i = 0; i < tchars.length; i++) {
//            if (tchars[i] != schars[i]) {
//                return false;
//            }
//        }
//        return true;
//    }

    public boolean isUnique(String astr) {
        if (astr.isEmpty()) {
            return true;
        }
        char[] chars = astr.toCharArray();
        long flag = 0;
        for (char aChar : chars) {
            long i = aChar - 'A';
            if ((flag & (1L << i)) != 0) {
                return false;
            } else {
                flag = flag | (1L << i);
            }
        }
        return true;
    }

    public boolean CheckPermutation(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return s1 == null && s2 == null;
        }
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();
        int a = 0;
        int b = 0;
        for (char s1Char : s1Chars) {
            a = a ^ s1Char;
        }
        for (char s2Char : s2Chars) {
            b = b ^ s2Char;
        }
        return a == b;
    }

    public boolean canPermutePalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> letters = new HashMap<>();
        for (char aChar : chars) {
            letters.merge(aChar, 1, (a, b) -> a + b);
        }
        int flag = 0;
        for (int letter : letters.values()) {
            if (letter % 2 != 0 && (++flag) > 1) {
                return false;
            }
        }
        return true;
    }

    public boolean oneEditAway(String first, String second) {
        if (first == null || second == null) {
            return true;
        }
        String longer = first.length() >= second.length() ? first : second;
        String shorter = first.length() < second.length() ? first : second;
        if (longer.length() - shorter.length() > 1) {
            return false;
        }
        if (longer.isEmpty() || shorter.isEmpty()) {
            return longer.length() - shorter.length() <= 1;
        }
        int flag = 0;
        int i = 0;
        int j = 0;
        if (longer.length() == shorter.length()) {
            for (int k = 0; k < longer.length(); k++) {
                if (longer.charAt(k) != shorter.charAt(k)) {
                    flag++;
                    if (flag > 1) {
                        return false;
                    }
                }
            }
        } else {
            for (; j < shorter.length(); ) {
                if (longer.charAt(i) != shorter.charAt(j)) {
                    i++;
                    flag++;
                    if (flag > 1) {
                        return false;
                    }
                    continue;
                }
                i++;
                j++;
            }
//            return flag==0;
        }


        return true;
    }

    public String compressString(String S) {
        if (S.isEmpty() || S.length() < 3) {
            return S;
        }
        char[] chars = S.toCharArray();
        StringBuilder sb = new StringBuilder();
        int repeat = 1;
        for (int i = 0; i < chars.length; i++) {
            if (i > 0) {
                if (chars[i] == chars[i - 1]) {
                    repeat++;
                    if (i == chars.length - 1) {
                        sb.append(chars[i]).append(repeat);
                    }
                } else {
                    sb.append(chars[i - 1]).append(repeat);
                    repeat = 1;
                    if (i == chars.length - 1) {
                        sb.append(chars[i]).append(repeat);
                    }
                }
            }
        }
        return S.length() <= sb.length() ? S : sb.toString();
    }

    public void rotate(int[][] matrix) {
        int N = matrix.length;
        if (N <= 1) return;
        for (int layer = 0; layer < N / 2; layer++) {
            for (int j = layer; j < N - layer - 1; j++) {
                int temp = matrix[layer][j];
                matrix[layer][j] = matrix[N - j - 1][layer];
                matrix[N - j - 1][layer] = matrix[N - layer - 1][N - j - 1];
                matrix[N - layer - 1][N - j - 1] = matrix[j][N - layer - 1];
                matrix[j][N - layer - 1] = temp;
            }
        }
    }


    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cloumns = matrix[0].length;
        int[] rowsToZero = new int[rows];
        int[] cloums = new int[cloumns];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    rowsToZero[i] = 1;
                    cloums[j] = 1;
                }
            }
        }
        for (int i = 0; i < rowsToZero.length; i++) {
            if (rowsToZero[i] == 1) {
                //set i row to 0
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < cloums.length; i++) {
            if (cloums[i] == 1) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }

    public boolean isFlipedString(String s1, String s2) {

        if (s1.isEmpty() || s2.isEmpty()) {
            return s1.isEmpty() && s2.isEmpty();
        }
        if (s1.length() != s2.length()) {
            return false;
        }
//        String s1s1 = s1+s1;
//        return s1s1.contains(s2);
        int i = 0;
        int j = 0;
        boolean start = false;
        for (; i < s1.length(); ) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
            } else {
                i = 0;
            }
            j++;
            if (j >= s2.length()) {
                j = 0;
            }
        }
        return true;
    }

    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = head;
        ListNode p1 = head;
        while (tail.next != null) {
            ListNode next = tail.next;
            boolean nextIsDup = false;
            while (p1 != tail.next) {
                if (p1.val == next.val) {
                    nextIsDup = true;
                    break;
                }
                p1 = p1.next;
            }
            p1 = head;
            if (nextIsDup) {
                //delete current node
                tail.next = next.next;
            } else {
                tail = next;
            }
        }
        return head;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int kthToLast(ListNode head, int k) {
        ListNode b = head;
        ListNode a = head;
        for (int i = 1; i < k; i++) {
            b = b.next;
        }
        while (b.next != null) {
            b = b.next;
            a = a.next;
        }
        return a.val;
    }


    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null) {
            if (p2.val < x) {
//                int temp = p1.val;
                p1.val = p1.val ^ p2.val;
                p2.val = p1.val ^ p2.val;
                p1.val = p1.val ^ p2.val;
                p1 = p1.next;
            }
            p2 = p2.next;
        }
        return head;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        return sum(l1, l2, 0);
    }

    ListNode sum(ListNode s1, ListNode s2, int buy) {
        int currentLevelSum = s1.val + s2.val + buy;
        ListNode currentNode = new ListNode(currentLevelSum % 10);
        if (s1.next == null && s2.next == null) {
            if (currentLevelSum / 10 != 0) {
                currentLevelSum = currentLevelSum / 10;
                currentNode.next = new ListNode(currentLevelSum);
            }
            return currentNode;
        }
        s1 = s1.next != null ? s1.next : new ListNode(0);
        s2 = s2.next != null ? s2.next : new ListNode(0);

        currentNode.next = sum(s1, s2, currentLevelSum / 10);
        return currentNode;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode p1 = head;
        ListNode a = null;
        while (p1 != null) {
            ListNode b = new ListNode(p1.val);
            p1 = p1.next;
            b.next = a;
            a = b;
        }
        while (head != null) {
            if (a.val != head.val) {
                return false;
            }
            head = head.next;
            a = a.next;
        }
        return true;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return head;
            }
            head = head.next;
        }
        return null;

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p1 = headA;
        ListNode p2 = headB;
        int len1 = 0;
        int len2 = 0;
        while (p1 != null) {
            len1++;
            p1 = p1.next;
        }
        while (p2 != null) {
            len2++;
            p2 = p2.next;
        }
        ListNode fast = len1 > len2 ? headA : headB;
        ListNode lag = len1 > len2 ? headB : headA;
        int dis = len1 > len2 ? len1 - len2 : len2 - len1;
        while (dis > 0) {
            fast = fast.next;
            dis--;
        }
        while (fast != lag) {
            fast = fast.next;
            lag = lag.next;
            if (fast == null) {
                break;
            }
        }
        if (fast == null) {
            return null;
        }
        return fast;
    }

    class TripleInOne {

        private int[] stackArray;
        private int eachStackSize;
        private int[] points = new int[3];

        public TripleInOne(int stackSize) {
            stackArray = new int[stackSize * 3];
            eachStackSize = stackSize;
            points[0] = eachStackSize;
            points[1] = eachStackSize * 2;
            points[2] = eachStackSize * 3;
        }

        public boolean isfull(int stackNum) {
            if (points[stackNum] == eachStackSize * stackNum) {
                return true;
            }
            return false;
        }

        public void push(int stackNum, int value) {
            if (isfull(stackNum)) {
                return;
            }
            points[stackNum] = points[stackNum] - 1;
            stackArray[points[stackNum]] = value;
        }

        public int pop(int stackNum) {
            if (isEmpty(stackNum)) {
                return -1;
            }
            points[stackNum] = points[stackNum] + 1;
            return stackArray[points[stackNum] - 1];
        }

        public int peek(int stackNum) {
            if (isEmpty(stackNum)) {
                return -1;
            }
            return stackArray[points[stackNum]];
        }

        public boolean isEmpty(int stackNum) {
            if (points[stackNum] == eachStackSize * (stackNum + 1)) {
                return true;
            }
            return false;
        }
    }

    static class MinStack {

        private int[] array = new int[3];
        private int topPoint = array.length;
        private int minPoint = array.length - 1;

        /**
         * initialize your data structure here.
         */
        public MinStack() {

        }

        public void push(int x) {
            if (topPoint == 0) {
                int[] temp = new int[array.length * 2];
                System.arraycopy(array, 0, temp, temp.length - array.length, array.length);
                topPoint = temp.length - array.length;
                minPoint = minPoint + temp.length - array.length;
                array = temp;
            }
            topPoint = topPoint - 1;
            array[topPoint] = x;
            if (x < array[minPoint]) {
                minPoint = topPoint;
            }
        }

        public void pop() {
            if (minPoint == topPoint && minPoint < array.length - 1) {
                minPoint = minPoint + 1;
                for (int i = minPoint; i < array.length; i++) {
                    if (array[i] < array[minPoint]) {
                        minPoint = i;
                    }
                }
            }
            topPoint = topPoint + 1;
        }

        public int top() {
            return array[topPoint];
        }

        public int getMin() {
            return array[minPoint];
        }
    }

    class StackOfPlates {
        private int cap;
        private List<Stack> stack = new ArrayList<>();

        public StackOfPlates(int cap) {
            stack.add(new Stack<>());
            this.cap = cap;
        }

        public void push(int val) {
            if (cap <= 0) {
                return;
            }
            if (stack.isEmpty() || stack.get(stack.size() - 1).size() == cap) {
                stack.add(new Stack<>());
            }
            stack.get(stack.size() - 1).push(val);
        }

        public int pop() {
            return popAt(stack.size() - 1);
        }

        public int popAt(int index) {
            if (index < 0 || index >= stack.size()) {
                return -1;
            }
            Stack<Integer> indexStack = stack.get(index);
            if (indexStack.isEmpty()) {
                return -1;
            }
            int val = indexStack.pop();
            if (indexStack.isEmpty()) {
                stack.remove(indexStack);
            }
            return val;
        }
    }

    class MyQueue {

        private Stack<Integer> oldStack = new Stack<>();
        private Stack<Integer> newStack = new Stack<>();

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {

        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            newStack.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            if (oldStack.isEmpty()) {
                while (!newStack.isEmpty()) {
                    oldStack.push(newStack.pop());
                }
            }
            return oldStack.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            if (oldStack.isEmpty()) {
                while (!newStack.isEmpty()) {
                    oldStack.push(newStack.pop());
                }
            }
            return oldStack.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return oldStack.isEmpty() && newStack.isEmpty();
        }
    }

    class SortedStack {

        private Stack<Integer> sortedStack = new Stack<>();
        private Stack<Integer> tempStack = new Stack<>();

        public SortedStack() {

        }

        public void push(int val) {
            if (sortedStack.isEmpty() && tempStack.isEmpty()) {
                sortedStack.push(val);
                return;
            }
            while (sortedStack.peek() < val) {
                tempStack.push(sortedStack.pop());
                if (sortedStack.isEmpty()) {
                    break;
                }
            }
            while (!sortedStack.isEmpty() && !tempStack.isEmpty() && sortedStack.peek() > val &&
                    tempStack.peek() > val) {
                sortedStack.push(tempStack.pop());
            }
            sortedStack.push(val);

        }

        public void pop() {
            while (!tempStack.isEmpty()) {
                sortedStack.push(tempStack.pop());
            }
            if (sortedStack.isEmpty()) {
                return;
            }
            sortedStack.pop();
        }

        public int peek() {
            while (!tempStack.isEmpty()) {
                sortedStack.push(tempStack.pop());
            }
            if (sortedStack.isEmpty()) {
                return -1;
            }
            return sortedStack.peek();
        }

        public boolean isEmpty() {
            return sortedStack.isEmpty();
        }
    }

    class AnimalShelf {

        private LinkedList<int[]> shelter = new LinkedList<>();
        private int[] negative = new int[]{-1, -1};

        public AnimalShelf() {

        }

        public void enqueue(int[] animal) {
            shelter.add(animal);
        }

        public int[] dequeueAny() {
            if (shelter.isEmpty()) {
                return negative;
            }
            return shelter.removeFirst();
        }

        public int[] dequeueDog() {
            if (shelter.isEmpty()) {
                return negative;
            }
            for (int i = 0; i < shelter.size(); i++) {
                if (shelter.get(i)[1] == 1) {
                    return shelter.remove(i);
                }
            }
            return negative;
        }

        public int[] dequeueCat() {
            if (shelter.isEmpty()) {
                return negative;
            }
            for (int i = 0; i < shelter.size(); i++) {
                if (shelter.get(i)[1] == 0) {
                    return shelter.remove(i);
                }
            }
            return negative;
        }
    }

    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        LinkedList<Integer> breathList = new LinkedList<>();
        HashMap<Integer, Set<Integer>> temp = new HashMap<>();
        for (int[] ints : graph) {
            int key = ints[0];
            Set<Integer> valueList = temp.computeIfAbsent(key, r -> new HashSet<>());
            valueList.add(ints[1]);
        }
        Set<Integer> nodeSet = new HashSet<>();
        nodeSet.add(start);
        breathList.add(start);
        while (!breathList.isEmpty()) {
            Integer first = breathList.removeFirst();
            if (first == target) {
                return true;
            }
            //从图中获取该节点的所有相关点
            Set<Integer> adjacent = temp.get(first);
            if (adjacent == null) {
                continue;
            }
            for (Integer i : adjacent) {
                if (!nodeSet.contains(i)) {
                    if (i == target) {
                        return true;
                    } else {
                        nodeSet.add(i);
                        breathList.add(i);
                    }
                }
            }
            nodeSet.add(first);
        }
        return false;
    }


    public TreeNode sortedArrayToBST(int[] nums) {
        return createTreeNode(nums, 0, nums.length - 1);
    }

    TreeNode createTreeNode(int[] nums, int start, int end) {
        if (end < start) {
            return null;
        }
        int midIndex = (start + end) / 2;
        TreeNode mid = new TreeNode(nums[midIndex]);
        mid.left = createTreeNode(nums, start, midIndex - 1);
        mid.right = createTreeNode(nums, midIndex + 1, end);
        return mid;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public ListNode[] listOfDepth(TreeNode tree) {
        List<ListNode> res = new ArrayList<>();
        ListNode depthPoint = new ListNode(tree.val);
        TreeNode head = tree;
        Queue<TreeNode> nextDepList = new LinkedList<>();
        Queue<TreeNode> tempnextDepList = new LinkedList<>();
        nextDepList.add(head);
        while (!nextDepList.isEmpty()) {
            tempnextDepList = new LinkedList<>();
            TreeNode poll = nextDepList.poll();
            if (poll.left != null) {
                tempnextDepList.add(poll.left);
            }
            if (poll.right != null) {
                tempnextDepList.add(poll.right);
            }
            depthPoint = new ListNode(poll.val);
            ListNode a = depthPoint;
            for (TreeNode treeNode : nextDepList) {
                if (treeNode.left != null) {
                    tempnextDepList.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    tempnextDepList.add(treeNode.right);
                }
                a.next = new ListNode(treeNode.val);
                a = a.next;
            }
            res.add(depthPoint);
            nextDepList = tempnextDepList;
        }
        ListNode[] resArray = new ListNode[res.size()];
        res.toArray(resArray);
        return resArray;
    }

    class BoTreeNode extends TreeNode {
        private boolean visited;

        BoTreeNode(int x) {
            super(x);
        }
    }


    public boolean isBalanced(TreeNode root) {
        int i = checkHeight(root, 0);
        return i != Integer.MIN_VALUE;
    }

    private int checkHeight(TreeNode node, int k) {
        if (node == null) {
            return k;
        }
        k++;
        int left = checkHeight(node.left, k);
        if (left == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        int right = checkHeight(node.right, k);
        if (right == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        int highAbs = Math.abs(left - right);
        if (highAbs > 1) {
            return Integer.MIN_VALUE;
        }
        return left > right ? left : right;
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;//测试用例中根节点为null时返回true
        }
        PacValue res = isBST(root, null, root.val);
        return res.isRes();
    }

    class PacValue {
        private int max;
        private int min;
        private boolean res = true;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public boolean isRes() {
            return res;
        }

        public void setRes(boolean res) {
            this.res = res;
        }
    }

    PacValue isBST(TreeNode node, Boolean isMax, Integer preNodeVal) {
        PacValue value = new PacValue();
        if (node == null) {
            return null;//递归返回条件
        }
        PacValue left = isBST(node.left, true, node.val);
        if (left != null && !left.isRes()) {
            value.setRes(false);//左子树已不满足二叉搜索树时，直接向上传递结果
            return value;
        }
        PacValue right = isBST(node.right, false, node.val);
        if (right != null && !right.isRes()) {
            value.setRes(false);//右子树已不满足二叉搜索树时，直接向上传递结果
            return value;
        }
        //<editor-fold desc="对于基线条件的处理">
        if (left == null && right == null) {
            value.setMax(node.val);//左右子树都为空，则最大最小值为当前节点的值
            value.setMin(node.val);
            value.setRes(true);
        }
        if (left == null && right != null) {
            /**
             * 左子树为空，右子树不为空，若满足当前node小于右子树的最小值，则仍满足二叉搜索树的属性
             * 此时则最大值为右子树的最大值，最小值为当前节点值
             * 注意：此时若不满足属性，则最大值填了右子树最大值是错的，
             * 但是这样没有关系，因为一旦不满足属性递归不会再做处理直接层层向上传递false的结果
             */
            value.setMax(right.getMax());
            value.setMin(node.val);
            value.setRes(node.val < right.getMin());
        }
        if (left != null && right == null) {//类似上一种情况
            value.setMax(node.val);
            value.setMin(left.getMin());
            value.setRes(node.val > left.getMax());
        }
        if (left != null && right != null) {
            /**
             * 左右子树都不为空，则按照当前节点小于右子树的最小值，并大于左子树的最大值来进行判断是否
             * 满足二叉搜索树的属性
             * 此时，若满足属性则最小值为左子树的最小值，最大值为右子树的最大值。
             * 若不满足属性，此时的最大最小值设置是错的，但是没有关系，不满足属性的结果会直接向上传递。
             */
            value.setRes(node.val > left.getMax() && node.val < right.getMin());
            value.setMin(left.getMin());
            value.setMax(right.getMax());
        }
        //</editor-fold>
        return value;
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        return searchInorder(root, p);
    }

    private TreeNode lastNode;

    TreeNode searchInorder(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        if (root.val <= p.val) {
            TreeNode right = searchInorder(root.right, p);
            return right;
        } else {
            TreeNode left = searchInorder(root.left, p);
            return left == null ? root : left;
        }
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (containpq(root.left, p, q)) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (containpq(root.right, p, q)) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }


    boolean containpq(TreeNode root, TreeNode p, TreeNode q) {
        return root != null && forwardFind(root, p, q, 0) >= 2;
    }

    int forwardFind(TreeNode root, TreeNode p, TreeNode q, int k) {
        if (root == null) {
            return k;
        }
        k = forwardFind(root.left, p, q, k);
        if (k >= 2) {
            return k;
        }
        if (root.val == p.val || root.val == q.val) {
            k++;
        }
        if (k >= 2) {
            return k;
        }
        k = forwardFind(root.right, p, q, k);
        return k;
    }


    public boolean checkSubTree(TreeNode t1, TreeNode t2) {

        StringBuilder a = new StringBuilder();
        StringBuilder b = new StringBuilder();

        forwardTree(t1, a);
        forwardTree(t2, b);
        return a.indexOf(t2.toString()) != -1;
    }

    void forwardTree(TreeNode root, StringBuilder stringBuilder) {
        if (root == null) {
            stringBuilder.append(" ");
            return;
        }
        stringBuilder.append(root.val);
        forwardTree(root.left, stringBuilder);
        forwardTree(root.right, stringBuilder);
    }

    public int pathSum(TreeNode root, int sum) {
        return countPaths(root, new HashMap<>(), sum, 0);
    }

    int countPaths(TreeNode root, HashMap<Integer, Integer> pathSumValueCount, int targetSum, int pathSum) {
        if (root == null) {
            return 0;
        }
        pathSum = pathSum + root.val;
        int totalPath = pathSumValueCount.getOrDefault(pathSum - targetSum, 0);
        if (pathSum == targetSum) {
            totalPath++;
        }
        handlePathSumMap(pathSumValueCount, pathSum, 1);
        totalPath += countPaths(root.left, pathSumValueCount, targetSum, pathSum);
        totalPath += countPaths(root.right, pathSumValueCount, targetSum, pathSum);
        handlePathSumMap(pathSumValueCount, pathSum, -1);
        return totalPath;
    }

    void handlePathSumMap(HashMap<Integer, Integer> pathSumValueCount, int pathSum, int delta) {
        Integer paths = pathSumValueCount.computeIfAbsent(pathSum, s -> 0);
        pathSumValueCount.put(pathSum, paths + delta);
    }


    public int insertBits(int N, int M, int i, int j) {
        int left = 0;
        if (j > 31) {
            for (int b = 0; b < j - 31; b++) {
                left = left | 1 << b;
            }
        } else {

            left = ~0 << (j + 1);
        }

        int right = (1 << (i)) - 1;
        int mask = left | right;
        N = N & mask;
        return N | (M << i);

    }

    public String printBin(double num) {
        if (num >= 1 || num <= 0) {
            return "ERROR";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("0.");
        while (num > 0) {
            if (sb.length() > 32) {
                return "ERROR";
            }
            num = num * 2;
            if (num > 1) {
                sb.append("1");
                num = num - 1;
            } else {
                sb.append("0");
            }
        }
        return sb.toString();
    }


    public int reverseBits(int num) {

        if (~num == 0) return Integer.BYTES * 8;
        int currentCount = 0;
        int prveCount = 0;
        int maxCount = 0;
        while (num > 0) {

            if ((num & 1) == 1) {
                currentCount++;
            } else {
                if ((num & 2) == 0) {
                    prveCount = 0;
                } else {
                    prveCount = currentCount;
                }
                currentCount = 0;
            }
            maxCount = Math.max(prveCount + currentCount + 1, maxCount);
            num = num >>> 1;
        }
        return maxCount;

    }

    public int[] findClosedNumbers(int num) {
        int[] res = new int[]{-1, -1};
        if (num == 0 || num == (~0)) {
            return res;
        }
        res[0] = getNext(num);
        res[1] = getPrev(num);
        return res;
    }

    private int getPrev(int num) {
        int res = -1;
        boolean find0 = false;
        int count = 0;
        for (int i = 0; i < Integer.BYTES * 8; i++) {
            if ((num & (1 << i)) == 0) {
                find0 = true;
            }
            if (((num & (1 << i)) == (1 << i))) {
                count++;
            }
            if (((num & (1 << i)) == (1 << i)) && find0) {
                //将i位变为0  将i位右边全置为0 再添加count+1位的1
                res = num & (~(1 << i));
                for (int k = i - 1; k >= 0; k--) {
                    res = res & (~(1 << k));
                }
                int j = 1;
                while (count > 0) {
                    res = res | (1 << (i - j));
                    j++;
                    count--;
                }
                break;
            }
        }

        return res;
    }


    private int getNext(int num) {
        int res = -1;
        boolean find1 = false;
        boolean findFirstNotTail0 = false;
        int count = 0;
        for (int i = 0; i < Integer.BYTES * 8; i++) {
            if ((num & (1 << i)) == (1 << i)) {
                count++;
                find1 = true;
            } else {
                //出现1之后的第一个0置为1，将该位右边全置为0，再从右往左放入count-1个1
                if (find1) {
                    res = num | (1 << i);
                    for (int k = i - 1; k >= 0; k--) {
                        res = res & (~(1 << k));
                    }
                    int j = 0;
                    while ((count - 1) > 0) {
                        res = res | (1 << j);
                        count--;
                        j++;
                    }
                    findFirstNotTail0 = true;
                    break;
                }
            }
        }
        if (!findFirstNotTail0) {
            res = -1;
        }
        return res;
    }

    public int convertInteger(int A, int B) {
        int C = A ^ B;
        int res = 0;
        while (C != 0) {
            if ((C & 1) == 1) {
                res++;
            }
            C >>>= 1;
        }
        return res;
    }

    public int exchangeBits(int num) {
        int oddMask = 0xAAAAAAAA;
        int oddNumber = num & oddMask;
        int evenMask = ~0xAAAAAAAA;
        int evenNumber = num & evenMask;
        return (oddNumber >>> 1) | (evenNumber << 1);
    }

    int getPrev2(int n) {
        int temp = n;
        int c0 = 0;
        int c1 = 0;
        while ((temp & 1) == 1) {
            c1++;
            temp >>= 1;
        }
        if (temp == 0) return -1;
        while (((temp & 1) == 0) && (temp != 0)) {
            c0++;
            temp >>= 1;
        }
        int p = c0 + c1;
        n &= ((~0) << (p + 1));
        int mask = (1 << (c1 + 1)) - 1;
        n |= mask << (c0 - 1);
        return n;
    }


    public int[] drawLine(int length, int w, int x1, int x2, int y) {
        int[] screen = new int[length];

        int startIndex = (y * w + x1) / 32;
        int startBitIndex = (y * w + x1) % 32;
        int total = x2 - x1 + 1;
        int current = 0;
        while (total > 0) {
            for (int i = startBitIndex; i < 32 && total > 0; i++) {
                current = current | (1 << (31 - i));
                total--;
            }
            screen[startIndex++] = current;
            current = 0;
            startBitIndex = 0;
        }
        return screen;
    }

    public int waysToStep(int n) {
        if (n <= 2) return n;
        if (n == 3) return 4;
        int a = 1, b = 2, c = 4;
        int d = 0;
        while (n > 3) {
            d = a + b + c;
            c = d;
            b = c;
            a = b;
            n--;
        }
        return d;
    }

    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        HashSet<List<Integer>> failed = new HashSet<>();
        if (getPath(res, failed, obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length - 1)) {
            return res;
        }
        return null;
    }

    boolean getPath(List<List<Integer>> res, HashSet<List<Integer>> failed, int[][] obstacleGrid, int r, int c) {
        if (r < 0 || c < 0 || (obstacleGrid[r][c] == 1)) return false;
        List point = new ArrayList();
        point.add(r);
        point.add(c);
        if (failed.contains(point)) {
            return false;
        }
        boolean isAtSourse = (r == 0 && c == 0);
        if (isAtSourse || getPath(res, failed, obstacleGrid, r - 1, c) || getPath(res, failed, obstacleGrid, r, c - 1)) {
            res.add(point);
            return true;
        }
        failed.add(point);
        return false;
    }

    public int countWays(int n, int[] table) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (table[n] > -1) {
            return table[n];
        } else {
            table[n] = countWays(n - 1, table) + countWays(n - 2, table) + countWays(n - 3, table);
            return table[n];
        }
    }

    public int findMagicIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        return binaryFind(nums, 0, nums.length - 1);
    }

    int binaryFind(int[] nums, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (nums[mid] == mid) {
            return mid;
        }
        int leftres = binaryFind(nums, start, mid - 1);
        if (leftres != -1) {
            return leftres;
        }
        return binaryFind(nums, mid + 1, end);
    }

    public List<List<Integer>> subsets(int[] nums) {
        Set<List<Integer>> sets = new HashSet<>();
        getSubSets(nums, nums.length - 1, sets);
        return new ArrayList<>(sets);
    }

    void getSubSets(int[] nums, int i, Set<List<Integer>> sets) {
        if (i < 0) {
            sets.add(new ArrayList<>());
            return;
        }
        getSubSets(nums, --i, sets);
        Set<List<Integer>> temp = new HashSet<>();
        for (List<Integer> integerList : sets) {
            List<Integer> inn = new ArrayList<>(integerList);
            inn.add(nums[i + 1]);
            temp.add(inn);
        }
        sets.addAll(temp);
    }

//    public int multiply(int A, int B) {
//        HashMap<Integer, Integer> memo = new HashMap<>();
//        return getMultiplyValue(A, B, memo);
//    }

    int getMultiplyValue(int A, int B, HashMap<Integer, Integer> memo) {
        if (B == 0) {
            return 0;
        }
        if (B == 1) {
            return A;
        }
        if (memo.get(B) != null && memo.get(B) > 0) {
            return memo.get(B);
        }
        int C = B >> 1;
        int res = getMultiplyValue(A, C, memo) + getMultiplyValue(A, B - C, memo);
        memo.put(B, res);
        return res;
    }

    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        moveDisk(A.size() - 1, A, C, B);
    }

    void moveDisk(int n, List<Integer> orign, List<Integer> dest, List<Integer> buffer) {
        if (n < 0) {
            return;
        }
        moveDisk(n - 1, orign, buffer, dest);
        dest.add(orign.remove(orign.size() - 1));
        moveDisk(n - 1, buffer, dest, orign);
    }

    public String[] permutation(String S) {
        Set<String> res = permutationRecusive(S);
        String[] ress = new String[res.size()];
        return res.toArray(ress);
    }

    Set<String> permutationRecusive(String S) {
        Set<String> res = new HashSet<>();
        if (S.length() == 1) {
            res.add(S);
            return res;
        }
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            StringBuilder sb = new StringBuilder(S);
            sb.deleteCharAt(i);
            Set<String> te = permutationRecusive(sb.toString());
            for (String teString : te) {
                res.add(teString + c);
            }
        }
        return res;
    }

    public List<String> generateParenthesis(int n) {
        Set<String> res = new HashSet<>();
        if (n == 1) {
            res.add("()");
            return new ArrayList<>(res);
        }
        List<String> temp = generateParenthesis(--n);
        for (String s : temp) {
            int length = s.length();
            for (int i = 0; i < length; i++) {
                if (s.charAt(i) == '(') {
                    res.add(insertParent(s, i));
                }
                res.add("()" + s);
            }
        }

        return new ArrayList<>(res);
    }

    public static String insertParent(String orign, int offset) {
        String left = orign.substring(0, offset + 1);
        String right = orign.substring(offset + 1, orign.length());
        return left + "()" + right;
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int[][] memo = new int[image.length][image[0].length];
        return floodFill(image, sr, sc, newColor, image[sr][sc], memo);
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor, int origColor, int[][] memo) {
        if (sr < 0 || sc < 0 || sr > image.length - 1 || sc > image[0].length - 1) {
            return image;
        }
        if (origColor == image[sr][sc] && memo[sr][sc] != 1) {
            image[sr][sc] = newColor;
            memo[sr][sc] = 1;
            image = floodFill(image, sr - 1, sc, newColor, origColor, memo);
            image = floodFill(image, sr + 1, sc, newColor, origColor, memo);
            image = floodFill(image, sr, sc - 1, newColor, origColor, memo);
            image = floodFill(image, sr, sc + 1, newColor, origColor, memo);
        }
        return image;
    }

    int mod = 1000000007;

    public int waysToChange(int n) {
        int[][] map = new int[n + 1][4];
        int res = makeChange(n, new int[]{25, 10, 5, 1}, 0, map);
        return res;

    }

    public int makeChange(int amount, int[] denoms, int index, int[][] map) {
        if (index >= denoms.length - 1) {
            return 1;
        }
        if (map[amount][index] > 0) {
            return map[amount][index];
        }
        int res = 0;
        int denoAmount = denoms[index];
//        int i = amount / denoms[index];
        for (int i = 0; i * denoAmount <= amount; i++) {
            int amountRemaining = amount - i * denoAmount;
            res = (res + makeChange(amountRemaining, denoms, index + 1, map)) % mod;
        }
        map[amount][index] = res;
        return res;
    }

    public List<List<String>> solveNQueens(int n) {
        List<Integer[]> result = new ArrayList<>();
        List<List<String>> stringRes = new ArrayList<>();
        placeQueen(result, 0, new Integer[n], n);
        for (Integer[] integerArray : result) {
            List<String> stringList = transString(integerArray);
            stringRes.add(stringList);
        }
        return stringRes;
    }

    private List<String> transString(Integer[] arrays) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            Integer columns = arrays[i];
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < arrays.length; j++) {
                if (columns == j) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            res.add(sb.toString());
        }
        return res;

    }

    private void placeQueen(List<Integer[]> result, int row, Integer[] columns, int n) {
        if (row == n) {
            result.add(columns.clone());
        } else {
            for (int col = 0; col < n; col++) {
                if (checkValid(row, col, columns)) {
                    columns[row] = col;
                    placeQueen(result, row + 1, columns, n);
                }
            }
        }

    }

    private boolean checkValid(int row, int col, Integer[] columns) {
        for (int i = 0; i < row; i++) {
            //检查其他行的col列是否有数据
            if (columns[i] == col) {
                return false;
            }
            int columnDistance = Math.abs(columns[i] - col);
            int rowDistance = row - i;
            if (columnDistance == rowDistance) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        UEHLogger uehLogger = new UEHLogger();
        int[] bir = new int[]{1972, 1908, 1915, 1957, 1960, 1948, 1912, 1903, 1949, 1977, 1900, 1957, 1934, 1929, 1913, 1902, 1903, 1901};
        int[] dea = new int[]{1997, 1932, 1963, 1997, 1983, 2000, 1926, 1962, 1955, 1997, 1998, 1989, 1992, 1975, 1940, 1903, 1983, 1969};
        int i = uehLogger.maxAliveYear(bir, dea);
        System.out.println(i);
    }

    public int pileBox(int[][] box) {
        sort(box);
        int[] memo = new int[box.length];
        int maxHeight = 0;
        for (int i = 0; i < box.length; i++) {
            int stackHeight = createStack(box, i, memo);
            maxHeight = Math.max(stackHeight, maxHeight);
        }
        return maxHeight;
    }


    private void sort(int[][] box) {
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box.length - i - 1; j++) {
                if (box[j][2] < box[j + 1][2]) {
                    int[] temp = box[j];
                    box[j] = box[j + 1];
                    box[j + 1] = temp;
                }
            }
        }
    }

    public int countEval(String s, int result) {
        int n = s.length();
        int[][][] dp = new int[n][n][2];
        //区间长度，至少新增2
        for (int len = 1; len <= n; len += 2) {
            for (int i = 0; i + len - 1 < n; i += 2) {
                if (len == 1) {
                    dp[i][i + len - 1][s.charAt(i) == '0' ? 0 : 1]++;
                }
                //遍历子区间的起点和终点，通过操作符分割左右两边。
                for (int j = i + 1; j < i + len - 1; j += 2) {
                    //第j位是分隔符的位置
                    char op = s.charAt(j);
                    switch (op) {
                        case '&':
                            //分割符为 & 时，左边为0，则右边为0或1均可
                            dp[i][i + len - 1][0] += dp[i][j - 1][0] * (dp[j + 1][i + len - 1][0] + dp[j + 1][i + len - 1][1]) +
                                    //左边为1，则右边必须为0
                                    dp[i][j - 1][1] * dp[j + 1][i + len - 1][0];
                            dp[i][i + len - 1][1] += dp[i][j - 1][1] * dp[j + 1][i + len - 1][1];
                            break;
                        case '|':
                            dp[i][i + len - 1][0] += dp[i][j - 1][0] * dp[j + 1][i + len - 1][0];
                            dp[i][i + len - 1][1] += dp[i][j - 1][1] * (dp[j + 1][i + len - 1][0] + dp[j + 1][i + len - 1][1]) +
                                    dp[i][j - 1][0] * dp[j + 1][i + len - 1][1];
                            break;
                        case '^':
                            dp[i][i + len - 1][0] += dp[i][j - 1][0] * dp[j + 1][i + len - 1][0] +
                                    dp[i][j - 1][1] * dp[j + 1][i + len - 1][1];
                            dp[i][i + len - 1][1] += dp[i][j - 1][0] * dp[j + 1][i + len - 1][1] +
                                    dp[i][j - 1][1] * dp[j + 1][i + len - 1][0];
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        return dp[0][n - 1][result];
    }


    private int createStack(int[][] box, int bottomIndex, int[] memo) {
        if (memo[bottomIndex] > 0) {
            return memo[bottomIndex];
        }
        int[] bottom = box[bottomIndex];
        int height = 0;
        for (int i = bottomIndex + 1; i < box.length; i++) {
            if (canAbove(bottom, box[i])) {
                int stackHight = createStack(box, i, memo);
                height = Math.max(stackHight, height);
            }
        }
        height += bottom[2];
        memo[bottomIndex] = height;
        return height;
    }

    public void merge(int[] A, int m, int[] B, int n) {
        System.arraycopy(B, 0, A, m, n);
        quickSort(A, 0, m + n - 1);
    }

    private void quickSort(int[] array, int start, int end) {
        if (start > end) {
            return;
        }
        int pivot = array[end];
        int i = start;
        for (int j = start; j < end; j++) {
            if (array[j] < pivot) {
                swap(array, j, i);
                i++;
            }
        }
        swap(array, i, end);
        quickSort(array, start, i - 1);
        quickSort(array, i + 1, end);
    }

    private void swap(int[] A, int src, int dest) {
        int temp = A[src];
        A[src] = A[dest];
        A[dest] = temp;
    }

    private boolean canAbove(int[] lower, int[] upper) {
        if (lower[0] > upper[0] && lower[1] > upper[1] && lower[2] > upper[2]) {
            return true;
        }
        return false;
    }


    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> lengthStr = new HashMap<>();
        for (String str : strs) {
            List<String> strings = lengthStr.computeIfAbsent(sortString(str), (k) -> {
                List<String> temp = new ArrayList<>();
                res.add(temp);
                return temp;
            });
            strings.add(str);
        }
        return res;
    }

    private String sortString(String orig) {
        char[] chars = orig.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public int search(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] < arr[right]) {
                //从mid至right之间是递增的
                if (arr[mid] < target && arr[right] >= target && target != arr[left]) {
                    left = mid + 1;//下一次从[mid+1,right]中查找
                } else {
                    right = mid;//下一次从[left，mid]中查找
                }
            } else if (arr[mid] > arr[right]) {
                //从left到mid之间是递增的
                if (arr[left] <= target && arr[mid] >= target) {
                    right = mid;//下一次从[left,mid]中查找
                } else {
                    left = mid + 1;//下一次从[mid+1,right]中查找
                }
            } else {
                //arr[mid] == arr[right]
                if (arr[mid] == target) {
                    right = mid;
                } else {
                    right--;//下一轮从 [left,right-1]
                }
            }
        }
        if (arr[left] == target) {
            return left;
        }
        return -1;
    }


    public int findString(String[] words, String s) {
        int left = 0;
        int right = words.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            while (words[left].equals("")) left++;
            while (words[right].equals("")) right--;
            while (words[mid].equals("") && mid < right) mid++;
            if (words[mid].equals("")) {
                right = ((left + right) >>> 1) - 1;
            } else {
                if (words[mid].compareTo(s) > 0) {
                    right = mid - 1;
                } else if (words[mid].compareTo(s) < 0) {
                    left = mid + 1;
                } else {
                    return mid;
                }
            }
        }

        return -1;
    }


    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int row = 0;
        int column = matrix[0].length - 1;
        while (row <= matrix.length - 1 && column > 0) {
            if (matrix[row][column] > target) {
                column--;
            } else if (matrix[row][column] < target) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }

    private int findRow(int[][] matrix, int target, int totalRow, int totalColumn) {
        if (matrix[totalRow - 1][totalColumn - 1] < target) {
            return -1;
        }
        int left = 0;
        int right = totalRow - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (matrix[mid][totalColumn - 1] > target) {
                right = mid - 1;
            } else if (matrix[mid][totalColumn - 1] < target) {
                left = mid + 1;
            } else if (matrix[mid][totalColumn - 1] == target) {
                return mid;
            }
        }
        return left;
    }

    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int mid = nums.length / 2;
        //排好序之后，将前半段的峰位与后半段的谷位进行交换即可得到峰-谷交错的序列
        for (int i = 0; i < mid; i += 2) {
            if ((mid & 1) == 0) {
                //当中间位是偶数时，那么后半段的谷位从mid+1开始，如[1,2,3,4]的mid是2，下标为2的数字是3，后半段的谷位开始是第2+1位=4
                swapab(nums, i, i + mid + 1);
            } else {
                //当中间位是奇数时，那么后半段的谷位从mid开始，如[1,2,3,4,5,6]的mid是3，下标为3的数字是4，后半段的谷位开始是第3位=4
                swapab(nums, i, i + mid);
            }
        }
    }

    private void swapab(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public int[] swapNumbers(int[] numbers) {
        if (numbers[0] == numbers[1]) {
            return numbers;
        }
        numbers[0] = numbers[0] ^ numbers[1];
        numbers[1] = numbers[0] ^ numbers[1];
        numbers[0] = numbers[0] ^ numbers[1];
        return numbers;
    }

    public int trailingZeroes(int n) {
        if (n < 2) return 0;
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n = n / 5;
        }
        return count;
    }

    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        long min = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        while (i < a.length && j < b.length) {
            if (Math.abs((long) a[i] - (long) b[j]) < min) {
                min = Math.abs((long) a[i] - (long) b[j]);
            }
            if (a[i] > b[j]) {
                j++;
            } else {
                i++;
            }
        }
        return (int) min;
    }

    public int maximum(int a, int b) {
        int signa = sign(a);
        int signb = sign(b);
        int signc = sign(a - b);
        int same_sign_a = signa ^ signb;//a,b符合相同为0  符号不同为1
        int same_sign_c = flip(same_sign_a);//same_sign_a 的反向数
        int k = same_sign_a * signa + same_sign_c * signc;//符合相同为signc，不同为signa
        int q = flip(k);
        return a * k + b * q;
    }

    //翻转符号位
    private int flip(int a) {
        return a ^ 1;
    }

    //负数返回0 正数返回a
    private int sign(int a) {
        return flip((a >> 31) & 0x01);//此处使用算术右移必须与0x01做与运算，不能与1做与运算
    }

    public int minus(int a, int b) {
        return a + (-b);
    }

    public int multiply(int a, int b) {
        int sameSign = ((a >> 31) ^ (b >> 31)) & 1;//符号位相同为0，相异为1
        long absA = Math.abs((long) a) < Math.abs((long) b) ? Math.abs((long) a) : Math.abs((long) b);
        long absB = Math.abs((long) a) < Math.abs((long) b) ? Math.abs((long) b) : Math.abs((long) a);
        long res = absB;
        int temp = 1;
        while (absA > temp) {
            if (absA > temp + temp) {
                res += res;
                temp += temp;
            } else {
                res += absB;
                temp++;
            }
        }
        return sameSign == 0 ? (int) res : (int) -res;
    }

    public int divide(int a, int b) {
        if (a == 0x80000000) {
            return a;
        }
        int sameSign = ((a >> 31) ^ (b >> 31)) & 1;//符号位相同为0，相异为1
        int absA = Math.abs(a);
        int absB = Math.abs(b);
        int res = 0;
        int index = 0;
        int[] temp = new int[32];
        int[] bit = new int[32];
        temp[0] = absB;
        bit[0] = 1;
        for (int i = 1; i < temp.length; i++) {
            if (temp[i - 1] + temp[i - 1] > absA || temp[i - 1] + temp[i - 1] < 0) break;
            temp[i] = temp[i - 1] + temp[i - 1];
            bit[i] = bit[i - 1] + bit[i - 1];
            index++;
        }
        for (int i = index; i >= 0; i--) {
            if (absA >= temp[i]) {
                absA = minus(absA, temp[i]);
                res += bit[i];
            }

        }
        return sameSign == 0 ? res : -res;
    }


    public int maxAliveYear(int[] birth, int[] death) {
        Arrays.sort(birth);
        Arrays.sort(death);
        int length = birth.length;
        int maxLivedYear = 0;
        int i = 0;
        int j = 0;
        int aliveCount = 0;
        int birthCount = 0;
        int deathCount = 0;
        while (i < length) {
            if (birth[i] <= death[j]) {
                birthCount++;
                if (birthCount - deathCount > aliveCount) {
                    aliveCount = birthCount - deathCount;
                    maxLivedYear = birth[i];
                }
                i++;
            } else if (j < length) {
                if (birthCount - deathCount > aliveCount) {
                    aliveCount = birthCount - deathCount;
                    maxLivedYear = death[j];
                }
                deathCount++;
                j++;
            }
        }
        return maxLivedYear;
    }

    public int[] divingBoard(int shorter, int longer, int k) {
        int[] ans = new int[k + 1];
        if (k == 0) {
            return new int[0];
        }
        if (shorter == longer) {
            return new int[]{shorter * k};
        }
        for (int i = 0; i <= k; i++) {
            ans[i] = shorter * (k - i) + longer * i;
        }
        return ans;
    }








}
