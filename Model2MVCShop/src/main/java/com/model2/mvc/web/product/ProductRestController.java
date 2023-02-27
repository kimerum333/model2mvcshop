package com.model2.mvc.web.product;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

//Strouts ������ .do ������κ��� ����� /�� ��� ��Ʈ�Ѵ��� �¿��� �����Ƿ� 
@RestController
@RequestMapping("/product/*")
public class ProductRestController {

	/// Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;


	/// Constructor
	public ProductRestController() {
		System.out.println(this.getClass() + " instance On");
	}
	
	//User �� �޸� ���⿡���� ������̼� ��� ��/����Ʈ ������ �Ẹ�ڴ�.

	/// Method
	@RequestMapping(value = "json/getProduct/{prodNo}",method=RequestMethod.GET)
	public Product getProduct(@PathVariable int prodNo) throws Exception {
		// arg �ܰ迡�� form �� binding�ϰ� request scope �� productToAdd��� �̸����� �־���.
		System.out.println("/product/json/getProduct : GET");

		// business logic
		Product productToGet = productService.getProduct(prodNo);
		
		// get���� �¿������� ����ѻ�ǰ�� �����ְڴ�.
		return productToGet;
	}
	@RequestMapping(value="json/getProduct}",method=RequestMethod.POST)
	public Map getProductByPost(@RequestParam("prodNo") int prodNo) throws Exception{
		System.out.println("/pruduct/json/getProduct : POST / to JSON Map");
		System.out.println("prodNo is "+prodNo);
		//business logic
		Product productToGet = productService.getProduct(prodNo);
		
		Map map = new HashMap();
		map.put("product", productToGet);
		return map;
	}


}
