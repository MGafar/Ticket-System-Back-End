package com.barclays.ticketsystem.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	public List<Ticket> readByDepartment(Long id) {
		return this.ticketRepository.findByDepartment(id);
	}
	
	public List<Ticket> readByTopic(Long id) {
		return this.ticketRepository.findByTopic(id);
	}
	
	public Ticket create(Ticket ticket) {
		return this.ticketRepository.save(ticket);
	}
	
	public Ticket update(Long id, Ticket updatedValues) {
		Ticket toUpdate = this.ticketRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		
		toUpdate.setAuthor(updatedValues.getAuthor());
		toUpdate.setTitle(updatedValues.getTitle());
		toUpdate.setDescription(updatedValues.getDescription());
		toUpdate.setDepartment(updatedValues.getDepartment());
		toUpdate.setStatus(updatedValues.getStatus());
		toUpdate.setSolution(updatedValues.getSolution());
		toUpdate.setTopic(updatedValues.getTopic());
		
		return this.ticketRepository.save(toUpdate);
	}

	public Ticket readById(Long id) {
		Optional<Ticket> ticket = this.ticketRepository.findById(id);
		return ticket.get();
	}

	public Map<String, Boolean> delete(Long id) {
		Ticket toDelete = this.ticketRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		this.ticketRepository.delete(toDelete);
		Map<String, Boolean> response = new HashMap<> ();
		response.put("Deleted", Boolean.TRUE);
		return response;
	}

	public Map<String, Boolean> markAsInProgress(Long id) {
		this.ticketRepository.markAsInProgress(id);
		Map<String, Boolean> response = new HashMap<> ();
		response.put("InProgress", Boolean.TRUE);
		return response;
	}
}