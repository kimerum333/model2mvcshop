package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.user.UserService;


/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={	
		"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" 
		})
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		product.setProdName("외발자전거");
		product.setProdDetail("느려요");
		product.setPrice(3000);
		product.setManuDate("20010317");
		product.setFileName("asdf/dfef.jpg");
		
		
		product = productService.addProduct(product);
		

		//==> console 확인
		//System.out.println(user);
		
		//==> API 확인
		Assert.assertEquals("외발자전거", product.getProdName());
		Assert.assertEquals("느려요", product.getProdDetail());
		Assert.assertEquals(3000, product.getPrice());
		Assert.assertEquals("20010317", product.getManuDate());
		Assert.assertEquals("asdf/dfef.jpg", product.getFileName());
	}
	
	  //@Test
	  public void testGetProduct() throws Exception {
			/*
			 * Product product = new Product(); //==> 필요하다면... //
			 * user.setUserId("testUserId"); // product.setUserName("testUserName"); //
			 * user.setPassword("testPasswd"); // user.setSsn("1111112222222"); //
			 * user.setPhone("111-2222-3333"); // user.setAddr("경기도"); //
			 * user.setEmail("test@test.com");
			 */
	  int testProdNo = 10121;
	  Product product = productService.getProduct(testProdNo);
	  
	  System.out.println(product);
	  
	  
	  //==> console 확인 System.out.println(user);
//	  
//	  //==> API 확인 Assert.assertEquals("testUserId", user.getUserId());
//	  Assert.assertEquals("testUserName", user.getUserName());
//	  Assert.assertEquals("testPasswd", user.getPassword());
//	  Assert.assertEquals("111-2222-3333", user.getPhone());
//	  Assert.assertEquals("경기도", user.getAddr());
//	  Assert.assertEquals("test@test.com", user.getEmail());
//	  
//	  Assert.assertNotNull(userService.getUser("user02"));
//	  Assert.assertNotNull(userService.getUser("user05")); }
	  }//end of getProd Test
	  
	  
	
	  //@Test
	  public void testUpdateProduct() throws Exception{
	  
	  int testProdNo = 10121;
	  Product product = productService.getProduct(testProdNo);
	  Assert.assertNotNull(product);
	  System.out.println(product);
	  
	  //Assert.assertEquals("testUserName", user.getUserName());
	  //Assert.assertEquals("111-2222-3333", user.getPhone());
	  //Assert.assertEquals("경기도", user.getAddr());
	  //Assert.assertEquals("test@test.com", user.getEmail());
	  
	  product.setProdName("chagedProdName");
	  product.setProdDetail("changedProdDetail");
	  product.setManuDate("20201599");
	  product.setPrice(7777);
	  product.setFileName("111/2222.jpg");
	  productService.updateProduct(product);
	  
	  product = productService.getProduct(testProdNo);
	  Assert.assertNotNull(product);
	  
	  //==> console 확인 //System.out.println(user);
	  
	  //==> API 확인 Assert.assertEquals("change", user.getUserName());
	  Assert.assertEquals("changedProdDetail", product.getProdDetail());
	  Assert.assertEquals("20201599", product.getManuDate());
	  Assert.assertEquals("111/2222.jpg", product.getFileName()); }
	
	
		
	  //==> 주석을 풀고 실행하면.... 
	  //@Test 
	  public void testGetUserListAll() throws  Exception{
		  
		  //서치컨디션 없이
		  Search search = new Search(); 
		  search.setCurrentPage(1);
		  search.setPageSize(3);
		  
		  Map<String,Object> map = productService.getProductList(search);
		  
		  List<Object> list = (List<Object>)map.get("list");
		  Assert.assertEquals(3,list.size());
		  
		  //==> console 확인 
		  System.out.println(list);
		  
		  Integer totalCount = (Integer)map.get("totalCount");
		  System.out.println(totalCount);
		  
		  System.out.println("=======================================");
		  
		  
		  //서치컨디션 
		  search.setCurrentPage(1); 
		  search.setPageSize(3);
		  search.setSearchCondition("0");
		  search.setSearchKeyword("");
		  map =productService.getProductList(search);
		  
		  list = (List<Object>)map.get("list");
		  Assert.assertEquals(3, list.size());
		  
		  //==> console 확인 //
		  System.out.println(list);
		  
		  totalCount = (Integer)map.get("totalCount"); System.out.println(totalCount);
		  }
		  
		  @Test
			  public void testGetProductListByProdName() throws Exception{//상품명으로 상품정보를 검색하는 테스트
			  
			  Search search = new Search();
			  search.setCurrentPage(1);
			  search.setPageSize(3);
			  search.setSearchCondition("1");
			  search.setSearchKeyword("외발자전거");
			  
			  Map<String,Object> map =
			  productService.getProductList(search);
			  
			  List<Object> list = (List<Object>)map.get("list"); 
			  Assert.assertEquals(1,list.size());
			  
			  //==> console 확인 //
			  System.out.println(list);
			  
			  Integer totalCount = (Integer)map.get("totalCount");
			  System.out.println(totalCount);
			  
			  System.out.println("=======================================");
			  
			  search.setSearchCondition("0");
			  search.setSearchKeyword(""+System.currentTimeMillis());
			  map = productService.getProductList(search);
			  
			  list = (List<Object>)map.get("list");
			  Assert.assertEquals(0, list.size());
			  
			  //==> console 확인 //
			  System.out.println(list);
			  
			  totalCount = (Integer)map.get("totalCount");
			  System.out.println(totalCount);
			  }
			  
			  //@Test public void testGetUserListByUserName() throws Exception{
				/*
				 * Search search = new Search(); search.setCurrentPage(1);
				 * search.setPageSize(3); search.setSearchCondition("1");
				 * search.setSearchKeyword("SCOTT"); Map<String,Object> map =
				 * userService.getUserList(search);
				 * 
				 * List<Object> list = (List<Object>)map.get("list"); Assert.assertEquals(3,
				 * list.size());
				 * 
				 * //==> console 확인 System.out.println(list);
				 * 
				 * Integer totalCount = (Integer)map.get("totalCount");
				 * System.out.println(totalCount);
				 * 
				 * System.out.println("=======================================");
				 * 
				 * search.setSearchCondition("1");
				 * search.setSearchKeyword(""+System.currentTimeMillis()); map =
				 * userService.getUserList(search);
				 * 
				 * list = (List<Object>)map.get("list"); Assert.assertEquals(0, list.size());
				 * 
				 * //==> console 확인 System.out.println(list);
				 * 
				 * totalCount = (Integer)map.get("totalCount"); System.out.println(totalCount);
				 * }
				 */
			 
}