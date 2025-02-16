<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

    <!-- myCSS -->
    <link rel="stylesheet" href="css/style.css">

    <title>イベント修正画面</title>
</head>

<body>
	<%@include file="navbar.jsp"%>

    <div class="takimoto">
        <form action="./CreateServlet" method="post">
            <h2>イベント登録画面</h2>
            <nav aria-label="breadcrumb">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="#">イベント一覧</a></li>
			    <li class="breadcrumb-item active" aria-current="page">イベント登録</li>
			  </ol>
			</nav>
            <div class="registration">
                <div class="items">
                    <label>イベント名</label>
                    <input type="text" class="input form-control" name="eventName" required>
                </div>
                <div class="items">
                    <label>内容</label>
                    <textarea class="input form-control" name="eventContent" required></textarea>
                </div>
                <div class="items">
                    <label>料金</label>
                    <input type="number" class="input form-control" name="price" required>円
                </div>
                <div class="items">
                    <label>イベント最大参加人数</label>
                    <select class="form-control" name="maxParticipants" required>
                        <option value="4" selected>4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                    </select>
                </div>
                <hr>
                
                <!-- 開催日の数を選択 -->
                <div class="mb-3">
                    <label>開催日の数</label>
                    <select id="numEvents" name="numEvents" onchange="updateEventFields()">
                        <option value="1" selected>1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                    </select>
                </div>
    
                <!-- 動的フィールドのプレースホルダー -->
                <div id="dynamicFields">
                    <!-- 最初の開催日と時間スロット -->
                    <div id="event-1" class="event-section">
                        <div class="items">
                            <label>開催日</label>
                            <input type="date" name="eventDate1" class="input form-control" required>
                        </div>
                        <div class="items">
                            <div id="timeSlots-1">
                                <label>レッスン時間</label>
                                <!-- 3つの時間スロットを固定表示 -->
                                <div class="time-slot d-flex align-items-center mb-2">
                                    <input type="time" name="eventTime_1_start_1" class="form-control" required> -
                                    <input type="time" name="eventTime_1_end_1" class="form-control ms-2" required>
                                </div>
                                <div class="time-slot d-flex align-items-center mb-2">
                                    <input type="time" name="eventTime_1_start_2" class="form-control"> -
                                    <input type="time" name="eventTime_1_end_2" class="form-control ms-2">
                                </div>
                                <div class="time-slot d-flex align-items-center mb-2">
                                    <input type="time" name="eventTime_1_start_3" class="form-control"> -
                                    <input type="time" name="eventTime_1_end_3" class="form-control ms-2">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
    
                <!-- 登録ボタン -->
                <button type="submit" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                	登録
                </button>
            </div>
        </form>
    </div>
    
    <!-- JavaScriptファイルを読み込み -->
    <script src="js/registration.js"></script>
    
</body>

</html>