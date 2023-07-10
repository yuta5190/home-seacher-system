package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.PinInfo;
import com.example.repository.InstitutionRepository;

/**
 * 建物情報を扱うサービスクラス.
 */
@Service
@Transactional
public class InstitutionService {

	@Autowired
	public InstitutionRepository institutionRepository;
	
	/**
	 * 緯度経度と範囲倍率を渡すメソッド.
	 * @param longitude　経度
	 * @param latitude　緯度
	 * @param ratio　範囲(km)
	 * @return  住所情報
	 */
	public List<PinInfo> selectMapInfo(double longitude,double latitude,int ratio){
		List<PinInfo> institutionList=institutionRepository.selectMapInfo(longitude, latitude,ratio);
		return institutionList;
	}
}
