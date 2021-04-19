package com.barclays.ticketsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	void testReadById() {
		Optional<Ticket> expectedTicket = Optional.of(new Ticket(1L, "Title", "Author", "Description"));
		Mockito.when(this.ticketRepository.findById(1L)).thenReturn(expectedTicket);
		Assertions.assertThat(this.ticketService.readById(1L)).isEqualTo(expectedTicket.get());
	}
	
	@Test
	void testCreate()  {
		Ticket toSave = new Ticket("Title", "Author", "Description");
		Ticket saved = new Ticket(1L, "Title", "Author", "Description");
		Mockito.when(this.ticketRepository.save(toSave)).thenReturn(saved);
		Assertions.assertThat(this.ticketService.create(toSave)).isEqualTo(saved);
		Mockito.verify(this.ticketRepository, Mockito.times(1)).save(toSave);
	}
	
	@Test
	void testUpdate()  {
		Optional<Ticket> ticket = Optional.of(new Ticket(1L, "Title", "Author", "Description"));
		Mockito.when(this.ticketRepository.findById(1L)).thenReturn(ticket);
		Ticket newVals = new Ticket(1L, "New Title", "New Author", "New Description");
		Mockito.when(this.ticketRepository.save(newVals)).thenReturn(newVals);
		Assertions.assertThat(this.ticketService.update(1L, newVals)).isEqualTo(newVals);
		Mockito.verify(this.ticketRepository, Mockito.times(1)).save(newVals);
	}
}
