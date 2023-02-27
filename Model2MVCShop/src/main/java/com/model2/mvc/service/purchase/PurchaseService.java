package com.model2.mvc.service.purchase;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public interface PurchaseService {
	//purchase ������
	public PurchaseVO addPurchase(PurchaseVO purchaseVO) throws Exception;
	
	public PurchaseVO getPurchase(int tranNo) throws Exception;
	
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception;

	public HashMap getPurchaseList(SearchVO searchVO) throws Exception;
		
	

}
