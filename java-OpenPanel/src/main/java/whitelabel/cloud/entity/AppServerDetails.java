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
package whitelabel.cloud.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import whitelabel.cloud.adapter.DateFormatterExt;
import whitelabel.cloud.remotews.jaxb.entity.ArrayOfJob;
import whitelabel.cloud.remotews.jaxb.entity.ArrayOfNetworkAdapter;
import whitelabel.cloud.remotews.jaxb.entity.ArrayOfVirtualDisk;
import whitelabel.cloud.remotews.jaxb.entity.Cpu;
import whitelabel.cloud.remotews.jaxb.entity.HypervisorServerTypes;
import whitelabel.cloud.remotews.jaxb.entity.HypervisorTypes;
import whitelabel.cloud.remotews.jaxb.entity.Job;
import whitelabel.cloud.remotews.jaxb.entity.NetworkAdapter;
import whitelabel.cloud.remotews.jaxb.entity.Ram;
import whitelabel.cloud.remotews.jaxb.entity.ServerDetails;
import whitelabel.cloud.remotews.jaxb.entity.ServerStatus;
import whitelabel.cloud.remotews.jaxb.entity.Template;
import whitelabel.cloud.remotews.jaxb.entity.VirtualDisk;


/**
 * @author luca
 *
 */
public class AppServerDetails {

    private List<AppJob> activeJobs;
    private Integer cpuQuantity;
    private Integer companyId;
    private XMLGregorianCalendar creationDate;
    private Integer datacenterId;
    private HypervisorServerTypes hypervisorServerType;
    private HypervisorTypes hypervisorType;
    private String name;

    private List<AppNetworkAdapter> networkAdapters;
    private String note;
    private Template osTemplate;
    private Integer ramQuantity;
    private Integer serverId;
    private ServerStatus serverStatus;
    private Boolean toolsAvailable;
    private Integer userId;
    private AppVirtualDisk[] virtualDisks = new AppVirtualDisk[4];
    //private ArrayOfKeyValuePair parameters;
    //private ArrayOfSnapshot snapshots;
    //private ArrayOfVirtualDVD virtualDVDs;



    /**
     * @param serverDetails
     */
    public AppServerDetails(ServerDetails serverDetails) {
    	if (serverDetails != null) {
    		setActiveJobs( serverDetails.getActiveJobs().getValue());
    		setCPUQuantity(serverDetails.getCPUQuantity().getValue());
    		setCompanyId(serverDetails.getCompanyId());
    		setCreationDate(serverDetails.getCreationDate());
    		setDatacenterId(serverDetails.getDatacenterId());
    		setHypervisorServerType(serverDetails.getHypervisorServerType());
    		setHypervisorType(serverDetails.getHypervisorType());
    		setName(serverDetails.getName().getValue());
    		setOSTemplate(serverDetails.getOSTemplate().getValue());
    		setRAMQuantity(serverDetails.getRAMQuantity().getValue());
    		setServerId(serverDetails.getServerId());
    		setServerStatus(serverDetails.getServerStatus());
    		setToolsAvailable(serverDetails.getToolsAvailable());
    		setUserId(serverDetails.getUserId());
    		if (serverDetails.getNetworkAdapters() != null) {
    			setNetworkAdapters(serverDetails.getNetworkAdapters().getValue());
    		}
    		if (serverDetails.getVirtualDisks() != null) {
    			setVirtualDisks(serverDetails.getVirtualDisks().getValue());
    		}
    	}
    }


    public List<AppJob> getActiveJobs() {
        return activeJobs;
    }

    private void setActiveJobs(ArrayOfJob value) {
    	if (value != null) {
    		this.activeJobs = new ArrayList<AppJob>();
    		for(Job j : value.getJob()) {
    			activeJobs.add(new AppJob(j));
    		}
    	}
    }

    public Integer getCPUQuantity() {
        return cpuQuantity;
    }

    public void setCPUQuantity(Integer value) {
        this.cpuQuantity = value != null ? value : 1;
    }

    private void setCPUQuantity(Cpu value) {
        this.cpuQuantity = value != null ? value.getQuantity() : 1;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer value) {
        this.companyId = value;
    }

    public String getCreationDate() {
    	if (creationDate != null) {
    		return (new DateFormatterExt()).print( creationDate.toGregorianCalendar().getTime());
    	}
    	return "---";
    }

    private void setCreationDate(XMLGregorianCalendar value) {
        this.creationDate = value;
    }


    public Integer getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(Integer value) {
        this.datacenterId = value;
    }

