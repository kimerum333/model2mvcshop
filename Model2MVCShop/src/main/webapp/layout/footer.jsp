<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>A6's Library</title>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
  <style type="text/css">
    #my-dialog {
      display: none;
      position: absolute;
      z-index: 9999;
      background-color: #fff;
      border: 1px solid #ccc;
      padding: 10px;
    }
    #my-dialog .dialog-handle {
      cursor: move;
    }
    #my-dialog .close-button {
      float: right;
      cursor: pointer;
    }
  </style>
</head>
<body>

<div class="btn-group dropup">
  <button id="dropupbutton" type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
    Dropup
  </button>
  
  <ul class="dropdown-menu">
    <li>
    	<button id="show-dialog-button">메모 보기</button>
    </li>
    <li>open memo 2</li>
    <li>open memo 3</li>
  </ul>
</div>

<div id="my-dialog">
    		<div class="dialog-handle">메모 제목</div>
    		<div class="close-button">x</div>
    		<p>This is my dialog content.</p>
    		<form method="post" action="/product/summernoteControl">
  				<textarea id="summernote" name="editordata"></textarea>
  				<input type="submit" value="써브밋">
			</form>
</div>
<script type="text/javascript">
    $(function() {
      // Make the dialog draggable
      $("#my-dialog").draggable({
        handle: ".dialog-handle"
      });
      // Show the dialog when the button is clicked
      $("#show-dialog-button").click(function() {
          var dialogWidth = $("#my-dialog").outerWidth();
          var buttonPos = $("#dropupbutton").offset();
          var buttonWidth = $("#dropupbutton").outerWidth();
          var buttonHeight = $("#dropupbutton").outerHeight();
          var dialogLeft = buttonPos.left + buttonWidth;
          var dialogTop = buttonPos.top + buttonHeight - $("#my-dialog").outerHeight();
          $("#my-dialog").css({
            left: dialogLeft,
            top: dialogTop
          }).show();
        });
      // Close the dialog when the close button is clicked
      $("#my-dialog .close-button").click(function() {
        $("#my-dialog").hide();
      });
    });
    $(document).ready(function() {
    	  $('#summernote').summernote();
    });	
  </script>
</body>
</html>