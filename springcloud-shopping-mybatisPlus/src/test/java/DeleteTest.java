import com.baomidou.mybatisplus.core.conditions.Wrapper;
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
public class DeleteTest {
    static User user;

    static {
        user = new User();
        user.setName("阿离");
        user.setAge(18);
        user.setEmail("641884200@qq.com");
    }

    @Autowired
    private UserMapper userMapper;



    /** 1. 通过id删除
     * DELETE
     *  FROM user
     *  WHERE id=1257518661061697538;
     */
    @Test
    public void deleteById() {
        Long id = 1257518661061697538L;
        int row = userMapper.deleteById(id);
        System.out.println("row" + row);

    }

    /** 2. 通过条件 wrapper 删除
     * DELETE
     *  FROM user
     *  WHERE name='阿离' AND age=18 AND email='641884200@qq.com';
     */
    @Test
    public void deleteByWrapper() {
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        int row = userMapper.delete(wrapper);
    }

    /** 通过map构建条件删除
     * DELETE
     *  FROM user
     *  WHERE name = '阿离' AND age = 18;
     */
    @Test
    public void deleteByMap() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("name", "阿离");
        map.put("age", 18);
        int row = userMapper.deleteByMap(map);
    }


}
