package com.example.domain;

import lombok.Data;

/**
 * municipalitiesテーブルの情報を保持するクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Data
public class Municipality {

	/** 市区町村ID */
	private int id;

	/** 市区町村名 */
	private String name;

	/** 市区町村名カナ */
	private String nameKana;

	/** 市区町村名ローマ字 */
	private String nameRome;

	/** 都道府県ID */
	private int prefectureId;
}
