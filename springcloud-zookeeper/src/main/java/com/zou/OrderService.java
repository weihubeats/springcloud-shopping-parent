package com.zou;

/**
 * @author WH
 * @version 1.0
 * @date 2020/2/15 9:58
 */
public class OrderService {
    private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();
    private ZKLock zkLock = new ZookeeperDistrbuteLock();


    //获取订单号
    public void getNumber() {
        try {
            zkLock.getLock();
            String number = orderNumGenerator.getNumber();
            System.out.println("线程：" + Thread.currentThread().getName() + "生成订单号为：" + number);
        } catch (Exception e) {

        } finally {
            zkLock.unLock();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() ->{
                //模拟多个JVM产生多个OrderService实例
                System.out.println("线程：" + Thread.currentThread().getName() + "启动");
                new OrderService().getNumber();
            }).start();
        }
    }


}
