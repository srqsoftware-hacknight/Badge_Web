package io.srqsoftware;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class UserDetailRowMapper implements RowMapper<UserDetail> {
	@Override
	public UserDetail mapRow(ResultSet rs, int idx) throws SQLException {
		UserDetail u = new UserDetail();
		u.setUserId(rs.getInt("user_id"));
		u.setEmailAddress(rs.getString("email_address"));
		u.setAddress1(rs.getString("address1"));
		u.setAddress2(rs.getString("address2"));
		u.setCity(rs.getString("city"));
		u.setState(rs.getString("state"));
		u.setZipcode(rs.getString("zipcode"));
		return u;
	}
}
