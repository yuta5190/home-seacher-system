//地図の初期表示を行うVue
//store.stateのついているものはvuexの要素

const setMap = Vue.createApp({}); 
setMap.component('map-create-vue', { 
//map-create-vueに表示した段階で実行するもの
   mounted() { 
     this.$nextTick(() => { 
     this.getPlaceData();
     this.initializeMap(); 
      }); 
   }, 
   methods: { 
   //位置情報をhtmlより取得
     getPlaceData() { 
       store.state.longitude =document.getElementById('longitudeInput').value; 
       store.state.latitude =document.getElementById('latitudeInput').value; 
     }, 
     //地図の生成
     initializeMap() { 
       let latitude = parseFloat(store.state.latitude); 
       let longitude =parseFloat(store.state.longitude); 
       
       //中心の位置
       const position = { lat: latitude, lng:longitude }; 
       store.state.centerPosition = new google.maps.LatLng(store.state.latitude,  store.state.longitude)
      //地図のオプション 
       store.state.option= { 
          zoom:  14,
          center: position, 
          mapTypeId: 'roadmap',
       }; 
      
       store.state.map = new google.maps.Map(
       document.getElementById('map'),store.state.option); 
	   
	   //中心の位置にヒト型マーカーを設定
	   store.state.currentLocationMarker= new google.maps.Marker({
				position: store.state.centerPosition,
				icon:{url:'/img/currentLocation.png',scaledSize : new google.maps.Size(110, 110)}
			});
	  
	  //作成した地図をset
	   store.state.currentLocationMarker.setMap(store.state.map);
       },
       
   },
   template: `
   <div class="map"></div>`,
    }); 

setMap.mount('#map-style');