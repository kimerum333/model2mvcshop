package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdateTranCodeByProdAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		///사용할 service 확보
		PurchaseService purchaseService = new PurchaseServiceImpl();
		
		///받아온 정보 나열
		//업데이트 대상 purchase 확보
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		PurchaseVO updatingPurchase = purchaseService.getPurchase(prodNo);
		//업데이트할 문구 확보 
		String nowIsTranCode = request.getParameter("proTranCode");		
		String toBeTranCode = "";
		if(nowIsTranCode.equals("sld")) {
			toBeTranCode = "del";
		}else if(nowIsTranCode.equals("del")) {
			toBeTranCode = "don";
		}
		//list 를 유지하기 위한 menu확보
		String menu = request.getParameter("menu");
		
		//business logic
		updatingPurchase.setTranCode(toBeTranCode);
		purchaseService.updateTranCode(updatingPurchase);
		
		return "redirect:/listProduct.do?menu="+menu;
	}

}
