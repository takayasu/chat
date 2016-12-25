package org.takastudy.tech.sample.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.takastudy.tech.sample.model.User;
import org.takastudy.tech.sample.repo.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private static final String ERRMSG = "メールアドレスまたはパスワードが異なリます";
	
	public static final String SESSION_ID_NAME = "jsession";
	
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@RequestMapping(path="/login",method=RequestMethod.POST)
	public ResponseEntity<UserDetail> login(@RequestParam("userId") String userId,@RequestParam("password") String passwd){
		
		User user = repository.findByUserId(userId);
		
		UserDetail detail = loginLogic(user, passwd);
		
		HttpHeaders headers = new HttpHeaders();
		if(detail.isLogin()){
			String sid = UUID.randomUUID().toString();
	        headers.add(HttpHeaders.SET_COOKIE, SESSION_ID_NAME+"="+sid+";");
	        stringRedisTemplate.opsForValue().set(sid, detail.getUserId());
		}

        HttpStatus status = HttpStatus.OK;
        
        return new ResponseEntity<>(detail, headers, status);
		
	}
	
	
	private UserDetail loginLogic(User user,String inputLogin){
		
		UserDetail detail = new UserDetail();
		String pdDigest = User.convertPasswordDigest(inputLogin);
		
		if(user == null || user.getPasswordDigest() == null){
			detail.setLogin(false);
			detail.setMsg(ERRMSG);
			
			return detail;
		}
		
		if(user.getPasswordDigest().equals(pdDigest)){
			detail.setLogin(true);
			detail.setUserId(user.getUserId());
			detail.setUserName(user.getName());

			return detail;
		}else{
			detail.setLogin(false);
			detail.setMsg(ERRMSG);
			return detail;
		}
		
	}

	
	
}
