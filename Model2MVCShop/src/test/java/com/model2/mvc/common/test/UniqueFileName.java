package com.model2.mvc.common.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UniqueFileName {

	public static void main(String[] args) {
		//����ũ ���̵� �����غ���
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid);
		
		//����ð��� ���Ѵ�.
		Date date = new Date();
		System.out.println(date);
		
		//SimpleDateFormat���� ����ð��� �����Ѵ�.
		SimpleDateFormat sDate= new SimpleDateFormat("yyyyMMddHHmmss");
		String now = sDate.format(date);
		System.out.println(now);
		
		//�Ʒ��Ű� ������
		
	}

}
