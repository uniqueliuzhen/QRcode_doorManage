package com.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RecordDao;
import com.dao.UserDao;
import com.entity.Record;
import com.entity.Room;
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
    
    @Autowired
    private RecordDao recordDao;
    
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
			result.setModel("user", user);
		}else{
			result.setError("400", "账号或密码错误");
		}
		return result;
	}

	@Override
	public Result regist(User user) {
		Result result = new ResultSupport();
		user.setStatus(3);
		user.setType(2);
		int i = userDao.save(user);
		if(i != 1){
			result.setError("400", "注册异常");
		}
		return result;
	}

	@Override
	public Result save(User user) {
		Result result = new ResultSupport();
		user.setStatus(3);
		user.setType(2);
		user.setToken(UUID.randomUUID().toString().replace("-", ""));
		user.setWechatId("or8gM5PmsW5lkFHkvqLhw5hwFxRo");
		int i = userDao.save(user);
		if(i == 1){
			List<Room> list = user.getList();
			for (Room room : list) {
				Record record = new Record();
				record.setRoomId(room.getId());
				record.setUserId(user.getId());
				recordDao.save(record);
			}
		}else{
			result.setError("400", "保存异常");
		}
		return result;
	}

	@Override
	public Result queryByWechatId(String wechatId) {
		Result result = new ResultSupport();
		try {
			User user = userDao.queryByWechatId(wechatId);
			if(null != user && null != user.getId()){
				result.setModel("user", user);
			}else{
				result.setError("401", "该账号未注册");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
