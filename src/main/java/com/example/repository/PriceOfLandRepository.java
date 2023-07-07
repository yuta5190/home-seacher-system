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

@Repository
public class PriceOfLandRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	@SuppressWarnings("null")
	public List<PriceOfLand> getPriceOfLandListByaddressId(List<Address> addressList) {
		List<PriceOfLand> priceOfLandList = null;
		for (Address address : addressList) {

			StringBuilder sql = new StringBuilder();
			sql.append("");
			SqlParameterSource param = new MapSqlParameterSource().addValue("addressId", address.getId());

			priceOfLandList.add((PriceOfLand) template.query(sql.toString(), param,
					new BeanPropertyRowMapper<PriceOfLand>(PriceOfLand.class)));
		}
		return priceOfLandList;
	}
}
