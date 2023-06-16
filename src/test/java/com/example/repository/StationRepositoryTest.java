package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Station;

@SpringBootTest
class StationRepositoryTest {

	@Autowired
	private StationRepository stationRepository;

	@Test
	void 全国の駅名検索テスト() {
		System.out.println("ー全国の駅名検索テスト開始ー");
		List<Station> testList = stationRepository.findAll();

		assertEquals(10898, testList.size(), "リストの数が合致していません");
		assertEquals(1110101, testList.get(0).getId(), "一列目のIDが一致していません");
		assertEquals("函館", testList.get(0).getStationName(), "一列目のIDが一致していません");
		assertEquals(11101, testList.get(0).getLineId(), "一列目のIDが一致していません");
		assertEquals("040-0063", testList.get(0).getPost(), "一列目のIDが一致していません");
		assertEquals("北海道函館市若松町１２-１３", testList.get(0).getAddress(), "一列目のIDが一致していません");
		assertEquals(140.726413, testList.get(0).getLongitude(), "一列目のIDが一致していません");
		assertEquals(41.773709, testList.get(0).getLatitude(), "一列目のIDが一致していません");

		assertEquals(9992804, testList.get(10897).getId(), "最終列のIDが一致していません");
		assertEquals("関門海峡めかり", testList.get(10897).getStationName(), "最終列のIDが一致していません");
		assertEquals(99928, testList.get(10897).getLineId(), "最終列のIDが一致していません");
		assertEquals("801-0855", testList.get(10897).getPost(), "最終列のIDが一致していません");
		assertEquals("福岡県北九州市門司区門司", testList.get(10897).getAddress(), "最終列のIDが一致していません");
		assertEquals(130.967347, testList.get(10897).getLongitude(), "最終列のIDが一致していません");
		assertEquals(33.960627, testList.get(10897).getLatitude(), "最終列のIDが一致していません");

		System.out.println("ー全国の駅名検索テスト終了ー");
	}

	@Test
	void 沿線ID検索テスト() {
		System.out.println("ー沿線ID検索テスト開始ー");
		final int testLineId = 11101;
		List<Station> testList = stationRepository.findByLineId(testLineId);

		assertEquals(38, testList.size(), "リストの数が合致していません");
		assertEquals(1110101, testList.get(0).getId(), "一列目のIDが一致していません");
		assertEquals("函館", testList.get(0).getStationName(), "一列目のIDが一致していません");
		assertEquals(11101, testList.get(0).getLineId(), "一列目のIDが一致していません");
		assertEquals("040-0063", testList.get(0).getPost(), "一列目のIDが一致していません");
		assertEquals("北海道函館市若松町１２-１３", testList.get(0).getAddress(), "一列目のIDが一致していません");
		assertEquals(140.726413, testList.get(0).getLongitude(), "一列目のIDが一致していません");
		assertEquals(41.773709, testList.get(0).getLatitude(), "一列目のIDが一致していません");

		assertEquals(1110138, testList.get(37).getId(), "最終列のIDが一致していません");
		assertEquals("長万部", testList.get(37).getStationName(), "最終列のIDが一致していません");
		assertEquals(11101, testList.get(37).getLineId(), "最終列のIDが一致していません");
		assertEquals("049-3521", testList.get(37).getPost(), "最終列のIDが一致していません");
		assertEquals("山越郡長万部町字長万部", testList.get(37).getAddress(), "最終列のIDが一致していません");
		assertEquals(140.37507, testList.get(37).getLongitude(), "最終列のIDが一致していません");
		assertEquals(42.512477, testList.get(37).getLatitude(), "最終列のIDが一致していません");

		System.out.println("ー沿線ID検索テスト終了ー");
	}

	@Test
	void 駅ID検索テスト() {
		System.out.println("ー駅ID検索テスト開始ー");
		final int testStationId = 1130801;
		Station testStation = stationRepository.load(testStationId);

		assertEquals(1130801, testStation.getId(), "一列目のIDが一致していません");
		assertEquals("東京", testStation.getStationName(), "一列目のIDが一致していません");
		assertEquals(11308, testStation.getLineId(), "一列目のIDが一致していません");
		assertEquals("100-0005", testStation.getPost(), "一列目のIDが一致していません");
		assertEquals("東京都千代田区丸の内一丁目", testStation.getAddress(), "一列目のIDが一致していません");
		assertEquals(139.766103, testStation.getLongitude(), "一列目のIDが一致していません");
		assertEquals(35.681391, testStation.getLatitude(), "一列目のIDが一致していません");

		System.out.println("ー駅ID検索テスト終了ー");
	}
}
