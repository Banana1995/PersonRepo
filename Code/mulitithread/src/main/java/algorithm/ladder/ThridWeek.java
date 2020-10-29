package algorithm.ladder;

import java.util.Stack;

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

//    public int trapRainWater(int[] heights) {
//        //维护一个单调递减的栈 遇到大于栈顶的元素，则将栈顶pop出来。栈顶表示的是左右最大的元素
//
//        int left = 0;
//        int right = heights.length - 1;
//        int sum = 0;
//        int minHeight = 0;
//        while (left < right) {
//            if (minHeight == 0) {
//                if (heights[left] <= heights[right]) {
//                    minHeight = heights[left];
//                    left++;
//                } else {
//                    minHeight = heights[right];
//                    right--;
//                }
//                continue;
//            }
//
//            if (heights[left] <= minHeight) {
//                sum += minHeight - heights[left];
//                left++;
//            }
//
//            if (heights[right] <= minHeight) {
//                //更新minHeight
//                right--;
//                sum += minHeight - heights[right];
//            }
//
//
//        }
//
//
//    }


}
