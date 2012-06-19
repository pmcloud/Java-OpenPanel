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

import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import whitelabel.cloud.adapter.DateFormatterExt;
import whitelabel.cloud.remotews.jaxb.entity.CloudEntity;
import whitelabel.cloud.remotews.jaxb.entity.VirtualDisk;

/**
 * @author luca
 *
 */
public class AppVirtualDisk extends CloudEntity
{

    private XMLGregorianCalendar creationDate;
    private Integer size;
    
    
    
    public AppVirtualDisk() {
    }
    
    public AppVirtualDisk(VirtualDisk virtualDisk) {
    	if (virtualDisk != null) {
    		setSize(virtualDisk.getSize());
    		setCreationDate(virtualDisk.getCreationDate());
    	}
    }
    
    public String getCreationDate() { 
    	if (creationDate != null) {
    		Date data = creationDate.toGregorianCalendar().getTime();    	
    		return (new DateFormatterExt()).print(data);
    	}
    	return "---";
    }

    
    private void setCreationDate(XMLGregorianCalendar value) {
        this.creationDate = value;
    }

    
    public Integer getSize() {
        return size;
    }

    
    public void setSize(Integer value) {
        this.size = value;
    }	

    
}
