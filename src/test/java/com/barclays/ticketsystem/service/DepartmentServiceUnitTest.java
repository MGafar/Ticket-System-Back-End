package com.barclays.ticketsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.barclays.ticketsystem.persistence.domain.Department;
import com.barclays.ticketsystem.persistence.repository.DepartmentRepository;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class DepartmentServiceUnitTest {

	@Autowired
	private DepartmentService departmentService;
	
	@MockBean
	private DepartmentRepository departmentRepository;
	
	@Test
	void testReadAll() {
		List<Department> expectedDepartments = new ArrayList<>();
		expectedDepartments.add(new Department(1L, "FX"));
		expectedDepartments.add(new Department(2L, "Credit"));
		
		Mockito.when(this.departmentRepository.findAll()).thenReturn(expectedDepartments);
		Assertions.assertThat(this.departmentService.readAll()).isEqualTo(expectedDepartments);
	}
}