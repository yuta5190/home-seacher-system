package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Station;
import com.example.repository.StationRepository;

/**
 * 駅情報を操作する業務処理を行うサービス.
 * 
 * @author yoshimatsushouta
 *
 */
@Service
public class StationService {

	@Autowired
	private StationRepository stationRepoitory;

	/**
	 * 駅一覧情報を取得します.
	 * 
	 * @return 駅一覧情報リスト
	 */
	public List<Station> findAll() {
		List<Station> stationList = stationRepoitory.findAll();
		return stationList;
	}

	/**
	 * 沿線IDから駅一覧情報を取得します.
	 * 
	 * @param lineId 沿線ID
	 * @return 駅一覧情報リスト
	 */
	public List<Station> findByLineId(int lineId) {
		List<Station> stationList = stationRepoitory.findByLineId(lineId);
		return stationList;
	}
}
