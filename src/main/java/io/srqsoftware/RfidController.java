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
 *
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
	public ResponseEntity<String> getRfidStatus(@RequestParam(name="id", required=true) int keyId) {
		int resp = ras.getAuthorizationStatus(keyId);
		if (resp == DefaultRfidAuthorizationService.AUTHORIZED) {
			return new ResponseEntity<String>("{\"response\": \"accept\"}", HttpStatus.OK);
		}
		return new ResponseEntity<String>("{\"response\": \"deny\"}", HttpStatus.UNAUTHORIZED);
	}
	
}
