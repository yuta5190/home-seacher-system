package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Town;

/**
 * townsテーブルへの処理を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Repository
public class TownRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	public List<Town> getTownListByMunicipaliryId(int municipalityId) {

		String sql = "SELECT id,municipality_id,name,name_kana,name_rome FROM towns WHERE deleted = false AND municipality_id = :municipalityId ORDER BY name";
		List<Town> townList = template.query(sql, new MapSqlParameterSource("municipalityId", municipalityId),
				new BeanPropertyRowMapper<Town>(Town.class));
		return townList;
	}

}
