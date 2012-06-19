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

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import whitelabel.cloud.entity.AppWsResult;
import whitelabel.cloud.exception.APPRuntimeException;
import whitelabel.cloud.remotews.jaxb.entity.ResourceBound;
import whitelabel.cloud.webapp.impl.model.CloudServer;
import whitelabel.cloud.webapp.impl.model.form.cloudServer.ChangeCPU;
import whitelabel.cloud.webapp.impl.model.form.cloudServer.ChangeName;
import whitelabel.cloud.webapp.impl.model.form.cloudServer.ChangeRAM;
import whitelabel.cloud.webapp.impl.model.form.cloudServer.DiskSize;
import whitelabel.cloud.webapp.impl.model.form.cloudServer.EthVlan;
import whitelabel.cloud.webapp.impl.service.CloudServerService;
import whitelabel.cloud.webapp.utils.UserUtil;

@Controller
@RequestMapping(value = { "/cloudServers" })
public class CloudServersController extends AppController {

	@Autowired
	private CloudServerService cloudServerService;

	@Override
	protected String getSelectedItem() {
		return "cloudServers";
	}

	@RequestMapping({ "", "/index" })
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {

		model.addAttribute("servers", cloudServerService.list());
		return "cloudServers/index";
	}

	@RequestMapping(value = "/view/{serverId}")
	public String view(HttpServletRequest request, HttpServletResponse response, @PathVariable("serverId") Integer serverId, Model model, Locale locale) {
		return detail(serverId, null, model, locale);
	}

	private String detail(Integer serverId, CloudServer cloudServer, Model model, Locale locale) {
		if (cloudServer == null) {
			cloudServer = cloudServerService.load(serverId);
			if (cloudServer == null || cloudServer.getAppServerDetail() == null) {
				throw new APPRuntimeException(getMessageSource().getMessage("Invalid.Cloud.Server", null, locale));
			}
		}
		model.addAttribute("CloudServer", cloudServer);
		model.addAttribute(cloudServer.getChangeCPU());
		model.addAttribute(cloudServer.getChangeRAM());
		model.addAttribute(cloudServer.getChangeName());
		model.addAttribute("diskSize", cloudServer.getDiskSize());
		model.addAttribute("ethVlan", new EthVlan(cloudServer.getAppServerDetail().getServerId()));
		return "cloudServers/view";
	}

	private String redirectToView(Integer serverId) {
		return "redirect:/cloudServers/view/" + serverId;
	}

	@RequestMapping(value = "/start/{serverId}", method = { RequestMethod.POST })
	public String start(HttpServletRequest request, HttpServletResponse response, @PathVariable("serverId") Integer serverId, Model model, Locale locale,
			RedirectAttributes redirectAttributes) {

		AppWsResult result = cloudServerService.startServer(serverId);

		if (result.isSuccess()) {
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.start", null, locale));
		} else {
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.not.start", null, locale));
		}

		return redirectToView(serverId);

	}

	@RequestMapping(value = "/stop/{serverId}", method = { RequestMethod.POST })
	public String stop(HttpServletRequest request, HttpServletResponse response, @PathVariable("serverId") Integer serverId, Model model, Locale locale,
			RedirectAttributes redirectAttributes) {

		AppWsResult result = cloudServerService.stopServer(serverId);

		if (result.isSuccess()) {
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.stop", null, locale));
		} else {
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.not.stop", null, locale));
		}

		return redirectToView(serverId);

	}

	@RequestMapping(value = "/powerOff/{serverId}", method = { RequestMethod.POST })
	public String powerOff(HttpServletRequest request, HttpServletResponse response, @PathVariable("serverId") Integer serverId, Model model, Locale locale,
			RedirectAttributes redirectAttributes) {

		AppWsResult result = cloudServerService.powerOffServer(serverId);

		if (result.isSuccess()) {
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.powerOff", null, locale));
		} else {
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.not.powerOff", null, locale));
		}

		return redirectToView(serverId);

	}

