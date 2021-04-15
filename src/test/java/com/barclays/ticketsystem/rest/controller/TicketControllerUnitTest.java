package com.barclays.ticketsystem.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.barclays.ticketsystem.persistence.domain.Ticket;
import com.barclays.ticketsystem.service.TicketService;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class TicketControllerUnitTest {

	@Autowired
	private TicketController ticketController;
	
	@MockBean
	private TicketService ticketService;
	
	@Test
	void testReadAll() {
		List<Ticket> expectedTickets = new ArrayList<>();
		expectedTickets.add(new Ticket(1L, "Title", "Author", "Description"));
		expectedTickets.add(new Ticket(2L, "Title2", "Author2", "Description2"));
		Mockito.when(this.ticketService.readAll()).thenReturn(expectedTickets);
		ResponseEntity<List<Ticket>> expected = new ResponseEntity<>(expectedTickets, HttpStatus.OK);
		Assertions.assertThat(this.ticketController.readAll()).isEqualTo(expected);
		Mockito.verify(this.ticketService, Mockito.times(1)).readAll();
	}
	
}
