<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<ArrayList<String>> list = (ArrayList<ArrayList<String>>) request.getAttribute("LIST");
ArrayList<ArrayList<String>> list2 = (ArrayList<ArrayList<String>>) request.getAttribute("LIST2");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>イベント一覧（管理者）</title>
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
		        <div class="d-flex justify-content-center align-items-center"">
				    <div class="alert alert-danger text-center">
				        <%= successMessage %>
				    </div>
				</div>
		<%
		    }
		%>
		<div class="bottom2">
			<a href="create.jsp" class="btn btn-primary">新規登録</a>
		</div>
		<br>

		<%
		for (ArrayList<String> record : list) {
		%>
		<div class="bottom2">
			<form action="./UpdateServlet" method="post">
				<input type="hidden" name="eventId" value="<%= record.get(2) %>"> <!-- イベントIDを渡す -->
				<button type="submit" class="btn btn-warning">修正</button>
			</form>
			<form action="./ManagementServlet" method="post">
				<input type="hidden" name="eventId" value="<%= record.get(2) %>"> <!-- イベントIDを渡す -->
				<button type="submit" class="btn btn-success">管理</button>
			</form>
		</div>
		<div class="accordion">
			<div class="accordion-item">
				<div class="accordion-header" onclick="toggleAccordion(this)">
					<span><%=record.get(0)%> 価格:<%=record.get(1)%>円</span> <span
						class="arrow">▼</span>
				</div>
				<div class="accordion-content">
					<table class="custom-table">
						<thead>
							<tr>
								<th>開催日</th>
								<th>イベント開始</th>
								<th>イベント終了</th>
								<th>参加可能人数</th>
							</tr>
						</thead>
						<tbody>
							<%
							for (ArrayList<String> record2 : list2) {
							%>
							<%
							if (record.get(0).equals(record2.get(0))) {
							%>
							<tr>
								<td><%=record2.get(2)%></td>
								<td><%=record2.get(3)%></td>
								<td><%=record2.get(4)%></td>
								<!-- ↓○○分の、というもとの参加可能人数を表示させる -->
								<td>残り <%=record2.get(7)%> / <%=record2.get(6)%>人</td>
							</tr>
							<%
							}
							%>
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
	</div>
</body>
</html>