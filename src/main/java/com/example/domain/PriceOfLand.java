package com.example.domain;

import lombok.Data;

/**
 * 地価の情報を保持するクラス.
 * 
 * @author yoshimatsushouta
 *
 */
@Data
public class PriceOfLand {
	/** 主キー */
	private int id;

	/** 市区町村ID */
	private int municipalityId;

	/** 住所ID */
	private int addressId;

	/** 住所 */
	private String addressNum;

	/** 現在の地価 */
	private int currentPrice;

	/** 前年の地価 */
	private int previosPrice;

	/** 前年の地価からの変動率 */
	private int changePreviosPrice;

	/** 面積 */
	private int acreage;

	/** 最寄り駅ID */
	private int nearestStationId;

	/** 土地使用状況ID */
	private int usageId;

	/** 土地近隣の利用状況 */
	private String neighborhoodUsage;

	/** 情報年 */
	private int infoYear;
}
