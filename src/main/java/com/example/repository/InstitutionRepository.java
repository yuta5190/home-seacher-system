package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.PinInfo;

/**
 * 建物情報のリポジトリ. yoshida_yuuta
 */
@Repository
public class InstitutionRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 建物情報のローマッパー
	 */
	private static final RowMapper<PinInfo> INSTITUTION_ROW_MAPPER = (rs, i) -> {
		PinInfo pinInfo = new PinInfo();
		pinInfo.setName(rs.getString("name"));
		// バグの為逆にしてる
		pinInfo.setLatitude(rs.getDouble("latitude"));
		pinInfo.setLongitude(rs.getDouble("longitude"));
		pinInfo.setDetail(rs.getString("available_days"));
		return pinInfo;
	};

	/**
	 * 緯度経度と倍率から範囲内の建物を検索するメソッド
	 * 
	 * @param longitude 経度
	 * @param latitude  緯度
	 * @param ratio     範囲(km)
	 * @return 建物情報のリスト
	 */
	public List<PinInfo> selectMapInfo(double longitude, double latitude, double ratio) {
		
		//0.015が大体1km
		Double numWidth = 0.015 * ratio;
		Double longmax = longitude + numWidth;
		Double longmin = longitude - numWidth;
		Double latmax = latitude + numWidth;
		Double latmin = latitude - numWidth;
		SqlParameterSource param = new MapSqlParameterSource().addValue("longmax", longmax).addValue("longmin", longmin)
				.addValue("latmax", latmax).addValue("latmin", latmin);
		String sql = "SELECT name,longitude, latitude,available_days FROM institutions  WHERE longitude BETWEEN :longmin AND :longmax AND latitude BETWEEN :latmin AND :latmax";
		List<PinInfo> institutionList = template.query(sql, param, INSTITUTION_ROW_MAPPER);
		return institutionList;
	}
}
