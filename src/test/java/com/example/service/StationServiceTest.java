package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Station;
import com.example.repository.StationRepository;

@SpringBootTest
class StationServiceTest {

	@Mock
	private StationRepository stationRepository;
	@InjectMocks
	private StationService stationService;

	@Test
	void 駅情報表示のテスト() {
		// DBにあるものを仮で定義
		Station station = new Station();

		// stationRepositoryから返すためのList<Station>を作成
		List<Station> stationList = new ArrayList<>();
		stationList.add(station);
		// stationRepositoryの動作を制御
		Mockito.doReturn(stationList).when(stationRepository).findAll();

		// findByLineIdの呼び出し
		List<Station> testList = stationService.findAll();

		// 内容の確認
		assertEquals(stationList, testList, "stationを正しく取得できていません");
	}

	@Test
	void 沿線ID検索をした駅情報表示のテスト() {
		// DBにあるものを仮で定義
		Station station = new Station();
		final int testLineId = 0;

		// stationRepositoryから返すためのList<Station>を作成
		List<Station> stationList = new ArrayList<>();
		stationList.add(station);
		// stationRepositoryの動作を制御
		Mockito.doReturn(stationList).when(stationRepository).findByLineId(testLineId);

		// findByLineIdの呼び出し
		List<Station> testList = stationService.findByLineId(0);

		// 内容の確認
		assertEquals(stationList, testList, "stationを正しく取得できていません");
	}

	@Test
	void 駅ID検索をした駅情報表示のテスト() {
		// DBにあるものを仮で定義
		Station station = new Station();
		final int testStationId = 0;
		// stationRepositoryの動作を制御
		Mockito.doReturn(station).when(stationRepository).load(testStationId);

		// loadの呼び出し
		Station testStation = stationService.load(0);

		// 内容の確認
		assertEquals(station, testStation, "stationを正しく取得できていません");
	}
}
