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
package whitelabel.cloud.webapp.impl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import whitelabel.cloud.entity.AppHypervisor;
import whitelabel.cloud.entity.AppNetworkAdapterConfiguration;
import whitelabel.cloud.entity.AppNewServer;
import whitelabel.cloud.entity.AppPrivateVLanDetails;
import whitelabel.cloud.entity.AppPublicIpAddressDetails;
import whitelabel.cloud.entity.AppTemplateDetails;
import whitelabel.cloud.entity.AppVLan;
import whitelabel.cloud.entity.AppVirtualDatacenter;
import whitelabel.cloud.entity.AppVirtualDiskDetails;
import whitelabel.cloud.entity.AppWsResult;
import whitelabel.cloud.entity.VDCResourceBoundsConfig;
import whitelabel.cloud.remotews.jaxb.entity.HypervisorTypes;
import whitelabel.cloud.remotews.jaxb.entity.IPAddress;
import whitelabel.cloud.remotews.jaxb.entity.NetworkAdapterTypes;
import whitelabel.cloud.remotews.jaxb.entity.ResourceTypes;
import whitelabel.cloud.remotews.jaxb.entity.VirtualDiskTypes;
import whitelabel.cloud.webapp.impl.model.NewCloudServer;
import whitelabel.cloud.webapp.utils.UserUtil;

/**
 * @author luca
 *
 */
@Service
public class NewCloudServerService extends AppService {



	/**
	 * @param selectedHypervType
	 * @param selectedTemplateProductId
	 * @return
	 */
	public final NewCloudServer load() {//HypervisorTypes selectedHypervType, Integer selectedTemplateProductId) {

		AppVirtualDatacenter vdc = loadDatacenter();
		VDCResourceBoundsConfig vdcConfig = UserUtil.getVDCResourceConfiguration();
		NewCloudServer model = new NewCloudServer();

		List<AppVLan> vlans = vdc.getVLans();
		if (vlans != null && vlans.size() > 0) {
			model.setAvailableAppVLANs(vlans);
		}

		//Map<HypervisorTypes,HypervisorTypes> appHypervs = new HashMap<HypervisorTypes, HypervisorTypes>();
		Map<String,HypervisorTypes> appHypervs = new HashMap<String, HypervisorTypes>();
		AppHypervisor firstHyper = null;
		for(AppHypervisor rht : vdcConfig.getAllRegisteredHypervisors()) {
			appHypervs.put(rht.getHypervisorType().value(), rht.getHypervisorType());
			if (firstHyper == null) {
				firstHyper = rht;
			}
		}
		model.setHypervisorTypes(appHypervs);
//		if (selectedHypervType == null) {
			model.setSelectedHypervisorType(firstHyper.getHypervisorType().value());
//		}
//		else {
//			model.setSelectedHypervisorType(selectedHypervType.value());
//		}

		Map<Long, String> templateOptions = new HashMap<Long, String>();
		AppTemplateDetails firstTemplate = null;
		for(AppTemplateDetails osTemplate :  vdcConfig.getTemplatesFor(model.getSelectedHypervisorType()).values() ) {
			templateOptions.put((long)osTemplate.getProductId(), osTemplate.getDescription());
			if (firstTemplate == null) {
				firstTemplate = osTemplate;
			}
		}
		model.setTemplateList(templateOptions);
//		if (selectedTemplateProductId == null) {
			model.setSelectedTemplateProdId((long)firstTemplate.getProductId());
//		}
//		else {
//			model.setSelectedTemplateProdId((long)selectedTemplateProductId);
//		}

		model.setAppCPUsBound(vdcConfig.getResourceBounds(model.getSelectedHypervisorType(), model.getSelectedTemplateProdId(), ResourceTypes.CPU));
		model.setAppRAMsBound(vdcConfig.getResourceBounds(model.getSelectedHypervisorType(), model.getSelectedTemplateProdId(), ResourceTypes.RAM));

		model.getAppHDxBound().put(0L, vdcConfig.getResourceBounds(model.getSelectedHypervisorType(), model.getSelectedTemplateProdId(), ResourceTypes.HARD_DISK_0));
		model.getAppHDxBound().put(1L, vdcConfig.getResourceBounds(model.getSelectedHypervisorType(), model.getSelectedTemplateProdId(), ResourceTypes.HARD_DISK_1));
		model.getAppHDxBound().put(2L, vdcConfig.getResourceBounds(model.getSelectedHypervisorType(), model.getSelectedTemplateProdId(), ResourceTypes.HARD_DISK_2));
		model.getAppHDxBound().put(3L, vdcConfig.getResourceBounds(model.getSelectedHypervisorType(), model.getSelectedTemplateProdId(), ResourceTypes.HARD_DISK_3));

		Integer def =model.getAppHDxBound().get(0L).getDefault();

		if (def.compareTo( model.getSelectedDiskSize().get(0L)) > 0) {
			model.getSelectedDiskSize().put(0L, def);
		}
		return model;
	}

