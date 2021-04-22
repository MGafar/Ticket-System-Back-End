package com.barclays.ticketsystem.persistence.domain;

import static org.assertj.core.api.Assertions.from;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DepartmentTest {

	@Test
	void testDepartmentConstructor() {
		Department testDepartment = new Department(1L, "FX");
		String expectedString = "Department [id: 1 name: FX]";
		Assertions.assertThat(testDepartment.toString()).isEqualTo(expectedString);
	}

	@Test
	void testGettersAndSetter() {
		Ticket testTicket1 = new Ticket(1L, "Title", "Author", "Description", "Solution", Status.DONE, null);
		Ticket testTicket2 = new Ticket(2L, "Title2", "Author2", "Description2", "Solution2", Status.DONE, null);
		Ticket testTicket3 = new Ticket(3L, "Title3", "Author3", "Description3", "Solution3", Status.DONE, null);
		Department testDepartment = new Department();
		
		testDepartment.setId(1L);
		testDepartment.setName("FX");
		testDepartment.setTickets(List.of(testTicket1, testTicket2, testTicket3));
		
		Assertions.assertThat(testDepartment)
			.returns(1L, from(Department::getId))
			.returns("FX", from(Department::getName))
			.returns(List.of(testTicket1, testTicket2, testTicket3), from(Department::getTickets));
	}
	
	@Test
	void testDepartmentEqualsOverride() {
		Department testDepartment = new Department(1L, "FX");

		Assertions.assertThat(new Department(1L, null))
			.isEqualTo(new Department(1L, null))
			.isNotEqualTo(null)
			.isNotEqualTo(123)
			.isNotEqualTo(new Department(1L, "Department"));
		
		Assertions.assertThat(testDepartment)
			.isEqualTo(testDepartment)
			.isEqualTo(new Department(1L, "FX"))
			.isNotEqualTo(new Department(1L, null))
			.isNotEqualTo(new Department(2L, "FX"));
	}
	
	@Test
	void testDepartmentHashcodeOverride() {
		Department testDepartment1 = new Department(1L, "FX");
		Department testDepartment2 = testDepartment1;
		Department testDepartment3 = new Department(2L, "FX");
		Department testDepartment4 = new Department(1L, null);

		Assertions.assertThat(testDepartment1.hashCode())
			.isEqualTo(testDepartment2.hashCode())
			.isNotEqualTo(testDepartment3.hashCode())
			.isNotEqualTo(testDepartment4.hashCode());
	}
}
