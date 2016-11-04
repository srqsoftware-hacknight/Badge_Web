package io.srqsoftware;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RfidControllerTest {

	@Test
	public void testRfidControllerNegative() {
		RfidAuthorizationService ras = new UnauthorizedRfidAuthorizationService();
		RfidController rc = new RfidController(ras);
		ResponseEntity<String> re = rc.getRfidStatus("1234", "1");
		
		assertEquals(re.getStatusCode(), HttpStatus.UNAUTHORIZED);
		assertEquals(re.getBody(), "{\"response\": \"deny\"}");
	}

	@Test
	public void testRfidControllerPositive() {
		RfidAuthorizationService ras = new AuthorizedRfidAuthorizationService();
		RfidController rc = new RfidController(ras);
		ResponseEntity<String> re = rc.getRfidStatus("1234","1");
		
		assertEquals(re.getStatusCode(), HttpStatus.OK);
		assertEquals(re.getBody(), "{\"response\": \"accept\"}");
	}
	

	private static class UnauthorizedRfidAuthorizationService implements RfidAuthorizationService {
		@Override
		public int getAuthorizationStatus(String badge_id, String device_id) {
			return RfidAuthorizationService.UNAUTHORIZED;
		}
	}
	
	private static class AuthorizedRfidAuthorizationService implements RfidAuthorizationService {
		@Override
		public int getAuthorizationStatus(String badge_id, String device_id) {
			return RfidAuthorizationService.AUTHORIZED;
		}
	}

}
