<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
	#voiceText{
		width:100%;
		height:200px;
	}
</style>
<script>
	$(function(){
		$("#voiceBtn").click(function(){
			let xhr = new XMLHttpRequest();
			
			xhr.responseType = "blob"; // 응답받는 데이터 타입
			
			// 응답받은 경우 실행하는 곳
			xhr.onload = function(){
				var audioURL = URL.createObjectURL(this.response);
				var audio = new Audio();
				audio.src = audioURL;
				audio.play(); // 재생
			};
			
			// 서버 매핑
			xhr.open("post", "/clova/voiceOk");
			xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			xhr.send("text="+$("#voiceText").val());
		});
	});
</script>
</head>
<body>
	<h2>CLOVA Voice</h2>
	<p>Premium API는 음성으로 변환할 텍스트와 음색, 속도, 감정 등을 파라미터로 입력받아 음성을 합성하여 그 결과를 반환하는 HTTP 기반의 REST API입니다.</p>
	
	<textarea id="voiceText">
스테판 윙켈만(Stephan Winkelmann) 오토모빌리 람보르기니의 회장 겸 CEO는 “우루스 퍼포만테는 람보르기니 슈퍼 SUV 우루스의 최고 성능과 개성 있는 외관을 한 차원 향상시킨 모델로, 
모든 주행 환경에서 가장 매력적인 운전 경험을 제공할 것이다. 새롭게 출시된 우루스 퍼포만테는 우루스의 다재다능하고 럭셔리한 매력을 유지하면서도, 드라이빙 다이내믹스를 발전시키기 위해 새롭게 디자인된 매력적인 외관에 차별점을 뒀다.”고 말했다. 
그는 “람보르기니는 브랜드의 디자인 DNA와 기술적 재능을 바탕으로 세계 최초의 슈퍼 SUV우루스를 출시했고, 우루스는 출시와 동시에 슈퍼 SUV의 기준을 세웠다. 
그 뒤를 잇는 우루스 퍼포만테는 SUV 세그먼트의 기준을 다시 한번 높일 것이다.”고 덧붙였다. 우루스 퍼포만테는 첫 공개 직전 파이크스 피크 인터내셔널 힐 클라임(Pikes Peak International Hill Climb) 
레이스에서 이전 2018년에 설정된 최고 기록인 10분 49초 902를 10분 32.064초로 단축하며 양산 SUV 부문의 새로운 기록을 달성하며 그 놀라운 성능을 증명한 바 있다.
	</textarea><br/>
	<input type="button" value="음성으로 변환하기" id="voiceBtn"/>
	
</body>
</html>