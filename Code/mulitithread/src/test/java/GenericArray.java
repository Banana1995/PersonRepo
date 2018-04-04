/**
 * 版权所有：恒生电子股份有限公司 保留所有权利
 * 版本： 1.0
 * ******************************************
 * Copyright (c) by Hundsun ,Ltd.
 * All right reserved.
 * Last version:  1.0
 */

import java.util.List;

/*********************************
 * 文件名称: GenericArray.java
 * 系统名称：交易银行系统V1.0
 * 模块名称：
 * 功能说明:  
 * 开发人员：gaomy15805
 * 开发时间：2018-04-04 15:15
 * 修改记录：程序版本	修改日期	修改人员	修改单号	修改说明
 *********************************/

public class GenericArray<T> {

    private T[] array;

    public GenericArray(int sz) {
        array= (T[])new Object[sz];
    }
    public void put(int index, T item) {
        array[index] = item;
    }
    public T[] rep() { return array; }


    public static void main(String[] args) {
        GenericArray<String> gai = new GenericArray<String>(3);
        String[] c = gai.rep();
        System.out.println(gai);
    }

}