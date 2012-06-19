
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

import whitelabel.cloud.remotews.jaxb.entity.ArrayOfPublicIpAddressDetails;
import whitelabel.cloud.remotews.jaxb.entity.NetworkAdapterConfiguration;
import whitelabel.cloud.remotews.jaxb.entity.NetworkAdapterTypes;
import whitelabel.cloud.remotews.jaxb.entity.PublicIpAddressDetails;


/**
 * @author luca
 *
 */
public class AppNetworkAdapterConfiguration {

    private NetworkAdapterTypes networkAdapterType;
    private AppPrivateVLanDetails privateVLan;
    
    //private ArrayOfPublicIpAddressDetails publicIpAddresses;
    private List<AppPublicIpAddressDetails> publicIpAddresses;
    
    
    /**
     * @param netConfiguration
     */
    public AppNetworkAdapterConfiguration(NetworkAdapterConfiguration netConfiguration) {
    	if (netConfiguration != null) {
    		setNetworkAdapterType(netConfiguration.getNetworkAdapterType());
    		if (netConfiguration.getPrivateVLan() != null) {
    			setPrivateVLan( new AppPrivateVLanDetails(netConfiguration.getPrivateVLan().getValue()));
    		}
    		if (netConfiguration.getPublicIpAddresses() != null) {
    			setPublicIpAddresses( netConfiguration.getPublicIpAddresses().getValue());
    		}
    	}
    }
    
    public AppNetworkAdapterConfiguration(NetworkAdapterTypes nat, AppPrivateVLanDetails apvd, List<AppPublicIpAddressDetails> list) {
    	setNetworkAdapterType(nat);
    	setPrivateVLan(apvd);
    	setPublicIpAddresses(list);
    }
    
    public NetworkAdapterTypes getNetworkAdapterType() {
        return networkAdapterType;
    }

    public void setNetworkAdapterType(NetworkAdapterTypes value) {
        this.networkAdapterType = value;
    }

    public AppPrivateVLanDetails getPrivateVLan() {
        return privateVLan;
    }

    public void setPrivateVLan(AppPrivateVLanDetails value) {
        this.privateVLan = value;
    }

    public List<AppPublicIpAddressDetails> getPublicIpAddresses() {
        return publicIpAddresses;
    }

    private void setPublicIpAddresses(ArrayOfPublicIpAddressDetails value) {
    	if (value != null) {
    		
    		this.publicIpAddresses = new ArrayList<AppPublicIpAddressDetails>();
    		for(PublicIpAddressDetails piad : value.getPublicIpAddressDetails()) {
    			publicIpAddresses.add(new AppPublicIpAddressDetails(piad));
    		}
    	}
    }
    private void setPublicIpAddresses(List<AppPublicIpAddressDetails> list) {
    	this.publicIpAddresses = list;
    }
}
