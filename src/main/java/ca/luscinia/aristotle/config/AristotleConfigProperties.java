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
    private boolean bootstrapBeta;
    private String tokenkey;
    private String privilegedip;

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

    public boolean getBootstrapBeta() {
        return bootstrapBeta;
    }

    public void setBootstrapBeta(boolean bootstrapBeta) {
        this.bootstrapBeta = bootstrapBeta;
    }

    public boolean isBootstrapBeta() {
        return bootstrapBeta;
    }

    public String getTokenkey() {
        return tokenkey;
    }

    public void setTokenkey(String tokenkey) {
        this.tokenkey = tokenkey;
    }

    public String getPrivilegedip() {
        return privilegedip;
    }

    public void setPrivilegedip(String privilegedip) {
        this.privilegedip = privilegedip;
    }
}
