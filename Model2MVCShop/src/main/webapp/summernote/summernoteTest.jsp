	<%@ page language="java" contentType="text/html; charset=EUC-KR"
	    pageEncoding="EUC-KR"%>
	<!DOCTYPE html>
	<html>
	<head>
	
		<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	
	<meta charset="EUC-KR">
	<title>Insert title here</title>
	</head>
	<body>
	
	<form method="post" action="/product/summernoteTest">
	  <textarea id="summernote" name="editordata"></textarea>
	  <input type="submit" value="써밋!!">
	</form>
	
	
	<!-- include libraries(jQuery, bootstrap) -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<!-- include summernote css/js -->
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	
	<script type="text/javascript">
	$(document).ready(function() {
		  $('#summernote').summernote();
		});
	</script>
	
	
	</body>
	</html>