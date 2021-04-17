package com.barclays.ticketsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barclays.ticketsystem.persistence.domain.Ticket;
import com.barclays.ticketsystem.persistence.repository.TicketRepository;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	public TicketService(TicketRepository ticketRepository) {
		super();
		this.ticketRepository = ticketRepository;
	}
	
	public List<Ticket> readAll() {
		return this.ticketRepository.findAll();
	}

	public Ticket create(Ticket ticket) {
		return this.ticketRepository.save(ticket);
	}	
}
