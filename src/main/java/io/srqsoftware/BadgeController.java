package io.srqsoftware;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author tdc
 */
@Controller
public class BadgeController {
	private static final Logger LOGGER = Logger.getLogger(BadgeController.class);

	private final BadgeService us;
	private final BadgeAuthorizationService bas;

	
	@Autowired
	public BadgeController(BadgeService us, BadgeAuthorizationService bas) {
		this.us = us;
		this.bas = bas;
	}

	private boolean validateBadge(Badge badge) {
		return !((StringUtils.isEmpty(badge)) || (StringUtils.isEmpty(badge.getFirstName())) ||
				(StringUtils.isEmpty(badge.getLastName())) || (StringUtils.isEmpty(badge.getBadgeId())) ||
				(badge.getFirstName().length() < 1) || (badge.getLastName().length() < 1) || (badge.getBadgeId().length() < 3));
	}

	@RequestMapping(path = "/badges/deactivate", method=RequestMethod.PUT)
	public ResponseEntity<String> deactivateBadge(@RequestBody Badge badge) {
		if (!validateBadge(badge)) {
			return new ResponseEntity<>("{\"response\": \"required parameter missing\"}", HttpStatus.NOT_ACCEPTABLE);
		}

		try {
			us.deactivateBadge(badge);

			return new ResponseEntity<>("{\"response\": \""+ badge.getBadgeId()+"\"}", HttpStatus.ACCEPTED);
		} catch(Exception e) {
			LOGGER.error("Deactivating badge: " + badge.getBadgeId() + " failed", e);

			return new ResponseEntity<>("{\"response\": \""+e.getLocalizedMessage()+"\"}", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@RequestMapping(path = "/badges", method=RequestMethod.PUT)
	public ResponseEntity<String> updateBadge(@RequestBody Badge badge) {
		if (!validateBadge(badge)) {
			return new ResponseEntity<>("{\"response\": \"required parameter missing\"}", HttpStatus.NOT_ACCEPTABLE);
		}

		try {
			us.updateBadge(badge);

			return new ResponseEntity<>("{\"response\": \""+ badge.getBadgeId()+"\"}", HttpStatus.ACCEPTED);
		} catch(Exception e) {
			LOGGER.error("Updating badge: " + badge.getBadgeId() + " failed", e);

			return new ResponseEntity<>("{\"response\": \""+e.getLocalizedMessage()+"\"}", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	
	@RequestMapping(path = "/badges", method=RequestMethod.POST)
	public ResponseEntity<String> addBadge(@RequestBody Badge badge) {
		if (!validateBadge(badge)) {
			return new ResponseEntity<>("{\"response\": \"required parameter missing\"}", HttpStatus.NOT_ACCEPTABLE);
		}

		try {
			return new ResponseEntity<>("{\"response\": \""+us.createBadge(badge)+"\"}", HttpStatus.CREATED);
		} catch(Exception e) {
			LOGGER.error("Creating badge: " + badge.getBadgeId() + " failed", e);

			return new ResponseEntity<>("{\"response\": \""+e.getLocalizedMessage()+"\"}", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@RequestMapping(path = "/adminpw", method=RequestMethod.POST)
	public ResponseEntity<String> addAdminPassword(@RequestBody User user) {
		if ((user== null) || (user.getPassword() == null) || (user.getPassword().length() < 1)) {
			return new ResponseEntity<>("{\"response\": \"required parameter missing\"}", HttpStatus.NOT_ACCEPTABLE);
		}

		User adminUser = new User();
		adminUser.setUserName("admin");
		adminUser.setPassword(encoder().encode(user.getPassword()));

		boolean rc = us.initAdmin(adminUser.getPassword());
		if (rc) {
			return new ResponseEntity<>("{\"response\": \"success\"}", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("{\"response\": \"failure\"}", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	private PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}


	@RequestMapping(path = "/badges", method = RequestMethod.GET)
	public ResponseEntity<Badge> getBadge(@RequestParam(name="badge_id") String badgeId) {
		return new ResponseEntity<>(us.getBadge(badgeId), HttpStatus.OK);
    }

	@RequestMapping(path = "/badges/list")
	public ResponseEntity<List<Badge>> listBadges() {
		return new ResponseEntity<>(us.getAllBadges(), HttpStatus.OK);
    }

	@RequestMapping(path = "/device/check", method = RequestMethod.GET)
	public ResponseEntity<String> checkBadgeAuthorization(@RequestParam(name="badge_id") String badgeId,
														  @RequestParam(name="device_id") String deviceId) {
		
		int resp = bas.getAuthorizationStatus(badgeId, deviceId);
		
		if (resp == DefaultBadgeAuthorizationService.AUTHORIZED) {
			return new ResponseEntity<>("{\"response\": \"accept\"}", HttpStatus.OK);
		}

		return new ResponseEntity<>("{\"response\": \"deny\"}", HttpStatus.UNAUTHORIZED);
	}
}
