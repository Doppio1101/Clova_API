/**
 * stt.js
 */
 
 $(function(){
	$('#sttForm').on('submit', function(event){
		event.preventDefault(); //submit 후에  reload 안 되게
		formData = new FormData($('#sttForm')[0]);
		
		$.ajax({
			type:"post",
			enctype:"multipart/form-data",
			contentType: "application/json; charset:UTF-8",
			url:"/spring-ai/clovaSTT",
			data:formData,
			processData:false, //필수
			contentType:false, //필수
			success:function(result){
				
				
				data = JSON.parse(result);
				alert(data.text);
				$('#resultDiv').text(data.text);
			},
			error:function(e){
				alert("에러 발생 : " + e);
			}			
		});
	});
	
});