package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
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
		//생각해보니까 나중에 만들어도 될 것 같습니다. 한 사람이 구매한 목록을 전부 합쳐봐야 한 페이지에 다 나올테니
		/*
		 * SearchVO searchVO = new SearchVO();
		 * if(request.getAttribute("searchVO")!=null) { searchVO =
		 * (SearchVO)request.getAttribute("searchVO"); }
		 */
		
		//비즈니스로직을 수행할 서비스를 만듭니다.
		PurchaseService purchaseService = new PurchaseServiceImpl();
		//TODO 서비스에 겟 퍼체이스리스트를 선언합니다.
		purchaseService.getPurchaseList()
		
		
		//TODO 퍼체이스리스트를 마무리하면됩니다.
		return null;
	}

}
