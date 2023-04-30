<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>A6's Library</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="//code.jquery.com/ui/1.13.0/jquery-ui.min.js"></script>
</head>
<body>

<header class="p-3 text-bg-dark">
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <a href="/index.jsp" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
          <img src="/images/logo2.png" width="75px"/>
        </a>

        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li><a href="/index.jsp" class="nav-link px-2 text-secondary">Home</a></li>
          <li><a href="/user/getUser?userId=" class="nav-link px-2 text-white">개인정보조회</a></li>
          <li><a href="https://getbootstrap.com/docs/5.3/examples/headers/#" class="nav-link px-2 text-white">Products</a></li>
          <li><a href="https://getbootstrap.com/docs/5.3/examples/headers/#" class="nav-link px-2 text-white">FAQs</a></li>
          <li><a href="https://getbootstrap.com/docs/5.3/examples/headers/#" class="nav-link px-2 text-white">About</a></li>
        </ul>

        <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
          <input type="search" class="form-control form-control-dark text-bg-dark" placeholder="Search..." aria-label="Search">
        </form>

        <div class="text-end">
          <c:if test="${empty user}">
          	<button type="button" class="btn btn-outline-light me-2" onclick="location.href='/user/loginView.jsp'">Login</button>
          </c:if>
          <c:if test="${!empty user}">
          	<button type="button" class="btn btn-outline-light me-2" onclick="location.href='/user/logout'">Logout</button>
          </c:if>
          <button type="button" class="btn btn-warning" onclick="location.href='/user/addUserView.jsp'">Sign-up</button>
        </div>
      </div>
    </div>
  </header>
</body>
</html>