//package com.model2.mvc.view.purchase;
//
//import java.util.HashMap;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.model2.mvc.framework.Action;
//import com.model2.mvc.service.domain.Product;
//import com.model2.mvc.service.domain.Purchase;
//import com.model2.mvc.service.domain.User;
//import com.model2.mvc.service.product.ProductService;
//import com.model2.mvc.service.product.impl.ProductServiceImpl;
//import com.model2.mvc.service.purchase.PurchaseService;
//import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
//
//public class AddPurchaseAction extends Action {
//
//	@Override
//	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
//		//세션만들기
//		HttpSession session = request.getSession();
//		
//		//구매정보 받기
//		int prodNo;
//		String buyerId = "";
//		String paymentOption = "";;
//		String receiverName = "";
//		String receiverPhone = "";
//		String receiverAddr = "";
//		String receiverRequest = "";
//		String receiverDate = "";
//		prodNo = Integer.parseInt(request.getParameter("prodNo"));
//		buyerId = (String)request.getParameter("buyerId");
//		paymentOption = (String)request.getParameter("paymentOption");
//		receiverName = (String)request.getParameter("receiverName");
//		receiverPhone = (String)request.getParameter("receiverPhone");
//		receiverAddr = (String)request.getParameter("receiverAddr");
//		receiverRequest = (String)request.getParameter("receiverRequest");
//		receiverDate = (String)request.getParameter("receiverDate");
//		//receiverDate = receiverDate.replaceAll("-", "");
//		System.out.println("AddPurchaseAction이 req로부터 받은 정보들"+prodNo+buyerId+paymentOption+receiverName+receiverPhone+receiverAddr+receiverRequest+receiverDate);
//		
//		//유저정보 받기
//		User userVO = (User)session.getAttribute("user");
//		if(!buyerId.equals(userVO.getUserId())) {
//			System.out.println("경고!!");
//			System.out.println("buyerID와 로그인된 user의 ID가 다릅니다!");
//			System.out.println("경고종료!!");
//		}else {
//			System.out.println("buyerID와 로그인된 user의 ID가 같습니다!");
//		}
//		
//		//받은 prodno로 db다녀오기
//		int searchTargetNumber = prodNo;
//		ProductService productService=new ProductServiceImpl();
//		//Product productVO = productService.getProduct(searchTargetNumber);
//		//System.out.println("구매하려는 상품정보 : "+productVO);
//		
//		//최종적인 PurchaseVO 세팅.
//		Purchase vo = new Purchase();
//		vo.setBuyer(userVO);
//		vo.setDivyAddr(receiverAddr);
//		vo.setDivyDate(receiverDate);
//		vo.setDivyRequest(receiverRequest);
//		//vo.setOrderDate(); 이건 sysdate로 넣을것이다.
//		vo.setPaymentOption(paymentOption);
//		//vo.setPurchaseProd(productVO);
//		vo.setReceiverName(receiverName);
//		vo.setReceiverPhone(receiverPhone);
//		vo.setTranCode("sld");
//		//tranNo는 시퀀스로 입력
//		
//
//		
//		//execute
//		PurchaseService purchaseService = new PurchaseServiceImpl();
//		purchaseService.addPurchase(vo);
//		
//		//세션에 박기
//		session.setAttribute("recentPurchase", vo);
//		return "redirect:/purchase/afterAddPurchase.jsp";
//	}
//
//}
