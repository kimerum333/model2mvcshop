<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<dialog id="favDialog">
  <form method="dialog">
    <p><label>좋아하는 동물:
      <select>
        <option></option>
        <option>아르테미아</option>
        <option>레서판다</option>
        <option>거미원숭이</option>
      </select>
    </label></p>
    <menu>
      <button value="cancel">취소</button>
      <button id="confirmBtn" value="default">확인</button>
    </menu>
  </form>
</dialog>

<menu>
  <button id="updateDetails">상세정보 업데이트</button>
</menu>

<output aria-live="polite"></output>

<script type="text/javascript">
	var updateButton = document.getElementById('updateDetails');
	var favDialog = document.getElementById('favDialog');
	var outputBox = document.getElementsByTagName('output')[0];
	var selectEl = document.getElementsByTagName('select')[0];
	var confirmBtn = document.getElementById('confirmBtn');
	
	// “Update details” button opens the <dialog> modally
	updateButton.addEventListener('click', function onOpen() {
	  if (typeof favDialog.showModal === "function") {
	    favDialog.show();
	  } else {
	    alert("The <dialog> API is not supported by this browser");
	  }
	});
	// "Favorite animal" input sets the value of the submit button
	selectEl.addEventListener('change', function onSelect(e) {
	  confirmBtn.value = selectEl.value;
	});
	// "Confirm" button of form triggers "close" on dialog because of [method="dialog"]
	favDialog.addEventListener('close', function onClose() {
	  outputBox.value = favDialog.returnValue + " button clicked - " + (new Date()).toString();
	});
</script>

</body>
</html>