    public HypervisorServerTypes getHypervisorServerType() {
        return hypervisorServerType;
    }

    public void setHypervisorServerType(HypervisorServerTypes value) {
        this.hypervisorServerType = value;
    }

    public HypervisorTypes getHypervisorType() {
        return hypervisorType;
    }

    public void setHypervisorType(HypervisorTypes value) {
        this.hypervisorType = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = ((String ) value);
    }

    public List<AppNetworkAdapter> getNetworkAdapters() {
        return networkAdapters;
    }

    private void setNetworkAdapters(ArrayOfNetworkAdapter value) {
    	if (value != null) {
    		this.networkAdapters = new ArrayList<AppNetworkAdapter>();
    		for(NetworkAdapter na : value.getNetworkAdapter()) {
    			this.networkAdapters.add(new AppNetworkAdapter(na));
    		}
    	}
    }

    public String getNote() {
        return note;
    }

    public void setNote(String value) {
        this.note = ((String ) value);
    }

    public String getOSTemplateName() {
    	return ((getOSTemplate() != null && getOSTemplate().getName() != null) ? getOSTemplate().getName().getValue() : "");
    }

	public String getOSTemplateDescription() {
		return ((getOSTemplate() != null && getOSTemplate().getDescription() != null) ? getOSTemplate().getDescription().getValue() : "");
	}

	public Integer getOSTemplateId() {
		return ((getOSTemplate() != null && getOSTemplate().getId() != null) ? getOSTemplate().getId() : Integer.valueOf(-1));
	}

	public Integer getOSTemplateProductId() {
		return ((getOSTemplate() != null && getOSTemplate().getProductId() != null) ? getOSTemplate().getProductId() : Integer.valueOf(-1));
	}

    private Template getOSTemplate() {
        return osTemplate;
    }

    private void setOSTemplate(Template value) {
        this.osTemplate = ((Template ) value);
    }

    public Integer getRAMQuantity() {
        return ramQuantity;
    }

    public void setRAMQuantity(Integer value) {
    	this.ramQuantity = value;
    }

    private void setRAMQuantity(Ram value) {
        this.ramQuantity = (value != null ? value.getQuantity() : 1);
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer value) {
        this.serverId = value;
    }

    public ServerStatus getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(ServerStatus value) {
        this.serverStatus = value;
    }

    public Boolean isToolsAvailable() {
        return toolsAvailable;
    }

    public void setToolsAvailable(Boolean value) {
        this.toolsAvailable = value;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer value) {
        this.userId = value;
    }

    public AppVirtualDisk[] getVirtualDisks() {
        return virtualDisks;
    }

    private final int findDiskIndex(VirtualDisk vd) {
    	if (vd != null) {
    		switch (vd.getResourceType()) {
			case HARD_DISK_0:
				return 0;
			case HARD_DISK_1:
				return 1;
			case HARD_DISK_2:
				return 2;
			case HARD_DISK_3:
				return 3;
			default:
				return 1;
			}
    	}
    	return 1;
    }

    public void setVirtualDisks(ArrayOfVirtualDisk value) {
    	if (value != null) {
    		for(VirtualDisk vd : value.getVirtualDisk()) {
    			this.virtualDisks[findDiskIndex(vd)] = new AppVirtualDisk(vd);
    		}
    	}
    }

    public boolean isCreating() {
		return ServerStatus.CREATING.equals(getServerStatus());
	}

	public boolean isRunning() {
		return ServerStatus.RUNNING.equals(getServerStatus());
	}

	public boolean isStopped() {
		return ServerStatus.STOPPED.equals(getServerStatus());
	}

	public boolean isArchived() {
		return ServerStatus.ARCHIVED.equals(getServerStatus());
	}

	public boolean isUpdating() {
		return getActiveJobs().size()>0;
	}

	public Integer getTotalDiskSize(){
		int total = 0;
		if(getVirtualDisks()!=null){
			for (AppVirtualDisk vd : getVirtualDisks()) {
				if(vd!=null){
					total += vd.getSize();
				}
			}
		}

		return total;
	}


	public final boolean isDiskInUse(int diskNumber) {
		return getDiskSize(diskNumber)>0;
	}


	public final Integer getDiskSize(int diskNumber) {

		if (getVirtualDisks()!= null && getVirtualDisks().length>diskNumber && getVirtualDisks()[diskNumber]!=null) {

			Integer size = getVirtualDisks()[diskNumber].getSize();
			if(size==null){
				size=0;
			}
			return size;
		}
		return 0;
	}


}
