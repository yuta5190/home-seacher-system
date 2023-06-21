package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Choume;

/**
 * choumeテーブルへの処理を行うクラス.
 *
 * @author sugaharatakamasa
 *
 */
@Repository
public class ChoumeRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	public List<Choume> getChoumeListByTownId(int townId) {
		String sql = "SELECT id,town_id,address_id,name FROM choume WHERE deleted = false AND town_id = :townId ORDER BY name_kana";
		List<Choume> choumeList = template.query(sql, new MapSqlParameterSource("townId", townId),
				new BeanPropertyRowMapper<Choume>(Choume.class));

		return choumeList;

	}
}
