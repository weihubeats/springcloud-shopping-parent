package mybatis;

import com.mybatis.MybatisApplication;
import com.mybatis.dao.UserDao;
import com.mybatis.po.Teacher;
import com.mybatis.po.User;
import com.mybatis.utils.TransactionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionStatus;

import java.util.List;


/**
 * @author WH
 * @version 1.0
 * @date 2019/11/24 22:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisApplication.class)
public class SpringbootTest {

    @Autowired
    Teacher teacher;

    @Autowired
    TransactionUtils transactionUtils;
    @Autowired
    UserDao userDao;


    @Test
    public void getUserTest() {
        List<User> users = userDao.getUser();
        users.forEach((user)-> System.out.println(user.getName()));

    }

    @Test
    public void addUserTest() {
//        TransactionStatus transactionStatus = null;
//        try {
            User user = new User("12365987", "6666", "女", "小奏");
            //开启事务
//            transactionStatus = transactionUtils.begin();
            userDao.addUser(user);
            int i = 1 / 0;
            userDao.addUser(user);
            //提交事务
//            transactionUtils.commit(transactionStatus);
//        } catch (Exception e) {
//            e.printStackTrace();
//            //回滚事务
//            if (transactionStatus != null) {
//                transactionStatus.rollbackToSavepoint(transactionStatus);
//            }
//        }

    }
//    @Test
//    public void testUser(){
//        System.out.println(user);
//    }

    }



