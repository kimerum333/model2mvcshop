<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%User loginUser = (User)session.getAttribute("user"); %>
<%Map map = (Map)request.getAttribute("map"); %>
<%Integer total = (Integer)map.get("total"); %>
<%List<Purchase> purchaseList = (List<Purchase>)map.get("purchaseList"); %>
<%int pageSize = 3; %>
<%
int currentPage = 1;
if(request.getParameter("currentPage")!=null){
	currentPage=Integer.parseInt(request.getParameter("currentPage"));
}
System.out.println(currentPage+"가 현재 설정된 페이지입니다.");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
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

<form name="detailForm" action="/listPurchase.do" method="post">

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
		<td colspan="11">전체 <%=total%> 건수, 현재 <%=currentPage %> 페이지</td>
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

	<%for(int i=0;i<purchaseList.size();i++){ %>
		<%Purchase purchase = purchaseList.get(i); %>
		<tr class="ct_list_pop">
			<td align="center">
				<a href="/getPurchase.do?tranNo=<%=purchase.getTranNo()%>"><%=i+1%></a>
			</td>
			<td></td>
			<td align="left">
				<a href="/getUser.do?userId=<%=loginUser.getUserId()%>"><%=loginUser.getUserId()%></a>
			</td>
			<td></td>
			<td align="left"><%=loginUser.getUserName()%></td>
			<td></td>
			<td align="left"><%=loginUser.getUserId()%></td>
			<td></td>
			<td align="left">현재
					
					<%if(purchase.getTranCode().equals("sld")){ %>
						구매완료. 판매자의 배송을 기다리는
					<%} %>
					<%if(purchase.getTranCode().equals("del")){ %>
						배송중
					<%} %>
					<%if(purchase.getTranCode().equals("don")){ %>
						배송완료
					<%} %>
					상태 입니다.</td>
			<td></td>
			<td align="left">
				
			</td>
		</tr>
		<tr>
			<td colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>
	<%}//end of for %>
	
</table>

<!-- PageNavigation Start... -->
<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
	<tr>
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value=""/>
	
			<jsp:include page="../common/pageNavigator.jsp"/>	
			
    	</td>
	</tr>
</table>
<!-- PageNavigation End... -->


</form>

</div>

</body>
</html>