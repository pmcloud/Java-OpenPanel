
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

import whitelabel.cloud.remotews.jaxb.entity.CloudEntity;
import whitelabel.cloud.remotews.jaxb.entity.IPAddress;


/**
 * @author luca
 *
 */
public class AppIPAddress extends CloudEntity
{

    private String gateway;
    private Integer serverId;
    private String subNetMask;
    private String value;
    private String serverName;


    public AppIPAddress(IPAddress ipAddress) {
    	if (ipAddress != null) {
    		setResourceId(ipAddress.getResourceId());
    		setGateway(ipAddress.getGateway().getValue());
    		setServerId(ipAddress.getServerId().getValue());
    		setSubNetMask(ipAddress.getSubNetMask().getValue());
    		setValue(ipAddress.getValue().getValue());
    	}
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String value) {
        this.gateway = value;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer value) {
        this.serverId = value;
    }

    public String getSubNetMask() {
        return subNetMask;
    }

    public void setSubNetMask(String value) {
        this.subNetMask = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public boolean isAssigned(){
		return serverId!=null;
	}

}
