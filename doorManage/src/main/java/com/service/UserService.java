package com.service;

import java.util.Map;

import com.entity.User;
import com.freamwork.Result;

public interface UserService {

    Result queryPage(Map<String, Object> params, Integer pageSize, Integer pageNum);

    Result login(String account,String password);
    
    Result regist(User user);
    
    Result save(User user);
    
    Result queryByWechatId(String wechatId);
}

