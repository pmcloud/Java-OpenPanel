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

import whitelabel.cloud.remotews.jaxb.entity.ArrayOfIPAddress;
import whitelabel.cloud.remotews.jaxb.entity.IPAddress;
import whitelabel.cloud.remotews.jaxb.entity.NetworkAdapter;
import whitelabel.cloud.remotews.jaxb.entity.NetworkAdapterTypes;


/**
 * @author luca
 *
 */
public class AppNetworkAdapter {


    private Integer id;
    private String macAddress;
    private NetworkAdapterTypes networkAdapterType;
    private Integer serverId;
    private List<AppIPAddress> ipAddresses;
    private AppVLan vLan;

    public AppNetworkAdapter(NetworkAdapter networkAdapter) {
    	if (networkAdapter != null) {
    		setId(networkAdapter.getId());
    		setMacAddress(networkAdapter.getMacAddress().getValue());
    		setServerId(networkAdapter.getServerId());

    		if (networkAdapter.getVLan() != null) {
    			setVLan(new AppVLan(networkAdapter.getVLan().getValue()));
    		}
    		if (networkAdapter.getIPAddresses() != null) {
    			setIPAddresses( networkAdapter.getIPAddresses().getValue());
    		}
    	}
    }

    public List<AppIPAddress> getIPAddresses() {
        return ipAddresses;
    }

    private void setIPAddresses(ArrayOfIPAddress value) {
    	if (value != null && value.getIPAddress() != null) {
    		this.ipAddresses = new ArrayList<AppIPAddress>();
    		for(IPAddress ipa : value.getIPAddress()) {
    			ipAddresses.add(new AppIPAddress(ipa));
    		}
    	}
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String value) {
        this.macAddress = value;
    }

    public NetworkAdapterTypes getNetworkAdapterType() {
        return networkAdapterType;
    }

    public void setNetworkAdapterType(NetworkAdapterTypes value) {
        this.networkAdapterType = value;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer value) {
        this.serverId = value;
    }

    public AppVLan getVLan() {
        return vLan;
    }

    private void setVLan(AppVLan value) {
        this.vLan = value;
    }

    public String getVLanName() {
		return (getVLan() != null ? getVLan().getName(): "");
	}

	public  boolean isAssociatedToPublicIPs() {
		return (getIPAddresses()!=null && getIPAddresses().size()>0);
	}

	public  boolean isAssociatedToVirtualSwitch() {
		return getVLan()!=null && getVLan().getResourceId()!=null;
	}

	public  boolean isConnected() {
		return (isAssociatedToPublicIPs() || isAssociatedToVirtualSwitch());
	}

}
