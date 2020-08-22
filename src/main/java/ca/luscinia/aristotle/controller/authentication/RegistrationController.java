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

package ca.luscinia.aristotle.controller.authentication;

import ca.luscinia.aristotle.controller.AristotleController;
import ca.luscinia.aristotle.database.User;
import ca.luscinia.aristotle.database.general.LearningStyle;
import ca.luscinia.aristotle.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/register")
public class RegistrationController extends AristotleController {
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView registerPage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("authentication/register");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @RequestMapping(path = "/{type}", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response,
                                 @ModelAttribute("user") User user,
                                 @PathVariable("type") String type,
                                 @RequestParam(name = "conf-new-password", required = false) String confirmPassword,
                                 @RequestParam(name = "learning-style-select", required = false) String learningPref,
                                 @RequestParam(name = "course-code", required = false) String courseCode,
                                 @RequestParam(name = "student-code", required = false) String studentCode) {
        System.out.println("\n\n" + type + "\n\n" + user.toString() + "\n\n");
        ModelAndView modelAndView = new ModelAndView();
        if (customUserDetailsService.findUserByEmail(user.getEmail()) != null) {
            modelAndView.setViewName("authentication/failure");
        } else {
            modelAndView.setViewName("authentication/success");
            if (user.getPassword().equals(confirmPassword)) {
                switch (type) {
                    case "admin":
                        System.out.println("\n\n\n\n\nwriting to db \n\n\n\n\n");
                        customUserDetailsService.saveAdminUser(user);
                        modelAndView.setViewName("authentication/success");
                        break;
                    case "teacher":
                        // check that teacher email is approved in admindb
                        customUserDetailsService.saveTeacherUser(user);
                        break;
                    case "parent":
                        user.getStudents().add(studentCode);
                        customUserDetailsService.saveParentUser(user);
                        break;
                    case "student":
                        LearningStyle learningStyle = new LearningStyle();
                        switch (learningPref) {
                            case "visual":
                                learningStyle.setSlss(75);
                            case "auditory":
                                learningStyle.setAlss(75);
                            case "verbal":
                                learningStyle.setLlss(75);
                            case "logistical":
                                learningStyle.setMlss(75);
                            case "kinesthetic":
                                learningStyle.setKlss(75);
                            default:
                                learningStyle.setTms(-25);
                        }
                        user.setLearningStyle(learningStyle);
                        user.getCourses().add(courseCode);
                        customUserDetailsService.saveStudentUser(user);
                        break;
                    default:
                        modelAndView.setViewName("authentication/failure");
                }
            } else {
                try {
                    response.sendRedirect("/register");
                } catch (Exception ignored) {
                }
            }

        }
        return modelAndView;
    }

    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public ModelAndView adminPage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("authentication/registerAdmin");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
}
