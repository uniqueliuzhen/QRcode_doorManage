package com.controller;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.entity.Room;
import com.freamwork.Result;
import com.service.RoomService;

@Controller
@RequestMapping("/room")
public class RoomController {
	int i=0;
	@Autowired
	private RoomService roomService;

	@RequestMapping("/{location}")
	public String location(@PathVariable String location) {
		return location;
	}

	/**
	 * 列表
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public Result list(@RequestParam Map<String, Object> params, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("pageNum") Integer pageNum) {
		return roomService.queryPage(params, pageSize, pageNum);
	}

	@RequestMapping("/queryAll")
	@ResponseBody
	public Result queryAll() {
		return roomService.queryAll();
	}

	@RequestMapping("/queryById")
	@ResponseBody
	public Result queryById(@RequestParam Integer id) {
		return roomService.queryById(id);
	}

	@RequestMapping("/save")
	@ResponseBody
	public Result save(@RequestBody Room room) {
		return roomService.save(room);
	}

	@RequestMapping("/update")
	@ResponseBody
	public Result update(@RequestBody Room room) {
		return roomService.update(room);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(@RequestParam Integer id) {
		return roomService.delete(id);
	}

	@RequestMapping("/open")
	@ResponseBody
	public String open(HttpServletRequest request) {
		String str = readLine(request);
		String [] string = str.split("&&");
		Map<String,Object> map = new HashMap<String,Object>();
		for (String s : string) {
			String[] _s = s.split("=");
			if(_s.length == 2){
				map.put(_s[0], _s[1]);
			}
		}
		if(!ObjectUtils.isEmpty(map.get("token"))&&!ObjectUtils.isEmpty(map.get("devicenumber"))){
			String token = map.get("token").toString();
			String devicenumber = map.get("devicenumber").toString();
			Result result = roomService.open(token,devicenumber);
			if(result.isSuccess()){
				return "code=0000";						//成功
			}else{
				return "code=failed";
			}
		}else{
			return "code=failed";
		}
		
		//System.out.println(i);
		//return "code=0000";
	}

	private String readLine(HttpServletRequest request) {
		int contentLength = request.getContentLength();
		byte buffer[] = new byte[contentLength];
		try {
			for (int i = 0; i < contentLength;) {
				int readlen = request.getInputStream().read(buffer, i, contentLength - i);
				if (readlen == -1) {
					break;
				}
				i += readlen;
			}
			return new String(buffer, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public final static Map<String, Object> getParameterMap(final HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for (Enumeration<String> en = request.getParameterNames(); en.hasMoreElements();) {
			String name = en.nextElement();
			String[] values = request.getParameterValues(name);
			String value = request.getParameter(name);
			if (null != values)
				paramMap.put(name, values);
			else
				paramMap.put(name, value);
		}
		return Collections.unmodifiableMap(paramMap);
	}
	
	
	
}
