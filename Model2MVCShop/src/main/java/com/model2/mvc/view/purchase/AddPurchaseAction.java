package com.model2.mvc.view.purchase;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class AddPurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//int �ʱ�ȭ
		//HashMap<String, String> intStringOrganizer= new HashMap<String, String>();
		//intStringOrganizer.put(null, null)
		
		//���Ǹ����
		HttpSession session = request.getSession();
		
		//�������� �ޱ�
		int prodNo;
		String buyerId = "";
		String paymentOption = "";;
		String receiverName = "";
		String receiverPhone = "";
		String receiverAddr = "";
		String receiverRequest = "";
		String receiverDate = "";
		prodNo = Integer.parseInt(request.getParameter("prodNo"));
		buyerId = (String)request.getParameter("buyerId");
		paymentOption = (String)request.getParameter("paymentOption");
		receiverName = (String)request.getParameter("receiverName");
		receiverPhone = (String)request.getParameter("receiverPhone");
		receiverAddr = (String)request.getParameter("receiverAddr");
		receiverRequest = (String)request.getParameter("receiverRequest");
		receiverDate = (String)request.getParameter("receiverDate");
		//receiverDate = receiverDate.replaceAll("-", "");
		System.out.println("AddPurchaseAction�� req�κ��� ���� ������"+prodNo+buyerId+paymentOption+receiverName+receiverPhone+receiverAddr+receiverRequest+receiverDate);
		
		//�������� �ޱ�
		UserVO userVO = (UserVO)session.getAttribute("user");
		if(!buyerId.equals(userVO.getUserId())) {
			System.out.println("���!!");
			System.out.println("buyerID�� �α��ε� user�� ID�� �ٸ��ϴ�!");
			System.out.println("�������!!");
		}else {
			System.out.println("buyerID�� �α��ε� user�� ID�� �����ϴ�!");
		}
		
		//���� prodno�� db�ٳ����
		int searchTargetNumber = prodNo;
		ProductService productService=new ProductServiceImpl();
		ProductVO productVO=productService.getProduct(searchTargetNumber);
		
		//�������� PurchaseVO ����.
		PurchaseVO vo = new PurchaseVO();
		vo.setBuyer(userVO);
		vo.setDivyAddr(receiverAddr);
		vo.setDivyDate(receiverDate);
		vo.setDivyRequest(receiverRequest);
		//vo.setOrderDate(); �̰� sysdate�� �������̴�.
		vo.setPaymentOption(paymentOption);
		vo.setPurchaseProd(productVO);
		vo.setReceiverName(receiverName);
		vo.setReceiverPhone(receiverPhone);
		vo.setTranCode("1");
		//tranNo ������
		
		//execute
		PurchaseService purchaseService = new PurchaseServiceImpl();
		purchaseService.addPurchase(vo);
		
		//���ǿ� �ڱ�
		session.setAttribute("vo", vo);
		return "redirect:/purchase/afterAddPurchase.jsp";
	}

}
