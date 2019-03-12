package com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entity.User;
import com.freamwork.Result;
import com.freamwork.ResultSupport;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.UserService;

@Service("userService" )
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

	@Override
	public Result queryPage(Map<String, Object> params, Integer pageSize,Integer pageNum) {
		Result result = new ResultSupport();
		PageHelper.startPage(pageNum, pageSize);
		List<User> list = userDao.queryByParams(params);
		PageInfo<User> info = new PageInfo<>(list);
		result.setModel("pageInfo", info);
		return result;
	}

	@Override
	public Result login(String account, String password) {
		Result result = new ResultSupport();
		User user = userDao.login(account, password);
		if(null != user && null != user.getId()){
			result.setModel("entity", user);
		}else{
			result.setError("400", "账号或密码错误");
		}
		return result;
	}

	@Override
	public Result regist(User user) {
		Result result = new ResultSupport();
		int i = userDao.save(user);
		if(i != 1){
			result.setError("400", "注册异常");
		}
		return result;
	}

    
}
