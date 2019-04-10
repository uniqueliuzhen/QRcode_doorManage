package com.dao;

import java.util.List;

import com.entity.Record;

public interface RecordDao {
	
	List<Record> queryByUserId(Integer id);
	
	int save(Record record);
}
