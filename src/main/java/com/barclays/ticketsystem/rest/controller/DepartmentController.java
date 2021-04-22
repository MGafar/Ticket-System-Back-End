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
	
//	@GetMapping("/readById/{id}")
//	public ResponseEntity<Department> readById(@PathVariable Long id) {
//		return ResponseEntity.ok(this.departmentService.readById(id));
//	}
//	
//	@PutMapping("/update/{id}")
//	public ResponseEntity<Department> update(@PathVariable Long id, @RequestBody Department department) {
//		return ResponseEntity.ok(this.departmentService.update(id, department));
//	}
//	
//	@PostMapping("/create")
//	public ResponseEntity<Department> create(@RequestBody Department department) {
//		return new ResponseEntity<>(this.departmentService.create(department), HttpStatus.CREATED);
//	}
//	
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id){
//		return ResponseEntity.ok(this.departmentService.delete(id));
//	}
}