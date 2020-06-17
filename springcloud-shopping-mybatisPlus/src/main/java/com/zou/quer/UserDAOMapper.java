package com.zou.quer;

import com.zou.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author WH
 * @version 1.0
 * @date 2020/5/10 22:53
 * @Description TODO
 */
@Mapper
public interface UserDAOMapper {

    UserDAOMapper INSTANCE = Mappers.getMapper( UserDAOMapper.class );

    UserDAO UserToUserDto(User user);

    List<UserDAO> UsersToUserDtos(List<User> users);
}
