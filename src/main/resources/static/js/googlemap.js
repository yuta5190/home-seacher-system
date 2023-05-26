let latitude = 35.6811673;
let longitude = 139.7670516;
function initializeMap() {

  var MyLatLng = new google.maps.LatLng(latitude, longitude);
  var Options = {
    zoom: 15,
    center: MyLatLng,
    mapTypeId: 'roadmap'
  };
  var map = new google.maps.Map(document.getElementById('map'), Options);
}

// HTMLの読み込みが完了したらinitializeMapを実行する
document.addEventListener('DOMContentLoaded', function() {
  initializeMap();
});