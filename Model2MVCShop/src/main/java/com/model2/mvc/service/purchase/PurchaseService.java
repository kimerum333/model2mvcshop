package com.model2.mvc.service.purchase;

import java.util.HashMap;

import com.model2.mvc.service.purchase.vo.PurchaseVO;

public interface PurchaseService {
	//purchase ¼º¸³½Ã
	public PurchaseVO addPurchase(PurchaseVO purchaseVO) throws Exception;
	
	public PurchaseVO getPurchase(int tranNo) throws Exception;
	
	

}
