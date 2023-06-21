package com.example.domain;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 丁目データを保持するクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Data
public class Choume {

	/** 丁目ID */
	private int id;

	/** 町域(丁目除く)ID */
	private int townId;

	/** 町域ID */
	private int addressId;

	/** 丁目名 */
	private String name;

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
