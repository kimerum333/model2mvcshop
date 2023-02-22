package com.model2.mvc.service.purchase;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDao {

	// INSERT
	public Purchase addPurchase(Purchase purchase) throws Exception;

	// SELECT ONE
	//TODO
	public Product getProduct(int prodNo) throws Exception ;
	  
	// SELECT LIST
	//TODO
	public List<Product> getProductList(Search search) throws Exception;
	  
	  
	  // UPDATE
	//TODO
	public void updateProduct(Product product) throws Exception ;
	  
	  // �Խ��� Page ó���� ���� ��üRow(totalCount) return
	//TODO
	public int getTotalCount(Search search) throws Exception ;
	 
}
