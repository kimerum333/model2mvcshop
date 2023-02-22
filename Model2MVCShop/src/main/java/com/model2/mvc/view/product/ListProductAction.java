package com.model2.mvc.view.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class ListProductAction extends Action {
	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		String menu = "search";  //����Ʈ���� �������ڰ��� search
		SearchVO searchVO=new SearchVO();
		if(request.getParameter("menu") != null) {
			menu=(String)(request.getParameter("menu"));
		}else {
			System.out.println(this.getClass()+" menu�� ���� �� ���� ����Ʈ���� search�� �����մϴ�.");
		}
		System.out.println("ListProductAction �� ���� �Ŵ� : "+menu);
		//�ʱ� �������� 1
		int page=1;
		//������Ʈ�������� �ִٸ� �װɷ� ������.
		if(request.getParameter("page") != null)
			page=Integer.parseInt(request.getParameter("page"));
		//������ ��ȣ ���
		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		String pageUnit=getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		ProductService service=new ProductServiceImpl(); 
		HashMap<String,Object> map=service.getProductList(searchVO);		
		
		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("menu", menu);

		
		
		
		return "forward:/product/listProductView.jsp";
	}
}