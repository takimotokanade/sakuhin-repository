<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%
    List<Map<String, String>> participants = (List<Map<String, String>>) request.getAttribute("participants");
    int numPeople = (int) request.getAttribute("numPeople");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>予約確認画面</title>
	<link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
	<%-- navbar --%>
	<%@include file="navbar.jsp"%>

	<div class="taki">
		<h2>予約内容確認</h2>
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="#">イベント一覧</a></li>
		    <li class="breadcrumb-item active" aria-current="page">イベント予約</li>
		  </ol>
		</nav>
		<div class="registration">
			<div class="reservation-bar">
                <div class="step">予約</div>
                <div class="step active">予約確認</div>
                <div class="step">完了</div>
            </div>
            
		    <p>参加人数: <%= numPeople %> 人</p>
		
	        <% for (int i = 0; i < participants.size(); i++) { %>
	        	<div class="date-time-section3">
                	<p><%= i + 1 %> 人目</p>
               	</div>
                <p>セイ: <%= participants.get(i).get("sei") %></p>
                <p>メイ: <%= participants.get(i).get("mei") %></p>
                <p>身長: <%= participants.get(i).get("height") %></p>
                <p>年齢: <%= participants.get(i).get("age") %></p>
                <p>利き手: <%= participants.get(i).get("handedness") %></p>
	        <% } %>
		    
		    <form action="./ReservationCompServlet" method="POST">
		    	<% for (int i = 0; i < participants.size(); i++) { %>
			        <input type="hidden" name="participants[<%=i%>].sei" value="<%= participants.get(i).get("sei") %>">
			        <input type="hidden" name="participants[<%=i%>].mei" value="<%= participants.get(i).get("mei") %>">
			        <input type="hidden" name="participants[<%=i%>].height" value="<%= participants.get(i).get("height") %>">
			        <input type="hidden" name="participants[<%=i%>].age" value="<%= participants.get(i).get("age") %>">
			        <input type="hidden" name="participants[<%=i%>].handedness" value="<%= participants.get(i).get("handedness") %>">
			    <% } %>
		    	<button type="back" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                    戻る
                </button>
		        <button type="submit" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                    確定
                </button>
		    </form>
	    </div>
    </div>
</body>
</html>