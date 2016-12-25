package org.takastudy.tech.sample.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.takastudy.tech.sample.model.LineItem;
import org.takastudy.tech.sample.model.User;
import org.takastudy.tech.sample.repo.LineItemRepository;
import org.takastudy.tech.sample.repo.UserRepository;

@RestController
@RequestMapping("/line")
public class LineController {
	
	@Autowired
	private LineItemRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@RequestMapping(path="/lists",method=RequestMethod.GET)
	public Page<LineItem> getList(){
		
		PageRequest page = new PageRequest(0, 200, new Sort(Sort.Direction.DESC,"date"));
		
		return repository.findAll(page);
	}
	
	@RequestMapping(path="/lists/user/{name}")
	public List<LineItem> getListByName(@PathVariable("name") String name){
		return repository.findByName(name,new Sort(Sort.Direction.DESC,"date"));
	}
	
	@RequestMapping(path="/lists",method=RequestMethod.POST,headers = {"content-type=*","accept=application/json"})
	public String postItem(@RequestBody LineItem item,@CookieValue(value="jsession", required=false) String uuid){
		
		if(StringUtils.isEmpty(uuid)){
			return "Not Login";
		}
		
		String userId = stringRedisTemplate.opsForValue().get(uuid);
		
		User user = userRepository.findByUserId(userId);
		
		String id = UUID.randomUUID().toString();
		
		item.setId(id);
		item.setDate(new Date());
		item.setUserid(userId);
		item.setName(user.getName());
		
		repository.save(item);
		
		
		return id;
	}
	
}
