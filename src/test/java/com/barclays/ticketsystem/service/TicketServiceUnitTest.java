package com.barclays.ticketsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.barclays.ticketsystem.persistence.domain.Ticket;
import com.barclays.ticketsystem.persistence.repository.TicketRepository;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class TicketServiceUnitTest {
	
	@Autowired
	private TicketService ticketService;
	
	@MockBean
	private TicketRepository ticketRepository;
	
	@Test
	void testReadAll() {
		
		List<Ticket> expectedTickets = new ArrayList<>();
		expectedTickets.add(new Ticket(1L, "Title", "Author", "Description"));
		expectedTickets.add(new Ticket(2L, "Title2", "Author2", "Description2"));
		
		Mockito.when(this.ticketRepository.findAll()).thenReturn(expectedTickets);
		Assertions.assertThat(this.ticketService.readAll()).isEqualTo(expectedTickets);
	}
	
	@Test
	void testCreate()  {
		Ticket toSave = new Ticket("Title", "Author", "Description");
		Ticket saved = new Ticket(1L, "Title", "Author", "Description");
		Mockito.when(this.ticketRepository.save(toSave)).thenReturn(saved);
		Assertions.assertThat(this.ticketService.create(toSave)).isEqualTo(saved);
		Mockito.verify(this.ticketRepository, Mockito.times(1)).save(toSave);
	}
}
