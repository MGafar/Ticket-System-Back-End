package com.barclays.ticketsystem.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.barclays.ticketsystem.persistence.domain.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	@Query(value = "Select * from tickets where department_id = ?", nativeQuery = true)
	List<Ticket> findByDepartment(Long departmentId);

	@Query(value = "Select * from tickets where topic_id = ?", nativeQuery = true)
	List<Ticket> findByTopic(Long departmentId);

	@Transactional
	@Modifying
	@Query("Update Ticket set status = 'INPROGRESS' where id = :id")
	void markAsInProgress(@Param(value = "id") long id);
}
