package com.barclays.ticketsystem.rest.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
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
	void testCreate() throws JsonProcessingException, Exception {
		Ticket ticket = new Ticket("SampleTitle", "SampleAuthor", "SampleDescription");
		
		this.mvc.perform(post("/ticket/create").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(this.jsonConverter.writeValueAsString(ticket)))
				.andExpect(status().isCreated())
				.andExpect(content().string(containsString("SampleTitle")))
				.andExpect(content().string(containsString("SampleAuthor")))
				.andExpect(content().string(containsString("SampleDescription")));
	}
	
	@Test
	void testReadById()  throws JsonProcessingException, Exception {
		this.mvc.perform(get("/ticket/readById/1"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("VDI Issues")))
				.andExpect(content().string(containsString("Muhamad Gafar")))
				.andExpect(content().string(containsString("The VDI has decided to stop working again fml")));
	}
	
	@Test
	void testUpdate() throws JsonProcessingException, Exception {
		Ticket ticket = new Ticket("UpdatedTitle", "UpdatedAuthor", "UpdatedDescription");
		
		this.mvc.perform(put("/ticket/update/1").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(this.jsonConverter.writeValueAsString(ticket)))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("UpdatedTitle")))
				.andExpect(content().string(containsString("UpdatedAuthor")))
				.andExpect(content().string(containsString("UpdatedDescription")));
	}
}
