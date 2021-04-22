package com.barclays.ticketsystem.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.barclays.ticketsystem.persistence.domain.Department;
import com.barclays.ticketsystem.persistence.domain.Status;
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
		expectedTickets.add(new Ticket(1L, "Title", "Author", "Description", "Solution", Status.DONE, null));
		expectedTickets.add(new Ticket(2L, "Title2", "Author2", "Description2", "Solution2", Status.DONE, null));
		Mockito.when(this.ticketService.readAll()).thenReturn(expectedTickets);
		ResponseEntity<List<Ticket>> expected = new ResponseEntity<>(expectedTickets, HttpStatus.OK);
		Assertions.assertThat(this.ticketController.readAll()).isEqualTo(expected);
		Mockito.verify(this.ticketService, Mockito.times(1)).readAll();
	}
	
	@Test
	void testReadById() {
		Ticket expectedTicket = new Ticket(1L, "Title", "Author", "Description", "Solution", Status.DONE, null);
		Mockito.when(this.ticketService.readById(123L)).thenReturn(expectedTicket);
		ResponseEntity<Ticket> expected = new ResponseEntity<>(expectedTicket, HttpStatus.OK);
		Assertions.assertThat(this.ticketController.readById(123L)).isEqualTo(expected);
		Mockito.verify(this.ticketService, Mockito.times(1)).readById(123L);
	}

	@Test
	void testReadByDepartment() {
		List<Ticket> expectedTickets = new ArrayList<>();
		Department department = new Department(1L, "FX");
		expectedTickets.add(new Ticket(1L, "Title", "Author", "Description", "Solution", Status.DONE, department));
		expectedTickets.add(new Ticket(2L, "Title2", "Author2", "Description2", "Solution2", Status.DONE, department));
		Mockito.when(this.ticketService.readByDepartment(1L)).thenReturn(expectedTickets);
		ResponseEntity<List<Ticket>> expected = new ResponseEntity<>(expectedTickets, HttpStatus.OK);
		Assertions.assertThat(this.ticketController.readByDepartment(1L)).isEqualTo(expected);
		Mockito.verify(this.ticketService, Mockito.times(1)).readByDepartment(1L);
	}
	
	@Test
	void testCreate() {
		Ticket toSave = new Ticket(1L, "Title", "Author", "Description", "Solution", Status.DONE, null);
		Ticket saved = new Ticket(1L, "Title", "Author", "Description", "Solution", Status.DONE, null);
		Mockito.when(this.ticketService.create(toSave)).thenReturn(saved);
		ResponseEntity<Ticket> expected = new ResponseEntity<Ticket>(saved, HttpStatus.CREATED);
		Assertions.assertThat(this.ticketController.create(toSave)).isEqualTo(expected);
		Mockito.verify(this.ticketService, Mockito.times(1)).create(toSave);
	}
	
	@Test
	void testUpdate() {
		Ticket expectedTicket = new Ticket(1L, "Title", "Author", "Description", "Solution", Status.DONE, null);
		Mockito.when(this.ticketService.update(123L, expectedTicket)).thenReturn(expectedTicket);
		ResponseEntity<Ticket> expected = new ResponseEntity<>(expectedTicket, HttpStatus.OK);
		Assertions.assertThat(this.ticketController.update(123L, expectedTicket)).isEqualTo(expected);
		Mockito.verify(this.ticketService, Mockito.times(1)).update(123L, expectedTicket);
	}
	
	@Test
	void testDelete() {
		Map<String, Boolean> response = new HashMap<> ();
		response.put("Deleted", Boolean.TRUE);
		Mockito.when(this.ticketService.delete(123L)).thenReturn(response);
		ResponseEntity<Map<String, Boolean>> expected = new ResponseEntity<>(response, HttpStatus.OK);
		Assertions.assertThat(this.ticketController.delete(123L)).isEqualTo(expected);
		Mockito.verify(this.ticketService, Mockito.times(1)).delete(123L);
	}
	
	@Test
	void testMarkAsInProgress() {
		Map<String, Boolean> response = new HashMap<> ();
		response.put("Deleted", Boolean.TRUE);
		Mockito.when(this.ticketService.markAsInProgress(123L)).thenReturn(response);
		ResponseEntity<Map<String, Boolean>> expected = new ResponseEntity<>(response, HttpStatus.OK);
		Assertions.assertThat(this.ticketController.markAsInProgress(123L)).isEqualTo(expected);
		Mockito.verify(this.ticketService, Mockito.times(1)).markAsInProgress(123L);
	}
	
}
