//package com.model2.mvc.view.purchase;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.model2.mvc.framework.Action;
//import com.model2.mvc.service.domain.Purchase;
//import com.model2.mvc.service.purchase.PurchaseService;
//import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
//
//public class UpdateTranCodeAction extends Action {
//
//	@Override
//	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
//		
//		String tranCode = request.getParameter("code");
//		//if map
//		System.out.println(" UpdateTranCodeAction 가 받은 코드"+tranCode);
//		if(tranCode.equals("sld")) {
//			tranCode = "del";
//		}
//		if(tranCode.equals("del")) {
//			tranCode = "done";
//		}
//		Purchase purchase = new Purchase();
//		purchase.setTranCode(tranCode);
//		PurchaseService purchaseService = new PurchaseServiceImpl();
//		purchaseService.updateTranCode(purchase);
//		
//		
//		return "forward:/listProduct.do";
//	}
//
//}
