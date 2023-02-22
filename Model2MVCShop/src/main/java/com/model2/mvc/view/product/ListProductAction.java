package com.model2.mvc.view.product;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class ListProductAction extends Action {
	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		String menu = "search";  //디폴트값은 구매자자격인 search
		SearchVO searchVO=new SearchVO();
		if(request.getParameter("menu") != null) {
			menu=(String)(request.getParameter("menu"));
		}else {
			System.out.println(this.getClass()+" menu를 읽을 수 없어 디폴트값인 search로 설정합니다.");
		}
		System.out.println("ListProductAction 이 받은 매뉴 : "+menu);
		//초기 페이지는 1
		int page=1;
		//리퀘스트페이지가 있다면 그걸로 덮어써라.
		if(request.getParameter("page") != null)
			page=Integer.parseInt(request.getParameter("page"));
		//페이지 번호 등록
		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		String pageUnit=getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		ProductService service=new ProductServiceImpl(); 
		HashMap<String,Object> map=service.getProductList(searchVO);
		
		//purchase 쪽에서 추가되는 protrancode 확보 로직
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		PurchaseService purchaseService = new PurchaseServiceImpl();
		PurchaseVO purchaseVO = purchaseService.getPurchase(prodNo);
		String proTranCode = purchaseVO.getPurchaseProd().getProTranCode();
		//protrancode 확보 끝
		
		
		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("menu", menu);
		request.setAttribute("proTranCode", proTranCode);
		//TODO purchase DAO를 만져서 제대로 proTranCode를 심는지 확인해야한다.
		
		
		
		return "forward:/product/listProductView.jsp";
	}
}