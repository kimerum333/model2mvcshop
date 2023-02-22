package com.model2.mvc.service.purchase;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseService {
	
	//
	public Purchase addPurchase(Purchase purchase) throws Exception;
	
	//���ų����� Ȯ���ϴ� �߿� 
	public Purchase getPurchase(int tranNo) throws Exception;
	
	//�ش� ����ڰ� ������ ���ų������� ������� �����ִ� ���. �˻��� Ű���忡 �´� ����� �������� ���� search�� arg�� ���ڴ�.
	public Map getPurchaseList(Search search, String loginUserId) throws Exception;
	
	//���ų����� �����ϴ� ���.
	//login���� user�� role�� manager �� admin �� �ƴ� ���, ��ǰ�ڵ尡 sld �� ���� ��������.
	//login���� user�� role�� manager �� admin ���, ���� �Ұ�.
	public Purchase updatePurchase(Purchase purchase) throws Exception;
	
	//listProduct �߿� ����ϴ� ����.
	//menu eq search �� ���� ��ǰ�ڵ尡 del �� ��, don���� �����Ѵ�.
	//menu eq manage �� ���� ��ǰ�ڵ尡 sld �� ��, del�� �����Ѵ�.
	public void updateTranCode(Purchase purchase) throws Exception;
}
