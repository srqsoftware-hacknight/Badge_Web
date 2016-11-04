package io.srqsoftware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This class is responsible for accepting a request and determining the authorization of a particular RFID device.
 * 
 * @author tdc
 */
@Controller
@RequestMapping("/rfid")
public class RfidController {

	private final RfidAuthorizationService ras;
	
	@Autowired
	public RfidController(RfidAuthorizationService ras) {
		this.ras = ras;
	}

	@RequestMapping("/check")
	public ResponseEntity<String> getRfidStatus(@RequestParam(name="badge_id") String badgeId,
												@RequestParam(name="device_id") String deviceId) {
		int resp = ras.getAuthorizationStatus(badgeId, deviceId);

		if (resp == DefaultRfidAuthorizationService.AUTHORIZED) {
			return new ResponseEntity<>("{\"response\": \"accept\"}", HttpStatus.OK);
		}

		return new ResponseEntity<>("{\"response\": \"deny\"}", HttpStatus.UNAUTHORIZED);
	}
}
