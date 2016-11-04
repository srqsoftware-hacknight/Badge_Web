package io.srqsoftware;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author tdc
 *
 */
@Controller
@RequestMapping("/users")
public class UserController {

	private final UserService us;
	
	@Autowired
	public UserController(UserService us) {
		this.us = us;
	}

	private boolean isValidUser(User user) {
		if ((user == null) || (user.getFirstName() == null) || (user.getLastName() == null) || (user.getRfidId() == null) ||
			(user.getFirstName().length() < 1) || (user.getLastName().length() < 1) || (user.getRfidId().length() < 3)) {
			return false;
		}
		return true;
	}

	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<String> updateUserStatus(@RequestBody User user) {
		if (!isValidUser(user)) {
			return new ResponseEntity<String>("{\"response\": \"required parameter missing\"}", HttpStatus.NOT_ACCEPTABLE);
		}
		try {
			us.updateUser(user);
			return new ResponseEntity<String>("{\"response\": \""+user.getUserId()+"\"}", HttpStatus.ACCEPTED);
		} catch(Exception e) {
			return new ResponseEntity<String>("{\"response\": \""+e.getLocalizedMessage()+"\"}", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestBody User user) {
		if (!isValidUser(user)) {
			return new ResponseEntity<String>("{\"response\": \"required parameter missing\"}", HttpStatus.NOT_ACCEPTABLE);
		}
		try {
			return new ResponseEntity<String>("{\"response\": \""+us.createUser(user)+"\"}", HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<String>("{\"response\": \""+e.getLocalizedMessage()+"\"}", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
  @RequestMapping("/list")
  public ResponseEntity<List<User>> listUsers() {
    	List<User> Temp = us.getAllUsers();
		return new ResponseEntity<List<User>>(Temp, HttpStatus.OK);
    }
  
	
}
