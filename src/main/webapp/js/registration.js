/**
 * 新規登録画面の開催日とレッスン時間を複数に増やすJS
 */
/**
 * 新規登録画面の開催日とレッスン時間を複数に増やすJS
 */

// 新しい開催日フィールドを作成する関数
function createEventFields(index) {
    return `
        <div id="event-${index}" class="event-section">
            <p>開催日${index}</p>
            <div class="items">
                <label>開催日</label>
                <input type="date" name="eventDate${index}" class="input form-control" required>
            </div>
            <div class="items">
                <div id="timeSlots-${index}">
                    <label>レッスン時間</label>
                    <!-- 3つの時間スロットを固定表示 -->
                    <div class="time-slot d-flex align-items-center mb-2">
                        <input type="time" name="eventTime_${index}_start_1" class="form-control" required> -
                        <input type="time" name="eventTime_${index}_end_1" class="form-control ms-2" required>
                    </div>
                    <div class="time-slot d-flex align-items-center mb-2">
                        <input type="time" name="eventTime_${index}_start_2" class="form-control"> -
                        <input type="time" name="eventTime_${index}_end_2" class="form-control ms-2">
                    </div>
                    <div class="time-slot d-flex align-items-center mb-2">
                        <input type="time" name="eventTime_${index}_start_3" class="form-control"> -
                        <input type="time" name="eventTime_${index}_end_3" class="form-control ms-2">
                    </div>
                </div>
            </div>
        </div>
    `;
}

// 開催日の数を更新する関数
function updateEventFields() {
    const numEvents = document.getElementById("numEvents").value;
    const dynamicFieldsContainer = document.getElementById("dynamicFields");

    // 現在の開催日フィールドをクリア
    dynamicFieldsContainer.innerHTML = "";

    // 選択された数に応じて開催日フィールドを追加
    for (let i = 1; i <= numEvents; i++) {
        dynamicFieldsContainer.innerHTML += createEventFields(i);
    }
}

//削除ボタン押下後に、確認アラートを出現させる関数
document.addEventListener("DOMContentLoaded", function () {
  const deleteButton = document.querySelector("form[action='./DeleteServlet'] button");

  if (deleteButton) {
    deleteButton.addEventListener("click", function (event) {
      event.preventDefault(); // デフォルトの送信を防止

      // アラート表示
      const confirmation = confirm("本当に削除しますか？\n\n[キャンセル]で戻る\n[OK]で削除を確定します。");

      if (confirmation) {
        // 確定ボタンを押した場合、フォームを送信
        deleteButton.closest("form").submit();
      }
    });
  }
});

// キャンセルボタン押下後に、確認アラートを出現させる
document.addEventListener("DOMContentLoaded", function () {
  const cancelButtons = document.querySelectorAll("form[action='CancelReserveServlet'] button");

  cancelButtons.forEach(function (button) {
    button.addEventListener("click", function (event) {
      event.preventDefault(); // デフォルトの送信を防止

      // アラート表示
      const confirmation = confirm("本当に予約をキャンセルしますか？\n\n[キャンセル]で戻る\n[OK]でキャンセルを確定します。");

      if (confirmation) {
        // 確定ボタンを押した場合、フォームを送信
        button.closest("form").submit();
      }
    });
  });
});