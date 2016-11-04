package io.srqsoftware;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserService implements UserService {
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public DefaultUserService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public String createUser(User x) {
		String query = "insert into badges(RFID_ID, FIRST_NAME, LAST_NAME) values(?, ?, ?)";
		jdbcTemplate.update(query, x.getRfidId(), x.getFirstName(), x.getLastName());

		return x.getRfidId();
	}

	@Override
	public List<User> getAllUsers() {
		String query = "select * from badges order by last_name";
		return jdbcTemplate.query(query, new UserRowMapper());
	}

	@Override
	public void updateUser(User x) {
		String archiveQuery = "insert into badges_history(return_date, rfid_id) values (?, ?)";
		jdbcTemplate.update(archiveQuery, new Date(), x.getRfidId());

		String query = "delete from badges where rfid_id = ?";
		jdbcTemplate.update(query, x.getRfidId());
	}
}
