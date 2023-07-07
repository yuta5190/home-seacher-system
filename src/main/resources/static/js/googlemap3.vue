const setMap = Vue.createApp({}); 
setMap.component('map-create-vue', { 
mounted() { 
this.$nextTick(() => { 
this.getPlaceData();
this.initializeMap(); 
}); 
}, 
methods: { 
getPlaceData() { 
store.state.longitude =document.getElementById('longitudeInput').value; 
store.state.latitude =document.getElementById('latitudeInput').value; 
}, 
initializeMap() { 
let latitude = parseFloat(store.state.latitude); 
let longitude =parseFloat(store.state.longitude); 
const position = { 
lat: latitude, 
lng:longitude 
}; 
store.state.option= { 
zoom: 13, 
center: position, 
mapTypeId: 'roadmap',
}; 
store.state.map = new google.maps.Map(
document.getElementById('map'),store.state.option); 
		store.state.currentLocationMarker= new google.maps.Marker({
						position: new google.maps.LatLng(store.state.latitude,  store.state.longitude),
						 icon:{url:'/img/currentLocation.png',scaledSize : new google.maps.Size(110, 110)}
					});
			store.state.currentLocationMarker.setMap(store.state.map);
},
},
template: `<div class="map"></div>`,
}); 

setMap.mount('#map-style');


