package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {
	///Field
	//Associated with Dao
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;
	//setter injection
	public void setProductDao(ProductDao productDao) {// setter injection ���� �ܺηκ��� Dao�� ���Թ޴´�.
		this.productDao = productDao;
	}

	public ProductServiceImpl() {
		System.out.println(this.getClass()+"instance on");
		// Autowired�ǹǷ� �� ��Ĵ�� new�� const���� dao�� �ʱ�ȭ�� ������ ��������.
	}

	@Override
	public Product addProduct(Product product) throws Exception {
		productDao.addProduct(product); // Dao���� add�Ѵ�
		return product; 
		//prodNo�� ���� product �� ��Ʈ�Ѵ����� �����Ѵ�.
	}

	
	 @Override
	 public Product getProduct(int prodNo) throws Exception { 
	 return productDao.getProduct(prodNo);
	  }
	
	 @Override
	 public Map<String, Object> getProductList(Search search) throws Exception {
		  //�̰��� ������ �ұ�? log AOP����� Ȯ���غ���.
		  //��� : ������ �ȴ�. context-aspect �� pointcut�� Ȯ���غ���
		  //pointcut="execution(* com.model2.mvc.service..*Impl.*(..) )" �̹Ƿ�
		  //service package �� ��� method�� advice �ϵ��� �Ǿ��ִ�.
		  //LogAspectJ�� Ȯ���ϸ�, target�� method�� ���޵Ǵ� arg�� �� �� �����Ƿ� Ȯ�ΰ���.
		  //System.out.println("ProductService : ���� Search : "+search);
		  
		 
		 //Dao�κ��� �ʿ��� ���� list�� �ް�, total���� ���� �� �̸� ������ Map���� �����
		 //Control �ܿ� �����Ѵ�.
		 int totalCount = productDao.getTotalCount(search);
		 List<Product> list = productDao.getProductList(search);
	  
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("list", list );
		 map.put("totalCount", new Integer(totalCount));
		  
	  return map;
	  }
	  
	  public Product updateProduct(Product product) throws Exception {
	  productDao.updateProduct(product); 
	  return product; 
	  }
	 
}
