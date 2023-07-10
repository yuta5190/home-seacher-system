//左側のタブ（近隣地区や近隣駅の再表示に関わるvue）

const tabMenuCreate = Vue.createApp({}); 
tabMenuCreate.component('tab-menu-vue', { 
 data() {
    return {
    distanceValue:"",
		showDistanceDropdown: false,
		showCategoryDropdown: false,
		distanceValue: "",
		showAddressDropdown: false,
		showStationDropdown: false,
		distanceOptions: [{distance:'500m',value:"0.5"}, {distance:'1km',value:"1"}, {distance:'3km',value:"3"}],
		categoryList:store.state.categoryList,
		selectedCategories: [],
		addressOptions: [],
		stationOptions: [],
		option: '',
		selectDistance: "距離選択▼",
		selectAddress: "近隣地区選択▼",
		selectStation: "近隣駅選択▼",
		resultsList: [],
      }
    },
    
mounted() { 
　　setTimeout(() => {
//初期表示で緯度経度から近隣情報を取得
　　　　　　　　axios.post('/map/getOptions', { longitude: store.state.longitude, latitude: store.state.latitude })
		.then(response => {
				const data = response.data;
				this.addressOptions = data.addressOptions;
				this.stationOptions = data.stationOptions;
				
				})
　　}, 1000);
}, 
methods: {  
  //距離のボタンに応じて地図の倍率を変える
  　emitInitializeMap(distance) {
  store.state.ratio=distance;
   　　　this.fetchData()
    　　　　if( store.state.ratio==="0.5"){
    　　　　　　store.state.map.setZoom(15.5)
    　　 　　};
　　　　  　　if( store.state.ratio==="1"){
  　　　　　　　　　　 store.state.map.setZoom(14.5) 
  　　　　　　};
  　　　　　　if( store.state.ratio==="3"){
   　　　　　　　　store.state.map.setZoom(13.5) 
  　　　　　　};
　　　},
  //施設情報の取得
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
  
  //ドロップダウンの表示設定
　　　　changeDistanceDropdown() {
		　　　this.showDistanceDropdown = !this.showDistanceDropdown;
	},
	changeCategoryDropdown() {
			this.showCategoryDropdown = !this.showCategoryDropdown;
	},
　　　　changeAddressDropdown() {
		　　　this.showAddressDropdown = !this.showAddressDropdown;
	},	
　　　　changeStationDropdown() {
			this.showStationDropdown = !this.showStationDropdown;
	},
	
	//距離を選択するとの数が一番上のタブに表示される
　　　　selectDistanceOption(options) {
			this.distanceValue= options.value;
			this.selectDistance = options.distance + "▼";
			this.emitInitializeMap(options.value);
			this.searchNearBy();
	},
	 searchNearBy() {
    store.state.service = new google.maps.places.PlacesService(store.state.map);
    console.log(this.selectedCategories);
    store.state.service.nearbySearch({
      location: store.state.centerPosition,
      radius: store.state.ratio*1000,
      type: this.selectedCategories,
    }, this.displayResults);
  },
  
  displayResults(results, status) {
    if (status === google.maps.places.PlacesServiceStatus.OK) {
      // 取得した結果をリストに追加
      for (var i = 0; i < results.length; i++) {
        this.resultsList.push(results[i]);
        store.state.marker[i] = new google.maps.Marker({
                        text : results[i].name,
						position:results[i].geometry.location,
					});
      }
      
    }
}
	},
　　template: `<div id="distance-drop"><button class="select-button" v-on:click="changeDistanceDropdown">{{selectDistance}}</button>
　　<transition name="tabslide">
　　　　<ul class="dropdown-menu" v-show="showDistanceDropdown"><li v-for="distance in distanceOptions" :key="distance" v-on:click="selectDistanceOption(distance)" class="options">{{distance.distance}}</li></ul>
  </transition></div>


<div id="category-drop">
  <button class="select-button" v-on:click="changeCategoryDropdown">カテゴリー選択▼</button>
  <form>
    <table class="dropdown-menu" v-show="showCategoryDropdown">
      <tr v-for="category in categoryList" :key="index">
        <td >
        <label class="ECM_CheckboxInput"><input name="category" class="ECM_CheckboxInput-Input" type="checkbox" v-bind="selectedCategories" :value=category.name v-on:click="searchNearBy"><span class="ECM_CheckboxInput-DummyInput"></span><span class="ECM_CheckboxInput-LabelText"> {{category.ja}}</span></label>
       </td>
      </tr>
    </table>
    </form>
</div>


    <div id="address-drop" >
      <button class="select-button" v-on:click="changeAddressDropdown">{{selectAddress}}</button>
      <transition name="tabslide5">
      <ul class="dropdown-menu"  v-show="showAddressDropdown">
        <li v-for="address in addressOptions" :key="address"   class="options">
        <form action="/map/" method="post">
          <input type="hidden" v-bind:value=address.id name="id" />
          <input type="hidden" value="address" name="infomationType"/>
          <button  class="select-button" type=submit>{{address.name}} </button>
         </form></li>
      </ul>
        </transition>
    </div>
    
    
        <div id="station-drop" >
      <button class="select-button" v-on:click="changeStationDropdown">{{selectStation}}</button>
        <transition name="tabslide5">
      <ul class="dropdown-menu"  v-show="showStationDropdown">
        <li v-for="station in stationOptions" :key="station"   class="options">    
        <form action="/map/" method="post">
          <input type="hidden" v-bind:value=station.id name="id" />
           <input type="hidden" value="station" name="infomationType"/>
           <button  class="select-button" type=submit>{{station.name}} </button>
         </form></li>
      </ul>
      </transition>
    </div>`
		 }); 
tabMenuCreate.mount('#menu');