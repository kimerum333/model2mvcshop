package com.model2.mvc.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;


public class CommonUtil {
	
	///Field
	
	///Constructor
	
	///Method
	public static String null2str(String org, String converted) {
		if (org == null || org.trim().length() == 0)
			//���̰ų� �ν�Ʈ���̸� ����Ƽ�带 �����ض�.
			return converted;
		else
			return org.trim();
			//���̰ų� �ν�Ʈ���� �ƴϸ� Ʈ���� �ؼ� �����ض�.
	}

	public static String null2str(String org) {
		return CommonUtil.null2str(org, "");
		//���̸� �ν�Ʈ���� �����ض�.
	}

	public static String null2str(Object org) { //���ڷ� ������Ʈ�� ��������
		if (org != null && org instanceof java.math.BigDecimal) {
			return CommonUtil.null2str((java.math.BigDecimal) org, ""); //���ڸ� ���ڸ� �����ϰ�
		} else {
			return CommonUtil.null2str((String) org, ""); 	//��Ʈ���̸� ��Ʈ���� �����ض�
		}
	}

	public static String null2str(java.math.BigDecimal org, String converted) {//���ʿ� �ε��� ������, �����Ͻ� �װ��� ���ڷ� �����ؼ� �����ض�
		if (org == null)
			return converted;
		else
			return org.toString();
	}

	public static String null2str(java.math.BigDecimal org) { //���ڸ� �־��µ�, ���̸� �ν�Ʈ�� ���ڸ� ���ڷ� �ٲ㼭 �����ض�.
		return CommonUtil.null2str(org, "");
	}

	public static String toDateStr(String dateStr) {//��¥�� �ǹ��ϴ� ���ڰ� ������, ���̸� �ν�Ʈ�� ����, 8�ڸ����ڸ� �״��, 8�ڸ��� /�� CSV�� (YYYY.MM.DD)
		if (dateStr == null)
			return "";
		else if (dateStr.length() != 8)
			return dateStr;
		else
			return dateStr.substring(0, 4) + "/" + dateStr.substring(4, 6)
					+ "/" + dateStr.substring(6, 8);
	}

	public static String toDateStr(Timestamp date) { //��¥Ÿ�� �����Ͱ� ������ ��, ���õ��������� : �� ����
		if (date == null)
			return "";
		else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			return sdf.format(new Date(date.getTime()));
		}
	}

	public static String toSsnStr(String ssnStr) { //�ֹι�ȣ ������ �� - �־���
		if (ssnStr == null)
			return "";
		else if (ssnStr.length() != 13)
			return ssnStr;
		else
			return ssnStr.substring(0, 6) + "-" + ssnStr.substring(6, 13);
	}

	public static String toAmountStr(String amountStr) {//�ڿ������� 3������ �߶������.
		String returnValue = "";
		if (amountStr == null)
			return returnValue;
		else {
			int strLength = amountStr.length();

			if (strLength <= 3)
				return amountStr;
			else {
				String s1 = "";
				String s2 = "";
				for (int i = strLength - 1; i >= 0; i--)
					s1 += amountStr.charAt(i);

				for (int i = strLength - 1; i >= 0; i--) {
					s2 += s1.charAt(i);
					if (i % 3 == 0 && i != 0)
						s2 += ",";
				}

				return s2;
			}
		}
	}

	public static String toAmountStr(java.math.BigDecimal amount) {//���ڰ� ���͵� �׷���.
		if (amount == null) {
			return "";
		} else {
			return toAmountStr(amount.toString());
		}
	}
}
