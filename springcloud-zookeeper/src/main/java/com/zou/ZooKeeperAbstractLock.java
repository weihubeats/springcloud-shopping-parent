package com.zou;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * 模板方法设计模式抽离重复代码到子类
 * @author WH
 * @version 1.0
 * @date 2020/2/15 9:26
 */
public abstract class ZooKeeperAbstractLock implements ZKLock {

    private static final String CONNECTION = "49.233.150.105:2181";
    protected ZkClient zkClient = new ZkClient(CONNECTION);
    protected String lockPath = "/lockPath";
    protected CountDownLatch countDownLatch = null;

    /*
    * 获取锁
    * */
    @Override
    public void getLock() {
        //1. 连接zk，在zk上创建/lock节点，节点为临时节点
        //2. 如果节点创建成功，获取锁，否则等待
        if (tryLock()) {
            System.out.println("获取锁成功");
        } else {
            //3. 事件监听节点是否被删除，删除则重新获取锁资源
            waitLock();
            // 4.重新获取锁
            getLock();
        }

    }

    /**
     * 获取锁资源，成功返回true，否则false
     */
    abstract void waitLock();

    abstract boolean tryLock();

    /*
    * 释放锁
    * */
    @Override
    public void unLock() {
        //程序执行完毕关闭连接
        if (zkClient != null) {
            zkClient.close();
            System.out.println("释放锁成功");
        }

    }

}
