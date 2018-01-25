package io.srqsoftware;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class BadgeUserDetailsService implements UserDetailsService {
    private static final Logger LOGGER = Logger.getLogger(DefaultBadgeService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String query = "select * from users where username = ?";
        User u = jdbcTemplate.queryForObject(query, new String[] {s}, new UserRowMapper());
        return new BadgeUserDetails(u);
    }
}
