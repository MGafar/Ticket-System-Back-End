package com.barclays.ticketsystem.service;

//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import javax.persistence.EntityNotFoundException;

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

//	public Department create(Department department) {
//		return this.departmentRepository.save(department);
//	}
//	
//	public Department update(Long id, Department updatedValues) {
//		Department toUpdate = this.departmentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
//		toUpdate.setName(updatedValues.getName());	
//		return this.departmentRepository.save(toUpdate);
//	}
//
//	public Department readById(Long id) {
//		Optional<Department> department = this.departmentRepository.findById(id);
//		return department.get();
//	}
//
//	public Map<String, Boolean> delete(Long id) {
//		Department toDelete = this.departmentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
//		this.departmentRepository.delete(toDelete);
//		Map<String, Boolean> response = new HashMap<> ();
//		response.put("Deleted", Boolean.TRUE);
//		return response;
//	}
}
