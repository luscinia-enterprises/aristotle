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

import ca.luscinia.aristotle.database.Admin;
import ca.luscinia.aristotle.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;

@Controller
@RequestMapping("/admin/authorisationEditor/")
public class AccountCreationAuthorisationController {

    @Autowired
    AdminRepository adminRepository;

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public ModelAndView admin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("admin/authorisationEditor");
        modelAndView.addObject("type", "Admin");
        ArrayList<Object> auths = null;
        try {
            auths = adminRepository.findAdminByKeyIs("authorisations.admin").getValues();
        } catch (NullPointerException ignored){}
        modelAndView.addObject("existingAuths", auths);
        return modelAndView;
    }

    @RequestMapping(value = "authoriseAdmin", method = RequestMethod.POST)
    public ModelAndView authoriseAdmin(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("email") String email
            ) {
        try{
            Admin admin = adminRepository.findAdminByKeyIs("authorisations.admin");
            admin.getValues().add(email);
            adminRepository.save(admin);
        } catch (NullPointerException ignored) {
            Admin admin = new Admin();
            admin.setKey("authorisations.admin");
            admin.setValues(new ArrayList<>(Collections.singleton(email)));
            adminRepository.save(admin);
        }
        return new ModelAndView("redirect:/admin/authorisationEditor/admin");
    }

    @RequestMapping(value = "deAuthoriseAdmin", method = RequestMethod.POST)
    public ModelAndView deAuthoriseAdmin(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("revocation") String revocation) {
        Admin admin = adminRepository.findAdminByKeyIs("authorisations.admin");
        admin.getValues().remove(revocation);
        adminRepository.save(admin);
        return new ModelAndView("redirect:/admin/authorisationEditor/admin");
    }

    @RequestMapping(value = "teacher", method = RequestMethod.GET)
    public ModelAndView teacher(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("admin/authorisationEditor");
        modelAndView.addObject("type", "Teacher");
        ArrayList<Object> auths = null;
        try {
            auths = adminRepository.findAdminByKeyIs("authorisations.teacher").getValues();
        } catch (NullPointerException ignored){}
        modelAndView.addObject("existingAuths", auths);
        return modelAndView;
    }

    @RequestMapping(value = "authoriseTeacher", method = RequestMethod.POST)
    public ModelAndView authoriseTeacher(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("email") String email
    ) {
        try{
            Admin admin = adminRepository.findAdminByKeyIs("authorisations.teacher");
            admin.getValues().add(email);
            adminRepository.save(admin);
        } catch (NullPointerException ignored) {
            Admin admin = new Admin();
            admin.setKey("authorisations.teacher");
            admin.setValues(new ArrayList<>(Collections.singleton(email)));
            adminRepository.save(admin);
        }
        return new ModelAndView("redirect:/admin/authorisationEditor/teacher");
    }

    @RequestMapping(value = "deAuthoriseTeacher", method = RequestMethod.POST)
    public ModelAndView deAuthoriseTeacher(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("revocation") String revocation) {
        Admin admin = adminRepository.findAdminByKeyIs("authorisations.teacher");
        admin.getValues().remove(revocation);
        adminRepository.save(admin);
        return new ModelAndView("redirect:/admin/authorisationEditor/teacher");
    }
}