	@RequestMapping(value = "/reset/{serverId}", method = { RequestMethod.POST })
	public String reset(HttpServletRequest request, HttpServletResponse response, @PathVariable("serverId") Integer serverId, Model model, Locale locale,
			RedirectAttributes redirectAttributes) {

		AppWsResult result = cloudServerService.resetServer(serverId);

		if (result.isSuccess()) {
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.reset", null, locale));
		} else {
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.not.reset", null, locale));
		}

		return redirectToView(serverId);

	}

	@RequestMapping(value = "/archive/{serverId}", method = { RequestMethod.POST })
	public String archive(HttpServletRequest request, HttpServletResponse response, @PathVariable("serverId") Integer serverId, Model model, Locale locale,
			RedirectAttributes redirectAttributes) {

		AppWsResult result = cloudServerService.archiveServer(serverId);

		if (result.isSuccess()) {
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.archive", null, locale));
		} else {
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.not.archive", null, locale));
		}

		return redirectToView(serverId);

	}

	@RequestMapping(value = "/restore/{serverId}", method = { RequestMethod.POST })
	public String restore(HttpServletRequest request, HttpServletResponse response, @PathVariable("serverId") Integer serverId, Model model, Locale locale,
			RedirectAttributes redirectAttributes) {

		AppWsResult result = cloudServerService.restoreServer(serverId, 1, 1);

		if (result.isSuccess()) {
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.restore", null, locale));
		} else {
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.not.restore", null, locale));
		}

		return redirectToView(serverId);

	}

	private CloudServer checkCloudServerExist(Integer serverId, Locale locale) {
		CloudServer cloudServer = cloudServerService.load(serverId);
		if (cloudServer == null || cloudServer.getAppServerDetail() == null) {
			throw new APPRuntimeException(getMessageSource().getMessage("Invalid.Cloud.Server", null, locale));
		}
		return cloudServer;
	}

	@RequestMapping(value = "/modifyCpus/{serverId}", method = { RequestMethod.POST })
	public String modifyCpus(HttpServletRequest request, HttpServletResponse response, @PathVariable("serverId") Integer serverId, @Valid ChangeCPU changeCPU,
			BindingResult bindingResult, Model model, Locale locale, RedirectAttributes redirectAttributes) {

		CloudServer cloudServer = checkCloudServerExist(serverId, locale);
		cloudServer.setChangeCPU(changeCPU);
		if (!bindingResult.hasErrors()) {

			AppWsResult result = cloudServerService.modifyCpus(serverId, changeCPU.getCpus());

			if (result.isSuccess()) {
				redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.modifyCpu", null, locale));
			} else {
				redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.not.modifyCpu", null, locale));
			}

			return redirectToView(serverId);
		} else {
			model.addAttribute("showServerCpusEdit", true);
			return detail(serverId, cloudServer, model, locale);
		}
	}

	@RequestMapping(value = "/modifyRams/{serverId}", method = { RequestMethod.POST })
	public String modifyRams(HttpServletRequest request, HttpServletResponse response, @PathVariable("serverId") Integer serverId, @Valid ChangeRAM changeRAM,
			BindingResult bindingResult, Model model, Locale locale, RedirectAttributes redirectAttributes) {

		CloudServer cloudServer = checkCloudServerExist(serverId, locale);
		cloudServer.setChangeRAM(changeRAM);
		if (!bindingResult.hasErrors()) {

			AppWsResult result = cloudServerService.modifyRams(serverId, changeRAM.getRams());

			if (result.isSuccess()) {
				redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.modifyRam", null, locale));
			} else {
				redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.not.modifyRam", null, locale));
			}

			return redirectToView(serverId);
		} else {
			model.addAttribute("showServerRamsEdit", true);
			return detail(serverId, cloudServer, model, locale);
		}
	}

