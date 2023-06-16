package com.example.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * railway_linesテーブルの情報を保持するドメイン.
 * 
 * @author yoshimatsushouta
 *
 */
@Getter
@Setter
@ToString
public class RailwayLine {
	/** 沿線ID */
	private Integer id;
	/** 鉄道会社ID */
	private Integer companyId;
	/** 沿線名 */
	private String lineName;
	/** エンセンメイ */
	private String lineNameK;
	/** 沿線名別名 */
	private String lineNameH;
	/** 経度 */
	private Double longitude;
	/** 緯度 */
	private Double latitude;
}
