<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.model2.mvc.common.SearchVO"%>
<%@page import="com.model2.mvc.service.user.vo.*" %>
<%@page import="com.model2.mvc.service.product.vo.*" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% // product list get �� �� proTranCode ��Ե� �� listProdctView�� ���������.%>
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
<title>��ǰ �����ȸ</title>
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
							titles.put("search", "��ǰ�����ȸ");
							titles.put("manage","��ǰ����");
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
			
				<option value="0"<%= (searchCondition == 0) ? "selected" : "" %>>��ǰ��ȣ</option>
				<option value="1"<%= (searchCondition == 1) ? "selected" : "" %>>��ǰ��</option>
				<option value="2"<%= (searchCondition == 2) ? "selected" : "" %>>��ǰ����</option>
			
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
						<a href="javascript:fncGetProductList();">�˻�</a>
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
		<td colspan="11" >��ü <%=total%> �Ǽ�, ���� <%=currentPage %> ������</td>
	</tr>
	
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">�������</td>	
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
				
			  <%//� �Ŵ��� ���Դ��Ŀ� ���� ��ǰ���� / ��ǰ���������� ���� �����ش�.
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
			<%//�̰��� �Ǹ� ���¿� ���� proTranCode update ��ư�� ���ڴ�. %>
			<%String proTranCode = vo.getProTranCode(); %>
			<%if(proTranCode==null){//�Ǹ����� %>
				�Ǹ���
			<%} %>
			<%String userRole = ((UserVO)session.getAttribute("user")).getRole();//session���κ��� �޾ƿ� User�� Role %>
			<%if(proTranCode!=null&&proTranCode.equals("sld")){//�ȷ��� %>
				�ǸſϷ�
				
				<%if(userRole.equals("admin")){//�α������� ����� �Ǹ����� %>
					<a href="/updateTranCodeByProd.do?prodNo=<%=vo.getProdNo()%>&proTranCode=sld"> ����ϱ� </a>
				<%} %>
			<%} %>
			
			<%if(proTranCode!=null&&proTranCode.equals("del")){//������� %>
				�����
				<%if(userRole.equals("user")){//�α������� ����� ���� %>
					<a href="/updateTranCodeByProd.do?prodNo=<%=vo.getProdNo()%>&proTranCode=del"> ����Ȯ�� </a>
				<%} %>
			<%} %>
			<%if(proTranCode!=null&&proTranCode.equals("don")){//�����Ϸ� %>
				��ۿϷ����
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
<!--  ������ Navigator �� -->

</form>

</div>

</body>
</html>