const map = new Vue({
  el: "#map",
  data: {
    latitude: "35.6811673",
    longitude: "139.7670516"
  },
  created() {
    this.getPlaceData();
  },
  mounted() {
    this.initializeMap();
  },
  methods: {
    initializeMap() {
      let latitude = parseFloat(this.latitude);
      let longitude = parseFloat(this.longitude);

      var MyLatLng = new google.maps.LatLng(latitude, longitude);
      var Options = {
        zoom: 15,
        center: MyLatLng,
        mapTypeId: 'roadmap'
      };
      var map = new google.maps.Map(document.getElementById('map'), Options);
    },
    getPlaceData() {
      this.longitude = document.getElementById('longitudeInput').value;
      this.latitude = document.getElementById('latitudeInput').value;
    }
  }
});
