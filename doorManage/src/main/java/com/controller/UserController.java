package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.entity.User;
import com.freamwork.Result;
import com.freamwork.ResultSupport;
import com.google.gson.Gson;
import com.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final String APPID = "wxce6eec5dd7a6c097";
	private static final String SECRET = "cc5173d0b6ebedae4a936a50e9a441e7";
	int i = 10;
	@Autowired
	private UserService userService;

	/**
	 * 列表
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public Result list(@RequestParam Map<String, Object> params, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("pageNum") Integer pageNum) {
		Result result = userService.queryPage(params, pageSize, pageNum);
		return result;
	}

	/**
	 * 登录
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Result login(@RequestParam("account") String account, @RequestParam("password") String password) {
		Result result = userService.login(account, password);
		return result;
	}

	@RequestMapping("/regist")
	@ResponseBody
	public Result regist(@RequestBody User user) {
		Result result = userService.regist(user);
		return result;
	}

	@RequestMapping("/{location}")
	public String location(@PathVariable String location) {
		return location;
	}

	@RequestMapping("/save")
	@ResponseBody
	public Result save(@RequestParam("account") String account, @RequestParam("username") String username,
			@RequestParam("wechat_id") String wechat_id) {
		User user = new User();
		user.setAccount(account);
		user.setUsername(username);
		return userService.save(user);
	}

	@RequestMapping("/queryByWechatId")
	@ResponseBody
	public Result queryByWechatId(@RequestParam("wechatId") String wechatId) {
		return userService.queryByWechatId(wechatId);
	}

	// 获取凭证校检接口
	@RequestMapping("/wxlogin")
	@ResponseBody
	public Result wxlogin(@RequestParam("code") String code) {
		Result result = new ResultSupport();
		// 微信的接口
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" + SECRET + "&js_code="
				+ code + "&grant_type=authorization_code";
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

		if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
			String sessionData = responseEntity.getBody();
			System.out.println(sessionData);
			Gson gson = new Gson();

			HashMap<String, String> map = gson.fromJson(sessionData, HashMap.class);

			String openid = map.get("openid");

			String session_key = map.get("session_key");

			result = userService.queryByWechatId(openid);
			result.setModel("openid", openid);
			result.setModel("session_key", session_key);
		} else {
			result.setError("400", "访问微信授权接口异常");
		}
		return result;
	}
	/*
	 * @RequestMapping("/getcode")
	 * 
	 * @ResponseBody public String getcode() { i++; String code =
	 * "vgdecoderesult=1&&token=123&&time=100000000000&&devicenumber=001&&otherparams="
	 * + i; return code; }
	 */
}
