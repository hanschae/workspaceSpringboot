<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인 과제</title>
		<style>
		body, ul, li{
			margin:0;
			padding:0;
			list-style-type:none;
		}
		.container{
			width:550px;
			overflow:auto;
			margin:0 auto 50px;
			border:5px solid black;
		}
		#log ul{width:500px; margin:0 auto;}
		#log li{margin-bottom:20px;}
		#log input{width:500px; height:50px;}
		#log input[type=submit]{
			border:none;
			background-color:#6d8c29;
			color:#fff;
			font-size:2em;
		}
		input::placeholder {
		  font-size:1.2em;
		}
		</style>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	</head>
<body>
<script>
	$(function(){
		$("#log").submit(function(){
			if($("#userid").val()==""){
				alert("아이디를 입력하세요.");
				return false;
			}
			if($("#userpwd").val()==""){
				alert("비밀번호를 입력하세요.");
				return false;
			}
			return true;
		});
	});
</script>

<div class="container">
	<h1 style="text-align:center; font-size:4em;">LOGO</h1>
	<form method="post" action="/loginOK" id="log">
		<ul>
			<li><input type="text" name="userid" id="userid" placeholder="아이디"/></li>
			<li><input type="password" name="userpwd" id="userpwd" placeholder="비밀번호"/></li>
			<li><input type="submit" value="로그인"/><li>
		</ul>
	</form>
</div>