package com.example.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 地域検索の条件を取得するフォーム.
 * 
 * @author sugaharatakamasa
 *
 */
@Data
public class SearchByAddressForm {

	/** 市区町村ID */
	@NotNull(message = "※市区町村を選択してください")
	private Integer municipalityId;

	/** 市区町村名 */
	private String municipalityName;

	/** 町域(丁目を除く)ID */
	@NotNull(message = "※町域を選択してください")
	private Integer townId;

	/** 町域(丁目を除く)名 */
	private String townName;

	/** 丁目ID */
	@NotNull(message = "※丁目を選択してください")
	private Integer addressId;
}
