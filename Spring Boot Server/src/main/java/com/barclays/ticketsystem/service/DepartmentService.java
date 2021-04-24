package com.barclays.ticketsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barclays.ticketsystem.persistence.domain.Department;
import com.barclays.ticketsystem.persistence.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public DepartmentService(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}
	
	public List<Department> readAll() {
		return this.departmentRepository.findAll();
	}
}
