<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>OCR</title>
		<script src="<c:url value='resources/js/jquery-3.6.0.min.js'/>"></script>
		<script src="<c:url value='resources/js/ocr.js'/>"></script>
		<script type="text/javascript">
		function getResult(){
			var data = {"version":"V2","requestId":"4a264019-28e6-4979-b14c-29ff5f29ba06","timestamp":1622697063130,
						"images":[
								{"uid":"f76834abae38453bb65e43b4c2415784",
								"name":"demo",
								"inferResult":"SUCCESS",
								"message":"SUCCESS",
								"matchedTemplate":{
									"id":8678,
									"name":"sample"},
								"validationResult":{
									"result":"NO_REQUESTED"},
								"title":{"name":"logo",
										"boundingPoly":{
											"vertices":[
												{"x":33.98361,"y":39.988857},
												{"x":106.98968,"y":39.990364},
												{"x":106.99049,"y":108.994026},
												{"x":33.9847,"y":108.99292}
												]
											},
										"inferText":"",
										"inferConfidence":0.0},
								"fields":[{"name":"url","valueType":"ALL","boundingPoly":{"vertices":[{"x":375.0,"y":210.0},{"x":494.0,"y":210.0},{"x":494.0,"y":222.0},{"x":375.0,"y":222.0}]},"inferText":"www.lgservice.co.kr","inferConfidence":0.9379,"type":"NORMAL","subFields":[{"boundingPoly":{"vertices":[{"x":375.0,"y":210.0},{"x":494.0,"y":210.0},{"x":494.0,"y":222.0},{"x":375.0,"y":222.0}]},"inferText":"www.lgservice.co.kr","inferConfidence":0.9379,"lineBreak":true}]},{"name":"tel","valueType":"ALL","boundingPoly":{"vertices":[{"x":63.0,"y":407.0},{"x":150.0,"y":408.0},{"x":150.0,"y":419.0},{"x":63.0,"y":418.0}]},"inferText":"080-023-7777","inferConfidence":0.9999,"type":"NORMAL","subFields":[{"boundingPoly":{"vertices":[{"x":63.0,"y":407.0},{"x":150.0,"y":408.0},{"x":150.0,"y":419.0},{"x":63.0,"y":418.0}]},"inferText":"080-023-7777","inferConfidence":0.9999,"lineBreak":true}]}]}]}
			alert(data.image[0].fields[0].name);
			alert(data.image[0].fields[0].inferText);
			alert(data.image[0].fields[1].name);
			alert(data.image[0].fields[1].inferText);
		
		}
		
		</script>
	</head>
	<body> 
		<h2>OCR : 이미지에서 텍스트 추출</h2>
		
		<form id="ocrForm"  method="post" enctype="multipart/form-data">	         
         	파일 :  <input type="file" id="uploadFile" name="uploadFile"> 
           <input type="submit" value="결과 확인">
		</form><br><br>		
				
		<h2> OCR : 이미지에서 텍스트 추출 결과</h2>
		<div id="resultDiv"></div>
		
		<br><br>
	</body>
</html>
