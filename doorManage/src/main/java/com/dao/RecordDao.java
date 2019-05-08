package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Record;

public interface RecordDao {
	
	List<Record> queryByUserId(Integer id);
	
	int save(Record record);
	
	int open(@Param("roomCode")String roomCode,@Param("token")String token);
}
