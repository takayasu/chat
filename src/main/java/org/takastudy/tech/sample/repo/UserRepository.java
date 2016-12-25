package org.takastudy.tech.sample.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.takastudy.tech.sample.model.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	public User findByUserId(String userid);

}
