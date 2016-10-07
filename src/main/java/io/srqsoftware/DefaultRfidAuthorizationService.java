package io.srqsoftware;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class DefaultRfidAuthorizationService implements RfidAuthorizationService {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public DefaultRfidAuthorizationService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int getAuthorizationStatus(int keyId) {
		try {
			String query = "select user_id, rfid_id, timestamp, firstname, lastname from user where rfid_id=?";
			String sKeyId = ""+keyId;
			User u = jdbcTemplate.queryForObject(query, new Object[] {sKeyId}, new UserRowMapper());
			return (u != null) ? AUTHORIZED : UNAUTHORIZED;
		} catch(Exception e) {
			return UNAUTHORIZED;
		}

	}
	
	public static final class UserRowMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int idx) throws SQLException {
			User u = new User();
			u.setUserId(rs.getInt("user_id"));
			u.setRfidId(rs.getString("rfid_id"));
			u.setTimestamp(new Date(rs.getTimestamp("timestamp").getTime()));
			u.setFirstName(rs.getString("firstname"));
			u.setLastName(rs.getString("lastname"));
			return u;
		}

	}

}
