package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.MapInfo;

@Repository
public class InstitutionRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<MapInfo> INSTITUTION_ROW_MAPPER = (rs, i) -> {
		MapInfo mapInfo = new MapInfo();
		mapInfo.setName(rs.getString("name")); 
		//バグの為逆にしてる
		mapInfo.setLatitude(rs.getDouble("longitude")); 
		mapInfo.setLongitude(rs.getDouble("latitude")); 
		
		return mapInfo;
	};
	public List<MapInfo> selectMapInfo(double longitude,double latitude) {
		//名前逆
		Double latmax=longitude+0.045;
		Double latmin=longitude-0.045;
		Double longmax=latitude+0.045;
		Double longmin=latitude-0.045;
		SqlParameterSource param = new MapSqlParameterSource().addValue("longmax", longmax).addValue("longmin", longmin).addValue("latmax", latmax).addValue("latmin", latmin);
		String sql = "SELECT name,longitude, latitude FROM institutions  WHERE longitude BETWEEN :longmin AND :longmax AND latitude BETWEEN :latmin AND :latmax";
		List<MapInfo> institutionList = template.query(sql, param, INSTITUTION_ROW_MAPPER);
		return institutionList;
	}
}
