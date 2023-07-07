package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Address;
import com.example.domain.TabItem;

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

	/**
	 * 近隣住所検索に使用するローマッパー
	 */
	private static final RowMapper<TabItem> TabItem_ROW_MAPPER = (rs, i) -> {
		TabItem tabItem = new TabItem();
		tabItem.setName(rs.getString("name"));
		tabItem.setId(rs.getInt("id"));
		return tabItem;
	};

	/**
	 * idから住所を取得するメソッド
	 * 
	 * @param id 住所id
	 * @return 住所情報
	 */

	public List<Address> getAddressById(int id) {
		String sql = "SELECT id,name,latitude,longitude FROM addresses WHERE prefecture_id=13  AND id = :id ";
		List<Address> AddressList = template.query(sql, new MapSqlParameterSource("id", id),
				new BeanPropertyRowMapper<Address>(Address.class));
		return AddressList;
	}

	/**
	 * 緯度と経度の情報から住所情報を取り出すメソッド.
	 * 
	 * @param longitude 経度
	 * @param latitude  緯度
	 * @return 住所情報のリスト
	 */
	public List<Address> getAddressByLongitudeAndLatitude(double longitude, double latitude) {
		// 名前逆
		Double latmax = longitude + 0.045;
		Double latmin = longitude - 0.045;
		Double longmax = latitude + 0.045;
		Double longmin = latitude - 0.045;

		SqlParameterSource param = new MapSqlParameterSource().addValue("longmax", longmax).addValue("longmin", longmin)
				.addValue("latmax", latmax).addValue("latmin", latmin);
		String sql = "SELECT id, name, name_kana, name_rome, latitude, longitude, prefecture_id, municipality_id FROM addresses WHERE longitude BETWEEN :longmin AND :longmax AND latitude BETWEEN :latmin AND :latmax ;";
		List<Address> addressList = template.query(sql, param, new BeanPropertyRowMapper<Address>(Address.class));
		return addressList;
	}
	/*
	 * 緯度経度から近隣の５つの住所を取得するメソッド（表示の関係で５つ）
	 * 
	 * @param longitude 経度
	 * 
	 * @param latitude 緯度
	 * 
	 * @return 近隣住所情報
	 */

	public List<TabItem> getAddressByLonLat(double longitude, double latitude) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("targetLongitude", longitude)
				.addValue("targetLatitude", latitude);
		String sql = "SELECT id, name FROM addresses WHERE prefecture_id = 13 AND longitude != :targetLongitude AND latitude != :targetLatitude  ORDER BY POWER((longitude - :targetLongitude), 2) + POWER((latitude - :targetLatitude), 2) LIMIT 5;";
		List<TabItem> addressList = template.query(sql, param, TabItem_ROW_MAPPER);
		return addressList;
	}
}