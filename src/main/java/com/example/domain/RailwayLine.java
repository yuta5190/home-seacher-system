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
	private int id;
	/** 鉄道会社ID */
	private int companyId;
	/** 沿線名 */
	private String lineName;
	/** エンセンメイ */
	private String lineNameK;
	/** 沿線名別名 */
	private String lineNameH;
	/** 経度 */
	private double longitude;
	/** 緯度 */
	private double latitude;
}
