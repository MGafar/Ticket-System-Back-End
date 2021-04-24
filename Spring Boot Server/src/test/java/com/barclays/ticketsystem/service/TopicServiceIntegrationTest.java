package com.barclays.ticketsystem.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.barclays.ticketsystem.persistence.domain.Topic;
import com.barclays.ticketsystem.persistence.repository.TopicRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
class TopicServiceIntegrationTest {

	@Autowired
	private TopicService topicService;
	
	@Autowired
	private TopicRepository topicRepository;
		
	@Test
	void testReadAll() {
		Topic topic1 = new Topic(1L, "Topic 1");
		Topic topic2 = new Topic(2L, "Topic 2");
		List<Topic> toSave = List.of(topic1, topic2);	
		
		this.topicRepository.saveAll(toSave);
		assertThat(this.topicService.readAll()).isEqualTo(toSave);
	}
}
