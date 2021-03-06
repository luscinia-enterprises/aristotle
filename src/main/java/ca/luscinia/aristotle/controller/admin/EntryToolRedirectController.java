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

package ca.luscinia.aristotle.controller.admin;

import ca.luscinia.aristotle.config.AristotleConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Controller
@RequestMapping("/admin/dataEntryTool")
public class EntryToolRedirectController {

    @Autowired
    DataEntryToolAuthRepository dataEntryToolAuthRepository;

    @Autowired
    AristotleConfigProperties aristotleConfigProperties;

    @RequestMapping(method = RequestMethod.GET)
    public void index(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            Object object = authentication.getPrincipal();
            UserDetails user = (UserDetails) object;
            Iterable<DataEntryToolAuth> iterable = dataEntryToolAuthRepository.findAll();
            for (DataEntryToolAuth dataEntryToolAuth: iterable) {
                try {
                    if (dataEntryToolAuth.getUsername().equals(user.getUsername())) {
                        dataEntryToolAuthRepository.delete(dataEntryToolAuth);
                    }
                } catch (NullPointerException ignored) {}
            }
            String id = dataEntryToolAuthRepository.save(
                    new DataEntryToolAuth(
                            user.getUsername(),
                            true,
                            aristotleConfigProperties.getCduUrl()
                    )
            ).getId();
            response.sendRedirect("http://" + aristotleConfigProperties.getCduUrl() + "/?token=" + id);
        }
    }
}

@Repository
interface DataEntryToolAuthRepository extends CrudRepository<DataEntryToolAuth, String> {}

@RedisHash(value="dataEntryToolAuth", timeToLive = 43200)
@TypeAlias("DataEntryToolAuth")
class DataEntryToolAuth implements Serializable {

    @Id
    private String id;
    private String username;
    private boolean authorized;
    private String redirectedURL;

    public DataEntryToolAuth(String username, boolean authorized, String redirectedURL) {
        this.username = username;
        this.authorized = authorized;
        this.redirectedURL = redirectedURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public String getRedirectedURL() {
        return redirectedURL;
    }

    public void setRedirectedURL(String redirectedURL) {
        this.redirectedURL = redirectedURL;
    }
}
