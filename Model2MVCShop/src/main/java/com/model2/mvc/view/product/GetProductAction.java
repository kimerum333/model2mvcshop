package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class GetProductAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		int searchTargetNumber = 0;
		String menu = null;
		
		
		//리퀘스트로부터 상품정보 받아보기
		if(request.getParameter("prodNo")!=null) {
			searchTargetNumber = Integer.parseInt(request.getParameter("prodNo"));	
		}
		//리퀘스트로부터 구매페이지인지 수정완료페이지인지 확인하기
		if(request.getParameter("menu")!=null) {
			menu = request.getParameter("menu");	
		}
		
		
		ProductService service=new ProductServiceImpl();
		ProductVO vo=service.getProduct(searchTargetNumber);
		System.out.println("GetProductAction vo = " + vo);
		request.setAttribute("vo", vo);
		request.setAttribute("menu", menu);

		return "forward:/product/getProductView.jsp";
	}
}