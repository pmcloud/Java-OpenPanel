/**
 *
 */
/**
 *
 * Copyright (c) 2012 <copyright Aruba spa>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 *
 */
package whitelabel.cloud.webapp.impl.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import whitelabel.cloud.webapp.security.CloudUser;
import whitelabel.cloud.webapp.utils.UserUtil;


@Controller
public class AccessController extends AppController{

    protected transient final Log LOG = LogFactory.getLog(getClass());


    @RequestMapping(value = { "/login", "/login/" })
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "login";
    }

    @RequestMapping(value = { "/login_ok", "/login_ok/" })
    public String loginOk(HttpServletRequest request, HttpServletResponse response, Model model) {

        HttpSession session = request.getSession(true);
        String home="home";
        if (session != null) {

            CloudUser user= UserUtil.getUser();

            synchronized (session) {
                session.setAttribute(CloudUser.SESSION_NAME, user);
            }

        }
        return "redirect:"+home;
    }

    @RequestMapping(value = { "/login_ko", "/login_ko/" })
    public String loginKo(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("loginKo", true);
        return "login";
    }



    @RequestMapping(value = "/AccessDenied**")
    public String accessDenied() {
        return "403";
    }

    @RequestMapping(value = "/404**")
    public String error404(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "404";
    }

    @RequestMapping(value = {"/home","/"}, method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "redirect:/dashboards";
    }

    @RequestMapping("/changeLanguage")
    public String changeLanguage(){
    	return "redirect:/dashboards";
    }

    @Override
    protected String getSelectedItem() {
    	return "home";
    }


}
