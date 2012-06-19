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
package whitelabel.cloud.wsclient.enduser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import whitelabel.cloud.entity.AppDatacenterConfig;
import whitelabel.cloud.entity.AppHypervisor;
import whitelabel.cloud.entity.AppTemplateDetails;
import whitelabel.cloud.entity.VDCResourceBoundsConfig;
import whitelabel.cloud.exception.WebServiceInvocationException;
import whitelabel.cloud.remotews.jaxb.entity.ArrayOfDatacenterConfig;
import whitelabel.cloud.remotews.jaxb.entity.ArrayOfHypervisor;
import whitelabel.cloud.remotews.jaxb.entity.DatacenterConfig;
import whitelabel.cloud.remotews.jaxb.entity.GetDatacenterConfigurations;
import whitelabel.cloud.remotews.jaxb.entity.GetDatacenterConfigurationsResponse;
import whitelabel.cloud.remotews.jaxb.entity.GetHypervisors;
import whitelabel.cloud.remotews.jaxb.entity.GetHypervisorsResponse;
import whitelabel.cloud.remotews.jaxb.entity.Hypervisor;
import whitelabel.cloud.remotews.jaxb.entity.HypervisorTypes;
import whitelabel.cloud.remotews.jaxb.entity.ObjectFactory;
import whitelabel.cloud.wsclient.RequestMessage;
import whitelabel.cloud.wsclient.SOAPHeaderAuthenticatedClient;

/**
 * @author luca
 *
 */
public class WsEndUserVDCConfigClient extends SOAPHeaderAuthenticatedClient {

	/**
	 * @param serviceNameSpace
	 * @param serviceName
	 * @param serviceEndPoint
	 */
	public WsEndUserVDCConfigClient(String serviceNameSpace,String serviceName, String serviceEndPoint) {
		super(serviceNameSpace, serviceName, serviceEndPoint, AuthenticationMode.USERNAME_TOKEN, new EndUserXMLBinder());
	}



	/**
	 * =====================================================================
	 * ------------- Specific WS VDC Config methods --------------------------
	 * =====================================================================
	 */

	/**
	 * @return
	 * @throws WebServiceInvocationException
	 */
	public final List<AppDatacenterConfig> getAllDatacenterConfigurations() throws WebServiceInvocationException {

		List<AppDatacenterConfig> response = new ArrayList<AppDatacenterConfig>();

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "GetDatacenterConfigurations");
		GetDatacenterConfigurations input = of.createGetDatacenterConfigurations();

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<GetDatacenterConfigurations>(portName, GetDatacenterConfigurations.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		try {
			GetDatacenterConfigurationsResponse wsResponse = invoke(portName, requestMessage, GetDatacenterConfigurationsResponse.class);

			//checkResponse(..) if necessary
			ArrayOfDatacenterConfig result = (ArrayOfDatacenterConfig) wsResponse.getGetDatacenterConfigurationsResult().getValue().getValue().getValue();

			if (result != null) {
				for(DatacenterConfig dcc : result.getDatacenterConfig()) {
					response.add(new AppDatacenterConfig(dcc));
				}
			}
		} catch (Throwable t) {
			invalidateCredentials();
			throw new WebServiceInvocationException(t);
		}
		return response;
	}


	/**
	 * @return
	 * @throws WebServiceInvocationException
	 */
	public final List<AppDatacenterConfig> getEnabledDatacenterConfigurations() throws WebServiceInvocationException {

		List<AppDatacenterConfig> response = new ArrayList<AppDatacenterConfig>();
		for(AppDatacenterConfig appDcc :  getAllDatacenterConfigurations()) {
			response.add(appDcc);
		}
		return response;
	}



	/**
	 * @return
	 */
	private final List<AppHypervisor> getHypervisors() {

		List<AppHypervisor> response = new ArrayList<AppHypervisor>();

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "GetHypervisors");

		GetHypervisors input = of.createGetHypervisors();
		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<GetHypervisors>(portName, GetHypervisors.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		try {
			GetHypervisorsResponse wsResponse = invoke(portName, requestMessage, GetHypervisorsResponse.class);

			//checkResponse(..) if necessary
			ArrayOfHypervisor result = (ArrayOfHypervisor) wsResponse.getGetHypervisorsResult().getValue().getValue().getValue();

			if (result != null) {
				for(Hypervisor item : result.getHypervisor()) {
					response.add(new AppHypervisor(item));
				}
			}
		} catch (Throwable t) {
			invalidateCredentials();
			throw new WebServiceInvocationException(t);
		}
		return response;
	}


	/**
	 * @return
	 */
	public final VDCResourceBoundsConfig getVDCResourceConfiguration() {

		VDCResourceBoundsConfig vdcConfig = new VDCResourceBoundsConfig();
		List<AppHypervisor> hypervisors = getHypervisors();
		List<AppTemplateDetails> commonTemplates = null;

		for(AppHypervisor hypervisor : hypervisors) {
			// if hypervisor-type is NOT  'ALL'
			if (! HypervisorTypes.ALL.equals( hypervisor.getHypervisorType())) {

				Map<Long, AppTemplateDetails> templateMap = new HashMap<Long, AppTemplateDetails>();
				for (AppTemplateDetails atd : hypervisor.getTemplates()) {
					templateMap.put((long)atd.getProductId(), atd);
				}
				vdcConfig.addHypervitorResource(hypervisor.getHypervisorType(), templateMap, hypervisor);
			}
			else { //handle common (to all hypervisors) templates
				commonTemplates = hypervisor.getTemplates();
			}
		}
		if (commonTemplates != null && commonTemplates.size() > 0) {
			//for ALL registered hypervisors
			for (String /*HypervisorTypes*/ registeredType : vdcConfig.getRegisteredHypervisorTypes()) {

				Map<Long, AppTemplateDetails> commontemplateMap = new HashMap<Long, AppTemplateDetails>();
				for(AppTemplateDetails atd : commonTemplates) {
					commontemplateMap.put((long)atd.getProductId(), atd);
				}
				// add commons templates
				vdcConfig.addHypervitorResource(HypervisorTypes.fromValue(registeredType), commontemplateMap, null);
			}
		}
		return vdcConfig;
	}

}
