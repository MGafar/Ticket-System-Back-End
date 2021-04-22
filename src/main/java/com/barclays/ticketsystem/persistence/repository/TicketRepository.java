package com.barclays.ticketsystem.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.barclays.ticketsystem.persistence.domain.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	@Query(value = "Select * from tickets where department_id = ?", nativeQuery = true)
	List<Ticket> findByDepartment(Long departmentId);
	
}
