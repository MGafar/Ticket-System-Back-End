package com.barclays.ticketsystem.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

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
	
	public Ticket update(Long id, Ticket updatedValues) {
		Ticket toUpdate = this.ticketRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		
		toUpdate.setAuthor(updatedValues.getAuthor());
		toUpdate.setTitle(updatedValues.getTitle());
		toUpdate.setDescription(updatedValues.getDescription());
		
		return this.ticketRepository.save(toUpdate);
	}

	public Ticket readById(Long id) {
		Optional<Ticket> ticket = this.ticketRepository.findById(id);
		return ticket.get();
	}
}
