package com.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Room;
import com.freamwork.Result;
import com.service.RoomService;

@Controller
@RequestMapping("/room")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@RequestMapping("/{location}")
	public String location(@PathVariable String location){
		return location;
	}
	
	 /**
     * 列表
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params,@RequestParam("pageSize") Integer pageSize,@RequestParam("pageNum")Integer pageNum){
        return roomService.queryPage(params,pageSize,pageNum);
    }
	
    @RequestMapping("/save")
    @ResponseBody
    public Result save(@RequestBody Room room){
        return roomService.save(room);
    }
}
