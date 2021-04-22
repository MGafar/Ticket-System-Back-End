package com.barclays.ticketsystem.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barclays.ticketsystem.persistence.domain.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
