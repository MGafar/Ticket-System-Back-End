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

import com.barclays.ticketsystem.persistence.domain.Topic;
import com.barclays.ticketsystem.persistence.repository.TopicRepository;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class TopicServiceUnitTest {

	@Autowired
	private TopicService topicService;
	
	@MockBean
	private TopicRepository topicRepository;
	
	@Test
	void testReadAll() {
		List<Topic> expectedTopics = new ArrayList<>();
		expectedTopics.add(new Topic(1L, "Topic 1"));
		expectedTopics.add(new Topic(2L, "Topic 2"));
		
		Mockito.when(this.topicRepository.findAll()).thenReturn(expectedTopics);
		Assertions.assertThat(this.topicService.readAll()).isEqualTo(expectedTopics);
	}
}