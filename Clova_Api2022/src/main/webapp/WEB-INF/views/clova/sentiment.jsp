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
	var sentiKor = {
			neutral : '중립',
			positive : '긍정',
			negative : '부정'
	}
	// sentiKor.neutral
	// sentiKor[neutral]

	$(function(){
		$("#sentiBtn").click(function(){
			
			$.ajax({
				type:"post",
				dataType:"text",
				async:false,
				url:"/clova/sentimentOk",
				data:{
					content:$("#content").val()
				},
				success:function(result){
					
					console.log(result);
					var jsonData = JSON.parse(result);
					console.log(jsonData)
					
					var tag = "<h2>감정분석결과 : " + sentiKor[jsonData.analVO.sentiment] + "("+ jsonData.analVO.sentiment +")</h2>";
					
					$("#sentiResult").append(tag);
					
					tag = "<h3>(중립 : " + jsonData.analVO.neutral + "%, 긍정 : " + jsonData.analVO.positive + "%, 부정 : " + jsonData.analVO.negative + "%)</h3>";
					$("#sentiResult").append(tag);
					
					tag = "<table class = 'table table-hover'><tr><td>번호</td><td>문장</td><td>감정분석</td><td>중립(%)</td><td>긍정</td><td>부정</td></tr>";
					
					jsonData.list.map(function(vo, idx){
						tag += "<tr><td>" + (idx+1) + "</td>";
						tag += "<td>" + vo.content + "</td>";
						tag += "<td>" + sentiKor[vo.sentiment] + "</td>";
						tag += "<td>" + vo.neutral + "</td>";
						tag += "<td>" + vo.positive + "</td>";
						tag += "<td>" + vo.negative + "</td></tr>";
					});
					
					$("#sentiResult").append(tag);
					
					
				},error:function(e){
					console.log(e.responseText);
				}
			});
		});
	});
</script>
</head>
<body>
	<h1>Sentiment API</h1>
	<p>텍스트 데이터를 분석해서 해당 단어/문장/문구 내용의 감정을 분석하는 서비스로 그 결과를 반환하는 HTTP 기반의 REST API입니다.</p>
	글내용
	<textarea name="content" id="content" style="width:600px; height:400px">
싸늘하다. 가슴에 비수가 날아와 꽂힌다.
하지만 걱정하지마라.
손은 눈보다 빠르니까.
아귀한텐 밑에서 한장.
정마담도 밑에서 한장.
나 한장.
아귀한텐 다시 밑에서 한장.
이제 정마담에게, 마지막 한장.

동작 그만 밑장빼기냐?
	</textarea><br/>
	<input type="button" id="sentiBtn" value="감정평가(neutral:중립, positive:긍정, negative:부정)"/>
	<div id="sentiResult"></div>
</body>
</html>