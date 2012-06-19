
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

import whitelabel.cloud.remotews.jaxb.entity.DatacenterConfig;
import whitelabel.cloud.remotews.jaxb.entity.DatacenterConfigStatus;


/**
 * @author luca
 *
 */
public class AppDatacenterConfig {

    private String adminPanelBaseUrl;
    private String country;
    private Integer datacenterId;
    private String name;
    private Integer priority;
    private DatacenterConfigStatus status;
    private String wsBaseUrl;

    
    /**
     * @param datacenterConfig
     */
    public AppDatacenterConfig(DatacenterConfig datacenterConfig) {
    	if (datacenterConfig != null) {
    		setAdminPanelBaseUrl(datacenterConfig.getAdminPanelBaseUrl().getValue());
    		setCountry(datacenterConfig.getCountry().getValue());
    		setDatacenterId(datacenterConfig.getDatacenterId());
    		setName(datacenterConfig.getName().getValue());
    		setPriority(datacenterConfig.getPriority());
    		setStatus(datacenterConfig.getStatus());
    		setWsBaseUrl(datacenterConfig.getWsBaseUrl().getValue());
    	}
    }
    
    public final String getDatacenterDescription() {
    	return getDatacenterId() + " - " + getName();
    }
    
    public final String toString() {
    	return getDatacenterDescription();
    }
    
    public String getAdminPanelBaseUrl() {
        return adminPanelBaseUrl;
    }

    public void setAdminPanelBaseUrl(String value) {
        this.adminPanelBaseUrl = value;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String value) {
        this.country = value;
    }

    public Integer getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(Integer value) {
        this.datacenterId = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer value) {
        this.priority = value;
    }

    public DatacenterConfigStatus getStatus() {
        return status;
    }

    public void setStatus(DatacenterConfigStatus value) {
        this.status = value;
    }

    public String getWsBaseUrl() {
        return wsBaseUrl;
    }

    public void setWsBaseUrl(String value) {
        this.wsBaseUrl = value;
    }

}
