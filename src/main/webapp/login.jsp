<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="css/style.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
</head>
<body>
	<%@include file="navbar.jsp"%>

	<div class="login">
		<h1>ログイン</h1>
		<div class="loginForm">
			<form action="./LoginServlet" method="post">
				<%
				String errorMsg = (String) request.getAttribute("errorMsg");
				if (errorMsg != null) {
				%>
				<p class="error-msg" style="color: red"><%=errorMsg%></p>
				<%
				}
				%>
				<div class="items">
					<label>ユーザID</label> <input type="text" class="input form-control"
						name="userName" required>
				</div>
				<div class="items">
					<label>パスワード</label> <input type="password"
						class="input form-control" name="password" required>
				</div>
				<button type="submit" class="btn btn-primary">ログイン</button>
			</form>
		</div>
	</div>
</body>
</html>