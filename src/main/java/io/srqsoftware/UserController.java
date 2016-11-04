package io.srqsoftware;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.apache.log4j.Logger;

/**
 * 
 * @author tdc
 */
@Controller
@RequestMapping("/users")
public class UserController {
	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	private final UserService us;
	
	@Autowired
	public UserController(UserService us) {
		this.us = us;
	}

	private boolean isValidUser(User user) {
		return !((StringUtils.isEmpty(user)) || (StringUtils.isEmpty(user.getFirstName())) ||
				(StringUtils.isEmpty(user.getLastName())) || (StringUtils.isEmpty(user.getRfidId())) ||
				(user.getFirstName().length() < 1) || (user.getLastName().length() < 1) || (user.getRfidId().length() < 3));
	}

	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<String> updateUserStatus(@RequestBody User user) {
		if (!isValidUser(user)) {
			return new ResponseEntity<>("{\"response\": \"required parameter missing\"}", HttpStatus.NOT_ACCEPTABLE);
		}

		try {
			us.updateUser(user);

			return new ResponseEntity<>("{\"response\": \""+user.getUserId()+"\"}", HttpStatus.ACCEPTED);
		} catch(Exception e) {
			LOGGER.error("Deactivating badge: " + user.getRfidId() + " failed", e);

			return new ResponseEntity<>("{\"response\": \""+e.getLocalizedMessage()+"\"}", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestBody User user) {
		if (!isValidUser(user)) {
			return new ResponseEntity<>("{\"response\": \"required parameter missing\"}", HttpStatus.NOT_ACCEPTABLE);
		}

		try {
			return new ResponseEntity<>("{\"response\": \""+us.createUser(user)+"\"}", HttpStatus.CREATED);
		} catch(Exception e) {
			LOGGER.error("Creating badge: " + user.getRfidId() + " failed", e);

			return new ResponseEntity<>("{\"response\": \""+e.getLocalizedMessage()+"\"}", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping("/list")
	public ResponseEntity<List<User>> listUsers() {
		return new ResponseEntity<>(us.getAllUsers(), HttpStatus.OK);
    }
}
