package com.example.domain;

import lombok.Data;

/**
 * Hittakuriテーブルの情報(ひったくり)を保持するドメイン.
 * 
 * @author yoshimatsushouta
 *
 */
@Data
public class Hittakuri {
	// 主キー
	private int id;
	// 犯罪発生日時
	private String eventDateTime;
	// 都道府県ID
	private int prefectureId;
	// 市区町村ID
	private int municipalityId;
	// 住所ID
	private int addressId;
	// 警察署
	private String policeStation;
	// 交番
	private String policeBox;
	// 被害者性別
	private String victimGender;
	// 被害者年代
	private String victimAge;
	// 現金損害の有無
	private String cashDamage;
}
