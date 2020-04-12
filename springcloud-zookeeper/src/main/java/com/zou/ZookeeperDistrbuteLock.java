package com.zou;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * @author WH
 * @version 1.0
 * @date 2020/2/15 9:38
 */
public class ZookeeperDistrbuteLock extends ZooKeeperAbstractLock {
    @Override
    void waitLock() {
        //监听事件通知
        IZkDataListener iZkDataListener = new IZkDataListener() {
            //节点修改
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            //节点删除
            @Override
            public void handleDataDeleted(String s) throws Exception {
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }

            }
        };
        //注册事件
        zkClient.subscribeDataChanges(lockPath, iZkDataListener);
        // 如果节点存在
        if (zkClient.exists(lockPath)) {
            try {
                countDownLatch = new CountDownLatch(1);
                countDownLatch.await();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        //删除事件  防止事件监听多次
        zkClient.unsubscribeDataChanges(lockPath, iZkDataListener);

    }

    @Override
    boolean tryLock() {
        try {
            //创建临时节点
            zkClient.createEphemeral(lockPath);
            return true;
        } catch (Exception e) {
            //如果临时节点创建失败
            return false;

        }
    }
}
