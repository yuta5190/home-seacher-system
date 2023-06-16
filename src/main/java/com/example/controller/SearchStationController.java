package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.RailwayLine;
import com.example.domain.Station;
import com.example.service.RailwayLineService;

@RequestMapping("/station")
@Controller
public class SearchStationController {

	@Autowired
	private RailwayLineService railwayLineService;

	@GetMapping("/search")
	public String toSeatch(Model model) {
		List<RailwayLine> railwayLineList = railwayLineService.findByWithin100000MetersTokyo();
		List<Station> stationList = new ArrayList<>();

		model.addAttribute("stationList", stationList);
		model.addAttribute("railwayLineList", railwayLineList);
		return "station-search.html";
	}
}
