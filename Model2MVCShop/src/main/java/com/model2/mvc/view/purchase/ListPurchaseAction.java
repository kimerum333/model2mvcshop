//package com.model2.mvc.view.purchase;
//
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.model2.mvc.common.Search;
//import com.model2.mvc.framework.Action;
//import com.model2.mvc.service.domain.User;
//import com.model2.mvc.service.purchase.PurchaseService;
//import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
//
//public class ListPurchaseAction extends Action {
//
//	@Override
//	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		//get user Id
//		HttpSession session = request.getSession();
//		User loginUser = (User) session.getAttribute("user");
//		String userId = loginUser.getUserId();
//		
//		//get search if it is available & set it
//		Search search = new Search();
//		if(request.getParameter("searchKeyword")!=null) {
//			search.setSearchKeyword(request.getParameter("searchKeyword"));
//		}
//		if(request.getParameter("currentPage")!=null) {
//			search.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
//		}
//		if(request.getParameter("searchCondition")!=null) {
//			search.setSearchCondition(request.getParameter("searchCondition"));
//		}
//		if(request.getParameter("searchKeyword")!=null) {
//			search.setSearchKeyword(request.getParameter("searchKeyword"));
//		}
//		
//		// web.xml  meta-data 로 부터 상수 추출 
//		int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
//		int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
//		search.setPageSize(pageSize); //서치 세팅 완료
//		
//		//business logic
//		PurchaseService purchaseService = new PurchaseServiceImpl();
//		Map returningMap = purchaseService.getPurchaseList(search, userId);
//		
//		//View 에 연결
//		request.setAttribute("map", returningMap);
//		
//		
//		return "forward:/purchase/listPurchase.jsp";
//	}
//
//}
