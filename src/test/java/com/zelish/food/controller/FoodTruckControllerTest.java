package com.zelish.food.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zelish.food.model.FoodTruckInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yml")
public class FoodTruckControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testPut() throws Exception {
		FoodTruckInfo foodTruckInfo = new PodamFactoryImpl().manufacturePojo(FoodTruckInfo.class);
		ObjectMapper mapper = new ObjectMapper();
		String reqJson = mapper.writeValueAsString(foodTruckInfo);

		MvcResult result = mockMvc
				.perform(put("/foodTruck/update/38838").contentType(MediaType.APPLICATION_JSON).content(reqJson))
				.andReturn();
		MockHttpServletResponse resp = result.getResponse();
		System.out.println(resp.getContentAsString());
		assertEquals(HttpStatus.OK.value(), resp.getStatus());
		assertNotNull(resp.getContentAsString());
	}

	@Test
	public void testDelete() throws Exception {
		MvcResult result = mockMvc.perform(delete("/foodTruck/delete/5")).andReturn();
		MockHttpServletResponse resp = result.getResponse();
		assertEquals(HttpStatus.OK.value(), resp.getStatus());
		System.out.println(resp.getContentAsString());
		assertNotNull(resp.getContentAsString());
	}

	@Test
	public void testSearch() throws Exception {

		MockHttpServletResponse response = mockMvc.perform(get("/foodTruck/search?searchText=xyz")).andReturn()
				.getResponse();

		assertThat(response).isNotNull();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
	}

	@Test
	public void testCreate() throws Exception {
		Long locationId = 2939l;
		FoodTruckInfo foodTruckInfo = new PodamFactoryImpl().manufacturePojo(FoodTruckInfo.class);
		foodTruckInfo.setLocationId(locationId);

		String reqJson = new ObjectMapper().writeValueAsString(foodTruckInfo);

		MockHttpServletResponse response = mockMvc
				.perform(post("/foodTruck/create").contentType(MediaType.APPLICATION_JSON).content(reqJson)).andReturn()
				.getResponse();
		assertEquals(locationId, Long.parseLong(response.getContentAsString()));
	}

}
