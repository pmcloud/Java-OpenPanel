/**
 *
 */
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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import whitelabel.cloud.remotews.jaxb.entity.HypervisorTypes;
import whitelabel.cloud.remotews.jaxb.entity.ResourceBound;
import whitelabel.cloud.remotews.jaxb.entity.ResourceTypes;

/**
 * @author luca
 *
 */
public class VDCResourceBoundsConfig {

//	private Map<HypervisorTypes, Map<Integer,AppTemplateDetails>> hypervisorTemplates = new HashMap<HypervisorTypes, Map<Integer,AppTemplateDetails>>();
//	private Map<HypervisorTypes, AppHypervisor> hypervisors = new HashMap<HypervisorTypes, AppHypervisor>();
	private Map<String, Map<Long,AppTemplateDetails>> hypervisorTemplates = new HashMap<String, Map<Long,AppTemplateDetails>>();
	private Map<String, AppHypervisor> hypervisors = new HashMap<String, AppHypervisor>();



	/**
	 * @param hypervisorType
	 * @param hypervisorTemplateArray
	 * @param appHypervisor
	 */
	public final void addHypervitorResource(HypervisorTypes hypervisorType, Map<Long, AppTemplateDetails> hypervisorTemplateArray,  AppHypervisor appHypervisor) {
		if (hypervisorType != null && hypervisorTemplateArray != null && hypervisorTemplateArray.size() > 0) {

			if (! hypervisorTemplates.containsKey( hypervisorType.value())) {
				hypervisorTemplates.put(hypervisorType.value(), new HashMap<Long, AppTemplateDetails>());
				if (appHypervisor != null) {
					hypervisors.put(hypervisorType.value(), appHypervisor);
				}
				hypervisorTemplates.put(hypervisorType.value(), hypervisorTemplateArray);
			}
		}
	}

	/**
	 * @return
	 */
	//public final List<HypervisorTypes> getRegisteredHypervisorTypes() {
	public final List<String> getRegisteredHypervisorTypes() {
		return new ArrayList<String>(hypervisors.keySet());
	}

	/**
	 * @param hypervisorType
	 * @return
	 */
	public final AppHypervisor getRegisteredHypervisor(HypervisorTypes hypervisorType) {
		return  (hypervisors.containsKey(hypervisorType.value()) ?  hypervisors.get(hypervisorType.value()) : null);
	}

	/**
	 * @return List<AppHypervisor>
	 */
	public final List<AppHypervisor> getAllRegisteredHypervisors() {				
		//return new ArrayList<AppHypervisor>(hypervisors.values());
		List<AppHypervisor> registered=new ArrayList<AppHypervisor>(hypervisors.values());
		Collections.sort(registered, new Comparator<AppHypervisor>() {
				@Override
				public int compare(AppHypervisor a1, AppHypervisor a2) {
					if (a1 != null && a2 != null) {						
						return (a1.getHypervisorType().value().compareToIgnoreCase(a2.getHypervisorType().value()));
					}
					else {
						if (a1 == null) {
							return 1;
						}
						else {
							return -1;
						}
					}					
				}
		});
		return registered;
	}

	
	/**
	 * @param hypervisorType
	 * @return Map<Long,AppTemplateDetails>
	 */
	public final Map<Long,AppTemplateDetails> getTemplatesFor(String hypervisorType) { //HypervisorTypes hypervisorType) {
		return hypervisorTemplates.get(hypervisorType);
	}

	/**
	 * @param hypervisorType
	 * @param templateProductId
	 * @return AppTemplateDetails
	 */
	public final AppTemplateDetails getTemplate(String hypervisorType, Long templateProductId) { //HypervisorTypes hypervisorType, Integer templateProductId) {
		Map<Long,AppTemplateDetails> tmpMap = getTemplatesFor(hypervisorType);
		if (tmpMap != null) {
			return tmpMap.get(templateProductId);
		}
		return null;
	}

	public final ResourceBound getResourceBounds(String hypervisorType, Long templateProdId, ResourceTypes resourceType) { //HypervisorTypes hypervisorType, Integer templateProdId, ResourceTypes resourceType) {

		if (hypervisorType != null && templateProdId != null && resourceType != null) {

			AppTemplateDetails templateDetails = hypervisorTemplates.get(hypervisorType).get(templateProdId);

			for (ResourceBound resource : templateDetails.getResourceBounds()) {
				if(resourceType.equals(resource.getResourceType())){
					return resource;
				}
			}
		}
		return null;
	}



}
