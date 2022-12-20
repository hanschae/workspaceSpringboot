<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시판 과제</title>
		<style>
		#board, #page{overflow:auto; list-style-type: none;}
		h1{text-align: center;}
		
		#board li{
			float:left; line-height:40px; 
			border-bottom:1px solid #ddd; width:10%;
			list-style-type: none;
		}
		#board li:nth-child(5n+2){
			width:55%;
			float: left;
			white-space:nowrap; 
			overflow:hidden;
			text-overflow:ellipsis;
		}
	/*페이지*/
	#page li{
		float:left; padding:10px;
	}
	#page a{text-decoration: none;}
	#searchFrm{text-align: center;}
	a:link, a:visited, a:hover{
    	text-decoration:none;
    	color:#333;
	}
		</style>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	</head>
<body>
	<div class="container">
		<h1>게시판 리스트</h1>
		<ul id="board">
			<li>번호</li>
			<li>제목</li>
			<li>작성자</li>
			<li>조회수</li>
			<li>등록일</li>
			
			<c:forEach var="vo" items="${list}">
				<li>${vo.no }</li>
				<li>${vo.subject}</li>
				<li>${vo.userid}</li>
				<li>${vo.hit}</li>
				<li>${vo.writedate}</li>
			</c:forEach>
		</ul>
	</div>
	<div>
		<ul id="page">
			<c:if test="${pVO.nowPage<=1}">
				<li>prev</li>
			</c:if>
			<c:if test="${pVO.nowPage>1}">
				<li><a href="?nowPage=${pVO.nowPage-1}<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord}</c:if>">prev</a></li>
			</c:if>

			<c:forEach var="p" begin="${pVO.startPage}" end="${pVO.startPage+pVO.onePageCount-1}">
				<c:if test="${p<=pVO.totalPage}">
					<li
					<c:if test="${p==pVO.nowPage}">
						style="background-color:#f00; color:#fff;"
					</c:if>
						><a href="?nowPage=${p}<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord}</c:if>">${p}</a></li>
				</c:if>
			</c:forEach>
			<c:if test="${pVO.nowPage==pVO.totalPage}">
				<li>next</li>
			</c:if>
			<c:if test="${pVO.nowPage<pVO.totalPage}">
				<li><a href="?nowPage=${pVO.nowPage+1}<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord}</c:if>">next</a></li>
			</c:if>
			
		</ul>
	</div>
	<div>
		<form method="get" action="/" id="searchFrm">
			<select name="searchKey">
				<option value="subject">제목</option>
				<option value="userid">작성자</option>
				<option value="content">글내용</option>
			</select>
			<input type="text" name="searchWord" id="searchWord"/>
			<input type="submit" value="Search"/>
		</form>
	</div>
</body>
</html>