
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

import whitelabel.cloud.remotews.jaxb.entity.ArrayOfTemplateDetails;
import whitelabel.cloud.remotews.jaxb.entity.Hypervisor;
import whitelabel.cloud.remotews.jaxb.entity.HypervisorServerTypes;
import whitelabel.cloud.remotews.jaxb.entity.HypervisorTypes;
import whitelabel.cloud.remotews.jaxb.entity.TemplateDetails;


/**
 * @author luca
 *
 */
public class AppHypervisor {

    private HypervisorServerTypes hypervisorServerType;
    private HypervisorTypes hypervisorType;
    private List<AppTemplateDetails> templates = new ArrayList<AppTemplateDetails>();
    
    public AppHypervisor(Hypervisor hypervisor) {
    	
    	if (hypervisor != null) {
    		setHypervisorType(hypervisor.getHypervisorType());
    		setHypervisorServerType(hypervisor.getHypervisorServerType());
    		
    		ArrayOfTemplateDetails aotd = hypervisor.getTemplates().getValue();
    		if (aotd != null) {
	    		for(TemplateDetails td : aotd.getTemplateDetails()) {
	    			templates.add( new AppTemplateDetails(td));
	    		}
    		}
    	}
    }

    
    public HypervisorServerTypes getHypervisorServerType() {
        return hypervisorServerType;
    }

    public void setHypervisorServerType(HypervisorServerTypes value) {
        this.hypervisorServerType = value;
    }

    public HypervisorTypes getHypervisorType() {
        return hypervisorType;
    }

    public void setHypervisorType(HypervisorTypes value) {
        this.hypervisorType = value;
    }

    public List<AppTemplateDetails> getTemplates() {
        return templates;
    }


}
