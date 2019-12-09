package mybatis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/26 21:32
 */
public class Test {
    public static void main(String[] args) {

        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            newCachedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    System.out.println(Thread.currentThread().getName() + "正在执行");

                }
            });
        }
        }
}
