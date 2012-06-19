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

import java.util.List;

public class AppNewServer {

    private String administratorPassword;
    private Integer cpuQuantity;
    private String name;
    private List<AppNetworkAdapterConfiguration> networkAdaptersConfiguration;    
    private Integer osTemplateId;
    private Integer ramQuantity;
    private List<AppVirtualDiskDetails> virtualDisks;
    //private String note;
    
    
    /**
     * @param newServer
     */
    public AppNewServer(String name, String adminPassword, Integer osTemplateId, Integer cpuQuantity, 
    		Integer ramQuantity, List<AppVirtualDiskDetails> virtualDisks, List<AppNetworkAdapterConfiguration> networkAdaptersConfiguration ) {
    	
		setAdministratorPassword(adminPassword);
		setCPUQuantity(cpuQuantity);
		setName(name);
		setOSTemplateId(osTemplateId);
		setRAMQuantity(ramQuantity);
		setVirtualDisks(virtualDisks);
		setNetworkAdaptersConfiguration(networkAdaptersConfiguration);    	
    }
    
    public String getAdministratorPassword() {
        return administratorPassword;
    }

    public void setAdministratorPassword(String value) {
        this.administratorPassword = value;
    }

    public Integer getCPUQuantity() {
        return cpuQuantity;
    }

    public void setCPUQuantity(Integer value) {
        this.cpuQuantity = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public List<AppNetworkAdapterConfiguration> getNetworkAdaptersConfiguration() {
        return networkAdaptersConfiguration;
    }

    private void setNetworkAdaptersConfiguration(List<AppNetworkAdapterConfiguration> value) {
    	if (value != null) {
    		this.networkAdaptersConfiguration = value;
    	}
    }

//    public String getNote() {
//        return note;
//    }
//
//    public void setNote(String value) {
//        this.note = value;
//    }

    public Integer getOSTemplateId() {
        return osTemplateId;
    }

    public void setOSTemplateId(Integer value) {
        this.osTemplateId = value;
    }

    public Integer getRAMQuantity() {
        return ramQuantity;
    }

    public void setRAMQuantity(Integer value) {
        this.ramQuantity = value;
    }

    public List<AppVirtualDiskDetails> getVirtualDisks() {
        return virtualDisks;
    }

    private void setVirtualDisks(List<AppVirtualDiskDetails> value) {
    	if (value != null) {
    		this.virtualDisks = value;
    	}
    }

}
