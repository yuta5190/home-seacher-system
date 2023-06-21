package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Choume;
import com.example.domain.Town;
import com.example.repository.ChoumeRepository;
import com.example.repository.TownRepository;

/**
 * 外部APIの業務処理を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Service
@Transactional
public class ApiSsrvice {

	@Autowired
	private TownRepository townRepository;
	@Autowired
	private ChoumeRepository choumeRepository;

	/**
	 * 市区町村IDから町域(丁目を除く)リストを取得.
	 * 
	 * @param municipalityId 市区町村ID
	 * @return 該当町域(丁目を除く)のリスト
	 */
	public List<Town> getTownListByMunicipaliryId(int municipalityId) {
		return townRepository.getTownListByMunicipaliryId(municipalityId);
	}

	public List<Choume> getChoumeListByTownId(int townId) {
		return choumeRepository.getChoumeListByTownId(townId);
	}

}
