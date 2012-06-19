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
package whitelabel.cloud.webapp.impl.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import whitelabel.cloud.entity.AppIPAddress;
import whitelabel.cloud.entity.AppServerDetails;
import whitelabel.cloud.entity.AppVLan;
import whitelabel.cloud.remotews.jaxb.entity.HypervisorTypes;
import whitelabel.cloud.remotews.jaxb.entity.ResourceBound;
import whitelabel.cloud.webapp.impl.model.form.cloudServer.ChangeCPU;
import whitelabel.cloud.webapp.impl.model.form.cloudServer.ChangeName;
import whitelabel.cloud.webapp.impl.model.form.cloudServer.ChangeRAM;
import whitelabel.cloud.webapp.impl.model.form.cloudServer.DiskSize;

public class CloudServer extends AppModel {

	private AppServerDetails appServerDetail;

	/**
	 * array of unconnected Ips
	 */
	private List<AppIPAddress> availableIps;

	/**
	 * array of VirtualSwitches not jet connected to appServerDetail
	 */
	private List<AppVLan> availableVirtualSwitches;

	/**
  	 * CPUs bounds for the OSTemplate
  	 */
  	private ResourceBound appCPUsBound;

  	/**
  	 * RAMs bounds for the OSTemplate
  	 */
  	private ResourceBound appRAMsBound;

  	/**
  	 * HDs bounds for the OSTemplate
  	 */
  	private Map<Integer, ResourceBound> appHDxBound= new HashMap<Integer, ResourceBound>();


  	/*
  	 * forms bean
  	 */

  	private ChangeName changeName = new ChangeName();

  	private ChangeCPU changeCPU = new ChangeCPU();

  	private ChangeRAM changeRAM = new ChangeRAM();

  	private DiskSize diskSize = new DiskSize();




	public CloudServer() {

	}



	public AppServerDetails getAppServerDetail() {
		return appServerDetail;
	}

	public void setAppServerDetail(AppServerDetails appServerDetail) {
		this.appServerDetail = appServerDetail;
	}



	public List<AppIPAddress> getAvailableIps() {
		return availableIps;
	}



	public void setAvailableIps(List<AppIPAddress> availableIps) {
		this.availableIps = availableIps;
	}



	public List<AppVLan> getAvailableVirtualSwitches() {
		return availableVirtualSwitches;
	}



	public void setAvailableVirtualSwitches(List<AppVLan> availableVirtualSwitches) {
		this.availableVirtualSwitches = availableVirtualSwitches;
	}



	public ResourceBound getAppCPUsBound() {
		return appCPUsBound;
	}



	public void setAppCPUsBound(ResourceBound appCPUsBound) {
		this.appCPUsBound = appCPUsBound;
	}



	public ResourceBound getAppRAMsBound() {
		return appRAMsBound;
	}



	public void setAppRAMsBound(ResourceBound appRAMsBound) {
		this.appRAMsBound = appRAMsBound;
	}



	public Map<Integer, ResourceBound> getAppHDxBound() {
		return appHDxBound;
	}



	public void setAppHDxBound(Map<Integer, ResourceBound> appHDxBound) {
		this.appHDxBound = appHDxBound;
	}



	public ChangeName getChangeName() {
		return changeName;
	}



	public void setChangeName(ChangeName changeName) {
		this.changeName = changeName;
	}

	public final boolean isShutdownAvailable() {
		// shutdown is not available for HyperV-lowcost type
		return getAppServerDetail()!=null && !HypervisorTypes.HYPER_V_LOW_COST.equals(getAppServerDetail().getHypervisorType());
	}



	public ChangeCPU getChangeCPU() {
		return changeCPU;
	}



	public void setChangeCPU(ChangeCPU changeCPU) {
		this.changeCPU = changeCPU;
	}



	public ChangeRAM getChangeRAM() {
		return changeRAM;
	}



	public void setChangeRAM(ChangeRAM changeRAM) {
		this.changeRAM = changeRAM;
	}



	public DiskSize getDiskSize() {
		return diskSize;
	}



	public void setDiskSize(DiskSize diskSize) {
		this.diskSize = diskSize;
	}





}
