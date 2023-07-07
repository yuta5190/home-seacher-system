package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Address;
import com.example.domain.MapInfo;
import com.example.domain.Station;
import com.example.service.InstitutionService;
import com.example.service.SearchByAddressService;
import com.example.service.StationService;

/**
 * map表示がめん.
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
	 * 仮画面表示(get時)
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String getIndex(Model model, Integer id, String infomationType) {
		MapInfo mapInfo = new MapInfo();
		if (infomationType.equals("station")) {
			Station station = stationService.load(id);
			mapInfo.setLatitude(station.getLatitude());
			mapInfo.setLongitude(station.getLongitude());
		}
		if(infomationType.equals("address")) {
			Address address =  searchByAddressService.load(id);
			mapInfo.setLatitude(address.getLatitude());
			mapInfo.setLongitude(address.getLongitude());
 		}
		model.addAttribute("id", id);
		model.addAttribute("infomationType", infomationType);
		model.addAttribute("mapInfo", mapInfo);
		return "map";
	}

	/**
	 * map表示のメソッド
	 * 
	 * @param model          モデル
	 * @param id             それぞれのid
	 * @param infomationType そのidが何のidなのか
	 * @return
	 */
	@PostMapping("/")
	public String postIndex(Model model, Integer id, String infomationType) {
		MapInfo mapInfo = new MapInfo();
		if (infomationType.equals("station")) {
			Station station = stationService.load(id);
			mapInfo.setLatitude(station.getLatitude());
			mapInfo.setLongitude(station.getLongitude());
		}
		if(infomationType.equals("address")) {
			Address address =  searchByAddressService.load(id);
			mapInfo.setLatitude(address.getLatitude());
			mapInfo.setLongitude(address.getLongitude());
 		}
		model.addAttribute("id", id);
		model.addAttribute("infomationType", infomationType);
		model.addAttribute("mapInfo", mapInfo);
		return "map";
	}

	@PostMapping("/getdata")
	@ResponseBody
	public Map<String,List<MapInfo>> getData(@RequestBody Map<String, String> requestData) {
	    Double longitude = Double.parseDouble(requestData.get("longitude"));
	    Double latitude = Double.parseDouble(requestData.get("latitude"));
		List<MapInfo> mapInfo = institutionService.selectMapInfo(longitude, latitude);
		Map<String,List<MapInfo>> data = new HashMap<>();
		data.put("institutionList", mapInfo);
		System.out.println("longitude:"+mapInfo);
		return data;
	}
}
