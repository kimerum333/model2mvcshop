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

//Strouts 형식의 .do 기반으로부터 벗어나서 /를 모두 컨트롤단을 태우기로 했으므로 
@Controller
@RequestMapping("/product/*")
public class ProductController {

	/// Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
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
	public ProductController() {
		System.out.println(this.getClass() + " instance On");
	}
	
	//User 와 달리 여기에서는 어노테이션 기반 겟/포스트 매핑을 써보겠다.

	/// Method
	@PostMapping("addProduct")
	public String addProduct(@ModelAttribute("product") Product product,@RequestParam
			(value="imageBinary",required=false)MultipartFile file,HttpServletRequest request) throws Exception {
		// arg 단계에서 form 을 binding하고 request scope 에 productToAdd라는 이름으로 넣었다.
		System.out.println("addProduct");
		//이미지파일의 파일명과 사이즈를 확보
		String imageFileName = file.getOriginalFilename();
		//나중을 위한 확장자 substring 메모
		String fileExtension = imageFileName.substring(imageFileName.lastIndexOf("."),imageFileName.length());
		System.out.println("파일 확장자는..."+fileExtension);
		long imageFileSize = file.getSize();
		
		//현재시각을 구한다.
		Date date = new Date();
		//SimpleDateFormat으로 현재시각을 적절한 문자로 가공한다.
		SimpleDateFormat sDate= new SimpleDateFormat("yyyyMMddHHmmss");
		String now = sDate.format(date);
		System.out.println(now);
		//파일이름과 현재시각을 더해서 유니크한 서버저장파일명을 형성한다. 
		String savingFileName = now+imageFileName;
		
		//파일이 저장될 위치를 확보해야한다.
		String uploadPath = request.getServletContext().getRealPath("/images/uploadFiles/");
		String finalDestination = uploadPath+savingFileName;
		System.out.println("파일이 저장될 위치와 경로는..."+finalDestination);
		
		/*
		 * //sql val check if(finalDestination.length()>100) {
		 * System.out.println(finalDestination.length()); throw new
		 * Exception("파일 이름이 너무 깁니다."); }
		 */

		//파일 생성 후 위치에 저장
		File saveFile = new File(finalDestination);
			try {
				file.transferTo(saveFile);
			}catch(Exception e){
				e.printStackTrace();
			}
		//업로드된 파일경로를 바인드.
		product.setFileName(savingFileName);

		// business logic
		Product addedProduct = productService.addProduct(product);		
		int prodNo = addedProduct.getProdNo();
		System.out.println("등록한 상품의 " + prodNo);
		
		// get으로 태워보내서 등록한상품을 보여주겠다.
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
		System.out.println("상품번호는..."+prodNo);
		// get으로 태워보내서 등록상품을 보여주겠다.
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
