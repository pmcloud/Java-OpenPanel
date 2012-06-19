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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import whitelabel.cloud.entity.AppTemplateDetails;
import whitelabel.cloud.entity.AppWsResult;
import whitelabel.cloud.entity.VDCResourceBoundsConfig;
import whitelabel.cloud.remotews.jaxb.entity.ResourceBound;
import whitelabel.cloud.remotews.jaxb.entity.ResourceTypes;
import whitelabel.cloud.webapp.impl.model.NewCloudServer;
import whitelabel.cloud.webapp.impl.service.NewCloudServerService;
import whitelabel.cloud.webapp.utils.JSONBoundedItem;
import whitelabel.cloud.webapp.utils.JSONItem;
import whitelabel.cloud.webapp.utils.UserUtil;

/**
 * @author luca
 *
 */
@Controller
@RequestMapping(value={"/newCloudServers"})
@SessionAttributes(value={"NewCloudServer"})
public class NewCloudServersController extends AppController {

	@Autowired
	private NewCloudServerService newCloudServerService;


	@Override
	protected String getSelectedItem() {
		return "cloudServers";
	}


	@RequestMapping(value = {"","/index"})
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {

		model.addAttribute("NewCloudServer", getNewCloudServer());
//		NewCloudServer ncs = (NewCloudServer) request.getSession().getAttribute("NewCloudServer");

		return "redirect:view";
	}

	@RequestMapping(value = {"/view"})
	public String view(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "newCloudServer/view";
	}

	@ModelAttribute("NewCloudServer")
	public NewCloudServer getNewCloudServer() {
		return newCloudServerService.load();//null, null);
	}

	@RequestMapping(value = {"/createServer"}, method={RequestMethod.POST})
	public String createServer(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("NewCloudServer") @Valid NewCloudServer newCloudServer, BindingResult bResult, Model model, Locale locale, RedirectAttributes redirectAttributes) {

		if (!bResult.hasErrors()) {
			boolean isOk = true;
			if (!pwdCompare(newCloudServer)) {
				bResult.rejectValue("passwordChk", "NotMatch");
				isOk = false;
			}

			String dsk = request.getParameter("selectedDiskSize[1]");
			if (dsk == null) {
				newCloudServer.getSelectedDiskSize().put(1L, -1);
			}
			dsk = request.getParameter("selectedDiskSize[2]");
			if (dsk == null) {
				newCloudServer.getSelectedDiskSize().put(2L, -1);
			}
			dsk = request.getParameter("selectedDiskSize[3]");
			if (dsk == null) {
				newCloudServer.getSelectedDiskSize().put(3L, -1);
			}

			int res = validateHd(newCloudServer);
			if (res > 0) {
				isOk = false;
				int disk = res / 10;
				int errn = res % 10;
				String errMSG = (errn == 1 ? "NewCloudServer.error.size" : "NewCloudServer.error.hdrange");
				bResult.rejectValue("selectedDiskSize["+ disk + "]", errMSG);
			}
			res = validateEthParams(newCloudServer);
			if (res > 0) {
				isOk = false;
				switch (res) {
				case 2:
					bResult.rejectValue("vlan_eth02", "NotValid");
					break;
				case 3:
					bResult.rejectValue("vlan_eth03", "NotValid");
					break;
				case 23:
					bResult.rejectValue("vlan_eth03", "NotValid");
					break;
				default:
					bResult.rejectValue("vlan_eth02", "NotValid");
					break;
				}
			}
			if (isOk) {
				AppWsResult result = newCloudServerService.invokeServerCreation(newCloudServer);
				if (result != null && result.isSuccess()) {
					return "newCloudServer/inprogress";
				}else{
					bResult.rejectValue("createError", "NewCloudServer.create.error",new String[]{result.getErrorMessage()},null);
				}
			}
		}
		return "newCloudServer/view";
	}


