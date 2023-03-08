package com.model2.mvc.common.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UniqueFileName {

	public static void main(String[] args) {
		//유니크 아이디를 구성해본다
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid);
		
		//현재시각을 구한다.
		Date date = new Date();
		System.out.println(date);
		
		//SimpleDateFormat으로 현재시각을 도입한다.
		SimpleDateFormat sDate= new SimpleDateFormat("yyyyMMddHHmmss");
		String now = sDate.format(date);
		System.out.println(now);
		
		//아래거가 나은듯
		
	}

}
