package com.barclays.ticketsystem.rest.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@Transactional
@AutoConfigureMockMvc
class DepartmentControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	void testReadAll()  throws Exception {
		this.mvc.perform(get("/department/readAll"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("FX")))
				.andExpect(content().string(containsString("Credit")));
	}
}

