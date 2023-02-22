package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

@Controller
public class PurchaseController {

	/// Field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	// setter Method 이쪽도 UserController와 마찬가지로 구현 안해보겠음.

	// Product Controller 는 String Return 전략을, Purchase Controller 는 ModelAndView
	// 리턴전략을 쓰기로 했다.

	// ==> classpath:config/common.properties , classpath:config/commonservice.xml
	// 참조 할것
	// ==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	// src/main/resources 의 config/context-common에 <util:properties
	// id="commonProperties" location="classpath:config/common.properties"/> 를 선언했기에
	// 사용할 수 있는 Spring EL이다
	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;

	/// Constructor
	public PurchaseController() {
		System.out.println(this.getClass() + " instance On");
	}

	/// Method
	@RequestMapping("/addPurchase.do")
	public String addPurchase(@ModelAttribute("purchaseToAdd") Purchase purchase) throws Exception {
		// arg 단계에서 form 을 binding하고 request scope 에 productToAdd라는 이름으로 넣었다.
		System.out.println("/addPurchase.do");

		// business logic
		Purchase addedPurchase = purchaseService.addPurchase(purchase);
		int prodNo = addedProduct.getProdNo();
		System.out.println("등록한 상품의 " + prodNo);

		// get으로 태워보내서 등록한상품을 보여주겠다.
		return "redirect:/getProduct.do?prodNo=" + prodNo;
	}

	@RequestMapping("/getProduct.do")
	public String getProduct(@RequestParam("prodNo") int prodNo, Model model) throws Exception {
		System.out.println("/getProduct.do");

		
		Product DbProduct = productService.getProduct(prodNo);
		model.addAttribute("product", DbProduct);
		return "forward:/product/getProductView.jsp";
	}

	@RequestMapping("/listProduct.do")
	public String listProduct(@ModelAttribute("search") Search search, Model model) throws Exception {
		System.out.println("/listProduct.do");
		//TODO listproduct가 고장나잇음...상품 업데이트가 아낻ㅁ
		// AutoBinding된 search로부터 page를 추출해보고, 만약 set되지 않았다면 1로 한다.
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);// properties로부터 Spring EL로 가져온 pageSize 바인딩

		// Business Logic
		Map<String, Object> map = productService.getProductList(search);

		int currentPage = search.getCurrentPage();
		int totalCount = (Integer) map.get("totalCount");
		// pageUnit, pageSize는 property에서 받아왔다.
		Page resultPage = new Page(currentPage, totalCount, pageUnit, pageSize);

		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		return "forward:/product/listProduct.jsp";
	}

	@RequestMapping("/updateProductView.do")
	public String updateProductView(@RequestParam("prodNo") int prodNo, HttpServletRequest request) throws Exception {
		System.out.println("updateProductView.do");
		Product updatingProduct = productService.getProduct(prodNo);
		request.setAttribute("product", updatingProduct);

		return "forward:/product/updateProductView.jsp";
	}

	@RequestMapping("/updateProduct.do")
	public String updateProduct(@ModelAttribute("updatingProduct") Product updatingProduct) throws Exception {
		productService.updateProduct(updatingProduct);

		int prodNo = updatingProduct.getProdNo();
		// get으로 태워보내서 등록상품을 보여주겠다.
		return "redirect:/getProduct.do?prodNo=" + prodNo;
	}
}
