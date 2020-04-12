package com.zou;

/**
 * @author WH
 * @version 1.0
 * @date 2020/2/15 9:25
 */
public interface ZKLock {

    //获取锁
    public void getLock();

    //释放锁
    public void unLock();
}
