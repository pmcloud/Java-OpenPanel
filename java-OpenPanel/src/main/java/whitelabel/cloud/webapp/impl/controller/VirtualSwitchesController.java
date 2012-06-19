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
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import whitelabel.cloud.entity.AppWsResult;
import whitelabel.cloud.webapp.impl.model.VirtualSwitch;
import whitelabel.cloud.webapp.impl.service.VirtualSwitchService;

@Controller
@RequestMapping(value = { "/virtualSwitches" })
public class VirtualSwitchesController extends AppController {

	@Autowired
	private VirtualSwitchService service;

	@Override
	protected String getSelectedItem() {
		return "virtualSwitches";
	}

	@RequestMapping({ "", "/index" })
	public String index(HttpServletRequest request, HttpServletResponse response, Model model, Locale locale, RedirectAttributes redirectAttributes) {
		model.addAttribute("virtualSwitch",new VirtualSwitch());
		model.addAttribute("virtualSwitches", service.list());
		return "virtualSwitches/index";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public String add(HttpServletRequest request, HttpServletResponse response, @Valid VirtualSwitch virtualSwitch, BindingResult result, Model model,
			Locale locale, RedirectAttributes redirectAttributes) {

		if (!result.hasErrors()) {
			AppWsResult appWsResult = service.purchaseVlan(virtualSwitch.getName());

			if (appWsResult.isSuccess()) {
				redirectAttributes.addFlashAttribute("FLASH_MESSAGE",
						getMessageSource().getMessage("virtualSwitch.purchased", new String[] { virtualSwitch.getName() }, locale));
			} else {
				redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("virtualSwitch.not.purchased", null, locale));
			}

			return "redirect:/virtualSwitches/index";
		} else {
			model.addAttribute("showDlg", true);
			model.addAttribute("virtualSwitches", service.list());
			return "virtualSwitches/index";
		}
	}

	@RequestMapping(value = "/delete/{resourceId}", method = { RequestMethod.POST })
	public String delete(@PathVariable("resourceId") Integer resourceId, HttpServletRequest request, HttpServletResponse response, Model model, Locale locale,
			RedirectAttributes redirectAttributes) {

		AppWsResult result = service.removeVlan(resourceId);

		if (result.isSuccess()) {
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("virtualSwitch.removed", null, locale));
		} else {
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("virtualSwitch.not.removed", null, locale));
		}

		return "redirect:/virtualSwitches/index";
	}

}
