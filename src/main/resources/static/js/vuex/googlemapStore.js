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
		categoryList:[{ name: "atm", ja: "ATM" },
        { name: "school", ja: "学校" },
        { name: "grocery_or_supermarket", ja: "スーパー" },
        { name: "hospital", ja: "医療機関" },
        { name: "food", ja: "飲食店" },
        { name: "park", ja: "公園" },
        { name: "post_office", ja: "郵便局" },
        { name: "convenience_store", ja: "コンビニ" },
        { name: "itemStore", ja: "販売店" },
        { name: "amusument", ja: "アミューズメント" }],
		itemStore:["book_store","clothing_store","department_store","electronics_store","florist","furniture_store","home_goods_store","jewelry_store","shoe_store","shopping_mall"],
		amusument:["amusement_park","aquarium","art_gallery","bowling_alley","movie_theater","museum","spa","zoo"],
		map: null,
		marker: [],
		option:null,
		ratio:"",
		centerPosition:null,
		currentLocationMarker:"",
		service:"",
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

