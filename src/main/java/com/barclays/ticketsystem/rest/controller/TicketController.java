package com.barclays.ticketsystem.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}