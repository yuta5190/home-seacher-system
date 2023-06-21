package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Choume;
import com.example.domain.Town;
import com.example.service.ApiSsrvice;

/**
 * 外部APIの処理を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private ApiSsrvice service;

	@GetMapping("/getTownList")
	public Map<String, List<Town>> getTownListByMunicipalityId(int municipalityId) {
		Map<String, List<Town>> map = new HashMap<>();
		List<Town> townList = service.getTownListByMunicipaliryId(municipalityId);
		map.put("townList", townList);

		return map;
	}

	@GetMapping("/getChoumeList")
	public Map<String, List<Choume>> getChoumeListByTownId(int townId) {
		Map<String, List<Choume>> map = new HashMap<>();
		List<Choume> choumeList = service.getChoumeListByTownId(townId);
		if(choumeList.isEmpty()) {
			//問題ありなのでログや報告の処理を実装
		}
		map.put("choumeList", choumeList);

		return map;
	}

}
