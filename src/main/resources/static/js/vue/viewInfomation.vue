//タブに情報を表示させるvue

const createTab = Vue.createApp({}); 
createTab.component('tab-view-vue', { 
data(){return{
institutionList:store.state.institutionList
}
},mounted(){
 setTimeout(() => {
 //vuexに保存された施設情報を取得
      this.institutionList = store.state.institutionList;
    }, 300);

},
methods: {
//地図の中心移動
  moveCenter(longitude,latitude){
  store.state.map.panTo(new google.maps.LatLng(latitude,longitude));
},

}, 
template: `
<input id="tab1" type="radio" name="tab_btn">
	<input id="tab2" type="radio" name="tab_btn">
	<input id="tab3" type="radio" name="tab_btn">

	<div class="tab_area">
		<label class="tab1_label" for="tab1" >施設情報</label>
		<label class="tab2_label" for="tab2">値段</label>
		<label class="tab3_label" for="tab3">犯罪情報</label>
	</div>
	<div class="panel_area">
		<div id="panel1" class="tab_panel"  >
			<table>
    <tr>
      <th>名前</th>
      <th>営業時間</th>
    </tr>
    <tr v-for="institution in institutionList" :key="institution.id">
      <td v-on:click="moveCenter(institution.longitude,institution.latitude)">{{ institution.name }}</td>
      <td>{{ institution.detail }}</td>
    </tr>
  </table>
		</div>
		<div id="panel2" class="tab_panel" >
			<p>panel2</p>
		</div>
		<div id="panel3" class="tab_panel" >
			<p>panel3</p>
		</div>
	</div>
	` }); 
createTab.mount('.tab_wrap');