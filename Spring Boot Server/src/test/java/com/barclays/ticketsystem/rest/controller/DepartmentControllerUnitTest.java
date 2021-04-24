package com.barclays.ticketsystem.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.barclays.ticketsystem.persistence.domain.Department;
import com.barclays.ticketsystem.service.DepartmentService;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class DepartmentControllerUnitTest {

	@Autowired
	private DepartmentController departmentController;
	
	@MockBean
	private DepartmentService departmentService;
	
	@Test
	void testReadAll() {
		List<Department> expectedDepartments = new ArrayList<>();
		expectedDepartments.add(new Department(1L, "FX"));
		expectedDepartments.add(new Department(2L, "Credit"));
		Mockito.when(this.departmentService.readAll()).thenReturn(expectedDepartments);
		ResponseEntity<List<Department>> expected = new ResponseEntity<>(expectedDepartments, HttpStatus.OK);
		Assertions.assertThat(this.departmentController.readAll()).isEqualTo(expected);
		Mockito.verify(this.departmentService, Mockito.times(1)).readAll();
	}

}
