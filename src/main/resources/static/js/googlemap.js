const map = new Vue({
	el:"#map",
mounted() {
    this.initializeMap();
  },
  methods: {
    initializeMap() {
      let latitude = 35.6811673;
      let longitude = 139.7670516;

      var MyLatLng = new google.maps.LatLng(latitude, longitude);
      var Options = {
        zoom: 15,
        center: MyLatLng,
        mapTypeId: 'roadmap'
      };
      var map = new google.maps.Map(document.getElementById('map'), Options);
    }
  }
}
);