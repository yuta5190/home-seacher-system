package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.MapInfo;
import com.example.repository.InstitutionRepository;

@Service
@Transactional
public class InstitutionService {

	@Autowired
	public InstitutionRepository institutionRepository;
	
	public List<MapInfo> selectMapInfo(double longitude,double latitude){
		List<MapInfo> institutionList=institutionRepository.selectMapInfo(longitude, latitude);
		return institutionList;
	}
}
