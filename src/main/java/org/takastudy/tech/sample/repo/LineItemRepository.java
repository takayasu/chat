package org.takastudy.tech.sample.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.takastudy.tech.sample.model.LineItem;

public interface LineItemRepository extends MongoRepository<LineItem, String> {
	
	public Page<LineItem> findAll(Pageable page);
	
	public List<LineItem> findByName(String name,Sort sort);

}
