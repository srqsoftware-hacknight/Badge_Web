package io.srqsoftware;

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

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestBody User user) {
		// Require first name, last name, and the RFID identifier
		if (!isValidUser(user)) {
			return new ResponseEntity<String>("{\"response\": \"required parameter missing\"}", HttpStatus.NOT_ACCEPTABLE);
		}
		try {
			System.out.println(user);
			long resp = us.createUser(user);
			return new ResponseEntity<String>("{\"response\": \""+resp+"\"}", HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<String>("{\"response\": \""+e.getLocalizedMessage()+"\"}", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
}
