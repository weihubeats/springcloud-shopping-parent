package com.zou.springbooteasyexcel.com.Listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.zou.springbooteasyexcel.com.Dao.StudentDao;
import com.zou.springbooteasyexcel.com.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WH
 * @version 1.0
 * @date 2020/4/18 20:56
 * @Description TODO
 */
public class StudentListener extends AnalysisEventListener<Student> {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(com.zou.springbooteasyexcel.com.Listener.StudentListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<Student> list = new ArrayList<Student>();

    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private StudentDao studentDao;

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param studentDao
     */
    public StudentListener(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param student
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param analysisContext
     */
    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        System.out.println("invoke方法被调用");
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(student));
        list.add(student);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }

    }
    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("doAfterAllAnalysed方法 被调用");
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        LOGGER.info("所有数据解析完成！");

    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        studentDao.save(list);
        LOGGER.info("存储数据库成功！");
    }
}
