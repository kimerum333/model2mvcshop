package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class UpdatePurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//0. request 로부터 온 정보 모조리 받아두기
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		String buyerId = request.getParameter("buyerId");
		String paymentOption = request.getParameter("paymentOption");
		String receiverName = request.getParameter("receiverName");
		String receiverPhone = request.getParameter("receiverPhone");
		String receiverAddr = request.getParameter("receiverAddr");
		String receiverRequest = request.getParameter("receiverRequest");
		String divyDate = request.getParameter("divyDate");
		
		//그걸 purchase 에 세팅
		UserVO user = new UserVO();
		user.setUserId(buyerId);
		PurchaseVO purchase = new PurchaseVO();
		purchase.setTranNo(tranNo);
		purchase.setBuyer(user);
		purchase.setPaymentOption(paymentOption);
		purchase.setReceiverName(receiverName);
		purchase.setReceiverPhone(receiverPhone);
		purchase.setDivyAddr(receiverAddr);
		purchase.setDivyRequest(receiverRequest);
		purchase.setDivyDate(divyDate);
		
		//business
		PurchaseService purchaseService = new PurchaseServiceImpl();
		//purchaseService.updatePurchase(purchase);
		//TODO
		
		return  "redirect:/getPurchase.do?tranNo="+tranNo;
	}

}
