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

import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            AuthenticationException e)
            throws IOException, ServletException
    {
        if (e instanceof AccountStatusException) {
            httpServletResponse.sendRedirect("/login?error=status");
        } else if (e instanceof AuthenticationCredentialsNotFoundException) {
            httpServletResponse.sendRedirect("/login?error=crednf");
        } else if (e instanceof AuthenticationServiceException) {
            httpServletResponse.sendRedirect("/login?error=service");
        } else if (e instanceof BadCredentialsException) {
            httpServletResponse.sendRedirect("/login?error=badcred");
        } else if (e instanceof InsufficientAuthenticationException) {
            httpServletResponse.sendRedirect("/login?error=nsfcred");
        } else if (e instanceof RememberMeAuthenticationException) {
            httpServletResponse.sendRedirect("/login?error=rmbmeexp");
        } else if (e instanceof SessionAuthenticationException) {
            httpServletResponse.sendRedirect("/login?error=badsess");
        } else if (e instanceof UsernameNotFoundException) {
            httpServletResponse.sendRedirect("/login?error=usernf");
        } else {
            httpServletResponse.sendRedirect("/login?error=unclsfd");
        }
    }
}
