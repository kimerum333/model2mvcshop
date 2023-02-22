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
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={	
		"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" 
		})
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		product.setProdName("�ܹ�������");
		product.setProdDetail("������");
		product.setPrice(3000);
		product.setManuDate("20010317");
		product.setFileName("asdf/dfef.jpg");
		
		
		product = productService.addProduct(product);
		

		//==> console Ȯ��
		//System.out.println(user);
		
		//==> API Ȯ��
		Assert.assertEquals("�ܹ�������", product.getProdName());
		Assert.assertEquals("������", product.getProdDetail());
		Assert.assertEquals(3000, product.getPrice());
		Assert.assertEquals("20010317", product.getManuDate());
		Assert.assertEquals("asdf/dfef.jpg", product.getFileName());
	}
	
	  //@Test
	  public void testGetProduct() throws Exception {
			/*
			 * Product product = new Product(); //==> �ʿ��ϴٸ�... //
			 * user.setUserId("testUserId"); // product.setUserName("testUserName"); //
			 * user.setPassword("testPasswd"); // user.setSsn("1111112222222"); //
			 * user.setPhone("111-2222-3333"); // user.setAddr("��⵵"); //
			 * user.setEmail("test@test.com");
			 */
	  int testProdNo = 10121;
	  Product product = productService.getProduct(testProdNo);
	  
	  System.out.println(product);
	  
	  
	  //==> console Ȯ�� System.out.println(user);
//	  
//	  //==> API Ȯ�� Assert.assertEquals("testUserId", user.getUserId());
//	  Assert.assertEquals("testUserName", user.getUserName());
//	  Assert.assertEquals("testPasswd", user.getPassword());
//	  Assert.assertEquals("111-2222-3333", user.getPhone());
//	  Assert.assertEquals("��⵵", user.getAddr());
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
	  //Assert.assertEquals("��⵵", user.getAddr());
	  //Assert.assertEquals("test@test.com", user.getEmail());
	  
	  product.setProdName("chagedProdName");
	  product.setProdDetail("changedProdDetail");
	  product.setManuDate("20201599");
	  product.setPrice(7777);
	  product.setFileName("111/2222.jpg");
	  productService.updateProduct(product);
	  
	  product = productService.getProduct(testProdNo);
	  Assert.assertNotNull(product);
	  
	  //==> console Ȯ�� //System.out.println(user);
	  
	  //==> API Ȯ�� Assert.assertEquals("change", user.getUserName());
	  Assert.assertEquals("changedProdDetail", product.getProdDetail());
	  Assert.assertEquals("20201599", product.getManuDate());
	  Assert.assertEquals("111/2222.jpg", product.getFileName()); }
	
	
		
	  //==> �ּ��� Ǯ�� �����ϸ�.... 
	  //@Test 
	  public void testGetUserListAll() throws  Exception{
		  
		  //��ġ����� ����
		  Search search = new Search(); 
		  search.setCurrentPage(1);
		  search.setPageSize(3);
		  
		  Map<String,Object> map = productService.getProductList(search);
		  
		  List<Object> list = (List<Object>)map.get("list");
		  Assert.assertEquals(3,list.size());
		  
		  //==> console Ȯ�� 
		  System.out.println(list);
		  
		  Integer totalCount = (Integer)map.get("totalCount");
		  System.out.println(totalCount);
		  
		  System.out.println("=======================================");
		  
		  
		  //��ġ����� 
		  search.setCurrentPage(1); 
		  search.setPageSize(3);
		  search.setSearchCondition("0");
		  search.setSearchKeyword("");
		  map =productService.getProductList(search);
		  
		  list = (List<Object>)map.get("list");
		  Assert.assertEquals(3, list.size());
		  
		  //==> console Ȯ�� //
		  System.out.println(list);
		  
		  totalCount = (Integer)map.get("totalCount"); System.out.println(totalCount);
		  }
		  
		  @Test
			  public void testGetProductListByProdName() throws Exception{//��ǰ������ ��ǰ������ �˻��ϴ� �׽�Ʈ
			  
			  Search search = new Search();
			  search.setCurrentPage(1);
			  search.setPageSize(3);
			  search.setSearchCondition("1");
			  search.setSearchKeyword("�ܹ�������");
			  
			  Map<String,Object> map =
			  productService.getProductList(search);
			  
			  List<Object> list = (List<Object>)map.get("list"); 
			  Assert.assertEquals(1,list.size());
			  
			  //==> console Ȯ�� //
			  System.out.println(list);
			  
			  Integer totalCount = (Integer)map.get("totalCount");
			  System.out.println(totalCount);
			  
			  System.out.println("=======================================");
			  
			  search.setSearchCondition("0");
			  search.setSearchKeyword(""+System.currentTimeMillis());
			  map = productService.getProductList(search);
			  
			  list = (List<Object>)map.get("list");
			  Assert.assertEquals(0, list.size());
			  
			  //==> console Ȯ�� //
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
				 * //==> console Ȯ�� System.out.println(list);
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
				 * //==> console Ȯ�� System.out.println(list);
				 * 
				 * totalCount = (Integer)map.get("totalCount"); System.out.println(totalCount);
				 * }
				 */
			 
}