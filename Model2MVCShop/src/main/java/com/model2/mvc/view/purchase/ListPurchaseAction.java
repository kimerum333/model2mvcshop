package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class ListPurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("user")==null) {
			System.out.println(this.getClass()+" : �α��ε��� ���� ������� �����Դϴ�.");
			return "redirect:/login.do";
		}
		//�˻��������� ���� user�� ID�� Ȯ���մϴ�.
		String userId = ((UserVO)session.getAttribute("user")).getUserId();
		
		//�˻������� Search�� Ȯ���մϴ�.
		//�����غ��ϱ� ���߿� ���� �� �� �����ϴ�. �� ����� ������ ����� ���� ���ĺ��� �� �������� �� �����״�
		/*
		 * SearchVO searchVO = new SearchVO();
		 * if(request.getAttribute("searchVO")!=null) { searchVO =
		 * (SearchVO)request.getAttribute("searchVO"); }
		 */
		
		//����Ͻ������� ������ ���񽺸� ����ϴ�.
		PurchaseService purchaseService = new PurchaseServiceImpl();
		//TODO ���񽺿� �� ��ü�̽�����Ʈ�� �����մϴ�.
		purchaseService.getPurchaseList()
		
		
		//TODO ��ü�̽�����Ʈ�� �������ϸ�˴ϴ�.
		return null;
	}

}
