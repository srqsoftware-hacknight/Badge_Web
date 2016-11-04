package io.srqsoftware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;

@Component
public class DefaultRfidAuthorizationService implements RfidAuthorizationService {
	private final JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = Logger.getLogger(DefaultRfidAuthorizationService.class);

	@Autowired
	public DefaultRfidAuthorizationService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int getAuthorizationStatus(String badge_id, String device_id) {
		try {
			String query = "select rfid_id, issued_date, first_name, last_name from badges where rfid_id=?";
			User u = jdbcTemplate.queryForObject(query, new Object[]{badge_id}, new UserRowMapper());

			LOGGER.info("Badge id: " + badge_id + " for device id:" + device_id + " - " + (u != null ? "authorized" : "unauthorized"));

			return (u != null) ? AUTHORIZED : UNAUTHORIZED;
		} catch (Exception e) {
			LOGGER.error("Error authorizing badge id: " + badge_id + " with device id: " + device_id, e);

			return UNAUTHORIZED;
		}
	}
}
