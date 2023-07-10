package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Address;
import com.example.domain.Hittakuri;

/**
 * ひったくりテーブルへの処理を行うリポジトリ.
 * 
 * @author yoshimatsushouta
 *
 */
@Repository
public class HittakuriRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * ひったくり情報検索に使用するローマッパー.
	 */
	private static final RowMapper<Hittakuri> HITTAKURI_ROW_MAPPER = (rs, i) -> {
		Hittakuri hittakuri = new Hittakuri();
		hittakuri.setId(rs.getInt("h_id"));
		hittakuri.setEventDateTime(rs.getString("h_event_date_time"));
		hittakuri.setPrefectureId(rs.getInt("h_prefecture_id"));
		hittakuri.setMunicipalityId(rs.getInt("h_municipality_id"));
		hittakuri.setAddressId(rs.getInt("h_address_id"));
		hittakuri.setPoliceStation(rs.getString("h_police_station"));
		hittakuri.setPoliceBox(rs.getString("h_police_box"));
		hittakuri.setVictimGender(rs.getString("victim_gender"));
		hittakuri.setVictimAge(rs.getString("victim_age"));
		hittakuri.setCashDamage(rs.getString("cash_damage"));

		return hittakuri;
	};

	/**
	 * 複数の住所IDからひったくりの情報を引き出すメソッド.
	 * 
	 * @param addressList 住所情報のリスト
	 * @return ひったくり情報のリスト
	 */
	@SuppressWarnings("null")
	public List<Hittakuri> getHittakuriByAddressId(List<Address> addressList) {
		List<Hittakuri> hittakuriList = null;

		for (Address address : addressList) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append(
					"h.id AS h_id, h.event_date_time AS h_event_date_time, h.prefecture_id AS h_prefecture_id, h.municipality_id AS h_municipality_id, h.address_id AS h_address_id, h.police_station AS h_police_station, h.police_box AS h_police_box, vg.value AS victim_gender, va.value AS victim_age, cd.value AS cash_damage ");
			sql.append("FROM hittakuri AS h ");
			sql.append("LEFT OUTER JOIN victim_genders AS vg ON h.victim_gender_id = vg.id ");
			sql.append("LEFT OUTER JOIN victim_ages AS va ON h.victim_age_id = va.id ");
			sql.append("LEFT OUTER JOIN cash_damages AS cd ON h.cash_damage_id = cd.id ");
			sql.append("WHERE h.address_id = :addressId ;");
			SqlParameterSource param = new MapSqlParameterSource().addValue("addressId", address.getId());

			hittakuriList.add((Hittakuri) template.query(sql.toString(), param, HITTAKURI_ROW_MAPPER));
		}
		return hittakuriList;
	}

}
