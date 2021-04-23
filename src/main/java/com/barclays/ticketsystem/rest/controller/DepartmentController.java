package com.barclays.ticketsystem.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.ticketsystem.persistence.domain.Department;
import com.barclays.ticketsystem.service.DepartmentService;

@RestController
@RequestMapping("/department/")
public class DepartmentController  {

	@Autowired
	private DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}

	@GetMapping("/readAll")
	public ResponseEntity<List<Department>> readAll() {
		return ResponseEntity.ok(this.departmentService.readAll());
	}
}