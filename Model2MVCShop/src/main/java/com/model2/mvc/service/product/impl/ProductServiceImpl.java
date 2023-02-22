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
	public void setProductDao(ProductDao productDao) {// setter injection 으로 외부로부터 Dao를 주입받는다.
		this.productDao = productDao;
	}

	public ProductServiceImpl() {
		System.out.println(this.getClass()+"instance on");
		// Autowired되므로 옛 방식대로 new로 const에서 dao를 초기화할 이유가 없어졌다.
	}

	@Override
	public Product addProduct(Product product) throws Exception {
		productDao.addProduct(product); // Dao에서 add한다
		return product; 
		//prodNo를 받은 product 를 컨트롤단으로 리턴한다.
	}

	
	 @Override
	 public Product getProduct(int prodNo) throws Exception { 
	 return productDao.getProduct(prodNo);
	  }
	
	 @Override
	 public Map<String, Object> getProductList(Search search) throws Exception {
		  //이것을 지워야 할까? log AOP기능을 확인해보자.
		  //결론 : 지워도 된다. context-aspect 의 pointcut을 확인해보면
		  //pointcut="execution(* com.model2.mvc.service..*Impl.*(..) )" 이므로
		  //service package 의 모든 method에 advice 하도록 되어있다.
		  //LogAspectJ를 확인하면, target의 method에 전달되는 arg를 알 수 있으므로 확인가능.
		  //System.out.println("ProductService : 받은 Search : "+search);
		  
		 
		 //Dao로부터 필요한 값을 list로 받고, total값을 받은 뒤 이를 취합해 Map으로 만들어
		 //Control 단에 전달한다.
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
