package com.zou;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author WH
 * @version 1.0
 * @date 2020/2/15 10:01
 */
public class OrderNumGenerator {
    //生成订单号
    private static  int count = 0;

    public String getNumber() {
        try {
            Thread.sleep(200);
        } catch (Exception e) {

        }
        SimpleDateFormat simpt = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return simpt.format(new Date()) + "-" + ++count;

    }
}
