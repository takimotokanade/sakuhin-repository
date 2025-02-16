<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
    int numPeople = (int) request.getAttribute("numPeople"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約完了画面</title>
	<link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

</head>
<body>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
    <%@include file="navbar.jsp"%>

    <div class="taki">
        <h2>予約画面</h2>
    	<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="#">イベント一覧</a></li>
		    <li class="breadcrumb-item active" aria-current="page">イベント予約</li>
		  </ol>
		</nav>
        <div class="registration">
                <!-- 予約ステップバー -->
                <div class="reservation-bar">
                    <div class="step">予約</div>
                    <div class="step">予約確認</div>
                    <div class="step active">完了</div>
                </div>
        
                <h3>ご予約ありがとうございます</h3>
                <br>
                <p><%= session.getAttribute("lastName") %> <%= session.getAttribute("fristName") %>様</p>
                <p>開催日： <%= session.getAttribute("eventDate") %></p>
                <p>レッスン時間: <%= session.getAttribute("startTime") %> - <%= session.getAttribute("endTime") %></p>
                <p>予約人数: <%=numPeople %>名</p>
	            <form action="./BackServlet" method="post">
	                <button type="submit" class="btn btn-primary">イベント一覧に戻る</button>
	            </form>
        </div>
    </div>
</body>
</html>