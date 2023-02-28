package com.model2.mvc.view.purchase;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
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
		
		SearchVO searchVO = new SearchVO();
		
		//페이지를 확보하는 로직
		//디폴트 페이지는 1
		int page=1;
		//리퀘스트페이지가 있다면 그걸로 덮어써라.
		if(request.getParameter("page") != null)
		page=Integer.parseInt(request.getParameter("page"));
		//페이지 번호 등록
		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));

		//이닛파람으로 페이지 사이즈를 겟
		String pageUnit=getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
			
		//비즈니스로직을 수행할 서비스를 만듭니다.
		PurchaseService purchaseService = new PurchaseServiceImpl();
		searchVO.setSearchKeyword(userId); //언제나 로그인중인 유저의 아이디로 검색할 겁니다.
		HashMap<String, Object> map = purchaseService.getPurchaseList(searchVO);
		//수행 결과로 받은 검색결과가 들어있는 맵을 화면단으로 넘겨줍니다.
		request.setAttribute("map", map);
		
		return "forward:/purchase/listPurchase.jsp";
	}

}
