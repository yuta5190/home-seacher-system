package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Choume;
import com.example.domain.Municipality;
import com.example.domain.Town;
import com.example.form.SearchByAddressForm;
import com.example.service.SearchByAddressService;

/**
 * 住所検索画面の処理を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Controller
@RequestMapping("/search-by-address")
public class SearchByAddressController {

	private static final int TOKYO_ID = 13;

	@Autowired
	private SearchByAddressService service;

	@GetMapping("")
	public String showPage(Model model, SearchByAddressForm form) {
		System.out.println(form);

		List<Municipality> municipalitylist = service.getMunicipalityListByPrefectureId(TOKYO_ID);
		model.addAttribute("municipalityList", municipalitylist);

		if (form.getMunicipalityId() != null) {
			List<Town> townList = service.getTownListByMunicipaliryId(form.getMunicipalityId());
			model.addAttribute("townList", townList);
		}
		if (form.getTownId() != null) {
			List<Choume> choumeList = service.getChoumeListByTownId(form.getTownId());
			model.addAttribute("choumeList", choumeList);
		}

		return "search-by-address";

	}

	@PostMapping("search")
	public String search(Model model, @Validated SearchByAddressForm form, BindingResult br) {

		if (br.hasErrors()) {
			return showPage(model, form);
		}

		
		return "redirect:/map/?id=" + form.getAddressId() + "&infomationType=address";
	}

}