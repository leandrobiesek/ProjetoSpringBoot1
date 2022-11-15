package com.api.piorfilme;

import com.api.piorfilme.dtos.PrizeIntervalDTO;
import com.api.piorfilme.dtos.PrizeIntervalReturnDTO;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PiorfilmeApplicationTests {

	@Autowired
	MockMvc mockMvc;

	private PrizeIntervalReturnDTO apiReturnDTO;

	@BeforeAll
	void setup() {
		this.apiReturnDTO = new PrizeIntervalReturnDTO();

		//Adiciona os valores esperados para o(s) produtor(es) vencedor(res) com menor intervalo
		this.apiReturnDTO.getMin().add(new PrizeIntervalDTO("Joel Silver", 1L, 1990L, 1991L));

		//Adiciona os valores esperados para o(s) produtor(es) vencedor(res) com maior intervalo
		this.apiReturnDTO.getMax().add(new PrizeIntervalDTO("Matthew Vaughn", 13L, 2002L, 2015L));
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void appTestControllerCheckIfGetStatusOK() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk());
	}

	@Test
	public void appTestControllerCheckIfReturnedDataIsCorrect() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		String response = mvcResult.getResponse().getContentAsString();

		//Testa A lista dos menores intervalos
		for(int i = 0; i < this.apiReturnDTO.getMin().size(); i++) {
			assertEquals(this.apiReturnDTO.getMin().get(i).getProducer(), JsonPath.parse(response).read("$.min.[" + i + "].producer"));
			assertEquals(this.apiReturnDTO.getMin().get(i).getInterval(), Long.valueOf(JsonPath.parse(response).read("$.min.[" + i + "].interval").toString()));
			assertEquals(this.apiReturnDTO.getMin().get(i).getPreviousWin(), Long.valueOf(JsonPath.parse(response).read("$.min.[" + i + "].previousWin").toString()));
			assertEquals(this.apiReturnDTO.getMin().get(i).getFollowingWin(), Long.valueOf(JsonPath.parse(response).read("$.min.[" + i + "].followingWin").toString()));
		}

		//Testa a lista dos maiores intervalos
		for(int i = 0; i < this.apiReturnDTO.getMax().size(); i++) {
			assertEquals(this.apiReturnDTO.getMax().get(i).getProducer(), JsonPath.parse(response).read("$.max.[" + i + "].producer"));
			assertEquals(this.apiReturnDTO.getMax().get(i).getInterval(), Long.valueOf(JsonPath.parse(response).read("$.max.[" + i + "].interval").toString()));
			assertEquals(this.apiReturnDTO.getMax().get(i).getPreviousWin(), Long.valueOf(JsonPath.parse(response).read("$.max.[" + i + "].previousWin").toString()));
			assertEquals(this.apiReturnDTO.getMax().get(i).getFollowingWin(), Long.valueOf(JsonPath.parse(response).read("$.max.[" + i + "].followingWin").toString()));
		}
	}

}
