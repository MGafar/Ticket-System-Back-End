package com.barclays.ticketsystem.persistence.domain;

import static org.assertj.core.api.Assertions.from;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class TopicTest {

	@Test
	void testTopicConstructor() {
		Topic testTopic = new Topic(1L, "FX");
		String expectedString = "Topic [id: 1 name: FX]";
		Assertions.assertThat(testTopic.toString()).isEqualTo(expectedString);
	}

	@Test
	void testGettersAndSetter() {
		Ticket testTicket1 = new Ticket(1L, "Title", "Author", "Description", "Solution", Status.DONE, null, null);
		Ticket testTicket2 = new Ticket(2L, "Title2", "Author2", "Description2", "Solution2", Status.DONE, null, null);
		Ticket testTicket3 = new Ticket(3L, "Title3", "Author3", "Description3", "Solution3", Status.DONE, null, null);
		Topic testTopic = new Topic();
		
		testTopic.setId(1L);
		testTopic.setName("Remote Connectivity Issues");
		testTopic.setTickets(List.of(testTicket1, testTicket2, testTicket3));
		
		Assertions.assertThat(testTopic)
			.returns(1L, from(Topic::getId))
			.returns("Remote Connectivity Issues", from(Topic::getName))
			.returns(List.of(testTicket1, testTicket2, testTicket3), from(Topic::getTickets));
	}
	
	@Test
	void testTopicEqualsOverride() {
		Topic testTopic = new Topic(1L, "Messaging Problems");

		Assertions.assertThat(new Topic(1L, null))
			.isEqualTo(new Topic(1L, null))
			.isNotEqualTo(null)
			.isNotEqualTo(123)
			.isNotEqualTo(new Topic(1L, "Messaging Problems"));
		
		Assertions.assertThat(testTopic)
			.isEqualTo(testTopic)
			.isEqualTo(new Topic(1L, "Messaging Problems"))
			.isNotEqualTo(new Topic(1L, null))
			.isNotEqualTo(new Topic(2L, "Messaging Problems"));
	}
	
	@Test
	void testTopicHashcodeOverride() {
		Topic testTopic1 = new Topic(1L, "Messaging Problems");
		Topic testTopic2 = testTopic1;
		Topic testTopic3 = new Topic(2L, "Remote Connectivity Issues");
		Topic testTopic4 = new Topic(1L, null);

		Assertions.assertThat(testTopic1.hashCode())
			.isEqualTo(testTopic2.hashCode())
			.isNotEqualTo(testTopic3.hashCode())
			.isNotEqualTo(testTopic4.hashCode());
	}
}
