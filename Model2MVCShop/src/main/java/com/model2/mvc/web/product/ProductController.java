package com.model2.mvc.web.product;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

//Strouts ������ .do ������κ��� ����� /�� ��� ��Ʈ�Ѵ��� �¿��� �����Ƿ� 
@Controller
@RequestMapping("/product/*")
public class ProductController {

	/// Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
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
	public ProductController() {
		System.out.println(this.getClass() + " instance On");
	}
	
	//User �� �޸� ���⿡���� ������̼� ��� ��/����Ʈ ������ �Ẹ�ڴ�.

	/// Method
	@PostMapping("addProduct")
	public String addProduct(@ModelAttribute("product") Product product,@RequestParam
			(value="imageBinary",required=false)MultipartFile file,HttpServletRequest request) throws Exception {
		// arg �ܰ迡�� form �� binding�ϰ� request scope �� productToAdd��� �̸����� �־���.
		System.out.println("addProduct");
		//�̹��������� ���ϸ�� ����� Ȯ��
		String imageFileName = file.getOriginalFilename();
		//������ ���� Ȯ���� substring �޸�
		String fileExtension = imageFileName.substring(imageFileName.lastIndexOf("."),imageFileName.length());
		System.out.println("���� Ȯ���ڴ�..."+fileExtension);
		long imageFileSize = file.getSize();
		
		//����ð��� ���Ѵ�.
		Date date = new Date();
		//SimpleDateFormat���� ����ð��� ������ ���ڷ� �����Ѵ�.
		SimpleDateFormat sDate= new SimpleDateFormat("yyyyMMddHHmmss");
		String now = sDate.format(date);
		System.out.println(now);
		//�����̸��� ����ð��� ���ؼ� ����ũ�� �����������ϸ��� �����Ѵ�. 
		String savingFileName = now+imageFileName;
		
		//������ ����� ��ġ�� Ȯ���ؾ��Ѵ�.
		String uploadPath = request.getServletContext().getRealPath("/images/uploadFiles/");
		String finalDestination = uploadPath+savingFileName;
		System.out.println("������ ����� ��ġ�� ��δ�..."+finalDestination);
		
		/*
		 * //sql val check if(finalDestination.length()>100) {
		 * System.out.println(finalDestination.length()); throw new
		 * Exception("���� �̸��� �ʹ� ��ϴ�."); }
		 */

		//���� ���� �� ��ġ�� ����
		File saveFile = new File(finalDestination);
			try {
				file.transferTo(saveFile);
			}catch(Exception e){
				e.printStackTrace();
			}
		//���ε�� ���ϰ�θ� ���ε�.
		product.setFileName(savingFileName);

		// business logic
		Product addedProduct = productService.addProduct(product);		
		int prodNo = addedProduct.getProdNo();
		System.out.println("����� ��ǰ�� " + prodNo);
		
		// get���� �¿������� ����ѻ�ǰ�� �����ְڴ�.
		return "redirect:/product/getProduct?prodNo=" + prodNo;
	}

	@RequestMapping("getProduct")
	public String getProduct(@RequestParam("prodNo") int prodNo, Model model) throws Exception {
		System.out.println("getProduct");

		
		Product DbProduct = productService.getProduct(prodNo);
		model.addAttribute("product", DbProduct);
		return "forward:/product/getProductView.jsp";
	}

	@RequestMapping("listProduct")
	public String listProduct(@ModelAttribute("search") Search search, Model model) throws Exception {
		System.out.println("listProduct");
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
	@GetMapping("updateProduct")
	public String updateProduct(@RequestParam("prodNo") int prodNo, HttpServletRequest request) throws Exception {
		System.out.println("updateProduct : GET METHOD");
		Product updatingProduct = productService.getProduct(prodNo);
		
		request.setAttribute("product", updatingProduct);

		return "forward:/product/updateProductView.jsp";
	}

	@PostMapping("updateProduct")
	public String updateProduct(@ModelAttribute("updatingProduct") Product updatingProduct) throws Exception {
		System.out.println("updateProduct : POST");
		productService.updateProduct(updatingProduct);

		int prodNo = updatingProduct.getProdNo();
		System.out.println("��ǰ��ȣ��..."+prodNo);
		// get���� �¿������� ��ϻ�ǰ�� �����ְڴ�.
		return "redirect:/product/getProduct?prodNo=" + prodNo;
	}
	
	@PostMapping("summernoteTest")
	public String summernoteTest(Product updatingProduct,HttpServletRequest request) throws Exception {
		System.out.println("summernoteTest method on");
		
		String editordata= request.getParameter("editordata");
		System.out.println(editordata);
		return null;
	}
}