	@RequestMapping(value = "/loadTemplates/{hypervisorType}")
	public @ResponseBody List<JSONItem> loadTemplates(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("hypervisorType") String hypervisorType, Model model) {

		VDCResourceBoundsConfig vdcConfig = UserUtil.getVDCResourceConfiguration();

		List<JSONItem> jsonResp = new ArrayList<JSONItem>();
		for(AppTemplateDetails osTemplate : vdcConfig.getTemplatesFor(hypervisorType).values()) {
			jsonResp.add(new JSONItem((long)osTemplate.getProductId(), osTemplate.getDescription()));
		}

		Map<Long, String> templateOptions = new HashMap<Long, String>();
		AppTemplateDetails firstTemplate = null;
		for(AppTemplateDetails osTemplate :  vdcConfig.getTemplatesFor(hypervisorType).values()) {
			templateOptions.put((long)osTemplate.getProductId(), osTemplate.getDescription());
			if (firstTemplate == null) {
				firstTemplate = osTemplate;
			}
		}

		NewCloudServer ncs = (NewCloudServer) request.getSession().getAttribute("NewCloudServer");
		ncs.setSelectedHypervisorType(hypervisorType);
		ncs.setTemplateList(templateOptions);


		return jsonResp;
	}

	@RequestMapping(value = "/loadCPUs/{hypervisorType}/{osTemplateProdId}")
	public @ResponseBody List<JSONItem> loadCPUs(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("hypervisorType") String hypervisorType, @PathVariable("osTemplateProdId") Long osTemplateProdId) {

		VDCResourceBoundsConfig vdcConfig = UserUtil.getVDCResourceConfiguration();
		ResourceBound appCPUBounds = vdcConfig.getResourceBounds(hypervisorType, osTemplateProdId, ResourceTypes.CPU);
		List<JSONItem> jsonResp = new ArrayList<JSONItem>();
		if (appCPUBounds != null) {
			for (int i = appCPUBounds.getMin(); i<= appCPUBounds.getMax(); i++) {
				jsonResp.add(new JSONItem((long)i, i+""));
			}
		}

		NewCloudServer ncs = (NewCloudServer) request.getSession().getAttribute("NewCloudServer");
		ncs.setAppCPUsBound(vdcConfig.getResourceBounds(hypervisorType, osTemplateProdId, ResourceTypes.CPU));

		return jsonResp;
	}

	@RequestMapping(value = "/loadRAMs/{hypervisorType}/{osTemplateProdId}")
	public @ResponseBody List<JSONItem> loadRAMs(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("hypervisorType") String hypervisorType, @PathVariable("osTemplateProdId") Long osTemplateProdId) {

		VDCResourceBoundsConfig vdcConfig = UserUtil.getVDCResourceConfiguration();
		ResourceBound appRAMBounds = vdcConfig.getResourceBounds(hypervisorType, osTemplateProdId, ResourceTypes.RAM);
		List<JSONItem> jsonResp = new ArrayList<JSONItem>();
		if (appRAMBounds != null) {


			for (int i = appRAMBounds.getMin(); i<= appRAMBounds.getMax(); i++) {
				jsonResp.add(new JSONItem((long)i, i+""));
			}
		}

		NewCloudServer ncs = (NewCloudServer) request.getSession().getAttribute("NewCloudServer");
		ncs.setAppRAMsBound(vdcConfig.getResourceBounds(hypervisorType, osTemplateProdId, ResourceTypes.RAM));

		return jsonResp;
	}

