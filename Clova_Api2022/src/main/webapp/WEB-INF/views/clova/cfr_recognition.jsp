<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
	<style>
	</style>
	<script>
		$(function(){
			$("#cfrForm").submit(function(){
				event.preventDefault(); // 기본 이벤트 제거
				
				if($("#image").val()==""){
					alert("이미지를 선택하세요.");
					return false;
				}else{
					// 이미지파일이 포함된 폼을 객체로 만들어 서버로 전송한다.
					var form = $("#cfrForm")[0];
					var data = new FormData(form);
					
					$.ajax({
						type:"post",
						dataType:"text",
						url:"/cfrOk",
						async:false,
						processData:false,
						contentType:false,
						data: data,
						success:function(result){
							$("#txt").val(result);
							// 문자열을 json으로 변환해준다.
							var jsonData = JSON.parse(result);
							var gender = jsonData.faces[0].gender.value;
							$("#view").html(gender);
							console.log(jsonData);
							var tag = "<h2>폭="+ jsonData.info.size.width+",높이="+jsonData.info.size.height+"</h2>";
							
							tag += "<table class='table table-striped'>";
							tag += "<tr><td>번호</td><td>나이</td><td>성별</td><td>눈(좌)</td><td>눈(우)</td>";
							tag += "<td>코</td><td>입(좌)</td><td>입(오)</td><td>이모션</td><td>포즈</td></tr>"
							
							// 얼굴 수 만큼 반복문 실행
							// faces : [{},{},{}]	   위치정보,index
							jsonData.faces.map(function(face, idx){
								tag += "<tr><td>"+(idx+1)+"</td>";
								tag += "<td>"+face.age.value+"</td>"; // 나이
								if(face.gender.value=='male'){ // 성별
									tag += "<td>남</td>"
								}else{
									tag += "<td>여</td>"
								}
								
								if(face.landmark==null){ // landmark : 눈, 코, 입
									tag += "<td></td><td></td><td></td><td></td><td></td>";
								}else{
									tag += "<td>"+face.landmark.leftEye.x+","+face.landmark.leftEye.y+"</td>"; // 왼쪽눈
									tag += "<td>"+face.landmark.rightEye.x+","+face.landmark.rightEye.y+"</td>"; // 오른쪽눈
									tag += "<td>"+face.landmark.nose.x+","+face.landmark.nose.y+"</td>"; // 코
									tag += "<td>"+face.landmark.leftMouth.x+","+face.landmark.leftMouth.y+"</td>"; // 왼쪽입
									tag += "<td>"+face.landmark.rightMouth.x+","+face.landmark.rightMouth.y+"</td>"; // 오른쪽입									
								}
								tag += "<td>"+face.emotion.value+"</td>"; // 이모션
								tag += "<td>"+face.pose.value+"</td></tr>";// 포즈
							});
							tag += "</table>";
							
							$("#view").html(tag);
						},error:function(e){
							console.log(e.responseText);
						}
					});
				}
				
			});
		});	
	</script>
	</head>
<body>
	<div>
		<h1>얼굴과 관련된 다양한 정보를 제공하는 얼굴 인식 API</h1>
		<p>입력된 비전 데이터를 통해 얼굴을 인식하거나 얼굴 감지를 이용한 애플리케이션을 만들 때 유용한 API 서비스입니다.
		 이미지 속의 얼굴과 가장 닮은 유명인을 찾거나, 얼굴의 윤곽과 눈/코/입 위치, 표정 값을 얻을 수 있습니다.</p>
		 
		 <form method="post" enctype="multipart/form-data" id="cfrForm">
		 	이미지파일선택 : <input type="file" id="image" name="image"/><br/>
		 	<button id="cfr">확인하기(submit)</button>
		 	<input type="submit" value="Ok"/>
			<input type="image" src=""/>
		 </form>
		 <hr/>
		 <textarea id="txt" rows="20" cols="100"></textarea>
	</div>
	<div id="view">
		
	</div>
</body>
</html>