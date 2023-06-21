package com.example.domain;

import lombok.Data;

/**
 * prefecturesテーブルの情報を保持するクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Data
public class Prefecture {

	/** 都道府県ID */
	private int id;

	/** 都道府県名 */
	private String name;

	/** 都道府県名カナ */
	private String nameKana;

	/** 都道府県名ローマ字 */
	private String nameRome;

}
