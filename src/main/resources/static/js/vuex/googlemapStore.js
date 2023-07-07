const store = new Vuex.Store({
	state: {
		latitude: "0",
		longitude: "0",
		institutionList: [],
		priceList: [],
		crimeList: [],
		infoWindow: [],
		addressOptions:[],
		stationOptions:[],
		map: null,
		marker: [],
		option:null,
		ratio:"",
		currentLocationMarker:""
	},
	mutations: {
		updateLatitude(state, latitude) {
			state.latitude = latitude;
		},
		updateLongitude(state, longitude) {
			state.longitude = longitude;
		},
		updateMap(state, map) {
			state.map = map
		},
		updateInstitution(state, institutionList) {
			state.institutionList = institutionList
		},
		updateMarker(state, marker) {
			state.marker = marker
		},
		updateInfoWindow(state, infoWindow) {
			state.infoWindow = infoWindow
		},
	}
});

