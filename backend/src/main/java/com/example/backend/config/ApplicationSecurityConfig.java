package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.backend.jwt.JwtAuthenticationEntryPoint;
import com.example.backend.jwt.JwtAuthenticationFilter;
import com.example.backend.services.ApplicationUserManager;

import org.springframework.security.config.BeanIds;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
        
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserManager applicationUserManager;
    private final JwtAuthenticationEntryPoint handler;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
                return super.authenticationManagerBean();
        }

        @Bean
        public JwtAuthenticationFilter jwtAuthenticationFilter() {
                return new JwtAuthenticationFilter();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http
                                .csrf().disable()
                                .exceptionHandling().authenticationEntryPoint(handler)
                                .and()
                                .cors()
                                .and()
                                .sessionManagement()
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                .and()
                                .authorizeRequests()
                                .antMatchers("/api/v1/auth/**").permitAll()
                                .antMatchers("/api/**").permitAll()
                                .anyRequest()
                                .authenticated();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.authenticationProvider(daoAuthenticationProvider());
        }

        private AuthenticationProvider daoAuthenticationProvider() {
                DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
                provider.setPasswordEncoder(passwordEncoder);
                provider.setUserDetailsService(applicationUserManager);
                return provider;
        }
}
