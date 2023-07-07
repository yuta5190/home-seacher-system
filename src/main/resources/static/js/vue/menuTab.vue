//左側のタブ（近隣地区や近隣駅の再表示に関わるvue）

const tabMenuCreate = Vue.createApp({}); 
tabMenuCreate.component('tab-menu-vue', { 
 data() {
    return {
    distanceValue:"",
		showDistanceDropdown: false,
		distanceValue: "",
		showAddressDropdown: false,
		showStationDropdown: false,
		distanceOptions: [{distance:'1km',value:"1"}, {distance:'3km',value:"3"}, {distance:'5km',value:"5"}],
		addressOptions: [],
		stationOptions: [],
		option: '',
		selectDistance: "距離選択▼",
		selectAddress: "近隣地区選択▼",
		selectStation: "近隣駅選択▼",
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
   　　　this.fetchData(distance)
    　　　　if(distance==="1"){
    　　　　　　store.state.map.setZoom(14.5)
    　　 　　};
　　　　  　　if(distance==="3"){
  　　　　　　　　　　 store.state.map.setZoom(13.5) 
  　　　　　　};
  　　　　　　if(distance==="5"){
   　　　　　　　　store.state.map.setZoom(12.5) 
  　　　　　　};
　　　},
  //施設情報の取得
  　　fetchData(ratio) {
    　　　　　　　axios.post('/map/getdata', { longitude: store.state.longitude, latitude: store.state.latitude, ratio: ratio })
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
	}},
　　template: `

<div id="distance-drop">
　　<button class="select-button" v-on:click="changeDistanceDropdown">{{selectDistance}}</button>
　　<transition name="tabslide">
　　　　<ul class="dropdown-menu" v-show="showDistanceDropdown">
      <li v-for="distance in distanceOptions" :key="distance" v-on:click="selectDistanceOption(distance)" class="options">{{distance.distance}}</li>
    </ul>
  </transition>
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