	@RequestMapping(value = "/updateDisk/{serverId}", method = { RequestMethod.POST })
	public String updateDisk(HttpServletRequest request, HttpServletResponse response, @PathVariable("serverId") Integer serverId, @Valid DiskSize diskSize,
			BindingResult bindingResult, Model model, Locale locale, RedirectAttributes redirectAttributes) {

		CloudServer cloudServer = checkCloudServerExist(serverId, locale);
		cloudServer.setDiskSize(diskSize);
		validateHDxx(cloudServer, diskSize, bindingResult);
		if (!bindingResult.hasErrors()) {

			AppWsResult result = null;
			String commandOk = null;
			String commandKo = null;
			if (cloudServer.getAppServerDetail().isDiskInUse(diskSize.getDiskNum())) {
				result = cloudServerService.resizeDisk(serverId, diskSize.getDiskNum(), diskSize.getSize());
				commandOk = "cloudServer.resizeDisk";
				commandKo = "cloudServer.not.resizeDisk";
			} else {
				result = cloudServerService.createDisk(serverId, diskSize.getDiskNum(), diskSize.getSize());
				commandOk = "cloudServer.createDisk";
				commandKo = "cloudServer.not.createDisk";
			}

			if (result.isSuccess()) {
				redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage(commandOk, null, locale));
			} else {
				redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage(commandKo, null, locale));
			}

