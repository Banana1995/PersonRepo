/**
 * 版权所有：恒生电子股份有限公司 保留所有权利
 * 版本： 1.0
 * ******************************************
 * Copyright (c) by Hundsun ,Ltd.
 * All right reserved.
 * Last version:  1.0
 */

import java.util.ArrayList;
import java.util.List;

/*********************************
 * 文件名称: ArraryTest.java
 * 系统名称：交易银行系统V1.0
 * 模块名称：
 * 功能说明:  
 * 开发人员：gaomy15805
 * 开发时间：2018-04-04 16:06
 * 修改记录：程序版本	修改日期	修改人员	修改单号	修改说明
 *********************************/

public class ArraryTest {

    static class Fruit {
    }

    static class Apple extends Fruit {
    }

    static class Jonathan extends Apple {
    }

    static class Orange extends Fruit {
    }

    public static class CovariantArrays {
        public static void main(String[] args) {
            Fruit[] fruit = new Apple[10];
            fruit[0] = new Apple(); // OK
            fruit[1] = new Jonathan(); // OK
            // Runtime type is Apple[], not Fruit[] or Orange[]:
            try {
                // Compiler allows you to add Fruit:
                fruit[0] = new Fruit(); // ArrayStoreException
            } catch (Exception e) {
                System.out.println(e);
            }
            try {
                // Compiler allows you to add Oranges:
                fruit[0] = new Orange(); // ArrayStoreException
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}