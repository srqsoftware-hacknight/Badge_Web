package io.srqsoftware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;

@Component
public class DefaultBadgeAuthorizationService implements BadgeAuthorizationService {
	private final JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = Logger.getLogger(DefaultBadgeAuthorizationService.class);

	@Autowired
	public DefaultBadgeAuthorizationService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int getAuthorizationStatus(String badgeId, String deviceId) {
		try {
			String query = "select count(badge_id) from badges where badge_id=?";
			int activeBadges = jdbcTemplate.queryForObject(query, new Object[]{badgeId}, Integer.class);

			LOGGER.info("Badge id: " + badgeId + " for device id:" + deviceId + " - " + (activeBadges > 0 ? "authorized" : "unauthorized"));

			return activeBadges > 0 ? AUTHORIZED : UNAUTHORIZED;
		} catch (Exception e) {
			LOGGER.error("Error authorizing badge id: " + badgeId + " with device id: " + deviceId, e);

			return UNAUTHORIZED;
		}
	}
}
