package org.takastudy.tech.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.takastudy.tech.sample.model.LineItem;
import org.takastudy.tech.sample.model.User;
import org.takastudy.tech.sample.repo.LineItemRepository;
import org.takastudy.tech.sample.repo.UserRepository;
import org.takastudy.tech.sample.test.TestDataFactory;


@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private LineItemRepository repository;
	
	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping("/geneate/{count}")
	public String generate(@PathVariable("count") int count){
		List<LineItem> list = TestDataFactory.createTestData(count);
		
		repository.insert(list);
		
		return "OK:"+count;
	}
	
	@RequestMapping("/user")
	
	public String createUser(){
		
		User user = new User();
		user.setName("Test Name");
		user.setUserId("test@example.com");
		user.setPasswordDigest("test");
		
		userRepo.save(user);
		
		return "OK";
	}
	
	

}
