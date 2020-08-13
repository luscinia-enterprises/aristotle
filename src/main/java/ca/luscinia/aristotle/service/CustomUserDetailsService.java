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

package ca.luscinia.aristotle.service;

import ca.luscinia.aristotle.database.User;
import ca.luscinia.aristotle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveAdminUser(User user) {
        System.out.println("Saving Admin User");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRoles(new ArrayList<>(Collections.singleton("ADMIN")));
        userRepository.save(user);
    }
    public void saveTeacherUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRoles(new ArrayList<>(Collections.singleton("TEACHER")));
        userRepository.save(user);
    }
    public void saveParentUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(false);
        user.setRoles(new ArrayList<>(Collections.singleton("PARENT")));
        userRepository.save(user);
    }
    public void saveStudentUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(false);
        user.setRoles(new ArrayList<>(Collections.singleton("STUDENT")));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        System.out.println("queried user");
        if(user != null) {
            System.out.println("Found User");
            if (user.isUse2FA()) {
                List<GrantedAuthority> authorities = getUserAuthority(
                        new ArrayList<String>(Collections.singleton("OTP_LOGIN"))
                );
                return buildUserForAuthentication(user, authorities);
            } else {
                List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
                return buildUserForAuthentication(user, authorities);
            }
        } else {
            System.out.println("whoops");
            throw new UsernameNotFoundException("username not found");
        }
    }

    private List<GrantedAuthority> getUserAuthority(ArrayList<String> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}
