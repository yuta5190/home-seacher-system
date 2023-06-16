package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Station;
import com.example.service.StationService;

@RestController
@RequestMapping("/api")
public class StationAPIController {

	@Autowired
	private StationService stationService;

	@GetMapping("/stations")
	public List<Station> getStations(@RequestParam Integer lineId) {
		return stationService.findByLineId(lineId);
	}
}
