package com.model2.mvc.service.purchase.impl;

import java.sql.SQLException;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.dao.UserDAO;

public class PurchaseServiceImpl implements PurchaseService {
	
	///Field
	private PurchaseDAO purchaseDAO;
	
	//Const
	public PurchaseServiceImpl(){
	  purchaseDAO = new PurchaseDAO(); 
	}
	 
	@Override
	public PurchaseVO addPurchase(PurchaseVO purchaseVO) throws Exception {
		purchaseDAO.insertPurchase(purchaseVO);
		return purchaseVO;
	}

	@Override
	public PurchaseVO getPurchase(int tranNo) throws Exception {
		return purchaseDAO.getPurchase(tranNo);
	}

	@Override
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		purchaseDAO.updateTranCode(purchaseVO);
	}

	@Override
	public HashMap getPurchaseList(SearchVO searchVO) throws Exception {
		return purchaseDAO.getPurchaseList(searchVO);
	}

}
