package io.srqsoftware.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ExampleItemService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<ExampleItem> getSelectedItems(String name) {
		try {
			String query = "select * from items where name= ?";
			List<ExampleItem> items = jdbcTemplate.query(query, new Object[] {name}, new ItemRowMapper());
			return items;
		} catch(Exception e) {
			return null;
		}
	}

	public static final class ItemRowMapper implements RowMapper<ExampleItem> {

		@Override
		public ExampleItem mapRow(ResultSet rs, int idx) throws SQLException {
			ExampleItem i = new ExampleItem();
			i.setId(rs.getInt("id"));
			i.setName(rs.getString("name"));
			i.setDescription(rs.getString("description"));
			return i;
		}

	}
}
