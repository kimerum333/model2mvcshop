package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.vo.UserVO;

public class AddPurchaseViewAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int searchTargetNumber = 0;
		if(request.getParameter("prod_no")!=null) {
			searchTargetNumber = Integer.parseInt(request.getParameter("prod_no"));	
		}
		System.out.println("AddPurchaseViewAction �� ������ prod_no = "+searchTargetNumber);
		ProductService service=new ProductServiceImpl();
		ProductVO vo=service.getProduct(searchTargetNumber);
		System.out.println("AddPurchaseViewAction �� �޾ƿ� prodVO = " + vo);
		
		//HttpSession session = request.getSession(true);
		//UserVO loginUser = (UserVO)session.getAttribute("user");
		//�����غ��ϱ� ���ǿ� ������ ���⼭ ������ �ʿ䰡 ���µ�?
		
		request.setAttribute("prodVO", vo);
		
		return "forward:/purchase/addPurchaseView.jsp";
	}

}
