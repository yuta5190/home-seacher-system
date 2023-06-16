//距離を選択するインスタンス
const selectValue = new Vue({
	el: "#distance-drop",
	data: {
		value: "1",
		showDropdown: false,
		options: [{ distance: '1km', value: "1" }, { distance: '3km', value: "3" }, { distance: '5km', value: "5" }],
		selectedOption: '',
		selectDistance: "距離選択▼"
	},
	methods: {
		toggleDropdown() {
			this.showDropdown = !this.showDropdown;
		},
		selectOption(option) {
			this.value = option.value;
			this.selectDistance = option.distance + "▼";
		}
	}
});

//マップ自体の操作ができるインスタンス
const map = new Vue({
	el: "#map",
	data: {
		map: "",
		/*コントローラーが出来たらコメントアウト外す */
		/*areaId: document.getElementById('area-id').value,
		informationType: document.getElementById('infomation-type').value,*/
		latitude: "35.6811673",
		longitude: "139.7670516",

	},/*
	created() {
		
		this.getMapCenter();
		this.getAllData();
		addAllMarker();
	},*/
	mounted() {
		this.initializeMap();
	},
	methods: {
		//中心の緯度経度を取得
		getMapCenter() {
			$.ajax({
				url: 'http://localhost:8080/ルートを設定',
				type: 'GET',
				dataType: "json",
				data: {
					id: this.areaId,
				},
				success: function(data) {
					this.latitude = data.latitude;
					this.longitude = data.longitude;
				}
			});
		},

		//緯度経度を中心に地図表示
		initializeMap() {
			let latitude = parseFloat(this.latitude);
			let longitude = parseFloat(this.longitude);

			var MyLatLng = new google.maps.LatLng(latitude, longitude);
			var Options = {
				zoom: 15,
				center: MyLatLng,
				mapTypeId: 'roadmap'
			};
			this.map = new google.maps.Map(document.getElementById('map'), Options);
		},

	}
});

const pin = new Vue({
	el: "#pin-search",
	data: {
		/*コントローラーが出来たらコメントアウト外す */
		/*areaId: document.getElementById('area-id').value,
		informationType: document.getElementById('infomation-type').value,*/
		institutionList: "",
		priceList: "",
		crimeList: "",
		institutionMarker: [],
		priceMarker: [],
		crimeMarker: [],
		institutionStatus: false, // 施設情報のon/off
		priceStatus: false, // 地価情報のon/off
		crimeStatus: false, // 犯罪情報のon/off
	},
	methods: {
		//全データ取得name=表示名,latitude=緯度,longitude=経度
		getAllData() {
			//緯度経度から施設情報を取得
			let area = 0.009 * selectValue.value;
			$.ajax({
				url: 'http://localhost:8080/ルートを設定',
				type: 'GET',
				dataType: "json",
				data: { latitude: this.latitude, longitude: this.longitude, area: area },
				success: function(data) {
					this.institutionList = data.institutionList;
					this.priceList = data.priceList;
					this.crimeList = data.crimeList;
				}
			});
		},

		//マーカーを追加するクラス(今回は事前に全て追加し、後ほど表示非表示をおこなう)
		addAllMarker() {
			for (var i = 0; i < this.institutionList.length; i++) {
				var institution_latlng = new google.maps.LatLng({ lat: this.institution_latlng[i]['latitude'], lng: this.institution_latlng[i]['longitude'] });
				institutionMarker[i] = new google.maps.Marker({ // マーカーの追加
					position: institution_latlng, // マーカーを立てる位置を指定
					map: this.map // マーカーを立てる地図を指定
				});
			};
			for (var i = 0; i < this.priceList.length; i++) {
				var price_latlng = new google.maps.LatLng({ lat: this.price_latlng[i]['latitude'], lng: this.price_latlng[i]['longitude'] });
				priceMarker[i] = new google.maps.Marker({ // マーカーの追加
					position: price_latlng, // マーカーを立てる位置を指定
					map: this.map // マーカーを立てる地図を指定
				});
			};
			for (var i = 0; i < this.crimeList.length; i++) {
				var crime_latlng = new google.maps.LatLng({ lat: this.crime_latlng[i]['latitude'], lng: this.crime_latlng[i]['longitude'] });
				crimeMarker[i] = new google.maps.Marker({ // マーカーの追加
					position: crime_latlng, // マーカーを立てる位置を指定
					map: this.map // マーカーを立てる地図を指定
				});
			};
			this.dataCopy()
		},

		//表示事項きりかえ。
		institutionChange() {
			if (this.institutionStatus) {
				this.institutionStatus = false;
				// チェックがされた時の処理
				this.institutionMarker.setVisible(false);
			} else {
				this.institutionStatus = true;
				// チェックが外された時の処理
				this.institutionMarker.setVisible(true);
			}
		},
		priceChange() {
			if (this.priceStatus) {
				this.priceStatus = false;
				// チェックがされた時の処理
				this.pricenMarker.setVisible(false);
			} else {
				// チェックが外された時の処理
				this.priceStatus = true;
				this.pricenMarker.setVisible(true);
			}
		},
		crimeChange() {
			if (this.crimeStatus) {
				this.crimeStatus = false;
				// チェックがされた時の処理
				this.crimeMarker.setVisible(false);
			} else {
				this.crimeStatus = true;
				// チェックが外された時の処理
				this.pricenMarker.setVisible(true);
			}
		}
	}
});

