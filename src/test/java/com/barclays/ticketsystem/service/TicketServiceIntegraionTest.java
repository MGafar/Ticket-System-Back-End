package com.barclays.ticketsystem.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.barclays.ticketsystem.persistence.domain.Department;
import com.barclays.ticketsystem.persistence.domain.Status;
import com.barclays.ticketsystem.persistence.domain.Ticket;
import com.barclays.ticketsystem.persistence.repository.DepartmentRepository;
import com.barclays.ticketsystem.persistence.repository.TicketRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
class TicketServiceIntegraionTest {

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
		
	@Test
	void testReadAll() {
		Ticket ticket1 = new Ticket(1L, "Title", "Author", "Description", "Solution", Status.DONE, null, null);
		Ticket ticket2 = new Ticket(2L, "Title", "Author", "Description", "Solution", Status.DONE, null, null);
		List<Ticket> toSave = List.of(ticket1, ticket2);	
		
		this.ticketRepository.saveAll(toSave);
		assertThat(this.ticketService.readAll()).isEqualTo(toSave);
	}
	
	@Test
	void testReadByID() {
		Ticket ticket = new Ticket(1L, "Title", "Author", "Description", "Solution", Status.DONE, null, null);	
		this.ticketRepository.save(ticket);
		assertThat(this.ticketService.readById(1L)).isEqualTo(ticket);
	}
	
	@Test
	void testReadByDepartment() {
		Department fx = new Department(1L, "FX");
		Ticket unexpectedTicket = new Ticket(1L, "Title", "Author", "Description", "Solution", Status.DONE, null, null);
		Ticket expectedTicket = new Ticket(2L, "Title", "Author", "Description", "Solution", Status.DONE, fx, null);
		List<Ticket> toSave = List.of(expectedTicket);
		
		this.departmentRepository.save(fx);
		this.ticketRepository.saveAll(List.of(expectedTicket, unexpectedTicket));
		assertThat(this.ticketService.readByDepartment(1L)).isEqualTo(toSave);
	}
	
	@Test
	void testCreate() {
		Ticket ticket = new Ticket(1L, "Title", "Author", "Description", "Solution", Status.DONE, null, null);
		this.ticketRepository.save(ticket);
		assertThat(this.ticketService.create(ticket)).isEqualTo(ticket);
	}
	
	@Test
	@Rollback
	void testUpdate() {
		Ticket ticket = new Ticket(1L, "Title", "Author", "Description", "Solution", Status.DONE, null, null);
		this.ticketRepository.save(ticket);
		Ticket newVals = new Ticket(1L, "New Title", "New Author", "New Description", "New Solution", Status.OPEN, null, null);
		this.ticketService.update(1L, newVals);
		assertThat(this.ticketService.readById(1L)).isEqualTo(newVals);
	}
	
	@Test
	@Rollback
	void testDelete() {
		Map<String, Boolean> response = new HashMap<> ();
		response.put("Deleted", Boolean.TRUE);
		assertThat(this.ticketService.delete(1L)).isEqualTo(response);
	}
	
	@Test
	@Rollback
	void testMarkAsInProgress() {
		Map<String, Boolean> response = new HashMap<> ();
		response.put("InProgress", Boolean.TRUE);
		assertThat(this.ticketService.markAsInProgress(1L)).isEqualTo(response);
	}
}
