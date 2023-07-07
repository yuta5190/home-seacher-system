package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Address;
import com.example.domain.Choume;
import com.example.domain.Town;

/**
 * addressesテーブルへの処理を行うクラス.
 * 
 * @author yoshida_yuuta
 *
 */
@Repository
public class AddressRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public List<Address> getAddressById(int id) {
		String sql = "SELECT id,name,latitude,longitude FROM addresses WHERE prefecture_id=13  AND id = :id ";
		List<Address> AddressList = template.query(sql, new MapSqlParameterSource("id", id)
				,new BeanPropertyRowMapper<Address>(Address.class));
						return AddressList;

}
}