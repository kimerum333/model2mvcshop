package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdateTranCodeAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		///����� service Ȯ��
		PurchaseService purchaseService = new PurchaseServiceImpl();
				
		///�޾ƿ� ���� ����
		//������Ʈ ��� purchase Ȯ��
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		PurchaseVO updatingPurchase = purchaseService.getPurchase(prodNo);
		//������Ʈ�� ���� Ȯ�� 
		String toBeTranCode = "don";	
				
		//business logic
		updatingPurchase.setTranCode(toBeTranCode);
		purchaseService.updateTranCode(updatingPurchase);
				
		return "redirect:/listPurchase.do";
	}
	
}
