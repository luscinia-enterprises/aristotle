/*
 * Aristotle Learning Platform: Luscinia Enterprises Assn.
 * Copyright (C) 2020 
 *     1261612 B.C. LTD.
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

import ca.luscinia.aristotle.database.User;
import ca.luscinia.aristotle.database.user.LoginRecord;
import ca.luscinia.aristotle.repository.UserRepository;
import io.ipinfo.api.IPInfo;
import io.ipinfo.api.cache.Cache;
import io.ipinfo.api.cache.SimpleCache;
import io.ipinfo.api.model.ASNResponse;
import io.ipinfo.api.model.IPResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    AristotleConfigProperties aristotleConfigProperties;
    @Autowired
    UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        User user = userRepository.findByEmail(authentication.getPrincipal().toString());
        LoginRecord loginRecord = new LoginRecord();
        loginRecord.setDeviceType(request.getHeader("User-Agent"));
        loginRecord.setEventTime(Date.from(Instant.now()));
        loginRecord.setRequestAddress(request.getRemoteAddr());
        if (aristotleConfigProperties.getIpgeoKey() == null) {
            loginRecord.setLocation(null);
        } else {
            logIp(request, loginRecord, user);
        }
        for(GrantedAuthority auth: authentication.getAuthorities()) {
            switch (auth.getAuthority()) {
                case "2FA":
                    response.sendRedirect("/login/2fa");
                    break;
                case "ADMIN":
                    response.sendRedirect("/admin");
                    break;
                case "TEACHER":
                    response.sendRedirect("/teachers");
                    break;
                case "PARENT":
                    response.sendRedirect("/parents");
                    break;
                case "STUDENT":
                    response.sendRedirect("/students");
                    break;
                default:
                    response.sendRedirect("/login?error=noauth");
                    break;
            }
        }
    }

    @Async
    void logIp(HttpServletRequest request, LoginRecord loginRecord, User user) {
        IPInfo ipInfo = IPInfo.builder()
                .setToken(aristotleConfigProperties.getIpgeoKey())
                .setCache(new SimpleCache(Duration.ofDays(31)))
                .build();
        try {
            IPResponse ipResponse = ipInfo.lookupIP(request.getRemoteAddr());
            System.out.println(ipResponse.toString());
            String location =
                    ipResponse.getCity() + ", " +
                            ipResponse.getRegion() + ", " +
                            ipResponse.getCountryName() + " " +
                            ipResponse.getPostal();
            loginRecord.setLocation(location);
            try {
                user.getLoginRecords().add(loginRecord);
            } catch (NullPointerException ignored) {
                user.setLoginRecords(new ArrayList<>(Collections.singleton(loginRecord)));
            }
            userRepository.save(user);
        } catch (Exception e) {
            loginRecord.setLocation(null);
        }
    }
}