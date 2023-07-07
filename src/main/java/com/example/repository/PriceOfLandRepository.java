package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Address;
import com.example.domain.PriceOfLand;

/**
 * 地価テーブルへの処理を行うリポジトリ.
 * 
 * @author yoshimatsushouta
 *
 */
@Repository
public class PriceOfLandRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 複数の住所IDから地価の情報を引き出すメソッド.
	 * 
	 * @param addressList 住所情報のリスト
	 * @return 地価情報のリスト
	 */
	@SuppressWarnings("null")
	public List<PriceOfLand> getPriceOfLandListByaddressId(List<Address> addressList) {
		List<PriceOfLand> priceOfLandList = null;
		for (Address address : addressList) {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append(
					"id, municipality_id, address_id, address_num, current_price, previos_price, change_previos_price, acreage, nearest_station_id, usage_id, neighborhood_usage, info_year ");
			sql.append("FROM price_of_lands ");
			sql.append("WHERE address_id = :addressId;");
			SqlParameterSource param = new MapSqlParameterSource().addValue("addressId", address.getId());

			priceOfLandList.add((PriceOfLand) template.query(sql.toString(), param,
					new BeanPropertyRowMapper<PriceOfLand>(PriceOfLand.class)));
		}
		return priceOfLandList;
	}
}
