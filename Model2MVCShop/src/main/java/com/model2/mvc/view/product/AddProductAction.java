package com.model2.mvc.view.product;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;



public class AddProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {

		if(FileUpload.isMultipartContent(request)) {
			String temDir="C:\\workspace\\01.Model2MVCShop(str)\\src\\main\\webapp\\images\\uploadFiles\\";
			DiskFileUpload fileUpload = new DiskFileUpload();
			fileUpload.setRepositoryPath(temDir);
			//setSizeThresholder 크기를 벗어나면 지정한 위치에 임시로 저장.
			
			fileUpload.setSizeMax(1024*1024*10);
			//최대 10메가 업로드
			fileUpload.setSizeThreshold(1024*100);
			
			if(request.getContentLength()<fileUpload.getSizeMax()) {
				ProductVO productVO = new ProductVO();
				StringTokenizer token = null;
				
				//parseRequest 는 FileItem을 포함하고 있는 List타입을 리턴한다.
				List fileItemList = fileUpload.parseRequest(request);
				int size = fileItemList.size();
				for(int i=0;i<size;i++) {
					FileItem fileItem = (FileItem)fileItemList.get(i);
					//isFormField를 통해서 파일형식인지 파라매터인지를 구분한다. 파라매터라면 true
					if(fileItem.isFormField()) {
						if(fileItem.getFieldName().equals("manuDate")) {
							token = new StringTokenizer(fileItem.getString("euc-kr"),"-");
							String manuDate = token.nextToken()+token.nextToken()+token.nextToken();
							productVO.setManuDate(manuDate);
						}
						else if(fileItem.getFieldName().equals("prodName")) {
							
						}
					}
				}
			}
			
		}
		
		
		
		
		ProductVO productVO=new ProductVO();
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		String manuDate = request.getParameter("manuDate");
		productVO.setManuDate(manuDate.replaceAll("-", ""));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		
		System.out.println(productVO);
		
		ProductService service=new ProductServiceImpl();
		
		//세션에 박기
		HttpSession session = request.getSession(true);
		session.setAttribute("vo", service.addProduct(productVO)); //에드했음
		return "redirect:/product/afterAddProductView.jsp";
	}
}