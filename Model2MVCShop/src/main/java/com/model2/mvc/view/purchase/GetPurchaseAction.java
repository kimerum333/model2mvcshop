//package com.model2.mvc.view.purchase;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.model2.mvc.framework.Action;
//import com.model2.mvc.service.purchase.PurchaseService;
//import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
//
//public class GetPurchaseAction extends Action {
//
//	@Override
//	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		//view�κ��� ���� ȹ��
//		HttpSession session = request.getSession();
//		Integer tranNo = Integer.parseInt(request.getParameter("tranNo"));
//		
//		//business logic
//		PurchaseService purchaseService = new PurchaseServiceImpl();
//		
//		//request�� view �� ����
//		request.setAttribute("purchase", purchaseService.getPurchase(tranNo));
//		
//		return "forward:/purchase/getPurchaseView.jsp";
//	}
//
//}
