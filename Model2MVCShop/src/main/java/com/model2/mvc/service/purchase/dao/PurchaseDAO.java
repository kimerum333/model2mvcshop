package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class PurchaseDAO {

	public PurchaseDAO() {
	
	}
	
	public void insertPurchase(PurchaseVO purchaseVO) throws Exception{
		
		int prodNo = purchaseVO.getPurchaseProd().getProdNo();
		//커넥션을 겟했다.
		Connection con = DBUtil.getConnection();

		/*
		  INSERT INTO transaction VALUES ( TRAN_NO ,PROD_NO ,BUYER_ID ,PAYMENT_OPTION
		  ,RECEIVER_NAME ,receiver_phone ,demailaddr ,dlvy_request ,tran_status_code
		  ,SYSDATE ,dlvy_date )
		 */
		
		String sql = "INSERT INTO transaction\r\n"
				+ "		VALUES ("
				+ "		seq_transaction_tran_no.NEXTVAL	" // tranno
				+ "		,?"	//1prodno
				+ "		,?" //2buyerID
				+ "		,?" //3pay option
				+ "		,?" //4 receiverName
				+ "		,?" //5 receiverPhone
				+ "		,?" //6 email addr
				+ "		,?" //7 request
				+ "		,?"	//8 tran code
				+ "		,SYSDATE" //order data
				+ "		,?" //9 dlvy date
				+ "		)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);
		stmt.setString(2, purchaseVO.getBuyer().getUserId());
		stmt.setString(3, purchaseVO.getPaymentOption());
		stmt.setString(4, purchaseVO.getReceiverName());
		stmt.setString(5, purchaseVO.getReceiverPhone());
		stmt.setString(6, purchaseVO.getBuyer().getEmail());
		stmt.setString(7, purchaseVO.getDivyRequest());
		stmt.setString(8, purchaseVO.getTranCode());
		stmt.setString(9, purchaseVO.getDivyDate());
		
		stmt.executeUpdate();
		
		con.close();
		//리턴값으로 반영여부를 알려줘야하지않나?
	}
}
