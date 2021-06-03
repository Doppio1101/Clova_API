<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>translate</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="resources/js/nmt.js"></script>
<script type="text/javascript">
function checkResult(){
	temp = {"message":
				{"@type":"response",
				"@service":"naverservice.nmt.proxy",
				"@version":"1.0.0",
				"result":
					{"srcLangType":"ko",
					"tarLangType":"en",
					"translatedText":"It's a pleasure to meet you."}}};
	alert(temp.message.result.translatedText);
}


</script>

</head>
<body>
<h1>번역 페이지</h1>
<form action="" method="get">
	번역할 문장:<input type = "text" name="" id ="words">
	<input type="button" value="확인" onclick="sendWords()">
	<input type="button" value="체크" onclick="checkResult()">


</form>
번역된 문장: <span id="message"></span>
</body>
</html>