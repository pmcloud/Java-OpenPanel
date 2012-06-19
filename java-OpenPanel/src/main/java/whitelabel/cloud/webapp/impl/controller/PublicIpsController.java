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

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import whitelabel.cloud.entity.AppWsResult;
import whitelabel.cloud.remotews.jaxb.entity.IPAddress;
import whitelabel.cloud.webapp.impl.service.PublicIpService;

@Controller
@RequestMapping(value = { "/publicIps" })
public class PublicIpsController extends AppController {



	@Autowired
	private PublicIpService service;

	@Override
	protected String getSelectedItem() {
		return "publicIps";
	}

	@RequestMapping({ "", "/index"})
	public String index(HttpServletRequest request, HttpServletResponse response, Model model, Locale locale, RedirectAttributes redirectAttributes) {

		model.addAttribute("publicIps", service.list());
		return "publicIps/index";
	}

	@RequestMapping(value="/add", method={RequestMethod.POST})
	public String add(HttpServletRequest request, HttpServletResponse response, Model model, Locale locale, RedirectAttributes redirectAttributes) {

		AppWsResult result = service.purchaseIpAddress();

		if(result.isSuccess()){
			IPAddress ipAddress = (IPAddress)result.getValue();
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("publicIp.purchased", new String[] {ipAddress.getValue().getValue()}, locale));
		}else{
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("publicIp.not.purchased", null , locale));
		}


		return "redirect:/publicIps/index";
	}

	@RequestMapping(value="/delete/{resourceId}", method={RequestMethod.POST})
	public String delete(@PathVariable("resourceId")Integer resourceId, HttpServletRequest request, HttpServletResponse response, Model model, Locale locale, RedirectAttributes redirectAttributes) {

		AppWsResult result = service.removeIpAddress(resourceId);

		if(result.isSuccess()){
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("publicIp.removed", null, locale));
		}else{
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("publicIp.not.removed", null , locale));
		}


		return "redirect:/publicIps/index";
	}

}
