package com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RecordDao;
import com.dao.RoomDao;
import com.entity.Record;
import com.entity.Room;
import com.freamwork.Result;
import com.freamwork.ResultSupport;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.RoomService;

@Service("roomService" )
public class RoomServiceImpl implements RoomService{

	@Autowired
	private RoomDao roomDao;
	
	@Autowired
	private RecordDao recordDao;
	
	@Override
	public Result queryPage(Map<String, Object> params, Integer pageSize,Integer pageNum) {
		Result result = new ResultSupport();
		PageHelper.startPage(pageNum, pageSize);
		List<Room> list = roomDao.queryByParams(params);
		PageInfo<Room> info = new PageInfo<>(list);
		result.setModel("pageInfo", info);
		return result;
	}

	@Override
	public Result save(Room room) {
		Result result = new ResultSupport();
		try {
			int i = roomDao.save(room);
			if(i != 1){
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result queryById(Integer id) {
		Result result = new ResultSupport();
		Room room = roomDao.queryById(id);
		result.setModel("entity", room);
		return result;
	}
	
	@Override
	public Result update(Room room) {
		Result result = new ResultSupport();
		try {
			int i = roomDao.update(room);
			if(i != 1){
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result queryAll() {
		Result result = new ResultSupport();
		List<Room> list = roomDao.queryAll();
		result.setModel("list", list);
		return result;
	}

	@Override
	public Result delete(Integer id) {
		Result result = new ResultSupport();
		try {
			int i = roomDao.delete(id);
			if(i != 1){
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result open(String token, String roomCode) {
		Result result = new ResultSupport();
		try {
			int i = recordDao.open(roomCode, token);
			if(i != 1){
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}

