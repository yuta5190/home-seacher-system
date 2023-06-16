"use strict";
// 選択した沿線のidを取得
var selectRailwayLine = document.getElementById("railway-lines");
var selectStations = document.getElementById("stations");
const button = document.getElementById("button");

// ページ読み込みのタイミングで行う処理
$(function () {
	button.disabled = true
});

// 沿線を選択するたびに行う処理
selectRailwayLine.addEventListener("change", function() {
    station_search();
});

function station_search() {
	const selectedOptionOfRailwayLine = selectRailwayLine.options[selectRailwayLine.selectedIndex];
	
	const railwayLine = selectedOptionOfRailwayLine.value;
	
	//Ajaxリクエストを送信して駅情報を取得
	var xhrStation = new XMLHttpRequest();
	xhrStation.onreadystatechange = function() {
    if (xhrStation.readyState === XMLHttpRequest.DONE && xhrStation.status === 200) {
			// 取得したステーションリストをJSON形式からオブジェクトに変換
			var stationList = JSON.parse(xhrStation.responseText);
			// ステーションリストのセレクトボックスをクリア
			selectStations.innerHTML = "";
			// 取得したステーションリストをセレクトボックスに挿入
			for (var i = 0; i < stationList.length; i++){
				var station = stationList[i];
				var option = document.createElement("option");
				option.value = station.id;
				option.text = station.stationName;
				selectStations.add(option);
			}
		}
	};
	
	xhrStation.open(
		"GET",
		"/api/stations?lineId=" +
		railwayLine,
		true
	);
	
	xhrStation.send();
	button.disabled = false
}