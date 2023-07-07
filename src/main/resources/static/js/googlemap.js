Vue.use(Vuex);
const store = new Vuex.Store({
  state: {
   latitude: "0",
   longitude: "0",
   institutionList: [],
   map: "",
   marker:[],
  },
  mutations: {
    updateLatitude(state, latitude) {
      state.latitude = latitude;
    },
    updateLongitude(state,longitude){
		state.longitude=longitude;
	},
	 updateMap(state,map){
		 state.map=map
	 },
	  updateInstitution(state,institutionList){
		 state.institutionList=institutionList
	 },
	 updateMarker(state,marker){
		 state.markert=marker
	 },
  }
});

const map = new Vue({
	el: "#map",
	store: store,
	data: {
		latitude: "0",
		longitude: "0",
		
	},
	mounted() {
		this.$nextTick(() => {
			this.getPlaceData();
			this.initializeMap();
		});
	},
	methods: {
		
		initializeMap() {
			let latitude = parseFloat(this.$store.state.latitude);
			let longitude = parseFloat(this.$store.state.longitude);
			var MyLatLng = new google.maps.LatLng(latitude, longitude);
			var Options = {
				zoom: 15,
				center: MyLatLng,
				mapTypeId: 'roadmap'
			};
			this.$store.state.map = new google.maps.Map(document.getElementById('map'), Options);
		},
		getPlaceData() {
			this.$store.state.longitude = document.getElementById('longitudeInput').value;
			this.$store.state.latitude = document.getElementById('latitudeInput').value;
		}
	}
});

const status = new Vue({
	el: "#pin-check",
	store: store,
	data: {
		institutionStatus: false,
		priceStatus: false,
		crimeStatus: false,
		priceList: [],
		crimeList: [],
		markerLatLng: "",
	},
	mounted() {
		 setTimeout(() => {
		axios.post('/map/getdata', { longitude: this.$store.state.longitude, latitude: this.$store.state.latitude })
			.then(response => {
				var data = response.data;
				this.$store.state.institutionList = data.institutionList;
				//this.priceList = response.data.priceList;
				//this.crimeList = response.data.crimeList;
			})
	}, 1000);},
	
	methods: {
		changeInstitution() {
			this.institutionStatus = !this.institutionStatus;
			if (this.institutionStatus === true) {
				this.putmarker();
			}
		},
		changePrice() {
			this.priceStatus = !this.priceStatus;
			if (this.priceStatus === true) {
				this.putmarker();
			}
		},
		changeCrime() {
			this.crimeStatus = !this.crimeStatus;
			if (this.crimeStatus === true) {
				this.putmarker();
			}
		},
		putmarker() {
			    for (var i = 0; i < this.$store.state.institutionList.length; i++) {
			    this.$store.state.marker[i] = new google.maps.Marker( {
				map: this.$store.state.map , // 地図
				position: new google.maps.LatLng( this.$store.state.institutionList[i].latitude, this.$store.state.institutionList[i].longitude) }, 
				
				//とりあえず施設のみは調節した。
			/*this.marker.setMap(null);
			this.marker = [];
			this.markerLatLng = [];
			var j = 0;
			if (this.institutionStatus === true) {
				for (var i = 0; i < this.institutionList.length; i++) {
					this.markerLatLng = new google.maps.LatLng({ lat: this.institutionList[i]['latitude'], lng: this.institutionList[i]['longitude'] });
					j++;
					this.marker[j] = new google.maps.Marker({ // マーカーの追加
						position: this.markerLatLng, // マーカーを立てる位置を指定
						map: map.map // マーカーを立てる地図を指定
					});
				};
			};
			if (this.priceStatus === true) {
				for (var i = 0; i < this.priceList.length; i++) {
					this.markerLatLng = new google.maps.LatLng({ lat: this.priceList[i]['latitude'], lng: this.priceList[i]['longitude'] });
					this.marker[j] = new google.maps.Marker({ // マーカーの追加
						position: this.markerLatLng, // マーカーを立てる位置を指定
						map: map.map // マーカーを立てる地図を指定
					});
					j++;
				};
			};
			if (this.crimeStatus === true) {
				for (var i = 0; i < this.crimeList.length; i++) {
					this.markerLatLng = new google.maps.LatLng({ lat: this.crimeList[i]['latitude'], lng: this.crimeList[i]['longitude'] });
					j++;
					this.marker[j] = new google.maps.Marker({ // マーカーの追加
						position: this.markerLatLng, // マーカーを立てる位置を指定
						map: map.map // マーカーを立てる地図を指定
					});
				}
			};
			this.marker.setMap(map.map);*/
				console.log(this.$store.state.marker[i])
) };
		}
	}
});
