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

import com.barclays.ticketsystem.persistence.domain.Topic;
import com.barclays.ticketsystem.service.TopicService;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class TopicControllerUnitTest {

	@Autowired
	private TopicController topicController;
	
	@MockBean
	private TopicService topicService;
	
	@Test
	void testReadAll() {
		List<Topic> expectedTopics = new ArrayList<>();
		expectedTopics.add(new Topic(1L, "Topic 1"));
		expectedTopics.add(new Topic(2L, "Topic 2"));
		Mockito.when(this.topicService.readAll()).thenReturn(expectedTopics);
		ResponseEntity<List<Topic>> expected = new ResponseEntity<>(expectedTopics, HttpStatus.OK);
		Assertions.assertThat(this.topicController.readAll()).isEqualTo(expected);
		Mockito.verify(this.topicService, Mockito.times(1)).readAll();
	}

}
