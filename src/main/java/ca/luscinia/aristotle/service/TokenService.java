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

import ca.luscinia.aristotle.database.Token;
import ca.luscinia.aristotle.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenService implements PersistentTokenRepository {

    @Autowired
    TokenRepository tokenRepository;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        tokenRepository.save(new Token(null,
                token.getUsername(),
                token.getSeries(),
                token.getTokenValue(),
                token.getDate()));
    }

    @Override
    public void updateToken(String series, String value, Date lastUsed) {
        Token token = tokenRepository.findBySeries(series);
        tokenRepository.save(new Token(token.getId(), token.getUsername(), series, value, lastUsed));
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return tokenRepository.findBySeries(seriesId);
    }

    @Override
    public void removeUserTokens(String username) {
        Token token = tokenRepository.findByUsername(username);
        if (token != null) {
            tokenRepository.delete(token);
        }
    }
}