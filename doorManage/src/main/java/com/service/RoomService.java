package com.service;

import java.util.Map;

import com.entity.Room;
import com.freamwork.Result;

public interface RoomService {

	Result queryPage(Map<String, Object> params, Integer pageSize, Integer pageNum);
	
	Result save(Room room);
	
}
