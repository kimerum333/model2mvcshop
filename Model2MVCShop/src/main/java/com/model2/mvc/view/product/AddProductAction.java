package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;



public class AddProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {

		ProductVO productVO=new ProductVO();
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		String manuDate = request.getParameter("manuDate");
		productVO.setManuDate(manuDate.replaceAll("-", ""));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		
		System.out.println(productVO);
		
		ProductService service=new ProductServiceImpl();
		
		//세션에 박기
		HttpSession session = request.getSession(true);
		session.setAttribute("vo", service.addProduct(productVO)); //에드했음
		return "redirect:/product/afterAddProductView.jsp";
	}
}