<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<ArrayList<String>> list = (ArrayList<ArrayList<String>>) request.getAttribute("LIST");
	ArrayList<ArrayList<String>> list2 = (ArrayList<ArrayList<String>>) request.getAttribute("LIST2");
	ArrayList<Integer> reservedLessonIds = (ArrayList<Integer>) request.getAttribute("RESERVED_LESSON_IDS");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>イベント一覧</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="navbar.jsp"%>

	<form action="/ReserveServlet" method="post" class="taki">
		<h2>イベント一覧画面</h2>
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item active" aria-current="page">イベント一覧</li>
		  </ol>
		</nav>
		<%
		    String successMessage = (String) request.getAttribute("successMessage");
		    if (successMessage != null) {
		%>
		        <div class="d-flex justify-content-center align-items-center">
				    <div class="alert alert-danger text-center">
				        <%= successMessage %>
				    </div>
				</div>
		<%
		    }
		%>
		<%
		for (ArrayList<String> record : list) {
		%>
		<div class="accordion">
			<div class="accordion-item">
				<div class="accordion-header" onclick="toggleAccordion(this)">
					<span><%=record.get(0)%> 価格:<%=record.get(1)%>円</span>
					<span class="arrow">▼</span>
				</div>
				<div class="accordion-content">
					<table class="custom-table">
						<thead>
							<tr>
								<th>開催日</th>
								<th>イベント開始</th>
								<th>イベント終了</th>
								<th>参加可能人数</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<%
							for (ArrayList<String> record2 : list2) {
							%>
								<%if(record.get(0).equals(record2.get(0))) {%>
									<tr>
										<td><%=record2.get(2)%></td>
										<td><%=record2.get(3)%></td>
										<td><%=record2.get(4)%></td>
										<td>
											<% 
										        int remainingSeats = Integer.parseInt(record2.get(7));
										        int totalSeats = Integer.parseInt(record2.get(6));
										        if (remainingSeats == 0) { 
										    %>
										        満席
										    <% 
										        } else { 
										    %>
										        残り <%=remainingSeats%> / <%=totalSeats%>人
										    <% 
										        } 
										    %>
										</td>
										<td>
											<%
											String lessonTimeIdStr = record2.get(5);
											boolean isFull = (remainingSeats == 0); // ここでisFullを定義
											
											if (lessonTimeIdStr != null && !lessonTimeIdStr.isEmpty()) {
											    int lessonTimeId = Integer.parseInt(lessonTimeIdStr);
											    if (reservedLessonIds.contains(lessonTimeId)) {
											%>
											        <form action="CancelReserveServlet" method="post">
											            <input type="hidden" name="lessonTimeId" value="<%=lessonTimeId%>">
											            <button type="submit" class="btn btn-danger">キャンセル</button>
											        </form>
											<%
											    } else {
											%>
											        <form action="ReserveServlet" method="post">
											            <input type="hidden" name="eventName" value="<%=record.get(0)%>">
											            <input type="hidden" name="eventContent" value="<%=record.get(3)%>">
											            <input type="hidden" name="eventDate" value="<%=record2.get(2)%>">
											            <input type="hidden" name="startTime" value="<%=record2.get(3)%>">
											            <input type="hidden" name="endTime" value="<%=record2.get(4)%>">
											            <input type="hidden" name="lessonTimeId" value="<%=lessonTimeId%>">
											            <button type="submit" class="btn <%= isFull ? "btn-secondary" : "btn-primary" %>" 
											                <%= isFull ? "disabled" : "" %>>
											                <%= isFull ? "予約不可" : "予約" %>
											            </button>
											        </form>
											<%
											    }
											} else {
											    // lessonTimeIdがnullまたは空の場合の処理
											    out.println("無効なレッスンIDです。");
											}
											%>
										</td>
									</tr>
								<%} %>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<%
		}
		%>
		
		<script>
			function toggleAccordion(element) {
				const content = element.nextElementSibling;
				content.style.display = content.style.display === 'block' ? 'none'
						: 'block';
				const arrow = element.querySelector('.arrow');
				arrow.textContent = arrow.textContent === '▼' ? '▲' : '▼';
			}
		</script>
		
		<!-- JavaScriptファイルを読み込み -->
		<script src="js/registration.js"></script>
	<form>
</body>
</html>