<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.model2.mvc.common.SearchVO"%>
<%@page import="com.model2.mvc.service.user.vo.*" %>
<%@page import="com.model2.mvc.service.product.vo.*" %>
<%@page import="com.model2.mvc.service.purchase.vo.*" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	HashMap<String,Object> map=(HashMap<String,Object>)request.getAttribute("map");
	SearchVO searchVO=(SearchVO)map.get("searchVO");

	int total=0;
	ArrayList<PurchaseVO> list=null;
	if(map != null){
		total=((Integer)map.get("totalCount")).intValue();
		list=(ArrayList<PurchaseVO>)map.get("list");
	}
	
	int currentPage=searchVO.getPage();
	//
	
	int totalPage=0;
	if(total > 0) {
		totalPage= total / searchVO.getPageUnit() ;
		if(total%searchVO.getPageUnit() >0)
			totalPage += 1;
	}
	System.out.println("At list view, settings : "+searchVO);
	//변수
	//list = 구매정보가 담긴 리스트
	//totalCount = 총 검색 건수가 담긴 인트
	//currentPage = 현재 페이지수가 담긴 정보. 기본값은 1이고 다른 페이지를 클릭해서 추가 정보를 열람하기 위해 사용.
	//totalPage = 총 페이지수.
%>
<!DOCTYPE html>
<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetList() {
		document.detailForm.submit();
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/listUser.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">전체 <%=total %> 건수, 현재 <%=currentPage %> 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">전화번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">정보수정</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	<%for(int i=0;i<list.size();i++){ %>
		<%PurchaseVO thisPurchase = list.get(i); %>
	
	<tr class="ct_list_pop">
		<td align="center">
			<a href="/getPurchase.do?tranNo=<%=thisPurchase.getTranNo()%>"><%=list.size()-i%></a>
		</td>
		<td></td>
		<td align="left">
			<a href="/getUser.do?userId=<%=thisPurchase.getBuyer().getUserId()%>"><%=thisPurchase.getBuyer().getUserId()%></a>
		</td>
		<td></td>
		<td align="left"><%=((UserVO)session.getAttribute("user")).getUserName()%></td>
		<td></td>
		<td align="left">
			<%=((UserVO)session.getAttribute("user")).getPhone()%>
		</td>
		<td></td>
		<td align="left">
			<%if(thisPurchase.getTranCode().equals("sld")){%>
				구매완료. 배송을 기다리는 중.
			<%}%>
			<%if(thisPurchase.getTranCode().equals("del")){%>
				상품이 배송되었습니다.
			<%}%>
			<%if(thisPurchase.getTranCode().equals("don")){%>
				상품을 받았습니다.
			<%}%></td>
		<td></td>
		<td align="left">
			<%if(thisPurchase.getTranCode().equals("del")){%>
				<a href="updateTranCode.do?prodNo=<%=thisPurchase.getPurchaseProd().getProdNo()%>">배송을 받았습니다.</a>
			<%}%>
		</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	<%}//end of for %>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
		 
		 	<%for(int i=0;i<totalPage;i++){ %>
		 
			<a href="/listPurchase.do?page=<%=i+1%>"><%=i+1%></a> 
		 
			<%} %>		
		</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>