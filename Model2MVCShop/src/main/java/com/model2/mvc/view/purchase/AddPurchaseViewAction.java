//package com.model2.mvc.view.purchase;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.model2.mvc.framework.Action;
//import com.model2.mvc.service.domain.Product;
//import com.model2.mvc.service.domain.User;
//import com.model2.mvc.service.product.ProductService;
//import com.model2.mvc.service.product.impl.ProductServiceImpl;
//
//public class AddPurchaseViewAction extends Action {
//
//	@Override
//	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		int searchTargetNumber = 0;
//		if(request.getParameter("prod_no")!=null) {
//			searchTargetNumber = Integer.parseInt(request.getParameter("prod_no"));	
//		}
//		System.out.println("AddPurchaseViewAction 이 감지한 prod_no = "+searchTargetNumber);
//		ProductService service=new ProductServiceImpl();
//		//Product vo=service.getProduct(searchTargetNumber);
//		//System.out.println("AddPurchaseViewAction 이 받아온 prodVO = " + vo);
//		
//		//request.setAttribute("prodVO", vo);
//		
//		return "forward:/purchase/addPurchaseView.jsp";
//	}
//
//}
