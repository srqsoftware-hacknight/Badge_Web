package io.srqsoftware;

public interface BadgeAuthorizationService {
	int AUTHORIZED = 0;
	int UNAUTHORIZED = 1;
	
	int getAuthorizationStatus(String badgeId, String deviceId);
}
