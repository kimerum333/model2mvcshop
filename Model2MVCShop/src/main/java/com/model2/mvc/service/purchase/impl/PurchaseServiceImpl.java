package com.model2.mvc.service.purchase.impl;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;



public class PurchaseServiceImpl implements PurchaseService {
	private PurchaseDAO purchaseDAO;
	
	///Field
	public PurchaseServiceImpl() {
		if(this.purchaseDAO==null) {
			purchaseDAO=new PurchaseDAO(); 
		}
	}
	 
	@Override
	public Purchase addPurchase(Purchase purchase) throws Exception {
		purchaseDAO.insertPurchase(purchase);
		return purchase;
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return purchaseDAO.findPurchase(tranNo);
	}

	@Override
	public Map getPurchaseList(Search search, String loginUserId) throws Exception {
		return purchaseDAO.getPurchaseList(search, loginUserId);
	}

	@Override
	public Purchase updatePurchase(Purchase purchase) throws Exception {
		return purchaseDAO.updatePurchase(purchase);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		purchaseDAO.updateTranCode(purchase);		
	}
	
	

}
