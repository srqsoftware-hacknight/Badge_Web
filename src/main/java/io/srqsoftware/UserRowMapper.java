package io.srqsoftware;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {
	@Override
	public User mapRow(ResultSet rs, int idx) throws SQLException {
		User u = new User();
		u.setUserId(rs.getInt("user_id"));
		u.setRfidId(rs.getString("rfid_id"));
		u.setTimestamp(new Date(rs.getTimestamp("timestamp").getTime()));
		u.setFirstName(rs.getString("firstname"));
		u.setLastName(rs.getString("lastname"));
		u.setStatus(rs.getInt("status"));
		return u;
	}
}
