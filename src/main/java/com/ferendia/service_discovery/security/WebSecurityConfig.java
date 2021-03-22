package com.ferendia.service_discovery.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${discovery.user}")
    String userDiscovery;

    @Value("${discovery.password}")
    String passwordDiscovery;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(userDiscovery)
                .password(passwordEncoder().encode(passwordDiscovery))
                .roles("DISCOVERY");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and()
                .requestMatchers().antMatchers("/eureka/**").and()
                .authorizeRequests()
                .antMatchers("/eureka/**").hasAnyRole("DISCOVERY")
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and()
                .requestMatchers().antMatchers("/")
                .and().authorizeRequests().antMatchers("/").hasAnyRole("DISCOVERY")
                .anyRequest().authenticated().and()
                .httpBasic().and().csrf().disable();
    }
}
