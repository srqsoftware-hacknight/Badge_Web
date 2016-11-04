package io.srqsoftware;

public interface RfidAuthorizationService {
	int AUTHORIZED = 0;
	int UNAUTHORIZED = 1;
	
	int getAuthorizationStatus(String badge_id, String device_id);
}
