package com.model2.mvc.service.purchase.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

public class PurchaseDAO {

	public PurchaseDAO() {
	
	}
	
	public void insertPurchase(Purchase purchaseVO) throws Exception{
		
		int prodNo = purchaseVO.getPurchaseProd().getProdNo();
		//1. connection
		Connection con = null;

		/*
		  INSERT INTO transaction VALUES ( TRAN_NO ,PROD_NO ,BUYER_ID ,PAYMENT_OPTION
		  ,RECEIVER_NAME ,receiver_phone ,demailaddr ,dlvy_request ,tran_status_code
		  ,SYSDATE ,dlvy_date )
		 */
		//2. state
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
		
		//3 execute
		stmt.executeUpdate();
		
		con.close();
	}
	
	public void updateTranCode(Purchase purchase) throws Exception{
		//0.가져온 구매정보 분석
		int tranNo = purchase.getTranNo();
		String code = purchase.getTranCode();
		//1.커넥션을 겟했다.
		Connection con = null;
		
		//2.state
		String sql = "UPDATE TRANSACTION"
		+			 " SET"  
		+			 " tran_status_code = ?"
		+			 " WHERE tran_no = ?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, code);
		pStmt.setInt(2, tranNo);
		
		//resultset get
		pStmt.executeUpdate();
		System.out.println("Dao : "+tranNo+" 번 구매에 대하여 "+code+" 로 수정했습니다.");
	}
	
	public Map getPurchaseList(Search search, String userId) throws Exception{
		//자료정리
		
		//1. con
		Connection con = null;
		
		//2.state
		String sql = "SELECT tran_no, receiver_phone, tran_status_code"
					+ " FROM transaction"
					+ " WHERE buyer_id = ?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, userId);
		
		//resultset get
		ResultSet rs = pStmt.executeQuery();
		
		//set page
		List<Purchase> purchaseList = new ArrayList<Purchase>();
		int total=0;
		while(rs.next()) {
			Purchase thisPurchase = new Purchase();
			thisPurchase.setTranNo(rs.getInt("tran_no"));
			thisPurchase.setReceiverPhone(rs.getString("receiver_phone"));
			thisPurchase.setTranCode(rs.getString("tran_status_code"));
			purchaseList.add(thisPurchase);
			total++;
		}
		Map map = new HashMap();
		map.put("total", total);
		map.put("purchaseList", purchaseList);
		return map;
	}

	public Purchase findPurchase(int tranNo) throws Exception {
		//status set
		
		
		//con
		Connection con = null;
		
		//2.state
		String sql = "SELECT tran_no, prod_no, buyer_id, payment_option, receiver_name,receiver_phone, demailaddr, dlvy_request, dlvy_date, order_data"
					+ " FROM transaction"
					+ " WHERE tran_no = ?"
					+ " ORDER BY tran_no";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, tranNo);
		
		//resultset get
		ResultSet rs = pStmt.executeQuery();
		Purchase thisPurchase = new Purchase();
		User buyer = new User();
		Product product = new Product();
		
		while(rs.next()) {
			thisPurchase.setTranNo(rs.getInt("tran_no"));
			thisPurchase.setPaymentOption(rs.getString("payment_option"));
			thisPurchase.setReceiverName(rs.getString("receiver_name"));
			thisPurchase.setReceiverPhone(rs.getString("receiver_phone"));
			thisPurchase.setDivyRequest(rs.getString("dlvy_request"));
			thisPurchase.setDivyDate(rs.getString("dlvy_date"));
			thisPurchase.setOrderDate(rs.getDate("order_data"));
			product.setProdNo(rs.getInt("prod_no"));
			buyer.setUserId(rs.getString("buyer_id"));
			thisPurchase.setBuyer(buyer);
			thisPurchase.setPurchaseProd(product);
			System.out.println("Dao에서 넣어서 내보내는 구매정보는????????????????"+thisPurchase);
		}

		return thisPurchase;
	}
	
	
	public Purchase updatePurchase(Purchase purchase) throws Exception{
		//
		System.out.println("Dao 가 받은 purchase = " +purchase);
		
		//connection con
		Connection con = null;
				
		//2.state
		String sql = "UPDATE TRANSACTION"
				+ " SET"
				+ " buyer_id = ?"
				+ " ,payment_option = ?"
				+ " ,receiver_name = ?"
				+ " ,receiver_phone = ?"
				+ " ,dlvy_request = ?"
				+ " ,dlvy_date = ?"
				+ " ,demailaddr = ?"
				+ " WHERE tran_no = ?";
				
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, purchase.getBuyer().getUserId());
		pStmt.setString(2, purchase.getPaymentOption());
		pStmt.setString(3, purchase.getReceiverName());
		pStmt.setString(4, purchase.getReceiverPhone());
		pStmt.setString(5, purchase.getDivyRequest());
		pStmt.setString(6, purchase.getDivyDate());
		pStmt.setString(7, purchase.getDivyAddr());
		pStmt.setInt(8, purchase.getTranNo());
		pStmt.executeUpdate();
		return purchase;
	}
}
