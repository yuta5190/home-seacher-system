new Vue({
	el: "#distance-drop",
	data: {
		value:"",
		showDropdown: false,
		options: [{distance:'1km',value:"1"}, {distance:'3km',value:"3"}, {distance:'5km',value:"5"}],
		selectedOption: '',
		selectDistance: "距離選択▼"
	},
	methods: {
		toggleDropdown() {
			this.showDropdown = !this.showDropdown;
		},
		selectOption(option) {
			this.value= option.value;
			this.selectDistance = option.distance + "▼";
			// 別の動作を実行するコードを追加
			console.log('選択されたオプション:', option);
		}
	}
});


//アドレス検索
/*
new Vue({
	el: "#address-drop",
	data: {
		longitude: "",
		latitude: "",
		showDropdown: false,
		addressList: [],
		selectAddress: "近隣から再検索▼",
		id:"",
	},
	created(){
			getPlaceData();
			getAddressList();
		},
	methods: {
		
		toggleDropdown() {
			this.showDropdown = !this.showDropdown;
		},
		
		selectAddress(address) {
			this.id=address.id;
			this.selectAddresse = address.name + "▼";
			// 別の動作を実行するコードを追加
			showByAddres(this.id);
			console.log('選択されたオプション:', addressList);
		},
		
		//緯度経度取得
		getPlaceData(){
			 this.longitude=document.getElementById('longitudeInput').value;
			 this.latitude=document.getElementById('latitudeInput').value;
			
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
//駅検索終了


new Vue({
	el: "#station-drop",
	data: {
		longitude: "",
		latitude: "",
		showDropdown: false,
		stationList: [],
		selectStation: "駅から再検索▼",
		id:"",
	},
	created(){
			getStationData();
			getStationList();
		},
	methods: {
		
		toggleDropdown() {
			this.showDropdown = !this.showDropdown;
		},
		
		selectAddress(station) {
			this.id=station.id;
			this.selectStation = station.name + "▼";
			// 別の動作を実行するコードを追加
			showByStation(this.id);
			console.log('選択されたオプション:', stationList);
		},
		
		//緯度経度取得
		getStationData(){
			 this.longitude=document.getElementById('longitudeInput').value;
			 this.latitude=document.getElementById('latitudeInput').value;
			
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
})
*/;
//駅検索終了