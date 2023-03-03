<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>상품 목록조회</title>
<link rel="stylesheet" href="/css/admin.css" type="text/css">

<!-- CDN(Content Delivery Network) 호스트 사용 -->
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	// 검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScrpt 이용  
	function fncGetList(currentPage) {
		$("#currentPage").val(currentPage);
	   	//document.detailForm.submit();	
	   	//method의 어트리뷰트를 세팅, 
	   	$("form").attr("method","POST").attr("action","/product/listProduct").submit();
	}
	
	//init
	 $(function() {
		 
		 	//확인용 디버그
		 	//<input type="hidden" name="menu" value="${param.menu}" />
		 	alert($("input[name='menu']").val());
		 	alert($("input:contains('menu')").val());
		 	 
		 
			//==> 검색 Event 연결처리부분
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			//==> 1 과 3 방법 조합 : $("tagName.className:filter함수") 사용함. 
			$( "td.ct_btn01:contains('검색')" ).on("click" , function() {
				//Debug..
				//alert(  $( "td.ct_btn01:contains('검색')" ).html() );
				fncGetList(1);
			});
			
			
			//상품명을 붉게 물들이는 태그. 
			$( ".ct_list_pop td:nth-child(3)" ).css("color" , "red");
			$("h7").css("color" , "red");
			
			//pagesize 가 3이기 때문에 4의 배수로 둬야 번갈아가면서 흰검이 이어진다.
			$(".ct_list_pop:nth-child(4n+6)" ).css("background-color" , "whitesmoke");	
			
			//==> prodNo LINK Event 연결처리 (manage 냐 search냐에 따라)
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			//==> 3 과 1 방법 조합 : $(".className tagName:filter함수") 사용함.
			$( ".ct_list_pop td:nth-child(3)" ).on("click" , function() {
					//Debug..
					//alert(  $( this ).text().trim() );
					//self.location ="/user/getUser?userId="+$(this).text().trim();
					/* if($("input[name='menu']")){
						
					}
					if(){
						
					} */
					
			});
			
			
	 }//end of init func
</script>

</head>
	
<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/product/listProduct" method="post">
<input type="hidden" name="menu" value="${param.menu}" />

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
						<c:if test="${param.menu eq 'manage'}">판매상품관리</c:if>
						<c:if test="${param.menu eq 'search'}">상품조회</c:if>	
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
			
				<option value="0"${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>상품번호</option>
				<option value="1"${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>상품명</option>
				<option value="2"${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>상품가격</option>
			
			</select>
			<input type="text" name="searchKeyword" value="${search.searchKeyword}" class="ct_input_g" style="width:200px; height:19px" />
		</td>
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetList(${empty resultPage.currentPage? "1" : resultPage.currentPage});">검색</a>
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
		<td colspan="11" >전체 ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage } 페이지</td>
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
	
	<c:set var="i" value="0"/>
	<c:forEach var="product" items="${list}">
		<c:set var="i" value="${i+1}"/>
		<tr class="ct_list_pop">
			<td align="center">${i}</td>
			<td></td>
			<c:set var="nextLocation" value="/product/getProduct"/>
			<c:if test="${param.menu eq 'search'}">
				<c:set var="nextLocation" value="/product/getProduct"/>
			</c:if>
			<c:if test="${param.menu eq 'manage'}">
				<c:set var="nextLocation" value="/product/updateProduct"/>
			</c:if>
			
			<td align="left"><a href="${nextLocation}?prodNo=${product.prodNo}&menu=${param.menu}"> ${product.prodName}${product.prodNo} </a></td>
			
			<td></td>
			<td align="left">${product.price}</td>
			<td></td>
			<td align="left">${product.regDate}</td>
			<td></td>
			<td align="left">
			
			<c:if test="${empty product.proTranCode}"> 판매중 </c:if>
			<c:if test="${product.proTranCode eq 'sld'}"> 판매완료 
				<c:if test="${menu eq 'manage'}"> <a href="/purchase/updateProTranCode?proTranCode=sld">배송시작</a></c:if>		
			</c:if>
			<c:if test="${product.proTranCode eq 'del'}"> 배송중
				<c:if test="${menu eq 'search'}"> <a href="/purchase/updateProTranCode?proTranCode=del">상품받기</a></c:if>	
			</c:if>
			<c:if test="${product.proTranCode eq 'don'}"> 배송완료	
			</c:if>
			</td>	
		</tr>
		<tr>
			<td colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>
	<%//TODO 여기에 판매관리 구현해야함 %>	
	</c:forEach>
	
	
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	
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