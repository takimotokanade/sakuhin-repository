<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Arrays"%>
<%
// イベントのデータをリクエストから取得
String eventName = (String) session.getAttribute("eventName");
String eventContent = (String) session.getAttribute("eventContent");
String price = (String) session.getAttribute("price");
String maxParticipants = (String) session.getAttribute("maxParticipants");

// 開催日と時間のデータをリクエストから取得
List<List<String>> eventDates = (List<List<String>>) session.getAttribute("eventDates");
List<List<String>> startTimes = (List<List<String>>) session.getAttribute("startTimes");
List<List<String>> endTimes = (List<List<String>>) session.getAttribute("endTimes");

// eventDates, startTimes, endTimes が null の場合、初期化
if (eventDates == null) {
	eventDates = new ArrayList<>();
}
if (startTimes == null) {
	startTimes = new ArrayList<>();
}
if (endTimes == null) {
	endTimes = new ArrayList<>();
}

// デフォルト値を設定
if (eventDates.isEmpty()) {
	eventDates.add(new ArrayList<>(Arrays.asList("2023-01-01"))); // デフォルトの開催日
	startTimes.add(new ArrayList<>(Arrays.asList("10:00"))); // デフォルトの開始時間
	endTimes.add(new ArrayList<>(Arrays.asList("12:00"))); // デフォルトの終了時間
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>イベント修正画面</title>
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

		<h2>イベント修正画面</h2>
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="#">イベント一覧</a></li>
		    <li class="breadcrumb-item active" aria-current="page">イベント更新</li>
		  </ol>
		</nav>
		<form action="./UpdateCheckServlet" method="post">
			<div class="registration">
				<!-- イベント名 -->
				<div class="items">
					<label>イベント名</label>
					<input type="text" class="input form-control" name="eventName" value="<%=eventName%>">
				</div>

				<!-- 内容 -->
				<div class="items">
					<label>内容</label>
					<textarea class="input form-control" name="eventContent" required><%=eventContent%></textarea>
				</div>

				<!-- 料金 -->
				<div class="items">
					<label>料金</label>
					<input type="number" class="input form-control" name="price" value="<%=price%>" disabled>円
				</div>

				<!-- 最大参加人数 -->
				<div class="items">
					<label>イベント最大参加人数</label>
					<select class="form-control" name="maxParticipants" disabled>
						<option value="4" <%="4".equals(maxParticipants) ? "selected" : ""%>>4</option>
						<option value="5" <%="5".equals(maxParticipants) ? "selected" : ""%>>5</option>
						<option value="6" <%="6".equals(maxParticipants) ? "selected" : ""%>>6</option>
						<option value="7" <%="7".equals(maxParticipants) ? "selected" : ""%>>7</option>
						<option value="8" <%="8".equals(maxParticipants) ? "selected" : ""%>>8</option>
						<option value="9" <%="9".equals(maxParticipants) ? "selected" : ""%>>9</option>
						<option value="10" <%="10".equals(maxParticipants) ? "selected" : ""%>>10</option>
						<option value="11" <%="11".equals(maxParticipants) ? "selected" : ""%>>11</option>
						<option value="12" <%="12".equals(maxParticipants) ? "selected" : ""%>>12</option>
					</select>
				</div>
				<hr>

				<!-- 開催日の数を選択 -->
				<div class="mb-3">
					<label>開催日の数</label>
					<select id="numEvents" name="numEvents" onchange="updateEventFields()" disabled>
						<option value="1" <%=eventDates.size() == 1 ? "selected" : ""%>>1</option>
						<option value="2" <%=eventDates.size() == 2 ? "selected" : ""%>>2</option>
						<option value="3" <%=eventDates.size() == 3 ? "selected" : ""%>>3</option>
					</select>
				</div>

				<!-- 動的フィールドのプレースホルダー -->
				<div id="dynamicFields">
				    <% for (int i = 0; i < eventDates.size(); i++) { %>
				    <div id="event-<%= i + 1 %>" class="event-section">
				        <div class="items">
				            <label>開催日</label>
				            <input type="date" name="eventDate<%= i + 1 %>" class="input form-control" value="<%= eventDates.get(i) %>" disabled>
				        </div>
				        <div class="items">
				            <label>レッスン時間</label>
				            <div id="timeSlots-<%= i + 1 %>">
				                <% 
				                List<String> currentStartTimes = startTimes.get(i);
				                List<String> currentEndTimes = endTimes.get(i);
				                for (int j = 0; j < currentStartTimes.size(); j++) { 
				                %>
				                <div class="time-slot d-flex align-items-center mb-2">
				                    <input type="time" name="eventTime_<%= i + 1 %>_start_<%= j + 1 %>" class="form-control" value="<%= currentStartTimes.get(j) %>" disabled> - 
				                    <input type="time" name="eventTime_<%= i + 1 %>_end_<%= j + 1 %>" class="form-control ms-2" value="<%= currentEndTimes.get(j) %>" disabled>
				                </div>
				                <% } %>
				            </div>
				        </div>
				    </div>
				    <% } %>
				</div>

				<!-- 修正ボタン -->
				<input type="hidden" name="action" value="confirm">
				<button type="submit" class="btn btn-warning">修正</button>
		</form>
		<form action="./DeleteServlet" method="post">
			<button type="submit" class="btn btn-danger">削除</button>
		</form>
		<!-- 戻るボタン -->
		<form action="./LoginServlet" method="post">
		    <button type="submit" class="btn btn-secondary">戻る</button>
		</form>
	</div>

	</div>

	<!-- JavaScriptファイルを読み込み -->
	<script src="js/registration.js"></script>
</body>
</html>