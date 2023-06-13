package com.example.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.domain.RailwayLine;
import com.example.service.RailwayLineService;

@SpringBootTest
@AutoConfigureMockMvc
class SearchStationControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RailwayLineService railwayLineService;

	@Test
	public void 駅検索画面表示テスト() throws Exception {
		// DBにあるものを仮で定義
		RailwayLine railwayLine = new RailwayLine();

		// railwayLineRepositoryから返すためのList<RailwayLine>を作成
		List<RailwayLine> railwayLineList = new ArrayList<>();
		railwayLineList.add(railwayLine);
		when(railwayLineService.findByWithin100000MetersTokyo()).thenReturn(railwayLineList);

		// 検証
		mockMvc.perform(MockMvcRequestBuilders.get("/station/search")).andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("station-search.html"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("railwayLineList"));
	}
}
