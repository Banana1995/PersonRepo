package algorithm.ladder.normal;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class ThirdWeek {
    public static void main(String[] args) {
        ThirdWeek thirdWeek = new ThirdWeek();
//        String data = "abccba";
//        char[] chars = data.toCharArray();
        int[] data = new int[]{1, 2, 2, 1, 3, 4};
        int val = thirdWeek.firstUniqueNumber(data, 3);
        log.info("当前结果为：{}", val);
    }

    public class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public int closestValue(TreeNode root, double target) {

        if (root == null) {
            return -1;
        }
        TreeNode smallerOne = findFirstSmallerNode(root, target);
        TreeNode biggerOne = findRightBiggerNode(root, target);
        if (smallerOne != null && biggerOne != null) {
            if (target - smallerOne.val < biggerOne.val - target) {
                return smallerOne.val;
            } else {
                return biggerOne.val;
            }
        }
        if (smallerOne == null) {
            return biggerOne.val;
        }
        return smallerOne.val;

    }

    public TreeNode findFirstSmallerNode(TreeNode root, double target) {
        if (root == null) {
            return null;
        }
        if (root.val > target) {
            return findFirstSmallerNode(root.left, target);
        }
        TreeNode res = findFirstSmallerNode(root.right, target);
        return res == null ? root : res;
    }

    public TreeNode findRightBiggerNode(TreeNode root, double target) {
        if (root == null) {
            return null;
        }
        if (root.val < target) {
            return findRightBiggerNode(root.right, target);
        }
        TreeNode rightBiggerNode = findRightBiggerNode(root.left, target);

        return rightBiggerNode == null ? root : rightBiggerNode;

    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        char[] chars = digits.toCharArray();
        int[] nums = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            nums[i] = chars[i] - '0';
        }
        List<String> answer = new ArrayList<>();
        findCombineStrings(nums, 0, answer, "");
        return answer;
    }

    private void findCombineStrings(int[] nums, int depth, List<String> ans, String strb) {
        if (depth == nums.length) {
            ans.add(strb);
            return;
        }
        int dig = nums[depth];
        for (int i = 0; i < 3; i++) {
            char curChar = (char) ((dig - 2) * 3 + 'a' + i);
//            strb.append(curChar);
            findCombineStrings(nums, depth + 1, ans, strb + curChar);
        }
    }

    public List<String> stringPermutation2(String str) {
        List<String> ans = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return ans;
        }
        boolean[] visited = new boolean[str.length()];
//        Set<String> tempSet = new HashSet<>();
        dfsPer(str.toCharArray(), 0, visited, ans, new StringBuilder());
