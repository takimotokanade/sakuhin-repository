<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%@ page import="java.util.Set, java.util.HashSet" %>
<%
	String eventName = (String)request.getAttribute("eventName");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約者管理画面</title>
</head>
<body>
	<%@include file="navbar.jsp"%>

	<div class="takimoto">
        <h2>予約者管理画面</h2>
        <nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="#">イベント一覧</a></li>
		    <li class="breadcrumb-item active" aria-current="page">予約者管理</li>
		  </ol>
		</nav>
        <div class="kakuninn">
            <p>イベント名: <%=eventName %></p>
        </div>

        <%
        List<Map<String, String>> eventDates = (List<Map<String, String>>) request.getAttribute("eventDates");
        List<Map<String, String>> reservations = (List<Map<String, String>>) request.getAttribute("reservations");

        for (Map<String, String> date : eventDates) {
        %>
        <div class="registration">
            <div class="date-time-section2">
                <p>開催日: <%= date.get("eventDate") %></p>
            </div>
            <div class="date-time-section">
                <p>レッスン時間: <%= date.get("startTime") %> - <%= date.get("endTime") %></p>
            </div>

            <!-- 顧客情報を表示 -->
            <%
            Set<String> displayedCustomers = new HashSet<>(); // 表示済みの顧客リスト
            for (Map<String, String> reservation : reservations) {
                if (reservation.get("eventDate").equals(date.get("eventDate")) &&
                    reservation.get("startTime").equals(date.get("startTime")) &&
                    reservation.get("endTime").equals(date.get("endTime"))) {

                    // 顧客情報を一意に識別するキーを作成
                    String customerKey = reservation.get("userLastName") + 
                                         reservation.get("userFirstName") + 
                                         reservation.get("phoneNumber");

                    // まだ表示していない顧客だけ表示
                    if (!displayedCustomers.contains(customerKey)) {
                        displayedCustomers.add(customerKey); // 表示済みリストに追加
            %>
            	<div class="date-time-section4">
	                <div class="customer-info">
	                    <p><%= reservation.get("userLastName") %> <%= reservation.get("userFirstName") %> 様 <%= reservation.get("phoneNumber") %></p>
	                </div>
	            </div>
            <%
                    } // if (!displayedCustomers.contains(customerKey))
            %>
                <table class="table">
                    <thead class="table-light">
                        <tr>
                            <th>セイ</th>
                            <th>メイ</th>
                            <th>身長 (cm)</th>
                            <th>年齢</th>
                            <th>利き手</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><%= reservation.get("lastName") %></td>
                            <td><%= reservation.get("firstName") %></td>
                            <td><%= reservation.get("height") %></td>
                            <td><%= reservation.get("age") %></td>
                            <td><%= reservation.get("dominantHand") %></td>
                        </tr>
                    </tbody>
                </table>
            <%
                } // if 条件チェック
            } // for (reservations)
            %>
        </div>
        <%
        }
        %>
    </div>
</body>
</html>