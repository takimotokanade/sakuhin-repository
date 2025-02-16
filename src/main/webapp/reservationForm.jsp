<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String eventName = (String)request.getAttribute("eventName");
	String eventDate = (String)request.getAttribute("eventDate");
	String startTime = (String)request.getAttribute("startTime");
	String endTime = (String)request.getAttribute("endTime");
	String eventContent = (String)request.getAttribute("eventContent");
%>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>予約画面</title>
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
        <div class="kakuninn">
            <p>イベント名 : <%=eventName %></p>
            <p>開催日 : <%=eventDate %></p>
            <p>時間 : <%=startTime %> - <%=endTime %></p>
            <p>内容 : <%=eventContent %></p>
            <p>
            	注意事項<br>
            	①満4歳～小学校6年生までのお子様が対象（保護者様は参加不可）です。<br>
				②エプロン・ハンドタオル・年齢が証明できるものをお持ちください。<br>
				③調理中の火傷防止のため、長袖着用を推奨しています。<br>
				④当料理教室においては、食品アレルギー対応は行っておりません
            </p>
        </div>
    
        <div class="registration">
            <form action="./ReservationCheckServlet" method="post">
                <!-- 予約ステップバー -->
                <div class="reservation-bar">
                    <div class="step active">予約</div>
                    <div class="step">予約確認</div>
                    <div class="step">完了</div>
                </div>
        
                <!-- 人数 -->
                <div class="mb-3">
                    <label>人数</label>
                    <select id="numPeople" name="numPeople">
                        <option value="1" selected>1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                    </select>
                </div>
        
                <!-- 動的フィールドのプレースホルダー -->
                <div id="dynamicFields">
                    
                </div>
        
                <!-- Button trigger modal -->
                <button type="submit" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                	<input type="hidden" name="eventDate" value="<%=eventDate %>">
                	<input type="hidden" name="startTime" value="<%=startTime %>">
                	<input type="hidden" name="endTime" value="<%=endTime %>">
                    予約
                </button>
            </form>
        </div>
    </div>

    <!-- 外部 JavaScript ファイルを読み込み -->
    <script src="js/reservation.js"></script>
</body>

</html>

