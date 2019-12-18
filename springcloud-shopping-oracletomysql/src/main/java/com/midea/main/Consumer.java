package com.midea.main;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

/**
 * @author wh
 * @version 1.0
 * @date 2019-12-16 10:08
 */

public class Consumer implements Runnable {
    private BlockingQueue<String> blockingQueue;


    private volatile boolean FLAG = true;

    private Connection mysqlCon;


    public Consumer(BlockingQueue<String> blockingQueue  /*, Connection mysqlCon, boolean FLAG*/) {

        try {

            this.mysqlCon = Main.init(Main.MYSQL_DRIVER, Main.MYSQL_URL, Main.MYSQL_USERNAME, Main.MYSQL_PASSWORD);


            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        try {

                            PreparedStatement preparedStatement = mysqlCon.prepareStatement("select 1");
                            ResultSet rs = preparedStatement.executeQuery();
                            System.out.println("执行一次 连接线程" + rs.next());
                            Thread.sleep(50 * 1000);

                            if (preparedStatement != null) {
                                preparedStatement.close();
                            }
                            if (rs != null) {
                                rs.close();
                            }



                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();




        } catch (Exception e) {
            e.printStackTrace();
        }

        this.blockingQueue = blockingQueue;

    }

    @Override
    public void run() {
        System.out.println("消费者启动");


        while (true) {
            String mysqlSql = null;

                try {
                    mysqlSql = blockingQueue.take();

                    PreparedStatement preparedStatement = mysqlCon.prepareStatement(mysqlSql);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();



                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("消费者发生异常" );

                }

        }



    }

    //停止线程
    public void stop() {
        this.FLAG = false;
    }
}
