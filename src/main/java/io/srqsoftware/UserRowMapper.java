package io.srqsoftware;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {
	@Override
	public User mapRow(ResultSet rs, int idx) throws SQLException {
		User u = new User();
		u.setRfidId(rs.getString("rfid_id"));
		u.setFirstName(rs.getString("first_name"));
		u.setLastName(rs.getString("last_name"));
		u.setTimestamp(rs.getTimestamp("issued_date") != null ? new Date(rs.getTimestamp("issued_date").getTime()) : null);

		return u;
	}
}
