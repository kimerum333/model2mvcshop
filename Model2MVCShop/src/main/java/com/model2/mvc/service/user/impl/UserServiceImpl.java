package com.model2.mvc.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserDao;
import com.model2.mvc.service.user.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService{ //UserService를 구체화중.
	///Field
	//Associated with Dao
	@Autowired
	@Qualifier("userDaoImpl")	
	private UserDao userDao;
	//setter injection
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	///Constructor
	public UserServiceImpl() {
		/* userDAO=new UserDAO(); */
		System.out.println(this.getClass()+"instance on");
	}

	
	
	//Method
	public void addUser(User user) throws Exception {
		userDao.addUser(user);
	}

	/*
	 * public User loginUser(User user) throws Exception { User
	 * dbUser=userDao.getUser(user.getUserId());
	 * 
	 * if(! dbUser.getPassword().equals(user.getPassword())) throw new
	 * Exception("로그인에 실패했습니다.");
	 * 
	 * return dbUser; }
	 */

	//ID에 해당하는 유저 정보를 획득한다.
	public User getUser(String userId) throws Exception {
		return userDao.getUser(userId);
	}
 
	public Map<String , Object > getUserList(Search search) throws Exception {
		List<User> list= userDao.getUserList(search);
		int totalCount = userDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	public void updateUser(User user) throws Exception {
		userDao.updateUser(user);
	}

	public boolean checkDuplication(String userId) throws Exception {
		boolean result=true;
		User user=userDao.getUser(userId);
		if(user != null) {
			result=false;
		}
		return result;
	}
}