	@RequestMapping(value = "/loadHDBounds/{hypervisorType}/{osTemplateProdId}")
	public @ResponseBody List<List<JSONBoundedItem>> loadHDBounds(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("hypervisorType") String hypervisorType, @PathVariable("osTemplateProdId") Long osTemplateProdId) {

		VDCResourceBoundsConfig vdcConfig = UserUtil.getVDCResourceConfiguration();
		List<List<JSONBoundedItem>> jsonResp = new ArrayList<List<JSONBoundedItem>>();

		ResourceBound hdBounds = vdcConfig.getResourceBounds(hypervisorType, osTemplateProdId, ResourceTypes.HARD_DISK_0);
		if (hdBounds != null) {

			List<JSONBoundedItem> hdb = new ArrayList<JSONBoundedItem>();

			hdb.add(new JSONBoundedItem("Min:", 	hdBounds.getMin()));
			hdb.add(new JSONBoundedItem("Default:",hdBounds.getDefault()));
			hdb.add(new JSONBoundedItem("Max:", 	hdBounds.getMax()));
			jsonResp.add(hdb);
		}

		hdBounds = vdcConfig.getResourceBounds(hypervisorType, osTemplateProdId, ResourceTypes.HARD_DISK_1);
		if (hdBounds != null) {

			List<JSONBoundedItem> hdb = new ArrayList<JSONBoundedItem>();

			hdb.add(new JSONBoundedItem("Min:", 	hdBounds.getMin()));
			hdb.add(new JSONBoundedItem("Default:",hdBounds.getDefault()));
			hdb.add(new JSONBoundedItem("Max:", 	hdBounds.getMax()));
			jsonResp.add(hdb);
		}

		hdBounds = vdcConfig.getResourceBounds(hypervisorType, osTemplateProdId, ResourceTypes.HARD_DISK_2);
		if (hdBounds != null) {

			List<JSONBoundedItem> hdb = new ArrayList<JSONBoundedItem>();

			hdb.add(new JSONBoundedItem("Min:", 	hdBounds.getMin()));
			hdb.add(new JSONBoundedItem("Default:",hdBounds.getDefault()));
			hdb.add(new JSONBoundedItem("Max:", 	hdBounds.getMax()));
			jsonResp.add(hdb);
		}

		hdBounds = vdcConfig.getResourceBounds(hypervisorType, osTemplateProdId, ResourceTypes.HARD_DISK_3);
		if (hdBounds != null) {

			List<JSONBoundedItem> hdb = new ArrayList<JSONBoundedItem>();

			hdb.add(new JSONBoundedItem("Min:", 	hdBounds.getMin()));
			hdb.add(new JSONBoundedItem("Default:",hdBounds.getDefault()));
			hdb.add(new JSONBoundedItem("Max:", 	hdBounds.getMax()));
			jsonResp.add(hdb);
		}


		NewCloudServer ncs = (NewCloudServer) request.getSession().getAttribute("NewCloudServer");

		ncs.getAppHDxBound().put(0L, vdcConfig.getResourceBounds(hypervisorType, osTemplateProdId, ResourceTypes.HARD_DISK_0));
		ncs.getAppHDxBound().put(1L, vdcConfig.getResourceBounds(hypervisorType, osTemplateProdId, ResourceTypes.HARD_DISK_1));
		ncs.getAppHDxBound().put(2L, vdcConfig.getResourceBounds(hypervisorType, osTemplateProdId, ResourceTypes.HARD_DISK_2));
		ncs.getAppHDxBound().put(3L, vdcConfig.getResourceBounds(hypervisorType, osTemplateProdId, ResourceTypes.HARD_DISK_3));

		return jsonResp;
	}

	/**
	 * ****** SPECIFIC PARAMS VALIDATIONs ******
	 */

	private final boolean pwdCompare(NewCloudServer newCloudServer) {
		return newCloudServer.getPassword().equals(newCloudServer.getPasswordChk());
	}

	private final int validateHd(NewCloudServer newCloudServer) {

		ResourceBound rb;
		for (long disk = 0; disk < 4; disk++) {
			Integer value = newCloudServer.getSelectedDiskSize().get(disk);
			if (value != null && value > 0) {
				rb = newCloudServer.getAppHDxBound().get(disk);
				int min = (rb != null ? rb.getMin() : 10);
				if ((value % min) > 0) {
					return ((int)disk*10)+1;
				}
				if (rb != null && (value < rb.getMin() || value > rb.getMax())) {
					return ((int)disk*10)+2;
				}
			}
		}
		return 0;
	}
	private final int validateEthParams(NewCloudServer newCloudServer) {

		Long selSwitchEth2 = newCloudServer.getVlan_eth02();
		Long selSwitchEth3 = newCloudServer.getVlan_eth03();
		if (selSwitchEth2 != null && selSwitchEth2.intValue() > 0 && selSwitchEth2.equals(selSwitchEth3)) {
			return 23; // can not connect same switch to two different ethernets
		}
		if (selSwitchEth2.intValue() > 0) {
			if (newCloudServer.getEth02_IP() == null || newCloudServer.getEth02_IP().isEmpty() ) {
				return 2; // can not bind same IP to different ethernets
			}
			if (newCloudServer.getEth02_IP().equals(newCloudServer.getEth03_IP())) {
				return 23;
			}
			if (newCloudServer.getEth02_IP() != null && !newCloudServer.getEth02_IP().isEmpty()) {
				if (newCloudServer.getEth02_NM() == null || newCloudServer.getEth02_NM().isEmpty()) {
					return 2;
				}
			}
		}
		if (selSwitchEth3.intValue() > 0) {
			if (newCloudServer.getEth03_IP() == null || newCloudServer.getEth03_IP().isEmpty()) {
				return 3;
			}
			if (newCloudServer.getEth03_IP() != null && !newCloudServer.getEth03_IP().isEmpty()) {
				if (newCloudServer.getEth03_NM() == null || newCloudServer.getEth03_NM().isEmpty()) {
					return 3;
				}
			}
		}
		return 0;
	}


}
