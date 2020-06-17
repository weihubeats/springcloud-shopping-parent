import com.zou.entity.User;
import com.zou.quer.UserDAO;
import com.zou.quer.UserDAOMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author WH
 * @version 1.0
 * @date 2020/5/10 22:53
 * @Description TODO
 */
public class TestMapStruct {
    @Test
    public void test() {
        //given
        User user = new User();
        user.setId(100l);
        user.setAge(18);
        user.setEmail("641884200@163.com");

        User user1 = new User();
        user1.setId(222l);
        user1.setAge(222);
        user1.setEmail("2222@163.com");

        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user1);

        UserDAO userdao = UserDAOMapper.INSTANCE.UserToUserDto(user);
        List<UserDAO> userdaos = UserDAOMapper.INSTANCE.UsersToUserDtos(list);
        userdaos.forEach(e -> System.out.println(e) );
        System.out.println("\n" + userdao);


    }

    @Test
    public void test1() {
        User user = new User();
        User user1;
        System.out.println(user.getAge());
       Integer age =  Optional.ofNullable(user.getAge()).orElse(666);
        System.out.println(age);

    }








}
