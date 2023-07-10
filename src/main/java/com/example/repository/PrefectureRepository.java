package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * prefecturesテーブルの処理を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Repository
public class PrefectureRepository {

	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
//	public List<Prefecture> getPrefectureList(int prefectureId){
//		
//		
//	}
}
