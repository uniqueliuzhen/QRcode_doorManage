package com.service;

import java.util.Map;

import com.entity.Room;
import com.freamwork.Result;

public interface RoomService {

	Result queryPage(Map<String, Object> params, Integer pageSize, Integer pageNum);
	
	Result save(Room room);
	
	Result queryById(Integer id);
	
	Result update(Room room);
	
	Result queryAll();
	
	Result delete(Integer id);
}
