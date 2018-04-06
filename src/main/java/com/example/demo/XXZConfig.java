package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Description:
 *
 * @author xxz
 * Created on 04/06/2018
 */

@Configuration
@EnableWebSecurity
public class XXZConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final WYMService service;

    @Autowired
    public XXZConfig(BCryptPasswordEncoder bCryptPasswordEncoder,WYMService service) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.service=service;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .httpBasic().and()
                .cors().and().csrf().disable()
                .authorizeRequests()
//                .antMatchers("**/login").permitAll()
                .antMatchers("/user/**").hasRole("FEMALE")
                .antMatchers("/bug/**").hasRole("MALE")
                .anyRequest().fullyAuthenticated()
                .and()
                .addFilter(new JWTLoginFilter(authenticationManager()))
                .addFilter(new JwtAuthenticationFilter(authenticationManager(),service));
//                .anyRequest().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("ym").password("{noop}123").roles("MAN");
        auth.userDetailsService(service).passwordEncoder(bCryptPasswordEncoder);
    }
}
