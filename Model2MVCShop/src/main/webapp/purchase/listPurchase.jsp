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
	//����
	//list = ���������� ��� ����Ʈ
	//totalCount = �� �˻� �Ǽ��� ��� ��Ʈ
	//currentPage = ���� ���������� ��� ����. �⺻���� 1�̰� �ٸ� �������� Ŭ���ؼ� �߰� ������ �����ϱ� ���� ���.
	//totalPage = �� ��������.
%>
<!DOCTYPE html>
<html>
<head>
<title>���� �����ȸ</title>

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
					<td width="93%" class="ct_ttl01">���� �����ȸ</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">��ü <%=total %> �Ǽ�, ���� <%=currentPage %> ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ��ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��ȭ��ȣ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����Ȳ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��������</td>
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
				���ſϷ�. ����� ��ٸ��� ��.
			<%}%>
			<%if(thisPurchase.getTranCode().equals("del")){%>
				��ǰ�� ��۵Ǿ����ϴ�.
			<%}%>
			<%if(thisPurchase.getTranCode().equals("don")){%>
				��ǰ�� �޾ҽ��ϴ�.
			<%}%></td>
		<td></td>
		<td align="left">
			<%if(thisPurchase.getTranCode().equals("del")){%>
				<a href="updateTranCode.do?prodNo=<%=thisPurchase.getPurchaseProd().getProdNo()%>">����� �޾ҽ��ϴ�.</a>
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

<!--  ������ Navigator �� -->
</form>

</div>

</body>
</html>