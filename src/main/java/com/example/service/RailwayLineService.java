package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.RailwayLine;
import com.example.repository.RailwayLineRepository;

/**
 * 沿線情報を操作する業務処理を行うサービス.
 * 
 * @author yoshimatsushouta
 *
 */
@Service
public class RailwayLineService {

	@Autowired
	private RailwayLineRepository railwayLineRepository;

	/**
	 * 東京駅から10万m以内の沿線一覧情報を取得します。
	 * 
	 * @return 上記の情報リスト
	 */
	public List<RailwayLine> findByWithin100000MetersTokyo() {
		List<RailwayLine> railwayLineList = railwayLineRepository.findByWithin100000MetersTokyo();
		return railwayLineList;
	}
}
