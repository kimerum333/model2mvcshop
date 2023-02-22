package com.model2.mvc.service.product.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;

public class ProductServiceImpl implements ProductService {
	//has a관계로 DAO를 갖고 데이터베이서를 왔다갔다한다.
	private ProductDAO productDAO;
	
	//DAO 들고오는 생성자
	public ProductServiceImpl() {
		productDAO=new ProductDAO();
	}

	@Override
	public ProductVO addProduct(ProductVO productVO) throws Exception {
		productDAO.insertProduct(productVO);
		return productVO;
	}

	@Override
	public ProductVO getProduct(int prodNo) throws Exception {
		return productDAO.findProduct(prodNo);
	}

	@Override
	public HashMap<String, Object> getProductList(SearchVO searchVO) throws Exception {
		return productDAO.getProductList(searchVO);
	}

	@Override
	public ProductVO updateProduct(ProductVO productVO) throws Exception {
		productDAO.updateProduct(productVO);
		return productVO;
	}
}
