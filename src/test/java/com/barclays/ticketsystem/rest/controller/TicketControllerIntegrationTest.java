package com.barclays.ticketsystem.rest.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.barclays.ticketsystem.persistence.domain.Ticket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@Transactional
@AutoConfigureMockMvc
class TicketControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper jsonConverter;
	
	@Test
	@Rollback
	void testCreate() throws JsonProcessingException, Exception {
		Ticket ticket = new Ticket("SampleTitle", "SampleAuthor", "SampleDescription");
		
		this.mvc.perform(post("/ticket/create").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(this.jsonConverter.writeValueAsString(ticket)))
				.andExpect(status().isCreated())
				.andExpect(content().string(containsString("SampleTitle")))
				.andExpect(content().string(containsString("SampleAuthor")))
				.andExpect(content().string(containsString("SampleDescription")));
	}
}
