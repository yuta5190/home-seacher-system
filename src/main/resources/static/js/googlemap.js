// maps.google.comのJavaScriptライブラリを非同期で読み込む
function loadGoogleMapsScript() {
  var script = document.createElement('script');
  script.src = 'https://maps.google.com/maps/api/js?key=AIzaSyAG2tgnkXGl888HTaCPW8Bo9VPr8ytP8yc&language=ja';
  document.body.appendChild(script);
}

// Google Mapsの初期化と地図の表示
function initializeMap() {
  var MyLatLng = new google.maps.LatLng(35.6811673, 139.7670516);
  var Options = {
    zoom: 15,
    center: MyLatLng,
    mapTypeId: 'roadmap'
  };
  var map = new google.maps.Map(document.getElementById('map'), Options);
}


init();



