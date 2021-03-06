package io.srqsoftware;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BadgeUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
    	http.exceptionHandling().authenticationEntryPoint(new Send401ResponseEntryPoint());
    	http
    	.headers()
    		.frameOptions()
    			.sameOrigin();
        http
            .authorizeRequests()
                .antMatchers("/", "/home", "/app/**", "/lib/**", "/style.css", "/device/check", "/adminpw")
				.permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .withUser("badge").password("passw0rd").roles("USER");
		auth.authenticationProvider(authenticationProvider());
    }

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider
				= new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}

    private class Send401ResponseEntryPoint implements AuthenticationEntryPoint {
		@Override
		public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
				throws IOException, ServletException {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
    }
}