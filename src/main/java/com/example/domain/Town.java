package com.example.domain;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 町域(丁目除く)の情報を保持するクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Data
public class Town {

	private int id;

	/** 市区町村ID */
	private int municipalityId;

	/** 町域名(丁目を除く) */
	private String name;

	/** 町域名カナ(丁目を除く) */
	private String nameKana;

	/** 町域名ローマ字(丁目を除く) */
	private String nameRome;

	/** 登録ユーザー */
	private String registeredUser;

	/** 登録日 */
	private LocalDateTime registeredDateTime;

	/** 更新ユーザー */
	private String updatedUser;

	/** 更新日 */
	private LocalDateTime updatedDateTime;

	/** 論理削除判定 */
	private boolean deleted;

}
