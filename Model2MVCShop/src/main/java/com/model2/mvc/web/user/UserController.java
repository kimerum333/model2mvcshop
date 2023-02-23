package com.model2.mvc.web.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;

//==> 회원관리 Controller
@Controller
@RequestMapping("/user/*")
public class UserController {

	/// Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	// setter Method 구현 않음
	// 뭐지? setterinjection 없이 어떻게 돌아가냐
	public UserController() {
		System.out.println(this.getClass() + " instance On");
	}

	// ==> classpath:config/common.properties , classpath:config/commonservice.xml
	// 참조 할것
	// ==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	// src/main/resources 의 config/context-common에 <util:properties
	// id="commonProperties" location="classpath:config/common.properties"/> 를 선언했기에
	// 사용할 수 있는 Spring EL이다
	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;

	//여기에서는 리퀘스트매핑에 어트리뷰트를 줘서 겟/포스트를 받아보겠다.
	@RequestMapping(value="addUser", method=RequestMethod.GET)
	public String addUser() throws Exception {

		System.out.println("/user/addUser : GET");

		return "redirect:/user/addUserView.jsp";
	}


	@RequestMapping(value="addUser", method=RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user) throws Exception {

		System.out.println("/user/addUser : POST");
		// Business Logic
		userService.addUser(user);

		return "redirect:/user/loginView.jsp";
	}

	@RequestMapping(value="getUser", method=RequestMethod.GET)
	public String getUser(@RequestParam("userId") String userId, Model model) throws Exception {

		System.out.println("/user/getUser : GET");
		// Business Logic
		User user = userService.getUser(userId);
		// Model 과 View 연결
		model.addAttribute("user", user);

		return "forward:/user/getUser.jsp";
	}

	@RequestMapping(value="updateUser",method=RequestMethod.GET)
	public String updateUser(@RequestParam("userId") String userId, Model model) throws Exception {

		System.out.println("/user/updateUser : GET");
		// Business Logic
		User user = userService.getUser(userId);
		// Model 과 View 연결
		model.addAttribute("user", user);

		return "forward:/user/updateUser.jsp";
	}

	@RequestMapping(value="updateUser", method=RequestMethod.POST)
	@PostMapping
	public String updateUser(@ModelAttribute("user") User user, Model model, HttpSession session) throws Exception {

		System.out.println("/updateUser.do");
		// Business Logic
		userService.updateUser(user);

		String sessionId = ((User) session.getAttribute("user")).getUserId();
		if (sessionId.equals(user.getUserId())) {
			session.setAttribute("user", user);
		}

		return "redirect:/user/getUser?userId=" + user.getUserId();
	}

	@RequestMapping(value="login",method=RequestMethod.GET)
	public String loginView() throws Exception {

		System.out.println("user/logon : GET");

		return "redirect:/user/loginView.jsp";
	}

	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(@ModelAttribute("user") User user, HttpSession session) throws Exception {

		System.out.println("/user/login : POST");
		// Business Logic
		User dbUser = userService.getUser(user.getUserId());
		// @modelAttribute를 통해 loginView에서 전송한 form의 name인 userId와 password를 User
		// domain에 바인딩하여 얻어진 객체 user로부터 userId를 얻어내서 DB로부터 get한 dbUser와 비교한다.

		if (user.getPassword().equals(dbUser.getPassword())) {
			session.setAttribute("user", dbUser);
		}

		return "redirect:/index.jsp";
	}

	@RequestMapping(value= "logout")
	public String logout(HttpSession session) throws Exception {

		System.out.println("user / logout ");

		session.invalidate();

		return "redirect:/index.jsp";
	}

	@RequestMapping(value="checkDuplication")
	public String checkDuplication(@RequestParam("userId") String userId, Model model) throws Exception {

		System.out.println("/user/checkDuplication ");
		// Business Logic
		boolean result = userService.checkDuplication(userId);
		// Model 과 View 연결
		model.addAttribute("result", new Boolean(result));
		model.addAttribute("userId", userId);

		return "forward:/user/checkDuplication.jsp";
	}

	@RequestMapping("listUser")
	public String listUser(@ModelAttribute("search") Search search, Model model, HttpServletRequest request)
			throws Exception {

		System.out.println("listUser");

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		// Business logic 수행
		Map<String, Object> map = userService.getUserList(search);

		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);

		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		return "forward:/user/listUser.jsp";
	}
}