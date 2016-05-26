package com.zbao.server.service;

import org.apache.ibatis.session.SqlSession;

import com.zbao.server.entity.User;
import com.zbao.server.mapping.UserMapper;
import com.zbao.server.utils.Md5Util;
import com.zbao.server.utils.MyBatisService;

public class UserService {

	/**
	 * 保存用户信息
	 * 
	 * @param username
	 * @param password
	 * @param phone
	 * @param nickname
	 * 
	 * @return 受影响的行数
	 */
	public int saveUser(String username, String password, String nickname,
			String phone) {
		SqlSession session = MyBatisService.getSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			User user = new User();
			user.setNickname(nickname);
			user.setPhone(phone);
			user.setUsername(username);
			user.setPassword(Md5Util.md5Encryption(password));
			int index = mapper.saveUser(user);
			session.commit();
			return index;
		} finally {
			session.commit();
		}
	}

	/**
	 * 更新用户信息
	 * 
	 * @param username
	 * @param password
	 * @param phone
	 * @param nickname
	 * @return
	 */
	public int UpdateUser(String username, String password, String phone,
			String nickname) {
		SqlSession session = MyBatisService.getSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			User user = new User();
			user.setNickname(nickname);
			user.setPhone(phone);
			user.setUsername(username);
			// 密码加密
			user.setPassword(Md5Util.md5Encryption(password));
			return mapper.saveUser(user);
		} finally {
			session.close();
		}

	}

	/**
	 * 根据用户名查询用户
	 * 
	 * @param username
	 * @return
	 */
	public User getUserByName(String username) {
		SqlSession session = MyBatisService.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = mapper.getuserByName(username);
		return user;

	}
}
