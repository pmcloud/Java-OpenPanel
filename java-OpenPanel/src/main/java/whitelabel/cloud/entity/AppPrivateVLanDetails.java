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

import whitelabel.cloud.remotews.jaxb.entity.PrivateVLanDetails;



/**
 * @author luca
 *
 */
public class AppPrivateVLanDetails {

    private String gateway;
    private String ipAddress;
    private Integer privateVLanResourceId;
    protected String subNetMask;

    
    
    /**
     * @param pVLanDetails
     */
    public AppPrivateVLanDetails(PrivateVLanDetails pVLanDetails) {
    	if (pVLanDetails != null) {
    		setGateway(pVLanDetails.getGateway().getValue());
    		setPrivateVLanResourceId(pVLanDetails.getPrivateVLanResourceId());
    		setSubNetMask(pVLanDetails.getSubNetMask().getValue());
    		setIPAddress(pVLanDetails.getIPAddress().getValue());
    	}
    }
    
    /**
     * @param vlanResourceID
     * @param ipAddres
     * @param netmask
     */
    public AppPrivateVLanDetails(Integer vlanResourceID, String ipAddres, String netmask) {
    	setPrivateVLanResourceId(vlanResourceID);
    	setIPAddress(ipAddres);
    	setSubNetMask(netmask);
    }
    
    public String getGateway() {
        return gateway;
    }

   
    public void setGateway(String value) {
        this.gateway = value;
    }

   
    public String getIPAddress() {
        return ipAddress;
    }

   
    public void setIPAddress(String value) {
        this.ipAddress = value;
    }

   
    public Integer getPrivateVLanResourceId() {
        return privateVLanResourceId;
    }

    
    public void setPrivateVLanResourceId(Integer value) {
        this.privateVLanResourceId = value;
    }

    
    public String getSubNetMask() {
        return subNetMask;
    }

    
    public void setSubNetMask(String value) {
        this.subNetMask = value;
    }

}
