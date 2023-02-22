<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.model2.mvc.common.SearchVO"%>
<%@page import="com.model2.mvc.service.user.vo.*" %>
<%@page import="com.model2.mvc.service.product.vo.*" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% // product list get 할 때 proTranCode 어떻게든 얻어서 listProdctView로 보내줘야함.%>
<%
	HashMap<String,Object> map=(HashMap<String,Object>)request.getAttribute("map");
	SearchVO searchVO=(SearchVO)request.getAttribute("searchVO");
	String menu = "not set";
	if(request.getAttribute("menu")!=null){
	menu = (String)request.getAttribute("menu");
	}
	int total=0;
	ArrayList<ProductVO> list=null;
	if(map != null){
		total=((Integer)map.get("count")).intValue();
		list=(ArrayList<ProductVO>)map.get("list");
	}
	
	int currentPage=searchVO.getPage();
	
	int totalPage=0;
	if(total > 0) {
		totalPage= total / searchVO.getPageUnit() ;
		if(total%searchVO.getPageUnit() >0)
			totalPage += 1;
	}
	System.out.println("At list view, settings : "+searchVO+menu);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>상품 목록조회</title>
<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	
	function fncGetProductList(){
		document.detailForm.submit();
	}
	
</script>

</head>
	
<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do?menu=manage" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
							<%
							if(request.getParameter("menu")!=null){
							menu = request.getParameter("menu");}
							HashMap<String,String> titles = new HashMap<String,String>();
							titles.put("search", "상품목록조회");
							titles.put("manage","상품관리");
							%>
							<%=titles.get(menu) %>
					
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
		<%
			int searchCondition = (searchVO.getSearchCondition()!=null)? Integer.parseInt(searchVO.getSearchCondition()) : 0;
		%>
			
				<option value="0"<%= (searchCondition == 0) ? "selected" : "" %>>상품번호</option>
				<option value="1"<%= (searchCondition == 1) ? "selected" : "" %>>상품명</option>
				<option value="2"<%= (searchCondition == 2) ? "selected" : "" %>>상품가격</option>
			
			</select>
			<input type="text" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px" />
		</td>
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList();">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >전체 <%=total%> 건수, 현재 <%=currentPage %> 페이지</td>
	</tr>
	
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	
	<%int no=list.size();%>
	<%for(int i=0; i<list.size(); i++) {
			ProductVO vo = (ProductVO)list.get(i);%>
	
	<tr class="ct_list_pop">
		<td align="center"><%=no--%></td>
		<td></td>
				
			  <%//어떤 매뉴로 들어왔느냐에 따라 상품구입 / 상품정보수정을 갈라서 보내준다.
			    HashMap<String,String> nextLocation = new HashMap<String,String>();
				nextLocation.put("search", "/getProduct.do?prodNo="+vo.getProdNo()+"&menu=search");
				nextLocation.put("manage", "/updateProductView.do?prodNo="+vo.getProdNo()+"&menu=manage");%>
				<td align="left"><a href="<%=nextLocation.get(menu)%>"> <%=vo.getProdName() %> </a></td>
		
		<td></td>
		<td align="left"><%=vo.getPrice() %></td>
		<td></td>
		<%String date = vo.getManuDate(); %>
		<%String goodDate = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6); %>
		<td align="left"><%=goodDate %></td>
		<td></td>
		<td align="left">
			<%//이곳에 판매 상태에 따른 proTranCode update 버튼을 놓겠다. %>
			<%String proTranCode = vo.getProTranCode(); %>
			<%if(proTranCode==null){//판매중임 %>
				판매중
			<%} %>
			<%String userRole = ((UserVO)session.getAttribute("user")).getRole();//session으로부터 받아온 User의 Role %>
			<%if(proTranCode!=null&&proTranCode.equals("sld")){//팔렸음 %>
				판매완료
				
				<%if(userRole.equals("admin")){//로그인중인 사람이 판매자임 %>
					<a href="/updateTranCodeByProd.do?prodNo=<%=vo.getProdNo()%>&proTranCode=sld"> 배송하기 </a>
				<%} %>
			<%} %>
			
			<%if(proTranCode!=null&&proTranCode.equals("del")){//배송중임 %>
				배송중
				<%if(userRole.equals("user")){//로그인중인 사람이 고객임 %>
					<a href="/updateTranCodeByProd.do?prodNo=<%=vo.getProdNo()%>&proTranCode=del"> 도착확인 </a>
				<%} %>
			<%} %>
			<%if(proTranCode!=null&&proTranCode.equals("don")){//도착완료 %>
				배송완료상태
			<%} %>
		
		</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	<% }//end of for %>
	
	
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		<%for(int i=1;i<=totalPage;i++){%>
			<a href="/listProduct.do?page=<%=i%>&menu=<%=menu%>"> <%=i %> </a>
		<%}%>
			
		
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>

</body>
</html>