package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.RailwayLine;

/**
 * railway_linesテーブルを操作するリポジトリ.
 * 
 * @author yoshimatsushouta
 *
 */
@Repository
public class RailwayLineRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * RailwayLineオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<RailwayLine> RAILWAY_LINE_ROW_MAPPER = new BeanPropertyRowMapper<>(
			RailwayLine.class);

	/**
	 * 東京駅から100000メートル以内の沿線を検索する.
	 * 
	 * @return 沿線情報のリスト
	 */
	public List<RailwayLine> findByWithin100000MetersTokyo() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append(" id, company_id, line_name, line_name_k, line_name_h, longitude, latitude");
		sql.append(" FROM");
		sql.append(" railway_lines");
		sql.append(" WHERE");
		// PostGisの距離計算によるWHERE
		sql.append(
				" ST_DWithin (ST_MakePoint (longitude, latitude)::geography, ST_MakePoint (139.7671250000, 35.6812362100) :: geography, 100000);");

		List<RailwayLine> railwayLineList = template.query(sql.toString(), RAILWAY_LINE_ROW_MAPPER);
		return railwayLineList;
	}
}
