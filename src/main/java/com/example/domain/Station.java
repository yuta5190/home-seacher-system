package com.example.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * stationsテーブルの情報を保持するクラス.
 * 
 * @author yoshimatsushouta
 *
 */
@Getter
@Setter
@ToString
public class Station {
	/** 駅ID */
	private Integer id;
	/** 駅名 */
	private String stationName;
	/** 沿線ID */
	private Integer lineId;
	/** 郵便番号 */
	private String post;
	/** 郵便番号 */
	private String address;
	/** 経度 */
	private Double longitude;
	/** 緯度 */
	private Double latitude;

}
