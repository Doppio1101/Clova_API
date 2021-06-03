/**
 * 
 */
alert('outside jsfile');
function sendWords(){
	words = $("#words").val();//document.getElementById("words").value;
	//var가 빠져도 동작함? 왜?
	alert(words);
	/*$.ajax({
		type: "get",
		url:"/spring-ai/nmt1",
		contentType: "application/json",
		data:{"words":$("#words").val()},
		success: function (data,textStatus){
			alert(data);
		},
		error: function(data,textStatus) {
			alert("에러가 발생했습니다!");
		}
		complet: function(data,textStatus) {
			
		}
	});*/
	$.ajax({
        type:"get",
        url:"/spring-ai/nmt1",
        //보낼 곳
        contentType: "application/json",
        //json방식으로 보내겠다
        data :{"words":$("#words").val()},
      	//json을 string방식으로 보내겠다
     success:function (data,textStatus){
    	 alert(data);
    	 resultText = JSON.parse(data);
    	 text = resultText.message.result.translatedText;
    	 alert(text);
    	 $('#message').text(text);
     },
     error:function(data,textStatus){
        alert("에러가 발생했습니다.");
     },
     complete:function(data,textStatus){
     }
  });
}