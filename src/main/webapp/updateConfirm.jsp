<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String eventName = (String) session.getAttribute("eventName");
    String eventContent = (String) session.getAttribute("eventContent");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修正確認画面</title>
<link rel="stylesheet" href="css/style.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
</head>
<body>
	<%@include file="navbar.jsp"%>
	
	<div class="takimoto">
		<h2>イベント修正確認画面</h2>
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="#">イベント一覧</a></li>
		    <li class="breadcrumb-item active" aria-current="page">イベント更新</li>
		  </ol>
		</nav>
		<div class="registration">
			<p>イベント名: <%= eventName %></p>
			<p>イベント内容: <%= eventContent %></p>
			<div class="d-flex gap-3">
				<form action="update.jsp" method="post">
				    <input type="hidden" name="eventName" value="<%= eventName %>">
				    <input type="hidden" name="eventContent" value="<%= eventContent %>">
				    <button type="submit" class="btn btn-secondary">戻る</button>
				</form>
				
				<form action="./UpdateCompServlet" method="post">
				    <input type="hidden" name="action" value="update">
				    <input type="hidden" name="eventName" value="<%= eventName %>">
				    <input type="hidden" name="eventContent" value="<%= eventContent %>">
				    <button type="submit" class="btn btn-primary">確定</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>