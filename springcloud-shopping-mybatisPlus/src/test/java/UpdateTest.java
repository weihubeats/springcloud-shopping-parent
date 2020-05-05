import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.zou.Application;
import com.zou.dao.UserMapper;
import com.zou.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @author WH
 * @version 1.0
 * @date 2020/5/5 11:23
 * @Description TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UpdateTest {
    static User user;

    static {
        user = new User();
        user.setName("阿离");
        user.setAge(22);
        user.setEmail("641884200@qq.com");
    }

    @Autowired
    private UserMapper userMapper;


    @Test
    public void update() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        user.setId(1257562845848137729L);
        userMapper.updateById(user);

    }


}
