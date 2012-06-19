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

import whitelabel.cloud.remotews.jaxb.entity.VirtualDiskDetails;
import whitelabel.cloud.remotews.jaxb.entity.VirtualDiskTypes;


/**
 * @author luca
 *
 */
public class AppVirtualDiskDetails {

    protected String customVirtualDiskPath;
    protected Integer size;
    protected VirtualDiskTypes virtualDiskType;

    
    /**
     * @param size
     * @param customVPath
     * @param vdType
     */
    public AppVirtualDiskDetails(Integer size, String customVPath, VirtualDiskTypes vdType) {
    	setSize(size);
    	if (customVPath != null) {
    		setCustomVirtualDiskPath(customVPath);
    	}
    	setVirtualDiskType(vdType);
    }
    
    /**
     * @param vDiskDetails
     */
    public AppVirtualDiskDetails(VirtualDiskDetails vDiskDetails) {
    	if (vDiskDetails != null) {
    		setCustomVirtualDiskPath(vDiskDetails.getCustomVirtualDiskPath().getValue());
    		setSize(vDiskDetails.getSize());
    		setVirtualDiskType(vDiskDetails.getVirtualDiskType());
    	}
    }
    
    
    public String getCustomVirtualDiskPath() {
        return customVirtualDiskPath;
    }

    public void setCustomVirtualDiskPath(String value) {
        this.customVirtualDiskPath = value;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer value) {
        this.size = value;
    }

    public VirtualDiskTypes getVirtualDiskType() {
        return virtualDiskType;
    }

    public void setVirtualDiskType(VirtualDiskTypes value) {
        this.virtualDiskType = value;
    }

}
