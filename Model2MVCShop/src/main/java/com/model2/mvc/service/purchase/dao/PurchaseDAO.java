package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class PurchaseDAO {

	public PurchaseDAO() {
	
	}
	
	public void insertPurchase(PurchaseVO purchaseVO) throws Exception{
		
		int prodNo = purchaseVO.getPurchaseProd().getProdNo();
		//Ŀ�ؼ��� ���ߴ�.
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
		//���ϰ����� �ݿ����θ� �˷���������ʳ�?
	}
	
	public PurchaseVO getPurchase(int prodNo) throws Exception{
		
		//Ŀ�ؼ��� ���ߴ�.
		Connection con = DBUtil.getConnection();
		
		//sql �� �غ��Ѵ�.
		
		String sql = "SELECT * FROM transaction WHERE prod_no = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);
		
		ResultSet rs = stmt.executeQuery();
		
		//select one �̹Ƿ� ���� �ʱ�ȭ�ص� ���� ����.
		PurchaseVO purchaseVO = new PurchaseVO();
		while(rs.next()) {//binding place
			purchaseVO.setTranNo(rs.getInt("tran_no"));
			purchaseVO.getPurchaseProd().setProdNo(rs.getInt("prod_no"));
			purchaseVO.getBuyer().setUserId(rs.getString("buyer_id"));
			purchaseVO.setPaymentOption(rs.getString("payment_option"));
			purchaseVO.setReceiverName(rs.getString("receiver_name"));
			purchaseVO.setReceiverPhone(rs.getString("receiver_phone"));
			purchaseVO.setDivyAddr(rs.getString("demailaddr"));
			purchaseVO.setDivyRequest(rs.getString("dlvy_request"));
			purchaseVO.setTranCode(rs.getString("tran_status_code"));
			purchaseVO.setOrderDate(rs.getDate("order_data"));
			purchaseVO.setDivyDate(rs.getString("dlvy_date"));
		}		
		
		return purchaseVO;
	}

	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		
		//Ŀ�ؼ��� ���ߴ�.
		Connection con = DBUtil.getConnection();
				
		//sql �� �غ��Ѵ�.
		String sql = "UPDATE transaction"
				+ " SET tran_status_code = ?"
				+ " WHERE prod_no = ?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1,purchaseVO.getTranCode());
		System.out.println(purchaseVO.getTranCode()+"�� Ʈ���ڵ�� ������Ʈ�մϴ�.");
		stmt.setInt(2, purchaseVO.getPurchaseProd().getProdNo());
		System.out.println(purchaseVO.getPurchaseProd().getProdNo()+"�� ���ε�븦 ������Ʈ�մϴ�.");
		
		
		stmt.executeUpdate();
		
		con.close();
	}
}
