package com.barclays.ticketsystem.persistence.domain;

import static org.assertj.core.api.Assertions.from;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class TicketTest {

	@Test
	void testTicketConstructor() {
		Ticket testTicket = new Ticket("Title", "Author", "Description");
		testTicket.setId(1L);
		String expectedString = "Ticket [id: 1 title: Title author: Author description: Description]";
		Assertions.assertThat(testTicket.toString()).isEqualTo(expectedString);
	}
	
	@Test
	void testGettersAndSetter() {
		Ticket testTicket = new Ticket();
		
		testTicket.setId(1L);
		testTicket.setTitle("Title");
		testTicket.setAuthor("Author");
		testTicket.setDescription("Description");
		testTicket.setTimeCreated(new Date (123456789L));
		testTicket.setTimeUpdated(new Date (987654321L));
		
		Assertions.assertThat(testTicket)
			.returns(1L, from(Ticket::getId))
			.returns("Title", from(Ticket::getTitle))
			.returns("Author", from(Ticket::getAuthor))
			.returns("Description", from(Ticket::getDescription))
			.returns(new Date (123456789L), from(Ticket::getTimeCreated))
			.returns(new Date (987654321L), from(Ticket::getTimeUpdated));
	}

	@Test
	void testTicketEqualsAndHashcodeOverride() {
		Ticket testTicket1 = new Ticket(1L, "Title", "Author", "Description");
		Ticket testTicket2 = new Ticket(1L, "Title", "Author", "Description");
		Ticket testTicket3 = new Ticket(2L, "Title", "Author", "Description");

		Assertions.assertThat(testTicket1)
			.isEqualTo(testTicket1)
			.isEqualTo(testTicket2)
			.isNotEqualTo(null)
			.isNotEqualTo(123)
			.isNotEqualTo(testTicket3);
				
		Assertions.assertThat(testTicket1.hashCode())
			.isEqualTo(testTicket2.hashCode())
			.isNotEqualTo(testTicket3.hashCode());
	}
}