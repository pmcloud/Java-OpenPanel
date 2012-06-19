
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

import whitelabel.cloud.remotews.jaxb.entity.CloudEntity;
import whitelabel.cloud.remotews.jaxb.entity.VLan;

/**
 * @author luca
 *
 */
public class AppVLan extends CloudEntity
{

    protected String name;
    protected List<Integer> serverIds;
    protected String vlanCode;
    protected String serverNames;


    public AppVLan(VLan vlan) {
    	if (vlan != null) {
    		setResourceId(vlan.getResourceId());
    		setName(vlan.getName().getValue());
    		setVlanCode(vlan.getVlanCode().getValue());
    		if (vlan.getServerIds() != null && vlan.getServerIds().getValue() != null) {
    			setServerIds(vlan.getServerIds().getValue().getInt());
    		}
    	}
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public List<Integer> getServerIds() {
        return serverIds;
    }

    public void setServerIds(List<Integer> value) {
        this.serverIds = value;
    }

    public String getVlanCode() {
        return vlanCode;
    }

    public void setVlanCode(String value) {
        this.vlanCode = value;
    }

	public String getServerNames() {
		return serverNames;
	}

	public void setServerNames(String serverNames) {
		this.serverNames = serverNames;
	}

	public boolean isConnected(){
		return getServerIds()!=null && !getServerIds().isEmpty();
	}

	public boolean isConnectedTo(Integer serverId){
		return isConnected()&&getServerIds().contains(serverId);
	}



}
