/**
 * 利用者が予約画面で予約をする際、参加する人数ごとに入力フォームを生成する」
 */
// 各人のフィールドセットを作成する機能
function createPersonFields(index) {
    return `
    		<div class="date-time-section3">
            	<p>${index + 1} 人目</p>
            </div>
            <div class="col">
                <label for="sei${index}">セイ</label>
                <input type="text" name="sei${index}" id="sei${index}" class="input">
            </div>
            <div class="col">
                <label for="mei${index}">メイ</label>
                <input type="text" name="mei${index}" id="mei${index}" class="input">
            </div>
            <div class="col">
                <label>身長</label>
                <select aria-label="身長選択${index}" name="height${index}">
                    <option value="70" selected>70</option>
                    <option value="80">80</option>
                    <option value="90">90</option>
                    <option value="100">100</option>
                    <option value="110">110</option>
                    <option value="120">120</option>
                    <option value="130">130</option>
                    <option value="140">140</option>
                    <option value="150">150</option>
                </select>
            </div>
            <div class="col">
                <label>年齢</label>
                <select aria-label="年齢選択${index}" name="age${index}">
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
            <div class="col">
                <label>利き手</label>
                        <input type="radio" name="handedness${index}" id="rightHanded${index}" value="左">
                        <label class="form-check-label" for="rightHanded${index}">左</label>
                        <input type="radio" name="handedness${index}" id="leftHanded${index}" value="右" checked>
                        <label class="form-check-label" for="leftHanded${index}">右</label>
            </div>
    `;
}

//  選択した人数に基づいてフィールドを更新する機能
function updateFields() {
    const numPeople = document.getElementById("numPeople").value;
    const dynamicFieldsContainer = document.getElementById("dynamicFields");

    // 既存のフィールドをクリア
    dynamicFieldsContainer.innerHTML = '';

    // 指定した人数分のフィールドを作成する
    for (let i = 0; i < numPeople; i++) {
        dynamicFieldsContainer.innerHTML += createPersonFields(i);
    }
}

// 初期設定とイベントリスナー
document.getElementById("numPeople").addEventListener("change", updateFields);

// 初期ロード時にフィールドを入力する
updateFields();
