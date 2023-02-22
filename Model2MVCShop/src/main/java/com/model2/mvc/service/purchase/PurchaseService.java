package com.model2.mvc.service.purchase;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseService {
	
	//
	public Purchase addPurchase(Purchase purchase) throws Exception;
	
	//구매내역을 확인하는 중에 
	public Purchase getPurchase(int tranNo) throws Exception;
	
	//해당 사용자가 구매한 구매내역들을 목록으로 보여주는 기능. 검색한 키워드에 맞는 목록을 가져오기 위해 search를 arg로 갖겠다.
	public Map getPurchaseList(Search search, String loginUserId) throws Exception;
	
	//구매내용을 수정하는 기능.
	//login중인 user의 role이 manager 나 admin 이 아닐 경우, 상품코드가 sld 일 때만 수정가능.
	//login중인 user의 role이 manager 나 admin 경우, 수정 불가.
	public Purchase updatePurchase(Purchase purchase) throws Exception;
	
	//listProduct 중에 기능하는 서비스.
	//menu eq search 일 때는 상품코드가 del 일 시, don으로 변경한다.
	//menu eq manage 일 때는 상품코드가 sld 일 시, del로 변경한다.
	public void updateTranCode(Purchase purchase) throws Exception;
}
