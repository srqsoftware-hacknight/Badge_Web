package io.srqsoftware;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BadgeRowMapper implements RowMapper<Badge> {
	@Override
	public Badge mapRow(ResultSet rs, int idx) throws SQLException {
		Badge badge = new Badge();
		badge.setBadgeId(rs.getString("badge_id"));
		badge.setFirstName(rs.getString("first_name"));
		badge.setLastName(rs.getString("last_name"));
		badge.setEmail(rs.getString("email"));
		badge.setPhone(rs.getString("phone"));
		badge.setIssuedDate(rs.getDate("issued_date") != null ? rs.getDate("issued_date") : null);

		return badge;
	}
}
