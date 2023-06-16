package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.RailwayLine;
import com.example.repository.RailwayLineRepository;

@SpringBootTest
class RailwayLineServiceTest {

	@Mock
	private RailwayLineRepository railwayLineRepository;
	@InjectMocks
	private RailwayLineService railwayLineService;

	@Test
	void 沿線情報表示のテスト() {
		// DBにあるものを仮で定義
		RailwayLine railwayLine = new RailwayLine();

		// railwayLineRepositoryから返すためのList<RailwayLine>を作成
		List<RailwayLine> railwayLineList = new ArrayList<>();
		railwayLineList.add(railwayLine);
		// railwayLineRepositoryの動作を制御
		Mockito.doReturn(railwayLineList).when(railwayLineRepository).findByWithin100000MetersTokyo();

		// findByWithin100000MetersTokyoの呼び出し
		List<RailwayLine> testList = railwayLineService.findByWithin100000MetersTokyo();

		// 内容の確認
		assertEquals(railwayLineList, testList, "railwayLineを正しく取得できていません");
	}

}
