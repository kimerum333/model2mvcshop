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
	
		//실행부
		
		System.out.println("addProduct_Jackson_String_Post() 실행");
		ProductRestHttpClientForAddProduct.addProduct_Jackson_String_Post();
		
		
	}
	
	//http 모사 Client
	
	public static void addProduct_Jackson_String_Post() throws Exception {
		
		//httpClient 작성
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/addProduct";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept","application/json");
		httpPost.setHeader("Content-Type","application/json");
		
		//방법2 JSONObject 를 활용해서 직접 JSON 작성.
		JSONObject json = new JSONObject();
		json.put("prodName", "짭");
		json.put("price", "12500");
		json.put("prodDetail", "싼데비슷함");
		json.put("manuDate", "2023-03-04");	//date Format 이라 실수가능.
		
		System.out.println(json);
		
		//작성한 JSON정보가 들어있는 Body 작성
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		
		//Post 리퀘스트에 작성한 Body를 등록.
		httpPost.setEntity(httpEntity01);
		
		//Client 에 Post를 등록하고 리퀘스트 실행. 이후 받은 리스폰스를 리스폰스 객체로 저장.
		HttpResponse httpResponse = httpClient.execute(httpPost);
		System.out.println(httpResponse); //리스폰스 내용 확인
		
		//받은 리스폰스 중 바디를 선택
		HttpEntity httpEntity02 = httpResponse.getEntity();
		//바디를 읽을 인풋스트림 작성
		InputStream is = httpEntity02.getContent();
		//인풋스트림을 읽기 위한 버퍼드 리더 작성.
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("서버에서 받은 데이터 확인");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = objectMapper.readValue(serverData, Product.class);
		System.out.println(product);
	}
}
