package com.model2.mvc.web.product;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

//Strouts 형식의 .do 기반으로부터 벗어나서 /를 모두 컨트롤단을 태우기로 했으므로 
@RestController
@RequestMapping("/product/*")
public class ProductRestController {

	/// Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;


	/// Constructor
	public ProductRestController() {
		System.out.println(this.getClass() + " instance On");
	}
	
	//User 와 달리 여기에서는 어노테이션 기반 겟/포스트 매핑을 써보겠다.

	/// Method
	@RequestMapping(value = "json/getProduct/{prodNo}",method=RequestMethod.GET)
	public Product getProduct(@PathVariable int prodNo) throws Exception {
		// arg 단계에서 form 을 binding하고 request scope 에 productToAdd라는 이름으로 넣었다.
		System.out.println("/product/json/getProduct : GET");

		// business logic
		Product productToGet = productService.getProduct(prodNo);
		
		// get으로 태워보내서 등록한상품을 보여주겠다.
		return productToGet;
	}
	@RequestMapping(value="json/getProduct",method=RequestMethod.POST)
	public Map getProductByPost(@RequestBody Product product) throws Exception{
		System.out.println("/pruduct/json/getProduct : POST / to JSON Map");
		int prodNo = product.getProdNo();
		System.out.println("prodNo is "+prodNo);
		//business logic
		Product productToGet = productService.getProduct(prodNo);
		
		Map map = new HashMap();
		map.put("product", productToGet);
		return map;
	}
	@PostMapping("json/addProduct")
	public Product addProduct(@RequestBody Product product) throws Exception {
		// arg 단계에서 form 을 binding하고 request scope 에 productToAdd라는 이름으로 넣었다.
		System.out.println("addProduct");

		// business logic
		Product addedProduct = productService.addProduct(product);
		System.out.println(addedProduct);
		return addedProduct;
	}

	@RequestMapping("json/listProduct/{currentPage}")
	public Map listProduct(@PathVariable int currentPage) throws Exception {
		System.out.println("json/listProduct called");
		
		Search search = new Search();
		search.setCurrentPage(currentPage);
		search.setPageSize(pageSize);// properties로부터 Spring EL로 가져온 pageSize 바인딩

		// Business Logic
		Map<String, Object> map = productService.getProductList(search);

		return map;
	}


}
