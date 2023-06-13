package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.RailwayLine;

@SpringBootTest
class RailwayLineRepositoryTest {

	@Autowired
	private RailwayLineRepository railwayLineRepository;

	@Test
	void 東京駅周辺の沿線検索テスト() {
		System.out.println("ー東京駅周辺の沿線検索テスト開始ー");
		List<RailwayLine> testList = railwayLineRepository.findByWithin100000MetersTokyo();

		assertEquals(143, testList.size(), "リストの数が合致していません");
		assertEquals(11301, testList.get(0).getId(), "一列目のIDが一致していません");
		assertEquals(2, testList.get(0).getCompanyId(), "一列目の会社IDが一致していません");
		assertEquals("JR東海道本線(東京～熱海)", testList.get(0).getLineName(), "一列目の沿線名が一致していません");
		assertEquals("トウカイドウホンセン", testList.get(0).getLineNameK(), "一列目のエンセンメイが一致していません");
		assertEquals("JR東海道本線(東京～熱海)", testList.get(0).getLineNameH(), "一列目の沿線名別名が一致していません");
		assertEquals(139.43024413263, testList.get(0).getLongitude(), "一列目の経度が一致していません");
		assertEquals(35.395079623415, testList.get(0).getLatitude(), "一列目の緯度が一致していません");

		assertEquals(99401, testList.get(142).getId(), "最終列のIDが一致していません");
		assertEquals(154, testList.get(142).getCompanyId(), "最終列の会社IDが一致していません");
		assertEquals("富士急行線", testList.get(142).getLineName(), "最終列の沿線名が一致していません");
		assertEquals("フジキュウコウセン", testList.get(142).getLineNameK(), "最終列のエンセンメイが一致していません");
		assertEquals("富士急行線", testList.get(142).getLineNameH(), "最終列の沿線名別名が一致していません");
		assertEquals(138.86075605024, testList.get(142).getLongitude(), "最終列の経度が一致していません");
		assertEquals(35.553671122984, testList.get(142).getLatitude(), "最終列の緯度が一致していません");

		System.out.println("ー東京駅周辺の沿線検索テスト終了ー");
	}

}
