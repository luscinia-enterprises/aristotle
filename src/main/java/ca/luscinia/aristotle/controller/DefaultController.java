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

package ca.luscinia.aristotle.controller;

import ca.luscinia.aristotle.model.PublicSearch;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path = "/")
public class DefaultController extends AristotleController {

    @RequestMapping()
    public ModelAndView home(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @RequestMapping(path = "/info/{page}")
    public ModelAndView students(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable(name = "page") String page
    ) {
        ModelAndView modelAndView = new ModelAndView("info/index");
        modelAndView.addObject("request", page);
        return modelAndView;
    }

    @RequestMapping(path = "/about-us")
    public ModelAndView aboutUs(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        ModelAndView modelAndView = new ModelAndView("about-us");
        return modelAndView;
    }
}
