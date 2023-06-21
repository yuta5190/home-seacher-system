package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Municipality;

/**
 * municipalitiesテーブルへの処理を行うクラス.
 *
 * @author sugaharatakamasa
 *
 */
@Repository
public class MunicipalityRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	public List<Municipality> getMunicipalityListByPrefectureId(int prefectureId) {

		String sql = "SELECT id,name,name_kana,name_rome,prefecture_id FROM municipalities WHERE prefecture_id = :prefectureId ORDER BY name";
		List<Municipality> municipalityList = template.query(sql,
				new MapSqlParameterSource("prefectureId", prefectureId),
				new BeanPropertyRowMapper<Municipality>(Municipality.class));

		return municipalityList;

	}
}
