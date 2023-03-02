package com.model2.mvc.service.product.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import com.model2.mvc.service.domain.Product;

public class ProductRestHttpClientForAddProduct {

	
	public static void main(String[] args) throws Exception{
	
		//�����
		
		System.out.println("addProduct_Jackson_String_Post() ����");
		ProductRestHttpClientForAddProduct.addProduct_Jackson_String_Post();
		
		
	}
	
	//http ��� Client
	
	public static void addProduct_Jackson_String_Post() throws Exception {
		
		//httpClient �ۼ�
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/addProduct";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept","application/json");
		httpPost.setHeader("Content-Type","application/json");
		
		//���2 JSONObject �� Ȱ���ؼ� ���� JSON �ۼ�.
		JSONObject json = new JSONObject();
		json.put("prodName", "¬");
		json.put("price", "12500");
		json.put("prodDetail", "�ѵ������");
		json.put("manuDate", "2023-03-04");	//date Format �̶� �Ǽ�����.
		
		System.out.println(json);
		
		//�ۼ��� JSON������ ����ִ� Body �ۼ�
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		
		//Post ������Ʈ�� �ۼ��� Body�� ���.
		httpPost.setEntity(httpEntity01);
		
		//Client �� Post�� ����ϰ� ������Ʈ ����. ���� ���� ���������� �������� ��ü�� ����.
		HttpResponse httpResponse = httpClient.execute(httpPost);
		System.out.println(httpResponse); //�������� ���� Ȯ��
		
		//���� �������� �� �ٵ� ����
		HttpEntity httpEntity02 = httpResponse.getEntity();
		//�ٵ� ���� ��ǲ��Ʈ�� �ۼ�
		InputStream is = httpEntity02.getContent();
		//��ǲ��Ʈ���� �б� ���� ���۵� ���� �ۼ�.
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("�������� ���� ������ Ȯ��");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = objectMapper.readValue(serverData, Product.class);
		System.out.println(product);
	}
}
