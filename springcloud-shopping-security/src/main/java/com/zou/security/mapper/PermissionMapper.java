package com.zou.security.mapper;

import com.zou.security.module.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper {

	@Select(" select * from sys_permission ")
	List<Permission> findAllPermission();

}