			return redirectToView(serverId);
		} else {
			boolean[] showServerDiskEdit = { false, false, false, false };
			showServerDiskEdit[diskSize.getDiskNum()] = true;
			model.addAttribute("showServerDiskEdit", showServerDiskEdit);
			return detail(serverId, cloudServer, model, locale);
		}
	}

	@RequestMapping(value = "/removeDisk/{serverId}", method = { RequestMethod.POST })
	public String removeDisk(HttpServletRequest request, HttpServletResponse response, @PathVariable("serverId") Integer serverId, DiskSize diskSize,
			Model model, Locale locale, RedirectAttributes redirectAttributes) {

		AppWsResult result = cloudServerService.removeDisk(serverId, diskSize.getDiskNum());

		if (result.isSuccess()) {
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.removeDisk", null, locale));
		} else {
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.not.removeDisk", null, locale));
		}

		return redirectToView(serverId);

	}

	@RequestMapping(value = "/renameServer/{serverId}", method = { RequestMethod.POST })
	public String renameServer(HttpServletRequest request, HttpServletResponse response, @PathVariable("serverId") Integer serverId,
			@Valid ChangeName changeName, BindingResult bindingResult, Model model, Locale locale, RedirectAttributes redirectAttributes) {

		CloudServer cloudServer = checkCloudServerExist(serverId, locale);
		cloudServer.setChangeName(changeName);
		if (!bindingResult.hasErrors()) {

			AppWsResult result = cloudServerService.renameServer(serverId, changeName.getName());

			if (result.isSuccess()) {
				redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.modifyName", null, locale));
			} else {
				redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.not.modifyName", null, locale));
			}

			return redirectToView(serverId);
		} else {
			model.addAttribute("showServerNameEdit", true);
			return detail(serverId, cloudServer, model, locale);
		}

	}

	@RequestMapping(value = "/connectVirtualSwitch", method = { RequestMethod.POST })
	public String connectVirtualSwitch(HttpServletRequest request, HttpServletResponse response, EthVlan ethVlan, Model model, Locale locale,
			RedirectAttributes redirectAttributes) {

		Integer serverId = ethVlan.getServerId();
		checkCloudServerExist(serverId, locale);

		AppWsResult result = cloudServerService.connectVirtualSwitch(ethVlan.getVlanResourceId(), ethVlan.getVlanNetAdpId());

		if (result.isSuccess()) {
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.connect.vlan", null, locale));
		} else {
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.not.connect.vlan", null, locale));
		}

		return redirectToView(serverId);

	}

	@RequestMapping(value = "/disconnectVirtualSwitch", method = { RequestMethod.POST })
	public String disconnectVirtualSwitch(HttpServletRequest request,  HttpServletResponse response, EthVlan ethVlan, Model model, Locale locale,
			RedirectAttributes redirectAttributes) {

		Integer serverId = ethVlan.getServerId();
		checkCloudServerExist(serverId, locale);

		AppWsResult result = cloudServerService.disconnectVirtualSwitch(ethVlan.getVlanResourceId(), ethVlan.getVlanNetAdpId());

		if (result.isSuccess()) {
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.disconnect.vlan", null, locale));
		} else {
			redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.not.disconnect.vlan", null, locale));
		}

		return redirectToView(serverId);

	}

	@RequestMapping(value = "/connectIps", method = { RequestMethod.POST })
	public String connectIps(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="serverId", required=true)Integer serverId,
			@RequestParam(value="ipNetAdpId", required=true)Integer ipNetAdpId,
			@RequestParam(required=false, value="ipResourceId")List<Integer> ipResourceIds ,
			Model model, Locale locale,RedirectAttributes redirectAttributes) {

		CloudServer cloudServer = checkCloudServerExist(serverId, locale);
		if(ipResourceIds==null || ipResourceIds.size()==0){
			model.addAttribute("showAddIpDlg", true);
			model.addAttribute("addIpDlg_message", getMessageSource().getMessage("select.ips", null, locale));
			return detail(serverId, cloudServer, model, locale);
		}else{

			AppWsResult result = cloudServerService.connectIps(ipResourceIds, ipNetAdpId);

			if (result.isSuccess()) {
				redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.connect.ip", null, locale));
			} else {
				redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.not.connect.ip", null, locale));
			}

			return redirectToView(serverId);

		}

	}

	@RequestMapping(value = "/disconnectIps", method = { RequestMethod.POST })
	public String disconnectIps(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="serverId", required=true)Integer serverId,
			@RequestParam(value="ipNetAdpId", required=true)Integer ipNetAdpId,
			@RequestParam(required=false, value="ipResourceId")List<Integer> ipResourceIds ,
			Model model, Locale locale,RedirectAttributes redirectAttributes) {

		CloudServer cloudServer = checkCloudServerExist(serverId, locale);
		if(ipResourceIds==null || ipResourceIds.size()==0){
			model.addAttribute("showDelIpDlg", true);
			model.addAttribute("delIpDlg_message", getMessageSource().getMessage("select.ips", null, locale));
			return detail(serverId, cloudServer, model, locale);
		}else{

			AppWsResult result = cloudServerService.disconnectIps(ipResourceIds, ipNetAdpId);

			if (result.isSuccess()) {
				redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.disconnect.ip", null, locale));
			} else {
				redirectAttributes.addFlashAttribute("FLASH_MESSAGE", getMessageSource().getMessage("cloudServer.not.disconnect.ip", null, locale));
			}

			return redirectToView(serverId);

		}

	}

	private void validateHDxx(CloudServer cloudServer, DiskSize diskSize, BindingResult bindingResult){
		if(diskSize!=null && cloudServer!=null){
			try {
				ResourceBound hdxBound = cloudServer.getAppHDxBound().get(diskSize.getDiskNum());
				if(hdxBound!=null){

					if(diskSize.getSize()!=null && hdxBound.getMin()!=null){

						boolean showError = false;
						if(diskSize.getSize() % hdxBound.getMin() != 0){
							showError=true;
						}

						if(diskSize.getSize()<hdxBound.getMin() || diskSize.getSize()>hdxBound.getMax()){
							showError=true;
						}

						if(showError){
							bindingResult.rejectValue(null, "cloudServer.hdx.bad.size", new Object[]{diskSize.getDiskNum(), hdxBound.getMin(), hdxBound.getMax()},"");
						}
					}

				}

			} catch (Exception dontCare) {

			}
		}
	}

}
