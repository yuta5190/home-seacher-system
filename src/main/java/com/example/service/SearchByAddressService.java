package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Address;
import com.example.domain.Choume;
import com.example.domain.Municipality;
import com.example.domain.Town;
import com.example.repository.AddressRepository;
import com.example.repository.ChoumeRepository;
import com.example.repository.MunicipalityRepository;
import com.example.repository.TownRepository;

/**
 * 地域選択画面の業務処理を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Service
@Transactional
public class SearchByAddressService {

	@Autowired
	private MunicipalityRepository municipalityRepository;
	@Autowired
	private TownRepository townRepository;
	@Autowired
	private ChoumeRepository choumeRepository;
	@Autowired
	private AddressRepository addressRepository;

	/**
	 * 都道府県IDから市区町村リストを取得.
	 * 
	 * @param prefectureId 都道府県ID
	 * @return 該当市区町村のリスト
	 */
	public List<Municipality> getMunicipalityListByPrefectureId(int prefectureId) {
		return municipalityRepository.getMunicipalityListByPrefectureId(prefectureId);
	}

	/**
	 * 市区町村IDから町域(丁目を除く)リストを取得.
	 * 
	 * @param municipalityId 市区町村ID
	 * @return 該当町域(丁目を除く)のリスト
	 */
	public List<Town> getTownListByMunicipaliryId(int municipalityId) {
		return townRepository.getTownListByMunicipaliryId(municipalityId);
	}
	
	/**
	 * 町域IDから丁目リストを取得.
	 * 
	 * @param municipalityId 町域ID
	 * @return 該当丁目のリスト
	 */
	public List<Choume> getChoumeListByTownId(int townId) {
		return choumeRepository.getChoumeListByTownId(townId);
	}
	
	public Address load(int id) {
		return addressRepository.getAddressById(id).get(0);
	}
	

}
