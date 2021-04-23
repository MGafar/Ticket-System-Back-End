package com.barclays.ticketsystem.rest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.ticketsystem.persistence.domain.Ticket;
import com.barclays.ticketsystem.service.TicketService;

@RestController
@RequestMapping("/ticket/")
public class TicketController  {

	@Autowired
	private TicketService ticketService;

	public TicketController(TicketService ticketService) {
		super();
		this.ticketService = ticketService;
	}

	@GetMapping("/readAll")
	public ResponseEntity<List<Ticket>> readAll() {
		return ResponseEntity.ok(this.ticketService.readAll());
	}

	@GetMapping("/readByDepartment/{id}")
	public ResponseEntity<List<Ticket>> readByDepartment(@PathVariable Long id) {
		return ResponseEntity.ok(this.ticketService.readByDepartment(id));
	}

	@GetMapping("/readByTopic/{id}")
	public ResponseEntity<List<Ticket>> readByTopic(@PathVariable Long id) {
		return ResponseEntity.ok(this.ticketService.readByTopic(id));
	}
	
	@PutMapping("/markAsInProgress/{id}")
	public ResponseEntity<Map<String, Boolean>> markAsInProgress(@PathVariable Long id) {
		return ResponseEntity.ok(this.ticketService.markAsInProgress(id));
	}
	
	@GetMapping("/readById/{id}")
	public ResponseEntity<Ticket> readById(@PathVariable Long id) {
		return ResponseEntity.ok(this.ticketService.readById(id));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Ticket> update(@PathVariable Long id, @RequestBody Ticket ticket) {
		return ResponseEntity.ok(this.ticketService.update(id, ticket));
	}
	
	@PostMapping("/create")
	public ResponseEntity<Ticket> create(@RequestBody Ticket ticket) {
		return new ResponseEntity<>(this.ticketService.create(ticket), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id){
		return ResponseEntity.ok(this.ticketService.delete(id));
	}
}