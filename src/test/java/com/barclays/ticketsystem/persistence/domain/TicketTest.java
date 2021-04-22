package com.barclays.ticketsystem.persistence.domain;

import static org.assertj.core.api.Assertions.from;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class TicketTest {

	@Test
	void testTicketConstructor() {
		Ticket testTicket = new Ticket(1L, "Title", "Author", "Description");
		String expectedString = "Ticket [id: 1 title: Title author: Author description: Description department: null]";
		Assertions.assertThat(testTicket.toString()).isEqualTo(expectedString);
	}
	
	@Test
	void testGettersAndSetter() {
		Ticket testTicket = new Ticket();
		Department testDepartment = new Department(1L, "FX");
		
		testTicket.setId(1L);
		testTicket.setTitle("Title");
		testTicket.setAuthor("Author");
		testTicket.setDescription("Description");
		testTicket.setTimeCreated(new Date (123456789L));
		testTicket.setTimeUpdated(new Date (987654321L));
		testTicket.setDepartment(testDepartment);
		
		Assertions.assertThat(testTicket)
			.returns(1L, from(Ticket::getId))
			.returns("Title", from(Ticket::getTitle))
			.returns("Author", from(Ticket::getAuthor))
			.returns("Description", from(Ticket::getDescription))
			.returns(new Date (123456789L), from(Ticket::getTimeCreated))
			.returns(new Date (987654321L), from(Ticket::getTimeUpdated))
			.returns(testDepartment, from(Ticket::getDepartment));
	}

	@Test
	void testTicketEqualsOverride() {
		Department testDepartment = new Department(1L, "FX");
		Ticket testTicket = new Ticket(1L, "Title", "Author", "Description", testDepartment);

		Assertions.assertThat(new Ticket(1L, null, null, null, null))
			.isEqualTo(new Ticket(1L, null, null, null, null))
			.isNotEqualTo(null)
			.isNotEqualTo(123)
			.isNotEqualTo(new Ticket(1L, "Title", null, null, null))
			.isNotEqualTo(new Ticket(1L, null, "Author", null, null))
			.isNotEqualTo(new Ticket(1L, null, null, "Description", null))
			.isNotEqualTo(new Ticket(1L, null, null, null, testDepartment));
		
		Assertions.assertThat(testTicket)
			.isEqualTo(testTicket)
			.isEqualTo(new Ticket(1L, "Title", "Author", "Description", testDepartment))
			.isNotEqualTo(new Ticket(1L, null, null, null, null))
			.isNotEqualTo(new Ticket(1L, "Title", null, null, null))
			.isNotEqualTo(new Ticket(1L, null, "Author", null, null))
			.isNotEqualTo(new Ticket(1L, null, null, "Description", null))
			.isNotEqualTo(new Ticket(1L, null, null, null, testDepartment))
			.isNotEqualTo(new Ticket(1L, "Title", "Author", null, testDepartment))
			.isNotEqualTo(new Ticket(1L, null, "Author", "Description", testDepartment))
			.isNotEqualTo(new Ticket(2L, "Title", "Author", "Description", testDepartment))
			.isNotEqualTo(new Ticket(1L, "WrongTitle", "Author", "Description", testDepartment))
			.isNotEqualTo(new Ticket(1L, "Title", "WrongAuthor", "Description", testDepartment))
			.isNotEqualTo(new Ticket(1L, "Title", "Author", "WrongDescription", testDepartment))
			.isNotEqualTo(new Ticket(1L, "Title", "Author", "WrongDescription", new Department(2L, "Credit")))
			;
	}

	@Test
	void testTicketHashcodeOverride() {
		Ticket testTicket1 = new Ticket(1L, "Title", "Author", "Description");
		Ticket testTicket2 = testTicket1;
		Ticket testTicket3 = new Ticket(2L, "Title", "Author", "Description");
		Ticket testTicket4 = new Ticket(1L, null, "Author", "Description");
		Ticket testTicket5 = new Ticket(1L, "Title", null, "Description");
		Ticket testTicket6 = new Ticket(1L, "Title", "Author", null);
		
		Assertions.assertThat(testTicket1.hashCode())
			.isEqualTo(testTicket2.hashCode())
			.isNotEqualTo(testTicket3.hashCode())
			.isNotEqualTo(testTicket4.hashCode())
			.isNotEqualTo(testTicket5.hashCode())
			.isNotEqualTo(testTicket6.hashCode());
	}
}