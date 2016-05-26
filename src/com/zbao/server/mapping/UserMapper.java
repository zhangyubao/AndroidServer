package com.zbao.server.mapping;

import com.zbao.server.entity.User;

public interface UserMapper {

	/**
	 * 保存用户
	 * 
	 * @param user
	 */
	int saveUser(User user);

	/**
	 * 更新用户
	 * 
	 * @param user
	 */
	int updateUser(User user);

	/**
	 * 通过用户名查询用户
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	User getuserByName(String username);
}
