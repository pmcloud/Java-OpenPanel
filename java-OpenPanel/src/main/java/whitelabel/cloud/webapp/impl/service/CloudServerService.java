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
import java.util.List;

import org.springframework.stereotype.Service;

import whitelabel.cloud.entity.AppIPAddress;
import whitelabel.cloud.entity.AppServerDetails;
import whitelabel.cloud.entity.AppVLan;
import whitelabel.cloud.entity.AppVirtualDatacenter;
import whitelabel.cloud.entity.AppWsResult;
import whitelabel.cloud.entity.VDCResourceBoundsConfig;
import whitelabel.cloud.remotews.jaxb.entity.ResourceTypes;
import whitelabel.cloud.webapp.impl.model.CloudServer;
import whitelabel.cloud.webapp.utils.UserUtil;

@Service
public class CloudServerService extends AppService {

	public List<AppServerDetails> list(){

		AppVirtualDatacenter vdc = loadDatacenter();

		List<AppServerDetails> servers = null;

		if(vdc!=null){
			servers= vdc.getServers();
		}


		if(servers==null){
			servers = new ArrayList<AppServerDetails>();
		}

		return servers;
	}

	public CloudServer load(Integer serverId){

		CloudServer cloudServer = new CloudServer();
		AppVirtualDatacenter vdc = loadDatacenter();
		AppServerDetails tmp = vdc.getAppServerById(serverId);

		if(tmp!=null){

			cloudServer.setAvailableIps(new ArrayList<AppIPAddress>());
			for (AppIPAddress ipAddress : vdc.getIpAddresses()) {
				if(ipAddress.getServerId()==null){
					cloudServer.getAvailableIps().add(ipAddress);
				}
			}

			cloudServer.setAvailableVirtualSwitches(new ArrayList<AppVLan>());
			for (AppVLan vLan : vdc.getVLans()) {
				if(!vLan.isConnectedTo(serverId)){
					cloudServer.getAvailableVirtualSwitches().add(vLan);
				}
			}

			VDCResourceBoundsConfig vdcConfig = UserUtil.getVDCResourceConfiguration();

			cloudServer.setAppCPUsBound(vdcConfig.getResourceBounds(tmp.getHypervisorType().value(), (long)tmp.getOSTemplateProductId(), ResourceTypes.CPU));
			cloudServer.setAppRAMsBound(vdcConfig.getResourceBounds(tmp.getHypervisorType().value(), (long)tmp.getOSTemplateProductId(), ResourceTypes.RAM));

			cloudServer.getAppHDxBound().put(0, vdcConfig.getResourceBounds(tmp.getHypervisorType().value(), (long) tmp.getOSTemplateProductId(), ResourceTypes.HARD_DISK_0));
			cloudServer.getAppHDxBound().put(1, vdcConfig.getResourceBounds(tmp.getHypervisorType().value(), (long) tmp.getOSTemplateProductId(), ResourceTypes.HARD_DISK_1));
			cloudServer.getAppHDxBound().put(2, vdcConfig.getResourceBounds(tmp.getHypervisorType().value(), (long)tmp.getOSTemplateProductId(), ResourceTypes.HARD_DISK_2));
			cloudServer.getAppHDxBound().put(3, vdcConfig.getResourceBounds(tmp.getHypervisorType().value(), (long)tmp.getOSTemplateProductId(), ResourceTypes.HARD_DISK_3));

		}
		cloudServer.setAppServerDetail(tmp);
		return cloudServer;
	}


	public AppWsResult removeServer(Integer serverId){
		return UserUtil.getWsEndUserClient().setEnqueueServerDeletion(serverId);
	}

	public AppWsResult startServer(Integer serverId){
		return UserUtil.getWsEndUserClient().setEnqueueServerStart(serverId);
	}

	public AppWsResult stopServer(Integer serverId){
		return UserUtil.getWsEndUserClient().setEnqueueServerStop(serverId);
	}

	public AppWsResult powerOffServer(Integer serverId){
		return UserUtil.getWsEndUserClient().setEnqueueServerPowerOff(serverId);
	}

	public AppWsResult resetServer(Integer serverId){
		return UserUtil.getWsEndUserClient().setEnqueueServerReset(serverId);
	}

	public AppWsResult archiveServer(Integer serverId){
		return UserUtil.getWsEndUserClient().setEnqueueServerArchiviation(serverId);
	}

	public AppWsResult restoreServer(Integer serverId, Integer cpu, Integer ram){
		return UserUtil.getWsEndUserClient().setEnqueueServerRestore(serverId, cpu, ram);
	}

	public AppWsResult connectVirtualSwitch(Integer vLanResourceId, Integer networkAdapterId){
		return UserUtil.getWsEndUserClient().setEnqueueAssociateVLan(vLanResourceId, networkAdapterId);
	}

	public AppWsResult disconnectVirtualSwitch(Integer vLanResourceId, Integer networkAdapterId){
		return UserUtil.getWsEndUserClient().setEnqueueDeassociateVLan(vLanResourceId, networkAdapterId);
	}

	public AppWsResult connectIps(List<Integer> ipAddressResourceIds, Integer networkAdapterId){
		return UserUtil.getWsEndUserClient().setEnqueueAssociateIpAddress(ipAddressResourceIds, networkAdapterId);
	}

	public AppWsResult disconnectIps(List<Integer> ipAddressResourceIds, Integer networkAdapterId){
		return UserUtil.getWsEndUserClient().setEnqueueDeassociateIpAddress(ipAddressResourceIds, networkAdapterId);
	}


	public AppWsResult removeDisk(Integer serverId,Integer diskNumber){
		return UserUtil.getWsEndUserClient().setEnqueueServerUpdateDiskDelete(serverId, diskNumber,false);
	}

	public AppWsResult createDisk(Integer serverId,Integer diskNumber, Integer newSize){
		return UserUtil.getWsEndUserClient().setEnqueueServerUpdateDiskCreate(serverId, diskNumber, newSize,false);
	}

	public AppWsResult resizeDisk(Integer serverId,Integer diskNumber,Integer newSize){
		return UserUtil.getWsEndUserClient().setEnqueueServerUpdateDiskResize(serverId, diskNumber, newSize, false);
	}

	public AppWsResult renameServer(Integer serverId,String newName){
		return UserUtil.getWsEndUserClient().renameServer(serverId, newName);
	}

	public AppWsResult modifyCpus(Integer serverId,Integer quantity){
		return UserUtil.getWsEndUserClient().setEnqueueServerUpdateCPU(serverId, quantity,false);
	}

	public AppWsResult modifyRams(Integer serverId,Integer quantity){
		return UserUtil.getWsEndUserClient().setEnqueueServerUpdateRAM(serverId, quantity, false);
	}





}
