/*
 * Aristotle Learning Platform: Luscinia Enterprises Assn.
 * Copyright (C) 2020 
 *     Luscinia Enterprises Assn. <development@luscinia.ca>
 *     Varun Patel <vpatel@luscinia.ca>, <varun@varunpatel.ca>
 *     Milan Bumbulovic <mbumbulovic@luscinia.ca>
 *     Jacob Chun <jchun@luscinia.ca>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package ca.luscinia.aristotle.config;

import ca.luscinia.aristotle.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import sun.security.util.ManifestDigester;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Autowired
    CustomizeAuthenticationFailureHandler customizeAuthenticationFailureHandler;

    @Autowired
    CustomizeLogoutHandler customizeLogoutHandler;

    @Autowired
    UserDetailsService customUserDetailsService;

    @Autowired
    PersistentTokenRepository tokenRepository;

    @Autowired
    AristotleConfigProperties aristotleConfigProperties;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    protected BCryptPasswordEncoder bCryptPasswordEncoder() {
        /*
         *  BCrypt Encoder uses the current unix timestamp to determine how many iterations to run
         *  Iterations will increase by 1 every 1157 days and change (100 000 000 seconds).
         */
        return new BCryptPasswordEncoder(
                BCryptPasswordEncoder.BCryptVersion.$2B,
                (int) (Instant.now().getEpochSecond()/100000000)
        );
    }

    @Bean
    public UserDetailsService mongoUserDetails() {
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(customUserDetailsService)
            .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/*").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/info/**").permitAll()
                .antMatchers("/register/**").permitAll()
                .antMatchers("/register/admin").hasIpAddress("0/0")
                .antMatchers("/account/**").hasAnyAuthority()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/teachers/**").hasAuthority("TEACHER")
                .antMatchers("/parents/**").hasAuthority("PARENT")
                .antMatchers("/students/**").hasAuthority("STUDENT")
                .anyRequest().denyAll()
            .and()
                .csrf()
                    .disable()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .failureHandler(customizeAuthenticationFailureHandler)
                    .successHandler(customizeAuthenticationSuccessHandler)
                    .usernameParameter("email")
                    .passwordParameter("password")
            .and()
                .rememberMe()
                    .key(aristotleConfigProperties.getTokenKey())
                    .tokenRepository(tokenRepository)
                    .tokenValiditySeconds(2629800)
                    .authenticationSuccessHandler(customizeAuthenticationSuccessHandler)
                    .rememberMeParameter("aristotle_remember_token")
            .and()
                .sessionManagement()
                .sessionAuthenticationFailureHandler(customizeAuthenticationFailureHandler)
            .and()
                .logout()
                    .deleteCookies("aristotle_session_persist")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .addLogoutHandler(customizeLogoutHandler)
            .and()
                .headers(headers -> headers
                        .cacheControl(HeadersConfigurer.CacheControlConfig::disable)
                        .contentTypeOptions(HeadersConfigurer.ContentTypeOptionsConfig::disable)
                        .httpStrictTransportSecurity(hstsConfig -> hstsConfig
                            .includeSubDomains(true)
                            .preload(true)
                            .maxAgeInSeconds(31557600)
                        )
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                        .referrerPolicy(referrerPolicyConfig -> referrerPolicyConfig
                                .policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN)
                        )
                );
    }
}
