import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class SelectTest {
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

    /**
     * 通过id查询
     * SELECT id,name,age,email,manager_id,create_time
     *  FROM user
     *  WHERE id=1088248166370832385;
     */
    @Test
    public void selectById() {
        User user = userMapper.selectById(1088248166370832385L);
    }

    /**
     * 条件查询
     * SELECT id,name,age,email,manager_id,create_time
     *  FROM user
     *  WHERE (name = '阿离' AND age > 18 OR email IS NOT NULL);
     */
    @Test
    public void selectByCondition() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "阿离").gt("age", 18).or().isNotNull("email");
        List<User> users = userMapper.selectList(wrapper);
        print(users);
    }



    /** 通过user 查询
     * SELECT id,name,age,email,manager_id,create_time
     *  FROM user
     *  WHERE name='阿离' AND age=18 AND email='641884200@qq.com';
     */
    @Test
    public void selectByUser() {
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        List<User> users = userMapper.selectList(wrapper);
        print(users);

    }

    /**
     *  lambda 条件构造器 防误写
     * SELECT id,name,age,email,manager_id,create_time,update_time
     *  FROM user
     *  WHERE (age LIKE '%雨%' AND (age < 40 OR email IS NOT NULL));
     */
    @Test
    public void selectLambda() {

        LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
        lambda.like(User::getAge, "雨").
                and(q -> q.lt(User::getAge, 40).or().isNotNull(User::getEmail));
        userMapper.selectList(lambda);
    }

    /**
     * 拼接在sql最后
     * SELECT id,name,age,email,manager_id,create_time
     *  FROM user
     *  WHERE (age IN (30,31,32))
     *  LIMIT 1;
     */
    @Test
    public void selectOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.in("age", Arrays.asList(30, 31, 32)).last("limit 1");
        List<User> users = userMapper.selectList(wrapper);
    }

    /**
     *  查询指定字段
     */
    @Test
    public void selectColum() {
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        wrapper.select("name", "age");
        userMapper.selectList(wrapper);

    }

    /**
     * 排除指定字段
     * SELECT id,name,manager_id,create_time,update_time
     *  FROM user
     *  WHERE name='阿离' AND age=18 AND email='641884200@qq.com';
     */
    @Test
    public void selectExclude() {
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        wrapper.select(User.class, s ->
            !s.getColumn().equals("email") && !s.getColumn().equals("age")
        );
        List<User> users = userMapper.selectList(wrapper);


    }

    /**
     * 分组排序求和
     * SELECT avg(age) age,min(id)
     *  FROM user
     *  WHERE name='阿离' AND age=18 AND email='641884200@qq.com' GROUP BY id HAVING avg(age)<30;
     */
    @Test
    public void selectGroupBy() {
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        wrapper.select("avg(age) age", "min(id)").groupBy("id")
                .having("avg(age)<{0}", 30);
        List<User> users = userMapper.selectList(wrapper);

    }


    /**
     * 分页
     */
    @Test
    public void selectPage() {
        LambdaQueryWrapper<User> lambda = new LambdaQueryWrapper<>();
        lambda.ge(User::getAge,26).orderByDesc(User::getCreateTime);
        Page<User> page = new Page<> (1,2);
        IPage<User> userIPage = userMapper.selectPage(page, lambda);
        System.out.println("总页数："+userIPage.getPages());
        System.out.println("总记录数："+userIPage.getTotal());
        List<User> list = userIPage.getRecords();
        print(list);
    }

    /**
     * 一条sql不查询总记录数 减少性能消耗
     */
    @Test
    public void selectPageNotCount() {
        LambdaQueryWrapper<User> lambda = new LambdaQueryWrapper<>();
        lambda.ge(User::getAge,26).orderByDesc(User::getCreateTime);
        Page<User> page = new Page<> (1,2, false);//不查记录数
        IPage<User> userIPage = userMapper.selectPage(page, lambda);
        System.out.println("总页数："+userIPage.getPages());
        System.out.println("总记录数："+userIPage.getTotal());
        List<User> list = userIPage.getRecords();
        print(list);
    }

    /**
     * 自定义分页
     */
    @Test
    public void selectCustom() {
        Page<User> page = new Page<>(1, 3);
        IPage<User> userIPage = userMapper.selectPage(page, user);
        System.out.println("总页数："+userIPage.getPages());
        System.out.println("总记录数："+userIPage.getTotal());
        print(userIPage.getRecords());

    }



    public static void print(List<User> users) {
        users.forEach(s -> System.out.println("姓名：" + s.getName()));
    }


}
