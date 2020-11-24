package algorithm.ladder.normal;

import algorithm.AlgorithmUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@Data
@Slf4j
public class SeconWeek {


    public static void main(String[] args) {
        SeconWeek seconWeek = new SeconWeek();
        int[][] data = AlgorithmUtil.praseMatrix("[[3,9],[7,12],[3,8],[6,8],[9,10],[2,9],[0,9],[3,9],[0,6],[2,8]]");
//        boolean[][] booleansData = new boolean[data.length][data[0].length];
//        for (int i = 0; i < data.length; i++) {
//            for (int j = 0; j < data[0].length; j++) {
//                if (data[i][j] == 1) {
//                    booleansData[i][j] = true;
//                }
//
//            }
//        }


        Set<String> dict = getDict();
        dict.stream().map(s -> s.toUpperCase()).forEach(log::info);
//        int i = seconWeek.ladderLength("a","c",dict);
        int i = seconWeek.ladderLength("hit", "cog", dict);
//        System.out.println(Arrays.toString(lists.toArray()));
        System.out.println(i);
    }

    @NotNull
    private static Set<String> getDict() {
        Set<String> dict = new HashSet<>();
//        dict.add("a");
//        dict.add("b");
//        dict.add("c");
        dict.add("hot");
        dict.add("dot");
        dict.add("dog");
        dict.add("lot");
        dict.add("log");
        return dict;
    }

    public List<List<Integer>> threeSum(int[] numbers) {
        int len = numbers.length;
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(numbers);
        for (int i = 0; i < len; i++) {
            int firstnum = numbers[i];
            int twosumTarget = -firstnum;
            List<List<Integer>> twosumAns = twosum(numbers, twosumTarget, i + 1, len - 1, firstnum);
            ans.addAll(twosumAns);
        }
        return ans;
    }

