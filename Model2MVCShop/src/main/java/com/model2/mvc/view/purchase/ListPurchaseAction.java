package com.model2.mvc.view.purchase;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
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
		
		SearchVO searchVO = new SearchVO();
		
		//�������� Ȯ���ϴ� ����
		//����Ʈ �������� 1
		int page=1;
		//������Ʈ�������� �ִٸ� �װɷ� ������.
		if(request.getParameter("page") != null)
		page=Integer.parseInt(request.getParameter("page"));
		//������ ��ȣ ���
		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));

		//�̴��Ķ����� ������ ����� ��
		String pageUnit=getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
			
		//����Ͻ������� ������ ���񽺸� ����ϴ�.
		PurchaseService purchaseService = new PurchaseServiceImpl();
		searchVO.setSearchKeyword(userId); //������ �α������� ������ ���̵�� �˻��� �̴ϴ�.
		HashMap<String, Object> map = purchaseService.getPurchaseList(searchVO);
		//���� ����� ���� �˻������ ����ִ� ���� ȭ������� �Ѱ��ݴϴ�.
		request.setAttribute("map", map);
		
		return "forward:/purchase/listPurchase.jsp";
	}

}
