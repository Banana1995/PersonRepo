import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

@Slf4j
public class MultiThread extends Thread {

    private ExecutorService executorService;

    private ScheduledExecutorService singleScheduledExecutorService;

    private LinkedBlockingQueue<String> queue;

    public static void main(String[] args) {

        MultiThread multiThread = new MultiThread();
        boolean res = multiThread.wordPatternMatch("d", "ef");
        System.out.println(res);
    }

    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern == null || pattern.length() == 0) {
            return false;
        }
        Map<Character, String> charMap = new HashMap<>();
        Set<String> matchStrSet = new HashSet<>();
        return dfsDetect(pattern, str, charMap, matchStrSet);
    }

    private boolean dfsDetect(String pattern, String str, Map<Character, String> charMap,
                              Set<String> matchStrSet) {
        if (str.isEmpty() && pattern.isEmpty()) {
            return true;
        }
        if (str.isEmpty() || pattern.isEmpty()) {
            return false;
        }
        char curp = pattern.charAt(0);
        if (charMap.containsKey(curp)) {
            String curReplceStr = charMap.get(curp);
            //当前字符已经有了匹配项了
            return dfsDetect(pattern.substring(1, pattern.length()),
                    str.substring(curReplceStr.length(), str.length()),
                    charMap,
                    matchStrSet);
        }
        //当前字符没有匹配项
        for (int i = 1; i < str.length(); i++) {
            String curRep = str.substring(0, i);
            //此处需要检测curRep是否之前已经有过匹配项
            //不存在两个p字符对应同一段str的情况
            if (matchStrSet.contains(curRep)) {
                continue;
            }
            charMap.put(curp, curRep);
            matchStrSet.add(curRep);
            boolean match = dfsDetect(
                    pattern.substring(1, pattern.length()),
                    str.substring(curRep.length(), str.length()),
                    charMap,
                    matchStrSet
            );
            if (match) {
                return true;
            }
            matchStrSet.remove(curRep);
            charMap.remove(curp);
        }
        return false;
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null) {
            return new ArrayList<>();
        }

        //dp:nums中的值与之对应的最大的因子子集个数
        Map<Integer, Integer> dp = new HashMap<>();
        //dp中的因子，与之对应的前一个转换来的因子
        Map<Integer, Integer> pre = new HashMap<>();
        Arrays.sort(nums);
        for (int num : nums) {
            dp.put(num, 1);//1 是任何数的因子
            pre.put(num, 0);
        }

        for (int num : nums) {
            //获取num的因子集合
            List<Integer> factorList = getFactorList(num);

            for (Integer factor : factorList) {
                if (!dp.containsKey(factor)) {
                    continue;//检查因子是否存在于nums数组中
                }
                int tmp = dp.get(factor) + 1;
                if (tmp > dp.get(num)) {//dp[i] = Max(dp[0~i-1]) + 1
                    dp.put(num, tmp);
                    pre.put(num, factor);
                }
            }
        }

        int maxsize = 0;
        int maxnum = 0;
        for (Map.Entry<Integer, Integer> dpEntry : dp.entrySet()) {
            int num = dpEntry.getKey();
            int size = dpEntry.getValue();
            if (size > maxsize) {
                maxsize = size;
                maxnum = num;
            }
        }

        Deque<Integer> result = new LinkedList<>();
//        result.push(maxnum);
        // System.out.println(maxnum);
        int preNum = maxnum;
        while (preNum != 0) {
            result.push(preNum);//从头部推入
            preNum = pre.get(preNum);
        }

        return new ArrayList<>(result);
    }

    private List<Integer> getFactorList(int num) {
        List<Integer> result = new ArrayList<>();
        if (num == 1) {
            return result;
        }
        result.add(1);
        for (int i = 2; i * i < num; i++) {
            if (num % i != 0) {
                continue;//num 对 i 取余不为0 则不可能成为整除的因子
            }
            result.add(i);
            if (num / i != i) {
                result.add(num / i);
            }
        }
        return result;
    }
//    CountDownLatch

    /**
     * 模仿用户获取序列号方法 往队列中放入数据
     *
     * @param user
     * @throws InterruptedException
     */
    public void ImitateUser(String user) throws InterruptedException {
        queue.put(user);
        Map<String, Integer> ass = new HashMap<>();
//        ass.c

        System.out.println(Thread.currentThread().getName()+"线程添加节点："+user+"，队列中共有"+queue.size()+"条数据,当前线程状态为:"+Thread.currentThread().getState());
    }

    private Runnable ImitateDeleteNode() {
        return new Runnable() {
            @Override
            public void run() {

                System.out.println(Thread.currentThread().getName()+Thread.currentThread().getState()+" 删除了节点:"+MultiThread.this.queue.poll()+Thread.currentThread().getState());
            }
        };
    }


}
