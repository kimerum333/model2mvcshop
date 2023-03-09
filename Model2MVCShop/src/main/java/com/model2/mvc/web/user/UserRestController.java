package com.model2.mvc.web.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;


//==> ȸ������ RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	//setter Method ���� ����
	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
		
	public UserRestController(){
		System.out.println(this.getClass()+" instance on");
	}
	
	
	  @RequestMapping( value="json/getUser/{userId}", method=RequestMethod.GET )
	  public User getUser( @PathVariable String userId ) throws Exception{
	  
	  System.out.println("/user/json/getUser : GET");
	  
	  //Business Logic
	  return userService.getUser(userId);
	  }
	 
	
	/*
	 * @RequestMapping( value="json/getUser/{userId}", method=RequestMethod.GET )
	 * public Map getUser( @PathVariable String userId ) throws Exception{
	 * 
	 * System.out.println("/user/json/getUser : GET");
	 * 
	 * //Business Logic
	 * 
	 * User dbUser = userService.getUser(userId); System.out.println("dbUser is" +
	 * dbUser); Map map = new HashMap(); map.put("user",dbUser);
	 * 
	 * return map; }
	 */
	

	@RequestMapping( value="json/login", method=RequestMethod.POST )
	public User login(		@RequestBody User user,
										HttpSession session ) throws Exception{
	
		System.out.println("/user/json/login : POST");
		//Business Logic
		System.out.println("::"+user);
		User dbUser=userService.getUser(user.getUserId());
		
		if( user.getPassword().equals(dbUser.getPassword())){
			session.setAttribute("user", dbUser);
		}
		
		return dbUser;
	}
	
	@RequestMapping(value="/json/listUserAutocomplete/{searchConditionSearchKeyword}",method=RequestMethod.GET)
	public Map listUserAutocomplete(@PathVariable String searchConditionSearchKeyword) throws Exception {
		String searchCondition = searchConditionSearchKeyword.substring(0,1);
		String searchKeyword = searchConditionSearchKeyword.substring(1);
		
		Search search = new Search();
		search.setSearchCondition(searchCondition);
		search.setSearchKeyword(searchKeyword);
		search.setCurrentPage(1);
		search.setPageSize(pageSize);
		Map map = userService.getUserList(search);
		map.put("searchCondition", searchCondition);
		
		return map;
	}
	
}