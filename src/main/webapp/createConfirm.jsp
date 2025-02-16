<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%
    String eventName = (String) session.getAttribute("eventName");
    String eventContent = (String) session.getAttribute("eventContent");
    String price = (String) session.getAttribute("price");
    String maxParticipants = (String) session.getAttribute("maxParticipants");

    Map<Integer, Map<String, List<List<String>>>> eventDetails = (Map<Integer, Map<String, List<List<String>>>>) session.getAttribute("eventDetails");
%>

<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

    <!-- myCSS -->
    <link rel="stylesheet" href="css/style.css">

    <title>登録確認画面</title>
</head>
<body>
    <%@include file="navbar.jsp"%>
    <div class="takimoto">
        <h2>登録内容確認</h2>
        <nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="#">イベント一覧</a></li>
		    <li class="breadcrumb-item active" aria-current="page">イベント登録</li>
		  </ol>
		</nav>
        <div class="registration">
            <p>イベント名: <%= eventName %></p>
            <p>内容: <%= eventContent %></p>
            <p>料金: <%= price %>円</p>
            <p>最大参加人数: <%= maxParticipants %></p>
            <h3>開催日と時間</h3>
            <%
            if (eventDetails != null && !eventDetails.isEmpty()) {
                for (Map.Entry<Integer, Map<String, List<List<String>>>> entry : eventDetails.entrySet()) {
                    int index = entry.getKey();
                    Map<String, List<List<String>>> eventData = entry.getValue();
                    List<List<String>> dates = eventData.get("dates");
                    List<List<String>> startTimes = eventData.get("startTimes");
                    List<List<String>> endTimes = eventData.get("endTimes");
            %>
                    <p>開催日<%= index %>: <%= dates.get(0).get(0) %></p>
                    <%
                    for (int i = 0; i < startTimes.get(0).size(); i++) {
                    %>
                            <p>レッスン時間<%= i + 1 %>: <%= startTimes.get(0).get(i) %> - <%= endTimes.get(0).get(i) %></p>
                    <%
                    }
                    %>
                    <hr>
            <%
                }
            } else {
            %>
                <p>開催日と時間が登録されていません。</p>
            <%
            }
            %>
            <form action="CreateCompServlet" method="post">
                <button type="button" class="btn btn-primary" onclick="history.back()">戻る</button>
                <button type="submit" class="btn btn-primary">確定</button>
            </form>
        </div>
    </div>
</body>
</html>