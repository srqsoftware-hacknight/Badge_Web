package io.srqsoftware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DefaultRfidAuthorizationService implements RfidAuthorizationService {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public DefaultRfidAuthorizationService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	// todo: device_id is being used for API contract only, update later
	public int getAuthorizationStatus(String badge_id, String device_id) {
		try {
			System.out.println("HIA: " + badge_id + " device_id " + device_id);
			
			String query = "select rfid_id, issued_date, first_name, last_name from badges where rfid_id=?";
			User u = jdbcTemplate.queryForObject(query, new Object[]{badge_id}, new UserRowMapper());
			return (u != null) ? AUTHORIZED : UNAUTHORIZED;
		} catch (Exception e) {
			return UNAUTHORIZED;
		}
	}
}