//        ans.addAll(tempSet);
        return ans;
    }

    public void dfsPer(char[] str, int depth, boolean[] visited, List<String> ans, StringBuilder strbuilder) {
        if (depth == str.length) {
            ans.add(strbuilder.toString());
            return;
        }
        for (int i = 0; i < str.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i > 0 && str[i] == str[i - 1] && visited[i - 1]) {
                continue;
            }
            visited[i] = true;
            strbuilder.append(str[i]);
            dfsPer(str, depth + 1, visited, ans, strbuilder);
            strbuilder.deleteCharAt(depth);
            visited[i] = false;
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        dfsFindCombination(candidates, 0, 0, 0, target, ans, new LinkedList<>());

        return ans;
    }

    private void dfsFindCombination(int[] candidates, int start, int depth, int combinSum, int target, List<List<Integer>> ans, LinkedList<Integer> combinate) {
        if (depth == candidates.length) {
            return;
        }
        if (combinSum == target) {
            List<Integer> tempList = new ArrayList<>(combinate);
            Collections.sort(tempList);
            ans.add(tempList);
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] + combinSum > target) {
                continue;
            }
            combinate.push(candidates[i]);
            dfsFindCombination(candidates, i, depth + 1, candidates[i] + combinSum, target, ans, combinate);
            combinate.pop();
        }
    }

    public List<List<Integer>> kSumII(int[] A, int k, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfsCombineSum(A, 0, 0, k, 0, target, res, new LinkedList<>());
        return res;
    }

    private void dfsCombineSum(int[] A, int start, int sum, int k, int depth, int target, List<List<Integer>> res, LinkedList<Integer> combine) {
        if (depth > k) {
            return;
        }
        if (k == depth && sum == target) {
            List<Integer> newCopy = new ArrayList<>(combine);
            res.add(newCopy);
            return;
        }
        for (int i = start; i < A.length; i++) {
            if (A[i] + sum > target) {
                continue;
            }
            combine.push(A[i]);
            dfsCombineSum(A, i + 1, A[i] + sum, k, depth + 1, target, res, combine);

            combine.pop();
        }

    }

    public void rotateString(char[] str, int offset) {
        if (str == null || str.length == 0) {
            return;
        }
        int start = offset % str.length;
        for (int i = str.length - start; i < str.length; i++) {
            int k = i;
            for (int j = i - 1; j >= i - (str.length - start); j--) {
                swap(str, k, j);
                k--;
            }
        }
    }

    private void swap(char[] str, int a, int b) {
        char temp = str[a];
        str[a] = str[b];
        str[b] = temp;
    }

    public int firstUniqueNumber(int[] nums, int number) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        HashMap<Integer, Integer> hash = new HashMap<>();
        Queue<Integer> proiQueue = new PriorityQueue<>();
        boolean hashend = false;
        for (int i = 0; i < nums.length; i++) {

            if (hash.containsKey(nums[i])) {
                int oriIndex = hash.remove(nums[i]);
                proiQueue.remove(oriIndex);
            } else {
                hash.put(nums[i], i);
                proiQueue.add(i);
            }
            if (number == nums[i]) {
                hashend = true;
                break;
            }
        }
        if (hash.size() == 0 || !hashend) {
            return -1;
        }
        int resIndex = proiQueue.peek();
        return nums[resIndex];
    }

    class RandomizedSet {
        private Map<Integer, Integer> hashData;
        private Random random;
        private ArrayList<Integer> dataList;

        public RandomizedSet() {
            // do intialization if necessary
            hashData = new HashMap<>();
            dataList = new ArrayList<>();
            random = new Random();
        }


        /*
         * @param val: a value to the set
         * @return: true if the set did not already contain the specified element or false
         */
        public boolean insert(int val) {
            if (hashData.containsKey(val)) {
                return false;
            }
            dataList.add(val);
            hashData.put(val, dataList.size() - 1);
            return true;
        }

        /*
         * @param val: a value from the set
         * @return: true if the set contained the specified element or false
         */
        public boolean remove(int val) {
            // write your code here
            if (hashData.containsKey(val)) {
                int index = hashData.remove(val);
                if (index < dataList.size() - 1) {
                    //将index元素与最后一个元素置换  这样才能O（1）删除list中的元素
                    int last = dataList.get(dataList.size() - 1);
                    dataList.set(index, last);
                    hashData.put(last, index);
                    dataList.remove(dataList.size() - 1);
                }
                return true;
            }
            return false;
        }

        /*
         * @return: Get a random element from the set
         */
        public int getRandom() {
            // write your code here

            int i = random.nextInt(dataList.size());
            return dataList.get(i);
        }
    }

    public int nthUglyNumber(int n) {
        Queue<Long> priorityQueue = new PriorityQueue<>();
        HashSet<Long> inQ = new HashSet<>();
        Long[] primes = new Long[3];
        primes[0] = Long.valueOf(2);
        primes[1] = Long.valueOf(3);
        primes[2] = Long.valueOf(5);
        priorityQueue.offer(1L);
        inQ.add(1L);
        for (int i = n - 1; i > 0; i--) {
            Long top = priorityQueue.poll();
            for (int j = 0; j < 3; j++) {
                long nextPrime = top * primes[i];
                if (!inQ.contains(nextPrime)) {
                    priorityQueue.offer(nextPrime);
                    inQ.add(nextPrime);
                }
            }
        }
        return priorityQueue.peek().intValue();
    }

    class LRUCache {
        class ListNode {
            public int key;
            public int value;
            public ListNode next;

            ListNode(int key, int val) {
                this.key = key;
                this.value = val;
            }
        }

        HashMap<Integer, ListNode> key2prev;
        private ListNode dummy;
        private ListNode tail;
        private int capacity;
        private int size;

        /*
         * @param capacity: An integer
         */
        public LRUCache(int capacity) {
            key2prev = new HashMap<>();
            dummy = new ListNode(0, 0);
            this.tail = this.dummy;
            this.capacity = capacity;
        }

        private void move2tail(int key) {
            if (!key2prev.containsKey(key)) {
                return;
            }
            ListNode prev = key2prev.get(key);
            ListNode curt = prev.next;
            if (curt == tail) {
                return;
            }
            prev.next = curt.next;
            if (prev.next != null) {
                key2prev.put(prev.next.key, prev);
            }
            tail.next = curt;
            key2prev.put(curt.key, tail);
            tail = curt;
        }

        /*
         * @param key: An integer
         * @return: An integer
         */
        public int get(int key) {
            if (!key2prev.containsKey(key)) {
                return -1;
            }
            move2tail(key);
            return tail.value;
        }

        /*
         * @param key: An integer
         * @param value: An integer
         * @return: nothing
         */
        public void set(int key, int value) {
            if (get(key) != -1) {
                //已经存在，则更新value值
                ListNode prev = key2prev.get(key);
                prev.next.value = value;
                return;
            }
            if (size < capacity) {
                size++;
                ListNode cur = new ListNode(key, value);
                tail.next = cur;
                key2prev.put(key, tail);
                tail = cur;
                return;
            }

            ListNode first = dummy.next;
            key2prev.remove(first.key);
            //替换first节点
            first.key = key;
            first.value = value;
            key2prev.put(key, dummy);
            move2tail(key);
        }
    }
}
