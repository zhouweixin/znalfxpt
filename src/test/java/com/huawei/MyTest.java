package com.huawei;

import com.huawei.entity.User;
import junit.framework.TestCase;

public class MyTest extends TestCase {
    public void testFun1(){
        int[] a = new int[]{1,2, 3};
        fun1(a);
    }

    public void fun1(int... strs){
        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);
        }
    }
}
