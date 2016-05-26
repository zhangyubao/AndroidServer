package com.zbao.server.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zbao.server.config.ResponseConstant;
import com.zbao.server.entity.ResponseResult;
import com.zbao.server.entity.User;
import com.zbao.server.service.UserService;
import com.zbao.server.utils.JsonUtil;
import com.zbao.server.utils.Md5Util;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 注册接口
	 * 
	 * @param username
	 * @param phone
	 * @param nickname
	 * @param password
	 * @return
	 */
	@RequestMapping("/register.json")
	public @ResponseBody ResponseResult regist(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "nickname", required = true) String nickname,
			@RequestParam(value = "phone", required = true) String phone) {
		ResponseResult result = new ResponseResult();
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(phone)
				|| StringUtils.isEmpty(password)) {
			result.setCode(ResponseConstant.PARAMS_ERROR);
			result.setMessage(ResponseConstant.PARAMS_MSG);
		}
		User user = userService.getUserByName(username);
		if (user != null) {
			result.setCode(ResponseConstant.USERNAME_ERROR);
			result.setMessage("用户名重复注册");
		} else {
			int index = userService.saveUser(username, password, nickname,
					phone);
			if (index > 0) {
				result.setCode(ResponseConstant.RESULT_SUCCESS);
				result.setMessage(ResponseConstant.SUCCESS_MSG);
			}
		}
		return result;
		// return JsonUtil.getJson(result);
	}

	/**
	 * 登录接口
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login.json", method = RequestMethod.GET)
	public @ResponseBody ResponseResult login(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {
		ResponseResult result = new ResponseResult();
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)
				|| StringUtils.isEmpty(password)) {
			result.setCode(ResponseConstant.PARAMS_ERROR);
			result.setMessage(ResponseConstant.PARAMS_MSG);
		}
		User user = userService.getUserByName(username);
		if (user != null) {
			if (user.getPassword().equals(Md5Util.md5Encryption(password))) {
				result.setCode(ResponseConstant.RESULT_SUCCESS);
				result.setMessage(ResponseConstant.SUCCESS_MSG);
			} else {
				result.setMessage("用户名或密码错误");
			}
		} else {
			result.setCode(ResponseConstant.NO_REGIST_ERROR);
			result.setMessage("没有此用户信息");
		}
		return result;
		// return JsonUtil.getJson(result);
	}

}
