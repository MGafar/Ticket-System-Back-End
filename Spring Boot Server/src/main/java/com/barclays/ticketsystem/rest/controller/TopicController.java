package com.barclays.ticketsystem.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.ticketsystem.persistence.domain.Topic;
import com.barclays.ticketsystem.service.TopicService;

@RestController
@RequestMapping("/topic/")
public class TopicController  {

	@Autowired
	private TopicService topicService;

	public TopicController(TopicService topicService) {
		super();
		this.topicService = topicService;
	}

	@GetMapping("/readAll")
	public ResponseEntity<List<Topic>> readAll() {
		return ResponseEntity.ok(this.topicService.readAll());
	}
}