package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

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
	
	public PurchaseVO getPurchase(int prodNo) throws Exception{
		
		//커넥션을 겟했다.
		Connection con = DBUtil.getConnection();
		
		//sql 을 준비한다.
		
		String sql = "SELECT * FROM transaction WHERE prod_no = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);
		
		ResultSet rs = stmt.executeQuery();
		
		//select one 이므로 선행 초기화해도 문제 없음.
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
		
		//커넥션을 겟했다.
		Connection con = DBUtil.getConnection();
				
		//sql 을 준비한다.
		String sql = "UPDATE transaction"
				+ " SET tran_status_code = ?"
				+ " WHERE prod_no = ?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1,purchaseVO.getTranCode());
		System.out.println(purchaseVO.getTranCode()+"의 트랜코드로 업데이트합니다.");
		stmt.setInt(2, purchaseVO.getPurchaseProd().getProdNo());
		System.out.println(purchaseVO.getPurchaseProd().getProdNo()+"의 프로드노를 업데이트합니다.");
		
		
		stmt.executeUpdate();
		
		con.close();
	}

	public HashMap<String,Object> getPurchaseList(SearchVO searchVO) throws SQLException {
		// TODO Auto-generated method stub
		//필요한 정보를 꺼낸다.
		String userId = searchVO.getSearchKeyword();
		
		//커넥션을 겟했다.
		Connection con = DBUtil.getConnection();
				
		//sql 을 준비한다.
				
		String sql = "SELECT * FROM transaction where buyer_id = ?";
			   sql += " order by tran_no";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1,userId);
		
		ResultSet rs = stmt.executeQuery();
		
		//전체 로우의수 리턴
		rs.last();
		int total = rs.getRow();
		System.out.println("로우의 수:" + total);
		
		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

		ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				PurchaseVO purchaseVO = new PurchaseVO();
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
				list.add(purchaseVO);
				if (!rs.next())
					break;
			}
		}
		//맵을 만든다.
		HashMap<String,Object> map = new HashMap<String,Object>();
		//리스트를 맵에 넣는다.
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);		
		//전체 로우의 수 totalCount 라는 이름으로 맵에 넣는다.
		map.put("totalCount", new Integer(total));
		System.out.println("total count = "+total);
		//서치브이오도 넣는다.
		map.put("searchVO", searchVO);
		con.close();
		
		return map;
	}
}
