<%@ page pageEncoding="UTF-8"%>
<%
	String username = (String)session.getAttribute("username");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>TK料理教室</title>
	<link rel="stylesheet" href="css/style.css">
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
		crossorigin="anonymous">
</head>
<body>
	<div class="nvb">
		<h1>TK料理教室</h1>
		<%if(username == null) { %>
			<div class="navBotton">
				<button type="submit" class="btn btn-outline-dark">ログイン</button>
			</div>
		<%} else {%>
			<div class="navBotton">
				<p>こんにちは、 <%= session.getAttribute("lastName") %> <%= session.getAttribute("fristName") %> さん</p>
				<form action="./LogoutServlet" method="post">
					<button type="submit" class="btn btn-outline-dark">ログアウト</button>
				</form>
			</div>
		<%} %>
	</div>
</body>
</html>