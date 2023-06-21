package com.example.domain;

import lombok.Data;

/**
 * addressesテーブルの情報を保持するクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Data
public class Address {

	/** 町丁目ID */
	private int id;

	/** 町丁目名 */
	private String name;

	/** 町丁目名カナ */
	private String nameKana;

	/** 町丁目名ローマ字 */
	private String nameRome;

	/** 緯度 */
	private Double latitude;

	/** 経度 */
	private Double longitude;

	/* 都道府県ID */
	private int prefectureId;

	/** 市区町村ID */
	private int municipalityId;

}