	/**
	 * @param model
	 * @return
	 */
	public final AppWsResult invokeServerCreation(NewCloudServer model) {

		AppWsResult invokeResult = null;
		List<AppVirtualDiskDetails> appVDisks = new ArrayList<AppVirtualDiskDetails>();

		AppVirtualDiskDetails appVirtualDisk0 = new AppVirtualDiskDetails(model.getSelectedDiskSize().get(0L), null, VirtualDiskTypes.PRIMARY_VIRTUAL_DISK);
		appVDisks.add(appVirtualDisk0);

		if (model.getSelectedDiskSize().get(1L) != null && model.getSelectedDiskSize().get(1L).intValue() > 0) {
			AppVirtualDiskDetails appVirtualDisk1 = new AppVirtualDiskDetails(model.getSelectedDiskSize().get(1L),null,VirtualDiskTypes.ADDITIONAL_VIRTUAL_DISK_1);
			appVDisks.add(appVirtualDisk1);
		}
		if (model.getSelectedDiskSize().get(2L) != null && model.getSelectedDiskSize().get(2L).intValue() > 0) {
			AppVirtualDiskDetails appVirtualDisk2 = new AppVirtualDiskDetails(model.getSelectedDiskSize().get(2L),null,VirtualDiskTypes.ADDITIONAL_VIRTUAL_DISK_2);
			appVDisks.add(appVirtualDisk2);
		}
		if (model.getSelectedDiskSize().get(3L) != null && model.getSelectedDiskSize().get(3L).intValue() > 0) {
			AppVirtualDiskDetails appVirtualDisk3 = new AppVirtualDiskDetails(model.getSelectedDiskSize().get(3L),null,VirtualDiskTypes.ADDITIONAL_VIRTUAL_DISK_3);
			appVDisks.add(appVirtualDisk3);
		}

		VDCResourceBoundsConfig vdcConfig = UserUtil.getVDCResourceConfiguration();
		AppTemplateDetails serverOST = vdcConfig.getTemplate(model.getSelectedHypervisorType(), model.getSelectedTemplateProdId());



		String serverName = model.getName();
		String serverPwd = model.getPassword();
		Long serverCpus = model.getSelectedCPUNum();
		Long serverRams = model.getSelectedRAMNum();

		// Preparing Ethernet(s) configurations
		AppNetworkAdapterConfiguration appNAC2 = null;
		AppNetworkAdapterConfiguration appNAC3 = null;

		if (model.getEth02_IP() != null) {
			AppPrivateVLanDetails vlan2 = new AppPrivateVLanDetails(model.getVlan_eth02().intValue(), model.getEth02_IP(), model.getEth02_NM());
			appNAC2 = new AppNetworkAdapterConfiguration(NetworkAdapterTypes.ETHERNET_1, vlan2, null);
		}
		if (model.getEth03_IP() != null) {
			AppPrivateVLanDetails vlan3 = new AppPrivateVLanDetails(model.getVlan_eth03().intValue(), model.getEth03_IP(), model.getEth03_NM());
			appNAC3 = new AppNetworkAdapterConfiguration(NetworkAdapterTypes.ETHERNET_2, vlan3, null);
		}
		AppWsResult response = UserUtil.getWsEndUserClient().purchaseIpAddress();
		if (response == null) {
			return new AppWsResult(null);
		}
		else if (!response.isSuccess()) {
			return response;
		}
		Integer resourceId = ((IPAddress) response.getValue()).getResourceId(); //new Integer(22250);//

		// here we have a new public ip resource that we will associate to the new server..

		// Prepare Ethernet 0 (public-ip)
		AppPublicIpAddressDetails pip1 = new AppPublicIpAddressDetails(resourceId, true);
		List<AppPublicIpAddressDetails> assigned = new ArrayList<AppPublicIpAddressDetails>();
		assigned.add(pip1);
		AppNetworkAdapterConfiguration appNAC1 = new AppNetworkAdapterConfiguration(NetworkAdapterTypes.ETHERNET_0, null, assigned);

		List<AppNetworkAdapterConfiguration> appNetAdapConfigs = new ArrayList<AppNetworkAdapterConfiguration>();
		appNetAdapConfigs.add(appNAC1);
		if (appNAC2 != null) {
			appNetAdapConfigs.add(appNAC2);
		}
		if (appNAC3 != null) {
			appNetAdapConfigs.add(appNAC3);
		}

		AppNewServer appNewServer = new AppNewServer(serverName, serverPwd, serverOST.getId(), serverCpus.intValue(), serverRams.intValue(), appVDisks, appNetAdapConfigs);
		invokeResult = UserUtil.getWsEndUserClient().setEnqueueServerCreation(appNewServer);
		return invokeResult;
	}
}
