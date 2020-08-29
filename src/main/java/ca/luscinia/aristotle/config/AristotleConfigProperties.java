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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aristotle")
public class AristotleConfigProperties {
    private String title;
    private String author;
    private String version;
    private String description;
    private String owner;
    private String ipgeoKey;
    private String cduUrl;
    private String tokenKey;
    private String privilegedIp;
    private boolean useTwilio;
    private String twilioSid;
    private String twilioToken;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getIpgeoKey() {
        return ipgeoKey;
    }

    public void setIpgeoKey(String ipgeoKey) {
        this.ipgeoKey = ipgeoKey;
    }

    public String getCduUrl() {
        return cduUrl;
    }

    public void setCduUrl(String cduUrl) {
        this.cduUrl = cduUrl;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getPrivilegedIp() {
        return privilegedIp;
    }

    public void setPrivilegedIp(String privilegedIp) {
        this.privilegedIp = privilegedIp;
    }

    public boolean isUseTwilio() {
        return useTwilio;
    }

    public void setUseTwilio(boolean useTwilio) {
        this.useTwilio = useTwilio;
    }

    public String getTwilioSid() {
        return twilioSid;
    }

    public void setTwilioSid(String twilioSid) {
        this.twilioSid = twilioSid;
    }

    public String getTwilioToken() {
        return twilioToken;
    }

    public void setTwilioToken(String twilioToken) {
        this.twilioToken = twilioToken;
    }
}
