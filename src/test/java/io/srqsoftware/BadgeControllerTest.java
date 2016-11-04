package io.srqsoftware;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BadgeControllerTest {
	@Test
	public void testBadgeAuthNegative() {
		BadgeAuthorizationService ras = new UnauthorizedBadgeAuthorizationService();
		BadgeController bc = new BadgeController(null, ras);
		ResponseEntity<String> re = bc.checkBadgeAuthorization("1234", "1");
		
		assertEquals(re.getStatusCode(), HttpStatus.UNAUTHORIZED);
		assertEquals(re.getBody(), "{\"response\": \"deny\"}");
	}

	@Test
	public void testBadgeAuthPositive() {
		BadgeAuthorizationService ras = new AuthorizedBadgeAuthorizationService();
		BadgeController bc = new BadgeController(null, ras);
		ResponseEntity<String> re = bc.checkBadgeAuthorization("1234","1");
		
		assertEquals(re.getStatusCode(), HttpStatus.OK);
		assertEquals(re.getBody(), "{\"response\": \"accept\"}");
	}

	private static class UnauthorizedBadgeAuthorizationService implements BadgeAuthorizationService {
		@Override
		public int getAuthorizationStatus(String badgeId, String deviceId) {
			return BadgeAuthorizationService.UNAUTHORIZED;
		}
	}
	
	private static class AuthorizedBadgeAuthorizationService implements BadgeAuthorizationService {
		@Override
		public int getAuthorizationStatus(String badgeId, String deviceId) {
			return BadgeAuthorizationService.AUTHORIZED;
		}
	}
}
