package com.barclays.ticketsystem.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.barclays.ticketsystem.persistence.domain.Department;
import com.barclays.ticketsystem.persistence.repository.DepartmentRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
class DepartmentServiceIntegrationTest {

	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private DepartmentRepository departmentRepository;
		
	@Test
	void testReadAll() {
		Department department1 = new Department(1L, "FX");
		Department department2 = new Department(2L, "Credit");
		List<Department> toSave = List.of(department1, department2);	
		
		this.departmentRepository.saveAll(toSave);
		assertThat(this.departmentService.readAll()).isEqualTo(toSave);
	}
}
