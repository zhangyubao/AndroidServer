package com.zbao.server;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.zbao.server.entity.User;
import com.zbao.server.mapping.UserMapper;
import com.zbao.server.utils.MyBatisService;

public class MybatisTest {

	// private static List<Person> parseArray;

	@Test
	public void testSaveUser() {
		SqlSession session = MyBatisService.getSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			User user = new User();
			user.setUserid(0);
			user.setNickname("张玉保");
			user.setUsername("zhangyubao005");
			user.setPhone("18511287796");
			user.setPassword("zxcvqwer");
			mapper.saveUser(user);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Test
	public void testGetUserByName() {
		SqlSession session = MyBatisService.getSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			User user = mapper.getuserByName("zhangyubao");
			System.out.println(user.toString());
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void main(String[] args) {
		// String json = "[{'aGe':20,'age':8,'sex':'male'}]";
		// parseArray = JSON.parseArray(json, Person.class);
		// System.out.println(parseArray);
	}
}
