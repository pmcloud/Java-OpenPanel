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

import whitelabel.cloud.remotews.jaxb.entity.PublicIpAddressDetails;



/**
 * @author luca
 *
 */
public class AppPublicIpAddressDetails {

    private Boolean primaryIPAddress;
    private Integer publicIpAddressResourceId;


    /**
     * @param pubIPAddressDetails
     */
    public AppPublicIpAddressDetails(PublicIpAddressDetails pubIPAddressDetails) {

    	if (pubIPAddressDetails != null) {
    		setPrimaryIPAddress(pubIPAddressDetails.getPrimaryIPAddress());
    		setPublicIpAddressResourceId(pubIPAddressDetails.getPublicIpAddressResourceId());
    	}
    }

    /**
     * @param resourceId
     * @param primaryIpAddress
     */
    public AppPublicIpAddressDetails(Integer resourceId, boolean primaryIpAddress) {
    	setPublicIpAddressResourceId(resourceId);
    	setPrimaryIPAddress(primaryIpAddress);
    }


    public Boolean isPrimaryIPAddress() {
        return primaryIPAddress;
    }


    public void setPrimaryIPAddress(Boolean value) {
        this.primaryIPAddress = value;
    }


    public Integer getPublicIpAddressResourceId() {
        return publicIpAddressResourceId;
    }


    public void setPublicIpAddressResourceId(Integer value) {
        this.publicIpAddressResourceId = value;
    }

}
