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



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import whitelabel.cloud.webapp.security.CloudUser;
import whitelabel.cloud.webapp.utils.MenuObserver;
import whitelabel.cloud.webapp.utils.UserUtil;


@Controller
@SessionAttributes({ MenuObserver.SESSION_NAME })
public abstract class AppController {

    protected transient final Log LOG = LogFactory.getLog(getClass());

    @Autowired
	private MessageSource messageSource;

    /**
     * @return must return the 'key' of selected menu-item associated to this controller
     */
    protected abstract String getSelectedItem();

    @ModelAttribute
    public MenuObserver getMenuObserver(Model model) {
        MenuObserver menuObserver = (MenuObserver) model.asMap().get(MenuObserver.SESSION_NAME);
        if (menuObserver == null) {
            menuObserver = new MenuObserver();
            model.addAttribute(MenuObserver.SESSION_NAME, menuObserver);
        }
        menuObserver.setSelectedItem(getSelectedItem());
        return menuObserver;
    }


    protected MessageSource getMessageSource() {
		return messageSource;
	}

    @ModelAttribute
    public CloudUser getCloudUser(){
    	return UserUtil.getUser();
    }




}
