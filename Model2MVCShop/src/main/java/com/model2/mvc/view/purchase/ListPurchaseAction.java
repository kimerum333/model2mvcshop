package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.vo.UserVO;

public class ListPurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("user")==null) {
			System.out.println(this.getClass()+" : 로그인되지 않은 사용자의 접근입니다.");
			return "redirect:/login.do";
		}
		//검색조건으로 삼을 user의 ID를 확보합니다.
		String userId = ((UserVO)session.getAttribute("user")).getUserId();
		
		//검색조건인 Search를 확보합니다.
		SearchVO searchVO = new SearchVO();
		if(request.getAttribute("searchVO")!=null) {
			searchVO = (SearchVO)request.getAttribute("searchVO");
		}
		//TODO 퍼체이스리스트를 마무리하면됩니다.
		return null;
	}

}
