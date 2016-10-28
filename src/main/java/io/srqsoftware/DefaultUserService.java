package io.srqsoftware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserService implements UserService {
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public DefaultUserService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public long createUser(User x) {
		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {           

		                @Override
		                public PreparedStatement createPreparedStatement(Connection connection)
		                        throws SQLException {
		                    PreparedStatement ps = connection.prepareStatement("insert into user(RFID_ID, FIRSTNAME, LASTNAME, STATUS) values(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		                    ps.setString(1, x.getRfidId());
		                    ps.setString(2, x.getFirstName());
		                    ps.setString(3, x.getLastName());
		                    ps.setInt(4, x.getStatus());
		                    return ps;
		                }
		            }, holder);

		Long newUserId = holder.getKey().longValue();
		return newUserId;
	}

	@Override
	public List<User> getAllUsers() {
		String query = "select * from user order by lastname";		
		return jdbcTemplate.query(query, new UserRowMapper());
	}

	@Override
	public void updateUser(User x) {
		String query = "update user set status = ? where user_id = ?";		
		jdbcTemplate.update(query, x.getStatus(), x.getUserId());
	}
}
