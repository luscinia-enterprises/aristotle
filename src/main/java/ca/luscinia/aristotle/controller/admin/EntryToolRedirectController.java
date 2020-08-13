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

package ca.luscinia.aristotle.controller.admin;

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

    @RequestMapping(method = RequestMethod.GET)
    public void index(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            System.out.println("RUNNING");
            Object object = authentication.getPrincipal();
            UserDetails user = (UserDetails) object;
            for (DataEntryToolAuth dataEntryToolAuth: dataEntryToolAuthRepository.findAll()) {
                if (dataEntryToolAuth.getUsername().equals(user.getUsername())) {
                    dataEntryToolAuthRepository.delete(dataEntryToolAuth);
                }
            }
            String id = dataEntryToolAuthRepository.save(new DataEntryToolAuth(user.getUsername(), true)).getId();
            response.sendRedirect("http://localhost:8081?token=" + id);
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

    public DataEntryToolAuth(String username, boolean authorized) {
        this.username = username;
        this.authorized = authorized;
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
}
