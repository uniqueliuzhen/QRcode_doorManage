package com.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.entity.Room;

public interface RoomDao {

	int save(Room room);
	
	List<Room> queryByParams(@Param("params")Map<String,Object> params);
	
	int delete(Integer id);
	
	int update(Room room);
	
	Room queryById(@Param("id")Integer id);
	
	List<Room> queryAll();
	
}
