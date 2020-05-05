import com.zou.Application;
import com.zou.dao.UserMapper;
import com.zou.entity.User;
import com.zou.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author WH
 * @version 1.0
 * @date 2020/5/5 11:23
 * @Description TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class InsertTest {
    static User user;

    static {
        user = new User();
        user.setName("阿离");
        user.setAge(18);
        user.setEmail("641884200@qq.com");
    }

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;


    // 1. 通过user insert
    @Test
    public void insert() {
        int row = userMapper.insert(user);
        System.out.println("row" + row);
        System.out.println("自增ID " + user.getId());

    }

    /**
     * 批量插入
     */
    @Test
    public void saveBatch(){
        User user1 = new User();
        user1.setName("阿离");
        user1.setAge(19);

        User user2 = new User();
        user2.setName("韩信");
        user2.setAge(20);

        List<User> list = Arrays.asList(user1,user2);

        boolean success = userService.saveBatch(list);
        System.out.println("是否批量插入成功："+success);
    }


}
