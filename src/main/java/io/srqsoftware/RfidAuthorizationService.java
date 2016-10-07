package io.srqsoftware;

public interface RfidAuthorizationService {

	static final int AUTHORIZED = 0;
	static final int UNAUTHORIZED = 1;
	
	int getAuthorizationStatus(int keyId);

}
