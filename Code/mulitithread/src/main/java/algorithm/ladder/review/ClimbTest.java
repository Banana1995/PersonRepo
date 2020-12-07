package algorithm.ladder.review;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class ClimbTest {

    public static void main(String[] args) {
        ClimbTest test = new ClimbTest(3);
        List<String> words = new ArrayList<>();
        words.add("dog");
        words.add("dad");
        words.add("dgdg");
        words.add("can");
        char[][] board = new char[3][4];
        board[0] = "doaf".toCharArray();
        board[1] = "agai".toCharArray();
        board[2] = "dcan".toCharArray();

        test.add(3);
        test.add(10);
        List<Integer> res = test.topk();
        log.info("结果为：{}", Arrays.toString(res.toArray()));

    }

    int capacity;
    Queue<Integer> queue;

    public ClimbTest(int k) {
        // do intialization if necessary
        capacity = k;
        queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
    }

    /*
     * @param num: Number to be added
     * @return: nothing
     */
    public void add(int num) {
        // write your code here
        System.out.println("add:" + num);
        queue.offer(num);
    }

    /*
     * @return: Top k element
     */
    public List<Integer> topk() {
        // write your code here
        List<Integer> res = new ArrayList<>();
        System.out.println(queue.size());
        // System.out.println("capacity" + capacity);
        for (int i = 0; i < capacity && i < queue.size(); i++) {
            int topValue = queue.poll();
            res.add(topValue);
        }

        for (int i = 0; i < res.size(); i++) {
            queue.offer(res.get(i));
        }

        return res;
    }
}
