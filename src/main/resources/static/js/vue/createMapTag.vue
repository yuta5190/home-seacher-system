//近隣情報にピンをうつvue　マーカーの名前をばらけさせれば、通しnumが必要無くなりそうなので改善予定

const createTag = Vue.createApp({}); 
createTag.component('tag-create-vue', { 
 data() {
    return {
      //建物情報の表示有無
      institutionStatus: false,
      //どのピンの範囲が建物のものなのか設定。
      institutionTopNum: 0,
      institutionEndNum: 0,
      
      //地価の表示有無
      priceStatus: false,
      priceNum: 0,
      
      //犯罪情報の表示有無
      crimeStatus: false,
      crimeNum: 0}
    },
mounted() { 
setTimeout(() => {
//初期倍率は3km
store.state.ratio =3;
this.fetchData();
		},100);
}, 

methods: { 
//位置と倍率から情報を取得
 fetchData() {
    axios.post('/map/getdata', { longitude: store.state.longitude, latitude: store.state.latitude, ratio: store.state.ratio })
      .then(response => {
        var data = response.data;
        store.state.institutionList = data.institutionList;
        // コントローラーからListを遅れたら下のコメントアウトをはがしてください
        // store.state.priceList = data.priceList;
        // store.state.crimeList = data.crimeList;
      });
  },
     //施設情報の表示メソッド
		changeInstitution() {
			this.institutionStatus = !this.institutionStatus;
			//表示中のタグを全て消す処理
			if (this.institutionStatus === false) {
			for (let deleteNum = this.institutionTopNum; deleteNum < this.institutionEndNum; deleteNum++) {
			store.state.marker[deleteNum].setVisible(false);
			store.state.infoWindow[deleteNum].close();}
			};
			//現在ONの物を再表示
			this.putmarker();
		},
	
	//価格情報の表示メソッド	
		changePrice() {
			this.priceStatus = !this.priceStatus;
			this.putmarker();

		},
	
	//犯罪情報の表示メソッド
		changeCrime() {
			this.crimeStatus = !this.crimeStatus;
			this.putmarker();

		},
		
		putmarker() {
			//通し番号（マーカーが被ると上書きされるため、異なるグループを表示するには必要）
			let totalNum = 0;
			
			//増やす際は以下を全てコピーし、名前(犯罪だったらinstitution➤crimeに変更)
			if (this.institutionStatus === true) {
				this.institutionTopNum = totalNum;

				//以下リストを回し、ピンとウインドウを作成
				for (let i = 0; i < store.state.institutionList.length; i++) {
					this.institutionNum++;
					let j = totalNum;
					store.state.marker[j] = new google.maps.Marker({
						position: new google.maps.LatLng(store.state.institutionList[i].latitude, store.state.institutionList[i].longitude),
					});
					store.state.marker[j].setMap(store.state.map);
					store.state.infoWindow[j] = new google.maps.InfoWindow({
						content: '<div> ' + store.state.institutionList[j].name + '<div>',
					});

					store.state.marker[j].addListener('click', () => {
						for (closeNum = 0; closeNum < totalNum; closeNum++) {
							store.state.infoWindow[closeNum].close();
						}
						store.state.infoWindow[j - 1].open(store.state.map, store.state.marker[j - 1]);
					});

					j++;
					totalNum++;
				} this.institutionEndNum = totalNum;
			}
		}
},
template: `
  <div id="pin-search">
    <h2>検索条件</h2>
<div id="pin-check">
    <input type="checkbox" id="institutions" name="selection" value="institutions" v-on:click="changeInstitution">
    <label for="institutions">施設情報</label>
    <input type="checkbox" id="price" name="selection" value="price" v-on:click="changePrice">
    <label for="price">値段</label>
    <input type="checkbox" id="crimes" name="selection" value="crimes" v-on:click="changeCrime">
    <label for="crimes">犯罪情報</label>
</div>
</div>	` }); 
createTag.mount('#option-style');