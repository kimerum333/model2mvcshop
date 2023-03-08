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
			//널이거나 널스트링이면 컨버티드를 리턴해라.
			return converted;
		else
			return org.trim();
			//널이거나 널스트링이 아니면 트림만 해서 리턴해라.
	}

	public static String null2str(String org) {
		return CommonUtil.null2str(org, "");
		//널이면 널스트링만 리턴해라.
	}

	public static String null2str(Object org) { //인자로 오브젝트가 들어왔을때
		if (org != null && org instanceof java.math.BigDecimal) {
			return CommonUtil.null2str((java.math.BigDecimal) org, ""); //숫자면 숫자를 리턴하고
		} else {
			return CommonUtil.null2str((String) org, ""); 	//스트링이면 스트링을 리턴해라
		}
	}

	public static String null2str(java.math.BigDecimal org, String converted) {//왼쪽에 널들어갈시 오른쪽, 숫자일시 그것을 문자로 변경해서 리턴해라
		if (org == null)
			return converted;
		else
			return org.toString();
	}

	public static String null2str(java.math.BigDecimal org) { //숫자를 넣었는데, 널이면 널스트링 숫자면 문자로 바꿔서 리턴해라.
		return CommonUtil.null2str(org, "");
	}

	public static String toDateStr(String dateStr) {//날짜를 의미하는 문자가 들어갔을시, 널이면 널스트링 리턴, 8자리글자면 그대로, 8자리면 /를 CSV로 (YYYY.MM.DD)
		if (dateStr == null)
			return "";
		else if (dateStr.length() != 8)
			return dateStr;
		else
			return dateStr.substring(0, 4) + "/" + dateStr.substring(4, 6)
					+ "/" + dateStr.substring(6, 8);
	}

	public static String toDateStr(Timestamp date) { //날짜타입 데이터가 들어왔을 시, 심플데이터포맷 : 로 리턴
		if (date == null)
			return "";
		else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			return sdf.format(new Date(date.getTime()));
		}
	}

	public static String toSsnStr(String ssnStr) { //주민번호 들어왔을 시 - 넣어줌
		if (ssnStr == null)
			return "";
		else if (ssnStr.length() != 13)
			return ssnStr;
		else
			return ssnStr.substring(0, 6) + "-" + ssnStr.substring(6, 13);
	}

	public static String toAmountStr(String amountStr) {//뒤에서부터 3단위로 잘라버린다.
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

	public static String toAmountStr(java.math.BigDecimal amount) {//숫자가 들어와도 그렇다.
		if (amount == null) {
			return "";
		} else {
			return toAmountStr(amount.toString());
		}
	}
}
