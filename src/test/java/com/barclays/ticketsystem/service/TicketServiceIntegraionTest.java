package com.barclays.ticketsystem.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.barclays.ticketsystem.persistence.domain.Ticket;
import com.barclays.ticketsystem.persistence.repository.TicketRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TicketServiceIntegraionTest {

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private TicketRepository ticketRepository;
		
	@Test
	void testReadAll() {
		Ticket ticket1 = new Ticket(1L, "Title", "Author", "Description");
		Ticket ticket2 = new Ticket(2L, "Title2", "Author2", "Description2");
		List<Ticket> toSave = List.of(ticket1, ticket2);	
		
		this.ticketRepository.saveAll(toSave);
		assertThat(this.ticketService.readAll()).isEqualTo(toSave);
	}
	
	@Test
	void testCreate() {
		Ticket ticket = new Ticket(1L, "Title", "Author", "Description");
		this.ticketRepository.save(ticket);
		assertThat(this.ticketService.create(ticket)).isEqualTo(ticket);
	}
}
