<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
</style>
<script>
	$(function(){
		$(document).on('submit','#csrForm',function(){
			event.preventDefault();
			var filename = $("#audio").val();
			if(filename!=""){
				// mp3, aac, ac3, ogg, flac, wav
				let point = filename.lastIndexOf(".");
				let extension = filename.substring(point+1);
				
				if(extension=='mp3'||extension=='aac'||extension=='ac3'||extension=='ogg'||extension=='flac'||extension=='wav'){
					
					var formObj = new FormData($("#csrForm")[0]);
					var url="/clova/csr_speech_ok";
					
					$.ajax({
						type:'post',
						async:false,
						processData:false,
						contentType:false,
						data:formObj,
						dataType:"text",
						url:url,
						success:function(result, status){
							console.log(result);
							console.log(status);
							
							$("#csrResult").val(result);
							
							var jsonObject = JSON.parse(result);
							$("#csrTxt").html(jsonObject.text);
							
						},error:function(e){
							console.log(e.responseText);
						}
					});
					
				}else{
					alert("지원하는 확장자의 음성파일이 아닙니다.");
					return false;
				}
			}else{
				alert("음성파일을 선택하세요.");
				return false;
			}			
		});
	});
</script>
</head>
<body>
	<h2>가장 뛰어난 한국어 음성 인식률을 가진 음성 인식 API</h2>
	<p>
	사람의 목소리를 인식하여 작동하는 비서 애플리케이션, 챗봇, 음성 메모 등의 서비스를 만들 때 활용할 수 있는 음성 인식 API 서비스입니다.
	 음성 데이터는 API를 통해 CLOVA Speech Recognition(CSR) 엔진으로 전송되며, 해당 음성 데이터를 인식해서 텍스트로 변환하여 전달해줍니다.
	</p>
	<form method="post" enctype="multipart/form-data" id="csrForm">
		음성파일 선택 : <input type="file" name="audio" id="audio"/>
		<input type="submit" value="시작"/>
	</form>
	<textarea id="csrResult" style="width:600px; height:100px;"></textarea>
	<div id="csrTxt" style="border:1px solid red"></div>
</body>
</html>