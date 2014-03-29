package io.github.eneid.auth;

import com.googlecode.flyway.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
        
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/**").hasRole("USER")
                .anyRequest().anonymous()
                .and()
                .httpBasic();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, Flyway flyway, PasswordEncoder encoder) throws Exception {
        flyway.migrate();
        String password = encoder.encode("password");
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(encoder)
                .withUser("user").password(password).roles("USER").and()
                .withUser("admin").password(password).roles("USER", "ADMIN");
    }
}
