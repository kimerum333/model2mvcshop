package com.model2.mvc.service.domain;

import java.sql.Date;


public class Purchase {
	
	private User buyer;
	private String divyAddr;
	private String divyDate;
	private String divyRequest;
	private Date orderDate;
	private String paymentOption;
	//1 : ����
	//2 : �ſ�
	private Product purchaseProd;
	private String receiverName;
	private String receiverPhone;
	private String tranCode;
	// null �Ǹ��� ���.
	//sld = �ǸſϷ�
	//del = �����
	//don = ��ۿϷ�
	private int tranNo;
	
	public Purchase(){
		//��������� ��user�� �����ؼ� ���ο� userId�� ������ �� �ֵ��� �մϴ�.
		if(this.buyer==null) {
			System.out.println("�̰Ž����??????user");
			this.setBuyer(new User());
		}
		//Product ��.
		if(this.buyer==null) {
			System.out.println("�̰Ž����??????product");
			this.setPurchaseProd(new Product());
		}
	}
	
	public User getBuyer() {
		return buyer;
	}
	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	public String getDivyAddr() {
		return divyAddr;
	}
	public void setDivyAddr(String divyAddr) {
		this.divyAddr = divyAddr;
	}
	public String getDivyDate() {
		return divyDate;
	}
	public void setDivyDate(String divyDate) {
		this.divyDate = divyDate;
	}
	public String getDivyRequest() {
		return divyRequest;
	}
	public void setDivyRequest(String divyRequest) {
		this.divyRequest = divyRequest;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getPaymentOption() {
		return paymentOption;
	}
	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption.trim();//DB�� paymentoption�� varchar3�� �ν�Ʈ�� 2���� ���Ƿ� trim �� ���Ѽ� ���ϵ��� �մϴ�.
	}
	public Product getPurchaseProd() {
		return purchaseProd;
	}
	public void setPurchaseProd(Product purchaseProd) {
		this.purchaseProd = purchaseProd;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getTranCode() {
		return tranCode;
	}
	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}
	public int getTranNo() {
		return tranNo;
	}
	public void setTranNo(int tranNo) {
		this.tranNo = tranNo;
	}
	
	@Override
	public String toString() {
		return "PurchaseVO [buyer=" + buyer + ", divyAddr=" + divyAddr
				+ ", divyDate=" + divyDate + ", divyRequest=" + divyRequest
				+ ", orderDate=" + orderDate + ", paymentOption="
				+ paymentOption + ", purchaseProd=" + purchaseProd
				+ ", receiverName=" + receiverName + ", receiverPhone="
				+ receiverPhone + ", tranCode=" + tranCode + ", tranNo="
				+ tranNo + "]";
	}
}