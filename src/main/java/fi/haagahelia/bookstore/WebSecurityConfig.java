package fi.haagahelia.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fi.haagahelia.bookstore.service.UserDetailServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailServiceImpl userDetailsService;	
	
	@Override
	protected void configure (HttpSecurity webSec ) throws Exception {
		webSec
			.authorizeRequests()
			.antMatchers("/signup", "/saveuser").permitAll()
			.antMatchers("/index").hasAnyAuthority("USER","ADMIN")
			.antMatchers("/**").hasAuthority("ADMIN")
			.and().csrf().ignoringAntMatchers("/db/**")
			.and().headers().frameOptions().sameOrigin()
			.and()
			.authorizeRequests().anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/index")
			.permitAll()
			.and()
		.logout()
			.permitAll();
	}
	
	
	
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
	

}
