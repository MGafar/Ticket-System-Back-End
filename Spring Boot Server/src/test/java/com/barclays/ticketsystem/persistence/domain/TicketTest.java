package com.barclays.ticketsystem.persistence.domain;

import static org.assertj.core.api.Assertions.from;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class TicketTest {

	@Test
	void testTicketConstructor() {
		Ticket testTicket = new Ticket(1L, "Title", "Author", "Description", "Solution", Status.DONE, null, null);
		String expectedString = "Ticket [id: 1 title: Title author: Author description: Description department: null solution: Solution status: DONE topic: null]";
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
		testTicket.setSolution("Solution");
		testTicket.setStatus(Status.DONE);
		
		Assertions.assertThat(testTicket)
			.returns(1L, from(Ticket::getId))
			.returns("Title", from(Ticket::getTitle))
			.returns("Author", from(Ticket::getAuthor))
			.returns("Description", from(Ticket::getDescription))
			.returns(new Date (123456789L), from(Ticket::getTimeCreated))
			.returns(new Date (987654321L), from(Ticket::getTimeUpdated))
			.returns(testDepartment, from(Ticket::getDepartment))
			.returns("Solution", from(Ticket::getSolution))
			.returns(Status.DONE, from(Ticket::getStatus));
	}

	@Test
	void testTicketEqualsOverride() {
		Ticket testTicket1 = new Ticket(1L, "Title", "Author", "Description", "Solution", Status.DONE, null, null);
		Ticket testTicket2 = testTicket1;
		Ticket testTicket3 = new Ticket(2L, "Title", "Author", "Description", "Solution", Status.DONE, null, null);
		Ticket testTicket4 = new Ticket(1L, null, "Author", "Description", "Solution", Status.DONE, null, null);
		Ticket testTicket5 = new Ticket(1L, "WrongTitle", "Author", "Description", "Solution", Status.DONE, null, null);
		Ticket testTicket6 = new Ticket(1L, "Title", null, "Description", "Solution", Status.DONE, null, null);
		Ticket testTicket7 = new Ticket(1L, "Title", "WrongAuthor", "Description", "Solution", Status.DONE, null, null);
		Ticket testTicket8 = new Ticket(1L, "Title", "Author", null, "Solution", Status.DONE, null, null);
		Ticket testTicket9 = new Ticket(1L, "Title", "Author", "WrongDescription", "Solution", Status.DONE, null, null);
		Ticket testTicket10 = new Ticket(1L, "Title", "Author", "Description", null, Status.DONE, null, null);
		Ticket testTicket11 = new Ticket(1L, "Title", "Author", "Description", "WrongSolution", Status.DONE, null, null);
		Ticket testTicket12 = new Ticket(1L, "Title", "Author", "Description", "Solution", null, null, null);
		Ticket testTicket13 = new Ticket(1L, "Title", "Author", "Description", "Solution", Status.OPEN, null, null);
		
		
		Assertions.assertThat(testTicket1)
			.isEqualTo(testTicket1)
			.isEqualTo(testTicket2)
			.isNotEqualTo(testTicket3)
			.isNotEqualTo(testTicket4)
			.isNotEqualTo(testTicket5)
			.isNotEqualTo(testTicket6)
			.isNotEqualTo(testTicket7)
			.isNotEqualTo(testTicket8)
			.isNotEqualTo(testTicket9)
			.isNotEqualTo(testTicket10)			
			.isNotEqualTo(testTicket11)
			.isNotEqualTo(testTicket12)
			.isNotEqualTo(testTicket13)
			.isNotEqualTo(null)
			.isNotEqualTo(123);
	}

	@Test
	void testTicketHashcodeOverride() {
		Ticket testTicket1 = new Ticket(1L, "Title", "Author", "Description", "Solution", Status.DONE, null, null);
		Ticket testTicket2 = testTicket1;
		Ticket testTicket3 = new Ticket(2L, "Title", "Author", "Description", "Solution", Status.DONE, null, null);
		Ticket testTicket4 = new Ticket(1L, null, "Author", "Description", "Solution", Status.DONE, null, null);
		Ticket testTicket5 = new Ticket(1L, "WrongTitle", "Author", "Description", "Solution", Status.DONE, null, null);
		Ticket testTicket6 = new Ticket(1L, "Title", null, "Description", "Solution", Status.DONE, null, null);
		Ticket testTicket7 = new Ticket(1L, "Title", "WrongAuthor", "Description", "Solution", Status.DONE, null, null);
		Ticket testTicket8 = new Ticket(1L, "Title", "Author", null, "Solution", Status.DONE, null, null);
		Ticket testTicket9 = new Ticket(1L, "Title", "Author", "WrongDescription", "Solution", Status.DONE, null, null);
		Ticket testTicket10 = new Ticket(1L, "Title", "Author", "Description", null, Status.DONE, null, null);
		Ticket testTicket11 = new Ticket(1L, "Title", "Author", "Description", "WrongSolution", Status.DONE, null, null);
		Ticket testTicket12 = new Ticket(1L, "Title", "Author", "Description", "Solution", null, null, null);
		Ticket testTicket13 = new Ticket(1L, "Title", "Author", "Description", "Solution", Status.OPEN, null, null);
		
		
		Assertions.assertThat(testTicket1.hashCode())
			.isEqualTo(testTicket2.hashCode())
			.isNotEqualTo(testTicket3.hashCode())
			.isNotEqualTo(testTicket4.hashCode())
			.isNotEqualTo(testTicket5.hashCode())
			.isNotEqualTo(testTicket6.hashCode())
			.isNotEqualTo(testTicket7.hashCode())
			.isNotEqualTo(testTicket8.hashCode())
			.isNotEqualTo(testTicket9.hashCode())
			.isNotEqualTo(testTicket10.hashCode())			
			.isNotEqualTo(testTicket11.hashCode())
			.isNotEqualTo(testTicket12.hashCode())
			.isNotEqualTo(testTicket13.hashCode());
	}
}