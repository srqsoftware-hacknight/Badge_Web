package io.srqsoftware;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DefaultBadgeService implements BadgeService {
	private static final Logger LOGGER = Logger.getLogger(DefaultBadgeService.class);

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public DefaultBadgeService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public String createBadge(Badge badge) {
		String query = "insert into badges(badge_id, first_name, last_name, email, phone, issued_date) values(?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(query, badge.getBadgeId(), badge.getFirstName(), badge.getLastName(), badge.getEmail(),
				badge.getPhone(), new Date());

		LOGGER.info("Created badge with ID: " + badge.getBadgeId());

		return badge.getBadgeId();
	}

	@Override
	public List<Badge> getAllBadges() {
		String query = "select * from badges order by last_name";
		return jdbcTemplate.query(query, new BadgeRowMapper());
	}

	@Override
	@Transactional
	public void updateBadge(Badge badge) {
		String archiveQuery = "insert into badges_history(return_date, badge_id, first_name, last_name, email, phone, issued_date) values (?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(archiveQuery, new Date(), badge.getBadgeId(), badge.getFirstName(), badge.getLastName(),
				badge.getEmail(), badge.getPhone(), badge.getIssuedDate());

		LOGGER.info("Archived badge with ID: " + badge.getBadgeId());

		String query = "delete from badges where badge_id = ?";
		jdbcTemplate.update(query, badge.getBadgeId());

		LOGGER.info("Deactivated badge with ID: " + badge.getBadgeId());
	}
}
