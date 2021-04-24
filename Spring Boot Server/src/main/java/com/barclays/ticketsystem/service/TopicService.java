package com.barclays.ticketsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barclays.ticketsystem.persistence.domain.Topic;
import com.barclays.ticketsystem.persistence.repository.TopicRepository;

@Service
public class TopicService {
	
	@Autowired
	private TopicRepository topicRepository;
	
	public TopicService(TopicRepository topicRepository) {
		super();
		this.topicRepository = topicRepository;
	}
	
	public List<Topic> readAll() {
		return this.topicRepository.findAll();
	}
}