    private List<List<Integer>> twosum(int[] numbers, int target, int begin, int end, int firstnum) {
        int left = begin;
        int right = end;
        List<List<Integer>> twosumAns = new ArrayList<>();
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                List<Integer> temp = new ArrayList<>();
                temp.add(firstnum);
                temp.add(numbers[left]);
                temp.add(numbers[right]);
                twosumAns.add(temp);
                left++;
                right--;
                while (left < right && numbers[left] == numbers[left - 1]) {
                    left++;
                }
                while (left < right && numbers[right] == numbers[right + 1]) {
                    right--;
                }
            } else if (numbers[left] + numbers[right] < target) {
                left++;
            } else if (numbers[left] + numbers[right] > target) {
                right--;
            }
        }
        return twosumAns;
    }

    public int partitionArray(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        int tempStash = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= k) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] < k) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = tempStash;
        return tempStash >= k ? left : left + 1;
    }

    public int[] kClosestNumbers(int[] A, int target, int k) {
        int len = A.length;
        // bisect to find closest to target number index
        int left = 0;
        int right = len - 1;
        int mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (A[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        //跳出循环时right==left 指向的是大于等于mid的第一个值
        left = right - 1;

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            if (left >= 0 && right < len) {
                if (Math.abs(A[left] - target) <= Math.abs((A[right] - target))) {
                    res[i] = A[left];
                    left--;
                } else {
                    res[i] = A[right];
                    right++;
                }
            } else if (left > 0) {
                res[i] = A[left];
                left--;
            } else {
                res[i] = A[right];
                right++;
            }
        }
        return res;
    }

    public int findMin(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1] && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[right];

    }

    class Node {
        int i;
        int j;
        boolean val;

        public Node(int i, int j, boolean val) {
            this.i = i;
            this.j = j;
            this.val = val;
        }
    }

    private static int[] dx = new int[]{0, 1, 0, -1};
    private static int[] dy = new int[]{1, 0, -1, 0};

    public int numIslands(boolean[][] grid) {
        Queue<Node> queue = new LinkedList();
        if (isEmptyGrid(grid)) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] && !visited[i][j]) {
                    queue.add(new Node(i, j, grid[i][j]));
                    visited[i][j] = true;
                    bfsForIsland(visited, grid, i, j, queue);
                    count++;
                }
            }
        }
        return count;
    }


    private void bfsForIsland(boolean[][] visted, boolean[][] grid, int i, int j, Queue<Node> queue) {
        int m = grid.length;
        int n = grid[0].length;
        while (!queue.isEmpty()) {
            Node top = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nexti = top.i + dx[k];
                int nextj = top.j + dy[k];
                if (nexti >= 0 && nexti < m && nextj >= 0 && nextj < n) {
                    if (grid[nexti][nextj] && !visted[nexti][nextj]) {
                        queue.add(new Node(nexti, nextj, grid[nexti][nextj]));
                    }
                    visted[nexti][nextj] = true;
                }
            }
        }
    }


    private static int[] ddx = new int[]{1, 1, -1, -1, 2, 2, -2, -2};
    private static int[] ddy = new int[]{2, -2, 2, -2, 1, -1, 1, -1};

    private static boolean isEmptyGrid(boolean[][] grid) {
        return grid == null || grid.length == 0 || grid[0].length == 0;
    }

    private static boolean isOutBound(int xlen, int ylen, int nextx, int nexty) {
        if (nextx < 0 || nextx >= xlen || nexty < 0 || nexty >= ylen) {
            return true;
        }
        return false;
    }

    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        int minDis = 0;
        if (isEmptyGrid(grid)) {
            return -1;
        }
        Queue<Point> pointQueue = new LinkedList<>();
        pointQueue.offer(source);
        while (!pointQueue.isEmpty()) {
            int size = pointQueue.size();
            for (int k = 0; k < size; k++) {
                Point top = pointQueue.poll();
                for (int i = 0; i < 8; i++) {
                    int nextx = top.x + ddx[i];
                    int nexty = top.y + ddy[i];
                    if (isOutBound(grid.length, grid[0].length, nextx, nexty)) {
                        continue;
                    }
                    if (grid[nextx][nexty]) {
                        continue;
                    }
                    if (nextx == destination.x && nexty == destination.y) {
                        return minDis;
                    }
                    pointQueue.offer(new Point(nextx, nexty));
                }
                minDis++;
            }
        }
        return -1;
    }

    class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    public int findMinArrowShots(int[][] points) {
        if (isEmptyPoint(points)) {
            return 0;
        }
        Point[] pointsArray = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            pointsArray[i] = new Point(points[i][0], points[i][1]);
        }
        Arrays.sort(pointsArray, Comparator.comparingInt(o -> o.y));
        int topEnd = 0;
        int count = 0;
        for (int i = 0; i < pointsArray.length; i++) {
            Point point = pointsArray[i];
            if (point.x > topEnd || count == 0) {
                topEnd = point.y;
                count++;
            }
        }
        return count;
    }

    private static boolean isEmptyPoint(int[][] points) {
        return points == null || points.length == 0 || points[0].length == 0;
    }


    public int ladderLength(String start, String end, Set<String> dict) {
        Set<String> visited = new HashSet<>();
        Queue<String> bfsqueue = new LinkedList<>();
        bfsqueue.offer(start);
        dict.add(end);
        int count = 1;
        while (!bfsqueue.isEmpty()) {
            int size = bfsqueue.size();
            for (int i = 0; i < size; i++) {
                String topDict = bfsqueue.poll();
                if (topDict.equals(end)) {
                    return count;
                }
                Set<String> teStrSet = findNextDict(topDict, visited, dict);//在dict中找到与topdict相差一个字符的字符串
                for (String str : teStrSet) {
                    bfsqueue.offer(str);
                    visited.add(str);
                }
            }
            count++;
        }
        return 0;
    }

    private Set<String> findNextDict(String topDict, Set<String> visited, Set<String> dict) {
        Set<String> res = new HashSet<>();
        for (String dic : dict) {
            if (visited.contains(dic)) {
                continue;
            }
            int diff = 0;
            for (int i = 0; i < topDict.length() && diff <= 1; i++) {
                if (topDict.charAt(i) != dic.charAt(i)) {
                    diff++;
                }
            }
            if (diff == 1) {
                res.add(dic);
            }
        }
        return res;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];

        //统计每个课程的入度
        int[] degree = new int[numCourses];

        //统计依赖课程与当前课程的关系，下标为依赖课程，值为当前课程
        List<Integer>[] depCourse = new List[numCourses];

        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            degree[course]++;
            if (depCourse[prerequisites[i][1]] == null) {
                depCourse[prerequisites[i][1]] = new ArrayList<>();
            }
            depCourse[prerequisites[i][1]].add(course);
        }

        Queue<Integer> courseQueue = new LinkedList<>();
        int j = 0;
        for (int i = 0; i < numCourses; i++) {
            if (degree[i] == 0) {
                res[j] = i;
                courseQueue.add(i);
                j++;
            }
        }

        while (!courseQueue.isEmpty()) {
            int course = courseQueue.poll();
            //取出依赖course的课
            List<Integer> depList = depCourse[course];
            for (Integer c : depList) {
                degree[c]--;
                if (degree[c] == 0) {
                    res[j] = c;
                    courseQueue.add(c);
                    j++;
                }
            }
        }
        return j == numCourses ? res : new int[0];
    }
}

