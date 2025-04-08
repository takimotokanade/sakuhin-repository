<%@page import="dto.LessonTimesDTO"%>
<%@page import="dto.EventsDTO"%>
<%@page import="dto.EventDatesDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Time"%>
<%
	ArrayList<EventsDTO> eventList = (ArrayList<EventsDTO>) request.getAttribute("EVENTLIST");
	ArrayList<EventsDTO> eventListAll = (ArrayList<EventsDTO>) request.getAttribute("EVENTLISTALL");
	ArrayList<EventDatesDTO> eventDateList = (ArrayList<EventDatesDTO>) request.getAttribute("EVENTDATELIST");
	ArrayList<LessonTimesDTO> lessonTimeList = (ArrayList<LessonTimesDTO>) request.getAttribute("LESSONTIMELIST");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>イベント一覧</title>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
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
			for (EventsDTO event : eventList) {
			%>
			<div class="accordion">
				<div class="accordion-item">
					<div class="accordion-header" onclick="toggleAccordion(this)">
						<span><%=event.getEventName()%> 価格:<%=event.getPrice()%>円</span>
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
								// eventListAllから関連するeventIdを持つイベントを抽出
								for (EventsDTO eventAll : eventListAll) {
									// イベントIDが一致する場合
									if (event.getEventId() == eventAll.getEventId()) {
										// 開催日を取得（EventDatesDTOから）
										EventDatesDTO eventDate = eventAll.getEventDates(); // イベントの日付情報を取得
										LessonTimesDTO lessonTimes = eventAll.getLessonTimes();// イベントの時間情報を取得
										
										// 開催日や開始時間・終了時間などの情報を取得
										Date eventDateValue = eventDate.getEventDate();
										Time startTime = lessonTimes.getStartTime();
										Time endTime = lessonTimes.getEndTime();
								%>
										<tr>
											<td><%=eventDateValue%></td>
											<td><%=startTime%></td>
											<td><%=endTime%></td>
											<td>
												<% 
											        int remainingSeats = eventAll.getAvailableSeats(); // 残り席
											        int totalSeats = eventAll.getMaxParticipants(); // 最大参加人数
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
												// 必要に応じて予約ボタンを表示
												if (remainingSeats > 0) {
												%>
												    <form action="ReserveServlet" method="post">
												        <input type="hidden" name="eventName" value="<%=event.getEventName()%>">
												        <input type="hidden" name="eventContent" value="<%=event.getEventContent()%>">
												        <input type="hidden" name="eventDate" value="<%=eventDateValue%>">
												        <input type="hidden" name="startTime" value="<%=startTime%>">
												        <input type="hidden" name="endTime" value="<%=endTime%>">
												        <button type="submit" class="btn btn-primary">予約</button>
												    </form>
												<% 
												} else {
												%>
												    <span>予約不可</span>
												<% 
												}
												%>
											</td>
										</tr>
									<% 
									} 
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