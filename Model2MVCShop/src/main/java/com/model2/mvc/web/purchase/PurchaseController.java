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
	// setter Method ���ʵ� UserController�� ���������� ���� ���غ�����.

	// Product Controller �� String Return ������, Purchase Controller �� ModelAndView
	// ���������� ����� �ߴ�.

	// ==> classpath:config/common.properties , classpath:config/commonservice.xml
	// ���� �Ұ�
	// ==> �Ʒ��� �ΰ��� �ּ��� Ǯ�� �ǹ̸� Ȯ�� �Ұ�
	// src/main/resources �� config/context-common�� <util:properties
	// id="commonProperties" location="classpath:config/common.properties"/> �� �����߱⿡
	// ����� �� �ִ� Spring EL�̴�
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
		// arg �ܰ迡�� form �� binding�ϰ� request scope �� productToAdd��� �̸����� �־���.
		System.out.println("/addPurchase.do");

		// business logic
		Purchase addedPurchase = purchaseService.addPurchase(purchase);
		int prodNo = addedProduct.getProdNo();
		System.out.println("����� ��ǰ�� " + prodNo);

		// get���� �¿������� ����ѻ�ǰ�� �����ְڴ�.
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
		//TODO listproduct�� ���峪����...��ǰ ������Ʈ�� �ƆK��
		// AutoBinding�� search�κ��� page�� �����غ���, ���� set���� �ʾҴٸ� 1�� �Ѵ�.
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);// properties�κ��� Spring EL�� ������ pageSize ���ε�

		// Business Logic
		Map<String, Object> map = productService.getProductList(search);

		int currentPage = search.getCurrentPage();
		int totalCount = (Integer) map.get("totalCount");
		// pageUnit, pageSize�� property���� �޾ƿԴ�.
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
		// get���� �¿������� ��ϻ�ǰ�� �����ְڴ�.
		return "redirect:/getProduct.do?prodNo=" + prodNo;
	}
}
