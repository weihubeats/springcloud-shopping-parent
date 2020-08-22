package com.zou.springbooteasyexcel;

import com.zou.springbooteasyexcel.com.util.TestFileUtil;

/**
 * @author WH
 * @version 1.0
 * @date 2020/4/22 22:07
 * @Description TODO
 */
public class Test {

    @org.junit.jupiter.api.Test
    public void Test() {
        String s = TestFileUtil.getPath();
        System.out.println(s);
        System.out.println(Test.class.getResource("/").getPath());

    }
}
