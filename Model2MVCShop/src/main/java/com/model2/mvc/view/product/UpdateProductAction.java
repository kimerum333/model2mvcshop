package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;


public class UpdateProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {

		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		
		ProductVO productVO=new ProductVO();
		productVO.setProdNo(prodNo);
		
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setManuDate(request.getParameter("manuDate"));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));
		
		ProductService service=new ProductServiceImpl();
		service.updateProduct(productVO);
		
		/*
		HttpSession session=request.getSession(true);
		session.setAttribute("productVO", product);//겟으로보내나?
		sessionId=((UserVO)session.getAttribute("user")).getUserId();
		if(sessionId.equals(userId)){
			session.setAttribute("user", userVO);
		}*/
		
		return "redirect:/getProduct.do?prodNo="+prodNo+"&menu=manage";
	}
}