package com.model2.mvc.service.user.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.dao.UserDAO;
import com.model2.mvc.service.user.vo.UserVO;


public class UserServiceImpl implements UserService{ //UserService를 구체화중.
	
	//has a관계로 DAO를 갖고 데이터베이서를 왔다갔다한다.
	private UserDAO userDAO;
	
	public UserServiceImpl() {
		userDAO=new UserDAO();
	}

	public void addUser(UserVO userVO) throws Exception {
		userDAO.insertUser(userVO);
	}

	public UserVO loginUser(UserVO userVO) throws Exception {
			UserVO dbUser=userDAO.findUser(userVO.getUserId());

			if(! dbUser.getPassword().equals(userVO.getPassword()))
				throw new Exception("로그인에 실패했습니다.");
			
			return dbUser;
	}

	//ID만 받아서 바로 유저정보를 준다...?
	public UserVO getUser(String userId) throws Exception {
		return userDAO.findUser(userId);
	}

	//유저정보를 해쉬맵으로 준다.
	public HashMap<String,Object> getUserList(SearchVO searchVO) throws Exception {
		return userDAO.getUserList(searchVO);
	}

	public void updateUser(UserVO userVO) throws Exception {
		userDAO.updateUser(userVO);
	}

	public boolean checkDuplication(String userId) throws Exception {
		boolean result=true;
		UserVO userVO=userDAO.findUser(userId);
		if(userVO != null) {
			result=false;
		}
		return result;
	}
}