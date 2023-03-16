<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/thumbnail.css" type="text/css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css" type="text/css">
<meta charset="EUC-KR">
<title>listProductThumbnail</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">

var currentPage = 1;

$(function(){
	$("#testButton").on("click",function(){
		console.log("testButton working");
		needCurrentPage();
	});
});

function nextPageMaker(data, status){
	console.log("start of nextPageMaker()");
	console.log(JSON.stringify(data));
	let productList = data.list;
	let i = 0;
	console.log(productList[i]);
}

function needCurrentPage(){
	console.log("start of needCurrentPage()");
	console.log("currentPage is "+currentPage);
	$.ajax(
		{
	   		url: "/product/json/listProduct/"+currentPage, 
	    	method: "GET",   
	    	dataType: "json" ,
	    	headers : {
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},
			success : (data,status)=>nextPageMaker(data,status)
		}//end of ajax arg
	)//end of ajax
	console.log("end of needCurrentPage()");
}//end of needCurrentPage



	

</script>
</head>
<body>
	<div class="container mt-100">
       <div class="row">
          <div class="col-md-4 col-sm-6">
            <div class="card mb-30"><a class="card-img-tiles" href="#" data-abc="true">
                <div class="inner">
                  <div class="main-img"><img src="https://i.imgur.com/O0GMYuw.jpg" alt="Category"></div>
                  <div class="thumblist"><img src="https://i.imgur.com/ILEU18M.jpg" alt="Category"><img src="https://i.imgur.com/2kePJmX.jpg" alt="Category"></div>
                </div></a>
              <div class="card-body text-center">
                <h4 class="card-title">Laptops</h4>
                <p class="text-muted">Starting from $499</p><a class="btn btn-outline-primary btn-sm" href="#" data-abc="true">View Products</a>
              </div>
            </div>
          </div>
          <div class="col-md-4 col-sm-6">
            <div class="card mb-30"><a class="card-img-tiles" href="#" data-abc="true">
                <div class="inner">
                  <div class="main-img"><img src="https://i.imgur.com/uRgdVY1.jpg" alt="Category"></div>
                  <div class="thumblist"><img src="https://i.imgur.com/VwSKS7A.jpg" alt="Category"><img src="https://i.imgur.com/gTvZ2H5.jpg" alt="Category"></div>
                </div></a>
              <div class="card-body text-center">
                <h4 class="card-title">Mobiles</h4>
                <p class="text-muted">Starting from $50</p><a class="btn btn-outline-primary btn-sm" href="#" data-abc="true">View Products</a>
              </div>
            </div>
          </div>
          <div class="col-md-4 col-sm-6">
            <div class="card mb-30"><a class="card-img-tiles" href="#" data-abc="true">
                <div class="inner">
                  <div class="main-img"><img src="https://i.imgur.com/0jO40CF.jpg" alt="Category"></div>
                  <div class="thumblist"><img src="https://i.imgur.com/dWYAg41.jpg" alt="Category"><img src="https://i.imgur.com/5oQEZSC.jpg" alt="Category"></div>
                </div></a>
              <div class="card-body text-center">
                <h4 class="card-title">Accessories</h4>
                <p class="text-muted">Starting from $9</p><a class="btn btn-outline-primary btn-sm" href="#" data-abc="true">View Products</a>
              </div>
            </div>
          </div>
        </div>
        <input id="testButton" type="button" value="로드"/>
        </div>
</body>
</html>