package com.model2.mvc.web.memo;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
@RequestMapping("/memo/*")
public class MemoController {

	/// Field
	//@Autowired
	//@Qualifier("memoServiceImpl")
	//private MemoService memoService;
	// setter Method 이쪽도 UserController와 마찬가지로 구현 안해보겠음.
	/// Constructor
	public MemoController() {
		System.out.println(this.getClass() + " instance On");
	}
	
	//User 와 달리 여기에서는 어노테이션 기반 겟/포스트 매핑을 써보겠다.

	@PostMapping("summernoteControl")
	public String summernoteControl(HttpServletRequest request) throws Exception {
		//돌았는지 체크
		System.out.println("summernoteControl on");
		//html string 받은것 체크
		String editordata = request.getParameter("editordata");
		
		//image parse and make lists
		int imageNo = 0;
		List<String> images = new ArrayList<>();
		Document doc = Jsoup.parse(editordata);
        Elements imgElements = doc.select("img");
        
        for (Element img : imgElements) {
        	String srcAttr = img.attr("src");
            String[] parts = srcAttr.split(",");
            String imgString = parts[1];
            String filename = img.attr("data-filename");
            images.add(imgString);
            imageNo++;
            System.out.println(imageNo+filename+imgString);
        }
		
		
		
		return null;
	}
}
