package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.MapInfo;
import com.example.domain.PinInfo;
import com.example.domain.Station;
import com.example.domain.TabItem;
import com.example.service.InstitutionService;
import com.example.service.SearchByAddressService;
import com.example.service.StationService;

/**
 * map表示コントローラー.
 * 
 * @author yoshida_yuuta
 */
@RequestMapping("/map")
@Controller
public class ShowMapController {
	@Autowired
	private StationService stationService;
	@Autowired
	private SearchByAddressService searchByAddressService;
	@Autowired
	private InstitutionService institutionService;

	/**
	 * map表示のメソッド
	 * 
	 * @param model          モデル
	 * @param id             DBのid
	 * @param infomationType idの種類(addressかstationの物か)
	 * @return　　地図表示画面
	 */
	@PostMapping("/")
	public String postIndex(Model model, Integer id, String infomationType) {
		MapInfo mapInfo = new MapInfo();
		if (infomationType.equals("station")) {
			Station station = stationService.load(id);
			mapInfo.setLatitude(station.getLatitude());
			mapInfo.setLongitude(station.getLongitude());
		}
		model.addAttribute("id", id);
		model.addAttribute("infomationType", infomationType);
		model.addAttribute("mapInfo", mapInfo);
		return "map";
	}

	/**
	 * @param requestData　Vueで送信したデータ(緯度、経度、地図倍率)
	 * @return　createMapTag.vue  fetchData
	 */
	@PostMapping("/getdata")
	@ResponseBody
	public Map<String,List<PinInfo>> getData(@RequestBody Map<String, String> requestData) {
	    Double longitude = Double.parseDouble(requestData.get("longitude"));
	    Double latitude = Double.parseDouble(requestData.get("latitude"));
	    double ratio = Double.parseDouble(requestData.get("ratio"));
	    //３種のオブジェクトで行おうと思いましたが、returnできるMapのvaluが一種類であり、３回通信が必要となるため、PinInfoに合わせて送ってます。増やしたい情報有れば、domainに追加して問題ないです、
		List<PinInfo> institutionInfo = institutionService.selectMapInfo(longitude, latitude,ratio);
		//上と同じようにListをつくり、mapに詰めればhtmlで使用可能
		Map<String,List<PinInfo>> data = new HashMap<>();
		data.put("institutionList", institutionInfo);
		return data;
	}
	
	/**
	 * 近隣駅、地区検索
	 * @param requestData　表示地区の緯度経度
	 * @return　menuTab.vue
	 */
	@PostMapping("/getOptions")
	@ResponseBody
	public Map<String,List<TabItem>> getOptions(@RequestBody Map<String, String> requestData) {
	    Double longitude = Double.parseDouble(requestData.get("longitude"));
	    Double latitude = Double.parseDouble(requestData.get("latitude"));
		List<TabItem> addressOptions = searchByAddressService.getAddressByLonLat(longitude, latitude);
		List<TabItem> stationOptions = stationService.getStationByLonLat(longitude, latitude);
		Map<String,List<TabItem>> data = new HashMap<>();
		data.put("addressOptions", addressOptions);
		data.put("stationOptions", stationOptions);
		return data;
	}
}
