package com.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.entity.User;

public interface UserDao {

	int save(User user);
	
	User login(@Param("account")String account,@Param("password")String password);
	
	List<User> queryByParams(@Param("params")Map<String,Object> params);
	
}
