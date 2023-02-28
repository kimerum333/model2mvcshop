package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdatePurchaseViewAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//tranNo�� �޾Ƽ� purchase�� �ذ��մϴ�.
		int tranNO = Integer.parseInt(request.getParameter("tranNo"));
		PurchaseService perchaseService = new PurchaseServiceImpl();
		PurchaseVO purchase = perchaseService.getPurchase(tranNO);
		System.out.println(" UpdatePurchaseViewAction : �����ؾ��� �������� tranNo ��???" +tranNO);
		
		request.setAttribute("purchase", purchase);
		
		return "forward:/purchase/updatePurchaseView.jsp";
	}

}