const tab = new Vue({
	el: "#tab",
	data: {
		tabInstitutionList: "",
		tabPriceList: "",
		tabCrimeList: ""
	},
	method: {
		dataCopy() {
			this.tabInstitutionList = this.institutionList;
			this.tabPriceList = this.priceList;
			this.tabCrimeList = this.crimeList;
		},
	}
});


//地区検索のインスタンス
new Vue({
	el: "#address-drop",
	data: {
		showDropdown: false,
		addressList: [],
		selectAddress: "近隣から再検索▼",
		id: "",
	},
	created() {
		getPlaceData();
		getAddressList();
	},
	methods: {

		toggleDropdown() {
			this.showDropdown = !this.showDropdown;
		},

		selectAddress(address) {
			this.id = address.id;
			this.selectAddresse = address.name + "▼";
			// 別の動作を実行するコードを追加
			showByAddres(this.id);
			console.log('選択されたオプション:', addressList);
		},

		//緯度経度取得
		getPlaceData() {
			this.longitude = document.getElementById('longitudeInput').value;
			this.latitude = document.getElementById('latitudeInput').value;

		},

		getAddressList() {
			//Addressオブジェクト{name,id}
			// APIリクエストを送信してデータを取得する
			// 以下はaxiosを使用した例ですが、実際のAPIクライアントやリクエストライブラリを適用してください
			axios.post('アドレスを取得するコントローラーパス', {
				param1: this.longitude,
				param2: this.latitude,
			})
				.then(response => {
					this.addressList = response.data;
				})
				.catch(error => {
					console.error('APIリクエストエラー:', error);
				});

		},
		showByAddres(id) {
			// APIリクエストを送信してデータを取得する
			// 以下はaxiosを使用した例ですが、実際のAPIクライアントやリクエストライブラリを適用してください
			axios.get('アドレスをもとに表示するコントローラーパス', {
				id: this.id,
			})
				.catch(error => {
					console.error('APIリクエストエラー:', error);
				});

		},

	}
});

//駅検索のインスタンス
new Vue({
	el: "#station-drop",
	data: {
		longitude: "",
		latitude: "",
		showDropdown: false,
		stationList: [],
		selectStation: "駅から再検索▼",
		id: "",
	},
	created() {
		getStationData();
		getStationList();
	},
	methods: {

		toggleDropdown() {
			this.showDropdown = !this.showDropdown;
		},

		selectAddress(station) {
			this.id = station.id;
			this.selectStation = station.name + "▼";
			// 別の動作を実行するコードを追加
			showByStation(this.id);
			console.log('選択されたオプション:', stationList);
		},

		//緯度経度取得
		getStationData() {
			this.longitude = document.getElementById('longitudeInput').value;
			this.latitude = document.getElementById('latitudeInput').value;

		},

		getAddressList() {
			//Addressオブジェクト{name,id}
			// APIリクエストを送信してデータを取得する
			// 以下はaxiosを使用した例ですが、実際のAPIクライアントやリクエストライブラリを適用してください
			axios.post('駅を取得するコントローラーパス', {
				param1: this.longitude,
				param2: this.latitude,
			})
				.then(response => {
					this.stationList = response.data;
				})
				.catch(error => {
					console.error('APIリクエストエラー:', error);
				});

		},
		showByStation(id) {
			// APIリクエストを送信してデータを取得する
			// 以下はaxiosを使用した例ですが、実際のAPIクライアントやリクエストライブラリを適用してください
			axios.get('アドレスをもとに表示するコントローラーパス', {
				id: this.id,
			})
				.catch(error => {
					console.error('APIリクエストエラー:', error);
				});

		},

	}
});
//駅検索終了
