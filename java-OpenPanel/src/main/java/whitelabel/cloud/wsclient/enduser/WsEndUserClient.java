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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ListResourceBundle;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import whitelabel.cloud.entity.AppCredit;
import whitelabel.cloud.entity.AppDatacenterConfig;
import whitelabel.cloud.entity.AppJob;
import whitelabel.cloud.entity.AppLog;
import whitelabel.cloud.entity.AppNetworkAdapterConfiguration;
import whitelabel.cloud.entity.AppNewServer;
import whitelabel.cloud.entity.AppPublicIpAddressDetails;
import whitelabel.cloud.entity.AppServerDetails;
import whitelabel.cloud.entity.AppUserToken;
import whitelabel.cloud.entity.AppVirtualDatacenter;
import whitelabel.cloud.entity.AppVirtualDisk;
import whitelabel.cloud.entity.AppVirtualDiskDetails;
import whitelabel.cloud.entity.AppWsResult;
import whitelabel.cloud.exception.WebServiceInvocationException;
import whitelabel.cloud.remotews.jaxb.entity.ArrayOfJob;
import whitelabel.cloud.remotews.jaxb.entity.ArrayOfLog;
import whitelabel.cloud.remotews.jaxb.entity.ArrayOfNetworkAdapterConfiguration;
import whitelabel.cloud.remotews.jaxb.entity.ArrayOfPublicIpAddressDetails;
import whitelabel.cloud.remotews.jaxb.entity.ArrayOfVirtualDiskDetails;
import whitelabel.cloud.remotews.jaxb.entity.ArrayOfVirtualDiskUpdate;
import whitelabel.cloud.remotews.jaxb.entity.ArrayOfint;
import whitelabel.cloud.remotews.jaxb.entity.Credit;
import whitelabel.cloud.remotews.jaxb.entity.GetCredit;
import whitelabel.cloud.remotews.jaxb.entity.GetCreditResponse;
import whitelabel.cloud.remotews.jaxb.entity.GetJobs;
import whitelabel.cloud.remotews.jaxb.entity.GetJobsResponse;
import whitelabel.cloud.remotews.jaxb.entity.GetLogs;
import whitelabel.cloud.remotews.jaxb.entity.GetLogsResponse;
import whitelabel.cloud.remotews.jaxb.entity.GetServerDetails;
import whitelabel.cloud.remotews.jaxb.entity.GetServerDetailsResponse;
import whitelabel.cloud.remotews.jaxb.entity.GetUserAuthenticationToken;
import whitelabel.cloud.remotews.jaxb.entity.GetUserAuthenticationTokenResponse;
import whitelabel.cloud.remotews.jaxb.entity.GetVirtualDatacenter;
import whitelabel.cloud.remotews.jaxb.entity.GetVirtualDatacenterResponse;
import whitelabel.cloud.remotews.jaxb.entity.Job;
import whitelabel.cloud.remotews.jaxb.entity.Log;
import whitelabel.cloud.remotews.jaxb.entity.NetworkAdapterConfiguration;
import whitelabel.cloud.remotews.jaxb.entity.NewServer;
import whitelabel.cloud.remotews.jaxb.entity.ObjectFactory;
import whitelabel.cloud.remotews.jaxb.entity.PrivateVLanDetails;
import whitelabel.cloud.remotews.jaxb.entity.PublicIpAddressDetails;
import whitelabel.cloud.remotews.jaxb.entity.ServerDetails;
import whitelabel.cloud.remotews.jaxb.entity.ServerRestore;
import whitelabel.cloud.remotews.jaxb.entity.ServerUpdate;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueAssociateIpAddress;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueAssociateIpAddressResponse;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueAssociateVLan;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueAssociateVLanResponse;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueDeassociateIpAddress;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueDeassociateIpAddressResponse;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueDeassociateVLan;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueDeassociateVLanResponse;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueServerArchiviation;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueServerArchiviationResponse;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueServerCreation;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueServerCreationResponse;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueServerDeletion;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueServerDeletionResponse;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueServerPowerOff;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueServerPowerOffResponse;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueServerReset;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueServerResetResponse;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueServerRestart;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueServerRestartResponse;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueServerRestore;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueServerRestoreResponse;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueServerStart;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueServerStartResponse;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueServerStop;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueServerStopResponse;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueServerUpdate;
import whitelabel.cloud.remotews.jaxb.entity.SetEnqueueServerUpdateResponse;
import whitelabel.cloud.remotews.jaxb.entity.SetPurchaseIpAddress;
import whitelabel.cloud.remotews.jaxb.entity.SetPurchaseIpAddressResponse;
import whitelabel.cloud.remotews.jaxb.entity.SetPurchaseVLan;
import whitelabel.cloud.remotews.jaxb.entity.SetPurchaseVLanResponse;
import whitelabel.cloud.remotews.jaxb.entity.SetRemoveIpAddress;
import whitelabel.cloud.remotews.jaxb.entity.SetRemoveIpAddressResponse;
import whitelabel.cloud.remotews.jaxb.entity.SetRemoveVLan;
import whitelabel.cloud.remotews.jaxb.entity.SetRemoveVLanResponse;
import whitelabel.cloud.remotews.jaxb.entity.SetRenameServer;
import whitelabel.cloud.remotews.jaxb.entity.SetRenameServerResponse;
import whitelabel.cloud.remotews.jaxb.entity.SetRenameVLan;
import whitelabel.cloud.remotews.jaxb.entity.SetRenameVLanResponse;
import whitelabel.cloud.remotews.jaxb.entity.UserToken;
import whitelabel.cloud.remotews.jaxb.entity.VirtualDatacenter;
import whitelabel.cloud.remotews.jaxb.entity.VirtualDiskDetails;
import whitelabel.cloud.remotews.jaxb.entity.VirtualDiskOperationTypes;
import whitelabel.cloud.remotews.jaxb.entity.VirtualDiskTypes;
import whitelabel.cloud.remotews.jaxb.entity.VirtualDiskUpdate;
import whitelabel.cloud.remotews.jaxb.entity.WsResult;
import whitelabel.cloud.wsclient.RequestMessage;
import whitelabel.cloud.wsclient.SOAPHeaderAuthenticatedClient;

/**
 * @author luca
 *
 */
public final class WsEndUserClient extends SOAPHeaderAuthenticatedClient {

	/**
	 * @param serviceNameSpace
	 * @param serviceName
	 * @param serviceEndPoint
	 */
	public WsEndUserClient(String serviceNameSpace, String serviceName, String serviceEndPoint) {
        super(serviceNameSpace, serviceName, serviceEndPoint, AuthenticationMode.USERNAME_TOKEN, new EndUserXMLBinder());
	}


	/**
	 * =====================================================================
	 * ------------- Specific WS End-user methods --------------------------
	 * =====================================================================
	 */

	/**
	 * @param username
	 * @param passwordToken
	 * @return
	 * @throws WebServiceInvocationException
	 */
	public AppUserToken loginAs(String username, String passwordToken) throws WebServiceInvocationException {

		// set the credentials to login..
		setCredentials(username, passwordToken);

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "GetUserAuthenticationToken");
		GetUserAuthenticationToken input = of.createGetUserAuthenticationToken();

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<GetUserAuthenticationToken>(portName, GetUserAuthenticationToken.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart()); //GetUserAuthenticationToken);

		GetUserAuthenticationTokenResponse wsResponse = invoke(portName, requestMessage, GetUserAuthenticationTokenResponse.class);

		//checkResponse(..) if necessary
		UserToken userToken = (UserToken) wsResponse.getGetUserAuthenticationTokenResult().getValue().getValue().getValue();

		if (userToken != null) {
			AppUserToken applicationToken = new AppUserToken(userToken.getUserName().getValue(), userToken.getToken().getValue());
			if (applicationToken.isValid()) {
				// change the used credentials...
				setCredentials(applicationToken.getUserName(), applicationToken.getToken());
			}
			return applicationToken;
		}
		else {
			throw new WebServiceInvocationException();
		}
	}


	/**
	 * @return
	 */
	public final AppCredit getAvailableCredit() {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "GetCredit");
		GetCredit input = of.createGetCredit();

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<GetCredit>(portName, GetCredit.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		Credit value = null;
		try {
			GetCreditResponse wsResponse = invoke(portName, requestMessage, GetCreditResponse.class);

			//checkResponse(..) if necessary
			value = (Credit) wsResponse.getGetCreditResult().getValue().getValue().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppCredit(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}

	/**
	 * @return
	 */
	public final AppVirtualDatacenter getVirtualDatacenter() {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "GetVirtualDatacenter");
		GetVirtualDatacenter input = of.createGetVirtualDatacenter();

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<GetVirtualDatacenter>(portName, GetVirtualDatacenter.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		VirtualDatacenter value = null;
		try {
			GetVirtualDatacenterResponse wsResponse = invoke(portName, requestMessage, GetVirtualDatacenterResponse.class);

			//checkResponse(..) if necessary
			value = (VirtualDatacenter) wsResponse.getGetVirtualDatacenterResult().getValue().getValue().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppVirtualDatacenter(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}


	/**
	 * @param serverId
	 * @return
	 */
	public final AppServerDetails getServerDetails(int serverId) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "GetServerDetails");
		GetServerDetails input = of.createGetServerDetails();
		input.setServerId(serverId);

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<GetServerDetails>(portName, GetServerDetails.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		ServerDetails value = null;
		try {
			GetServerDetailsResponse wsResponse = invoke(portName, requestMessage, GetServerDetailsResponse.class);

			//checkResponse(..) if necessary
			value = (ServerDetails) wsResponse.getGetServerDetailsResult().getValue().getValue().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppServerDetails(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}

	/**
	 * @param serverId
	 * @param from
	 * @param to
	 * @return
	 */
	public final List<AppLog> getLogs(Integer serverId, Date from, Date to) {

		List<AppLog> response = new ArrayList<AppLog>();

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "GetLogs");

		GetLogs input = of.createGetLogs();
		if (serverId != null) {
			input.setServerId( of.createGetLogsServerId(serverId) );
		}

		DatatypeFactory df = null;
		if (from != null) {
			try {
				df = DatatypeFactory.newInstance();
				GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
				gc.setTime(from);
				XMLGregorianCalendar value = df.newXMLGregorianCalendar(gc);
				input.setFrom( of.createGetLogsFrom(value));
			}
			catch (Throwable e) {
			}
		}
		if (to != null) {
			try {
				if (df == null) {
					df = DatatypeFactory.newInstance();
				}
				GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
				gc.setTime(from);
				XMLGregorianCalendar value = df.newXMLGregorianCalendar(gc);
				input.setTo( of.createGetLogsTo(value));
			}
			catch (Throwable e) {
			}
		}

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<GetLogs>(portName, GetLogs.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		try {
			GetLogsResponse wsResponse = invoke(portName, requestMessage, GetLogsResponse.class);

			//checkResponse(..) if necessary
			ArrayOfLog result = (ArrayOfLog) wsResponse.getGetLogsResult().getValue().getValue().getValue();

			if (result != null) {
				for(Log item : result.getLog()) {
					response.add(new AppLog(item));
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
	public final List<AppJob> getJobs() {

		List<AppJob> response = new ArrayList<AppJob>();

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "GetJobs");

		GetJobs input = of.createGetJobs();
		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<GetJobs>(portName, GetJobs.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		try {
			GetJobsResponse wsResponse = invoke(portName, requestMessage, GetJobsResponse.class);

			//checkResponse(..) if necessary
			ArrayOfJob result = (ArrayOfJob) wsResponse.getGetJobsResult().getValue().getValue().getValue();

			if (result != null) {
				for(Job item : result.getJob()) {
					response.add(new AppJob(item));
				}
			}
		} catch (Throwable t) {
			invalidateCredentials();
			throw new WebServiceInvocationException(t);
		}
		return response;
	}



	/**
	 * @param serverId
	 * @param newServerName
	 * @return
	 */
	public final AppWsResult renameServer(Integer serverId, String newServerName) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetRenameServer");

		SetRenameServer input = of.createSetRenameServer();
		input.setServerId(serverId);
		input.setServerName( of.createSetRenameServerServerName(newServerName));

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetRenameServer>(portName, SetRenameServer.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetRenameServerResponse wsResponse = invoke(portName, requestMessage, SetRenameServerResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetRenameServerResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}

	}

	/**
	 * @param vlanId
	 * @param newVlanName
	 * @return
	 */
	public final AppWsResult renameVLan(Integer vlanId, String newVlanName) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetRenameVLan");

		SetRenameVLan input = of.createSetRenameVLan();
		input.setVLanResourceId(vlanId);
		input.setVLanName( of.createSetRenameServerServerName(newVlanName));

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetRenameVLan>(portName, SetRenameVLan.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetRenameVLanResponse wsResponse = invoke(portName, requestMessage, SetRenameVLanResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetRenameVLanResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}


	/**
	 * @param vlanId
	 * @return
	 */
	public final AppWsResult removeVLan(Integer vlanId) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetRemoveVLan");

		SetRemoveVLan input = of.createSetRemoveVLan();
		input.setVLanResourceId(vlanId);

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetRemoveVLan>(portName, SetRemoveVLan.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetRemoveVLanResponse wsResponse = invoke(portName, requestMessage, SetRemoveVLanResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetRemoveVLanResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}

	/**
	 * @param ipAddressResourceId
	 * @return
	 */
	public final AppWsResult removeIpAddress(Integer ipAddressResourceId) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetRemoveIpAddress");

		SetRemoveIpAddress input = of.createSetRemoveIpAddress();
		input.setIpAddressResourceId(ipAddressResourceId);

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetRemoveIpAddress>(portName, SetRemoveIpAddress.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetRemoveIpAddressResponse wsResponse = invoke(portName, requestMessage, SetRemoveIpAddressResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetRemoveIpAddressResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}

	/**
	 * @return
	 */
	public final AppWsResult purchaseIpAddress() {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetPurchaseIpAddress");

		SetPurchaseIpAddress input = of.createSetPurchaseIpAddress();

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetPurchaseIpAddress>(portName, SetPurchaseIpAddress.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetPurchaseIpAddressResponse wsResponse = invoke(portName, requestMessage, SetPurchaseIpAddressResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetPurchaseIpAddressResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}

	}


	/**
	 * @param vLanName
	 * @return
	 */
	public final AppWsResult purchaseVlan(String vLanName) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetPurchaseVLan");

		SetPurchaseVLan input = of.createSetPurchaseVLan();
		input.setVLanName( of.createSetPurchaseVLanVLanName(vLanName));

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetPurchaseVLan>(portName, SetPurchaseVLan.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetPurchaseVLanResponse wsResponse = invoke(portName, requestMessage, SetPurchaseVLanResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetPurchaseVLanResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}

	/**
	 * @param serverId
	 * @return
	 */
	public final AppWsResult setEnqueueServerStart(Integer serverId) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetEnqueueServerStart");

		SetEnqueueServerStart input = of.createSetEnqueueServerStart();
		input.setServerId(serverId);

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetEnqueueServerStart>(portName, SetEnqueueServerStart.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetEnqueueServerStartResponse wsResponse = invoke(portName, requestMessage, SetEnqueueServerStartResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetEnqueueServerStartResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}

	}

	/**
	 * @param serverId
	 * @return
	 */
	public final AppWsResult setEnqueueServerRestart(Integer serverId) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetEnqueueServerRestart");

		SetEnqueueServerRestart input = of.createSetEnqueueServerRestart();
		input.setServerId(serverId);

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetEnqueueServerRestart>(portName, SetEnqueueServerRestart.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetEnqueueServerRestartResponse wsResponse = invoke(portName, requestMessage, SetEnqueueServerRestartResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetEnqueueServerRestartResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}

	}


	/**
	 * @param serverId
	 * @return
	 */
	public final AppWsResult setEnqueueServerStop(Integer serverId) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetEnqueueServerStop");

		SetEnqueueServerStop input = of.createSetEnqueueServerStop();
		input.setServerId(serverId);

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetEnqueueServerStop>(portName, SetEnqueueServerStop.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetEnqueueServerStopResponse wsResponse = invoke(portName, requestMessage, SetEnqueueServerStopResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetEnqueueServerStopResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}

	/**
	 * @param serverId
	 * @return
	 */
	public final AppWsResult setEnqueueServerPowerOff(Integer serverId) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetEnqueueServerPowerOff");

		SetEnqueueServerPowerOff input = of.createSetEnqueueServerPowerOff();
		input.setServerId(serverId);

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetEnqueueServerPowerOff>(portName, SetEnqueueServerPowerOff.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetEnqueueServerPowerOffResponse wsResponse = invoke(portName, requestMessage, SetEnqueueServerPowerOffResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetEnqueueServerPowerOffResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}


	/**
	 * @param serverId
	 * @return
	 */
	public final AppWsResult setEnqueueServerReset(Integer serverId) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetEnqueueServerReset");

		SetEnqueueServerReset input = of.createSetEnqueueServerReset();
		input.setServerId(serverId);

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetEnqueueServerReset>(portName, SetEnqueueServerReset.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetEnqueueServerResetResponse wsResponse = invoke(portName, requestMessage, SetEnqueueServerResetResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetEnqueueServerResetResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}


	/**
	 * @param serverId
	 * @return
	 */
	public final AppWsResult setEnqueueServerArchiviation(Integer serverId) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetEnqueueServerArchiviation");

		SetEnqueueServerArchiviation input = of.createSetEnqueueServerArchiviation();
		input.setServerId(serverId);

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetEnqueueServerArchiviation>(portName, SetEnqueueServerArchiviation.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetEnqueueServerArchiviationResponse wsResponse = invoke(portName, requestMessage, SetEnqueueServerArchiviationResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetEnqueueServerArchiviationResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}

	}


	/**
	 * @param serverId
	 * @param cpu
	 * @param ram
	 * @return
	 */
	public final AppWsResult setEnqueueServerRestore(Integer serverId, Integer cpu, Integer ram) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetEnqueueServerRestore");

		SetEnqueueServerRestore input = of.createSetEnqueueServerRestore();
		ServerRestore sr = new ServerRestore();
		sr.setServerId(serverId);
		sr.setCPUQuantity(cpu);
		sr.setRAMQuantity(ram);
		input.setServer( of.createSetEnqueueServerRestoreServer(sr));

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetEnqueueServerRestore>(portName, SetEnqueueServerRestore.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetEnqueueServerRestoreResponse wsResponse = invoke(portName, requestMessage, SetEnqueueServerRestoreResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetEnqueueServerRestoreResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}


	/**
	 * @param serverId
	 * @param CPUQuantity
	 * @param RAMQuantity
	 * @param vDisks
	 * @param RestartAfterExecuted
	 * @return
	 */
	public final AppWsResult setEnqueueServerUpdate(Integer serverId, Integer CPUQuantity, Integer RAMQuantity, List<AppVirtualDisk> vDisks, Boolean restartAfterExecuted) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetEnqueueServerUpdate");

		SetEnqueueServerUpdate input = of.createSetEnqueueServerUpdate();
		ServerUpdate su = new ServerUpdate();
		su.setServerId(serverId);
		su.setCPUQuantity( CPUQuantity != null ? of.createServerUpdateCPUQuantity(CPUQuantity) : of.createServerUpdateCPUQuantity(1));
		su.setRAMQuantity( RAMQuantity != null ? of.createServerUpdateRAMQuantity(RAMQuantity) : of.createServerUpdateRAMQuantity(1));
		su.setRestartAfterExecuted( (restartAfterExecuted != null ? restartAfterExecuted : false) );

		ArrayOfVirtualDiskUpdate aovdu = new ArrayOfVirtualDiskUpdate();
		for(AppVirtualDisk appVD : vDisks) {
			VirtualDiskUpdate vdu = of.createVirtualDiskUpdate();
			vdu.setSize(appVD.getSize());
			vdu.setVirtualDiskUpdateType(VirtualDiskOperationTypes.RESIZE);
			aovdu.getVirtualDiskUpdate().add(vdu);
		}
		su.setVirtualDisks( of.createServerUpdateVirtualDisks(aovdu));
		input.setServer( of.createServerUpdate(su));

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetEnqueueServerUpdate>(portName, SetEnqueueServerUpdate.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetEnqueueServerUpdateResponse wsResponse = invoke(portName, requestMessage, SetEnqueueServerUpdateResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetEnqueueServerUpdateResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}


	/**
	 * @param serverId
	 * @param CPUQuantity
	 * @param restartAfterExecuted
	 * @return
	 */
	public final AppWsResult setEnqueueServerUpdateCPU(Integer serverId, Integer CPUQuantity, Boolean restartAfterExecuted) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetEnqueueServerUpdate");

		SetEnqueueServerUpdate input = of.createSetEnqueueServerUpdate();
		ServerUpdate su = of.createServerUpdate();
		su.setServerId(serverId);
		su.setCPUQuantity( CPUQuantity != null ? of.createServerUpdateCPUQuantity(CPUQuantity) : of.createServerUpdateCPUQuantity(1));
		su.setRAMQuantity(of.createServerUpdateRAMQuantity(null));
		su.setRestartAfterExecuted( (restartAfterExecuted != null ? restartAfterExecuted : false) );

		ArrayOfVirtualDiskUpdate arrayOfVirtualDiskUpdate = of.createArrayOfVirtualDiskUpdate();
		su.setVirtualDisks( of.createServerUpdateVirtualDisks(arrayOfVirtualDiskUpdate));
		input.setServer(of.createSetEnqueueServerUpdateServer(su));


		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetEnqueueServerUpdate>(portName, SetEnqueueServerUpdate.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetEnqueueServerUpdateResponse wsResponse = invoke(portName, requestMessage, SetEnqueueServerUpdateResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetEnqueueServerUpdateResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}

	/**
	 * @param serverId
	 * @param RAMQuantity
	 * @param restartAfterExecuted
	 * @return
	 */
	public final AppWsResult setEnqueueServerUpdateRAM(Integer serverId, Integer RAMQuantity, Boolean restartAfterExecuted) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetEnqueueServerUpdate");

		SetEnqueueServerUpdate input = of.createSetEnqueueServerUpdate();
		ServerUpdate su = of.createServerUpdate();
		su.setServerId(serverId);
		su.setRAMQuantity( RAMQuantity != null ? of.createServerUpdateRAMQuantity(RAMQuantity) : of.createServerUpdateRAMQuantity(1));
		su.setCPUQuantity( of.createServerUpdateCPUQuantity(null));
		su.setRestartAfterExecuted( (restartAfterExecuted != null ? restartAfterExecuted : false) );

		ArrayOfVirtualDiskUpdate aovdu =  of.createArrayOfVirtualDiskUpdate();
		su.setVirtualDisks( of.createServerUpdateVirtualDisks(aovdu));
		input.setServer( of.createSetEnqueueServerUpdateServer(su));


		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetEnqueueServerUpdate>(portName, SetEnqueueServerUpdate.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetEnqueueServerUpdateResponse wsResponse = invoke(portName, requestMessage, SetEnqueueServerUpdateResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetEnqueueServerUpdateResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}

	/**
	 * @param serverId
	 * @param diskNumber
	 * @param newSize
	 * @param restartAfterExecuted
	 * @return
	 */
	public final AppWsResult setEnqueueServerUpdateDiskResize(Integer serverId, Integer diskNumber, Integer newSize, Boolean restartAfterExecuted) {
		return setEnqueueServerUpdateDiskModify(serverId, diskNumber, newSize, restartAfterExecuted, VirtualDiskOperationTypes.RESIZE);
	}

	/**
	 * @param serverId
	 * @param diskNumber
	 * @param newSize
	 * @param restartAfterExecuted
	 * @return
	 */
	public final AppWsResult setEnqueueServerUpdateDiskCreate(Integer serverId, Integer diskNumber, Integer newSize, Boolean restartAfterExecuted) {
		return setEnqueueServerUpdateDiskModify(serverId, diskNumber, newSize, restartAfterExecuted, VirtualDiskOperationTypes.CREATE);
	}

	/**
	 * @param serverId
	 * @param diskNumber
	 * @param restartAfterExecuted
	 * @return
	 */
	public final AppWsResult setEnqueueServerUpdateDiskDelete(Integer serverId, Integer diskNumber, Boolean restartAfterExecuted) {
		return setEnqueueServerUpdateDiskModify(serverId, diskNumber, null, restartAfterExecuted, VirtualDiskOperationTypes.DELETE);
	}

	/**
	 * @param serverId
	 * @param diskNumber
	 * @param newSize
	 * @param restartAfterExecuted
	 * @param opType
	 * @return
	 */
	private final AppWsResult setEnqueueServerUpdateDiskModify(Integer serverId, Integer diskNumber, Integer newSize, Boolean restartAfterExecuted, VirtualDiskOperationTypes opType) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetEnqueueServerUpdate");

		SetEnqueueServerUpdate input = of.createSetEnqueueServerUpdate();
		ServerUpdate su = of.createServerUpdate();
		su.setServerId(serverId);
		su.setRestartAfterExecuted( (restartAfterExecuted != null ? restartAfterExecuted : false) );

		ArrayOfVirtualDiskUpdate aovdu = of.createArrayOfVirtualDiskUpdate();
		if (diskNumber != null) {
			VirtualDiskUpdate vdu = of.createVirtualDiskUpdate();
			if (newSize != null) {
				vdu.setSize(newSize);
			}
			vdu.setVirtualDiskUpdateType(opType); // <<---
			switch (diskNumber) {
			case 0:
				vdu.setVirtualDiskType(VirtualDiskTypes.PRIMARY_VIRTUAL_DISK);
				break;
			case 1:
				vdu.setVirtualDiskType(VirtualDiskTypes.ADDITIONAL_VIRTUAL_DISK_1);
				break;
			case 2:
				vdu.setVirtualDiskType(VirtualDiskTypes.ADDITIONAL_VIRTUAL_DISK_2);
				break;
			case 3:
				vdu.setVirtualDiskType(VirtualDiskTypes.ADDITIONAL_VIRTUAL_DISK_3);
				break;

			default:
				vdu.setVirtualDiskType(VirtualDiskTypes.PRIMARY_VIRTUAL_DISK);
				break;
			}
			aovdu.getVirtualDiskUpdate().add(vdu);
		}
		su.setVirtualDisks( of.createServerUpdateVirtualDisks(aovdu));
		input.setServer( of.createSetEnqueueServerUpdateServer(su));

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetEnqueueServerUpdate>(portName, SetEnqueueServerUpdate.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetEnqueueServerUpdateResponse wsResponse = invoke(portName, requestMessage, SetEnqueueServerUpdateResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetEnqueueServerUpdateResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}



	/**
	 * @param appNewServer
	 * @return
	 */
	public final AppWsResult setEnqueueServerCreation(AppNewServer appNewServer) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetEnqueueServerCreation");

		SetEnqueueServerCreation input = of.createSetEnqueueServerCreation();

		ArrayOfVirtualDiskDetails vDisks = of.createArrayOfVirtualDiskDetails();
		for(AppVirtualDiskDetails appVD : appNewServer.getVirtualDisks()) {
			VirtualDiskDetails vdd = of.createVirtualDiskDetails();
			vdd.setSize(appVD.getSize());
			vdd.setVirtualDiskType( appVD.getVirtualDiskType());
			vDisks.getVirtualDiskDetails().add(vdd);
		}

		ArrayOfNetworkAdapterConfiguration naConfigs = of.createArrayOfNetworkAdapterConfiguration();
		for (AppNetworkAdapterConfiguration appNAC : appNewServer.getNetworkAdaptersConfiguration()) {

			NetworkAdapterConfiguration nac = of.createNetworkAdapterConfiguration();
			nac.setNetworkAdapterType( appNAC.getNetworkAdapterType() );

			if (appNAC.getPrivateVLan() != null) {

				PrivateVLanDetails pvd = of.createPrivateVLanDetails();//  new PrivateVLanDetails();
				pvd.setPrivateVLanResourceId(appNAC.getPrivateVLan().getPrivateVLanResourceId());
				pvd.setIPAddress( of.createPrivateVLanDetailsIPAddress( appNAC.getPrivateVLan().getIPAddress() ));
				pvd.setSubNetMask(of.createPrivateVLanDetailsSubNetMask( appNAC.getPrivateVLan().getSubNetMask()));
				pvd.setGateway(of.createPrivateVLanDetailsGateway(null));
//				if (appNAC.getPrivateVLan().getGateway() != null) {
//					pvd.setGateway( of.createPrivateVLanDetailsGateway( appNAC.getPrivateVLan().getGateway() )); // not used anymore..
//				}
				// setting private-lan details...
				nac.setPrivateVLan( of.createNetworkAdapterConfigurationPrivateVLan(pvd));
			}

			if (appNAC.getPublicIpAddresses() != null && appNAC.getPublicIpAddresses().size() > 0) {

				ArrayOfPublicIpAddressDetails pias = of.createArrayOfPublicIpAddressDetails();

				for(AppPublicIpAddressDetails appPIA  : appNAC.getPublicIpAddresses()) {

					PublicIpAddressDetails piad = of.createPublicIpAddressDetails();
					piad.setPrimaryIPAddress( appPIA.isPrimaryIPAddress());
					piad.setPublicIpAddressResourceId( appPIA.getPublicIpAddressResourceId());

					pias.getPublicIpAddressDetails().add(piad);
				}
				// setting public-ips..
				nac.setPublicIpAddresses( of.createNetworkAdapterConfigurationPublicIpAddresses(pias));
			}
			// setting network-adapter-configuration
			naConfigs.getNetworkAdapterConfiguration().add(nac);
		}

		NewServer ns = of.createNewServer();
		ns.setAdministratorPassword(  of.createNewServerAdministratorPassword(appNewServer.getAdministratorPassword()) );
		ns.setName( of.createNewServerName(appNewServer.getName()));
		ns.setNetworkAdaptersConfiguration( of.createNewServerNetworkAdaptersConfiguration(naConfigs));
		ns.setVirtualDisks( of.createNewServerVirtualDisks(vDisks));
		ns.setOSTemplateId( appNewServer.getOSTemplateId());
		ns.setRAMQuantity(appNewServer.getRAMQuantity());
		ns.setCPUQuantity(appNewServer.getCPUQuantity());
		//ns.setNote()

		input.setServer( of.createSetEnqueueServerCreationServer(ns));

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetEnqueueServerCreation>(portName, SetEnqueueServerCreation.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetEnqueueServerCreationResponse wsResponse = invoke(portName, requestMessage, SetEnqueueServerCreationResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetEnqueueServerCreationResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}

	public final AppWsResult setEnqueueServerDeletion(Integer serverId) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetEnqueueServerDeletion");

		SetEnqueueServerDeletion input = of.createSetEnqueueServerDeletion();
		input.setServerId(serverId);

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetEnqueueServerDeletion>(portName, SetEnqueueServerDeletion.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetEnqueueServerDeletionResponse wsResponse = invoke(portName, requestMessage, SetEnqueueServerDeletionResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetEnqueueServerDeletionResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}


	public final AppWsResult setEnqueueAssociateVLan(Integer vLanResourceId, Integer networkAdapterId) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetEnqueueAssociateVLan");

		SetEnqueueAssociateVLan input = of.createSetEnqueueAssociateVLan();
		input.setVLanResourceId(vLanResourceId);
		input.setNetworkAdapterId(networkAdapterId);

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetEnqueueAssociateVLan>(portName, SetEnqueueAssociateVLan.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetEnqueueAssociateVLanResponse wsResponse = invoke(portName, requestMessage, SetEnqueueAssociateVLanResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetEnqueueAssociateVLanResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}


	public final AppWsResult setEnqueueDeassociateVLan(Integer vLanResourceId, Integer networkAdapterId) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetEnqueueDeassociateVLan");

		SetEnqueueDeassociateVLan input = of.createSetEnqueueDeassociateVLan();
		input.setVLanResourceId(vLanResourceId);
		input.setNetworkAdapterId(networkAdapterId);

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetEnqueueDeassociateVLan>(portName, SetEnqueueDeassociateVLan.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetEnqueueDeassociateVLanResponse wsResponse = invoke(portName, requestMessage, SetEnqueueDeassociateVLanResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetEnqueueDeassociateVLanResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}


	public final AppWsResult setEnqueueAssociateIpAddress(List<Integer> ipAddressResourceIds, Integer networkAdapterId) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetEnqueueAssociateIpAddress");

		SetEnqueueAssociateIpAddress input = of.createSetEnqueueAssociateIpAddress();
		input.setNetworkAdapterId(networkAdapterId);
		ArrayOfint aoint = of.createArrayOfint();
		for(Integer ipid : ipAddressResourceIds) {
			aoint.getInt().add(ipid);
		}
		input.setIpAddressResourceIds( of.createSetEnqueueAssociateIpAddressIpAddressResourceIds(aoint));


		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetEnqueueAssociateIpAddress>(portName, SetEnqueueAssociateIpAddress.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetEnqueueAssociateIpAddressResponse wsResponse = invoke(portName, requestMessage, SetEnqueueAssociateIpAddressResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetEnqueueAssociateIpAddressResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}


	public final AppWsResult setEnqueueDeassociateIpAddress(List<Integer> ipAddressResourceIds, Integer networkAdapterId) {

		ObjectFactory of = new ObjectFactory();
		QName portName =  new QName(getServiceNamespace(), "SetEnqueueDeassociateIpAddress");

		SetEnqueueDeassociateIpAddress input = of.createSetEnqueueDeassociateIpAddress();
		input.setNetworkAdapterId(networkAdapterId);
		ArrayOfint aoint = of.createArrayOfint();
		for(Integer ipid : ipAddressResourceIds) {
			aoint.getInt().add(ipid);
		}
		input.setIpAddressResourceIds( of.createSetEnqueueDeassociateIpAddressIpAddressResourceIds(aoint));

		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setBody(new JAXBElement<SetEnqueueDeassociateIpAddress>(portName, SetEnqueueDeassociateIpAddress.class, input));
		requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());

		WsResult value = null;
		try {
			SetEnqueueDeassociateIpAddressResponse wsResponse = invoke(portName, requestMessage, SetEnqueueDeassociateIpAddressResponse.class);

			//checkResponse(..) if necessary
			value = (WsResult) wsResponse.getSetEnqueueDeassociateIpAddressResult().getValue();
		}
		catch (Throwable t) {
			invalidateCredentials();
		}
		if (value != null) {
			return new AppWsResult(value);
		}
		else {
			throw new WebServiceInvocationException();
		}
	}


	public String getDatacenterName(Integer loginDatacenterId) {
		WsEndUserVDCConfigClient client = new WsEndUserVDCConfigClient(getServiceNamespace(), getServiceName(), getServiceEndPoint());
		copyCredentials(client);

		List<AppDatacenterConfig> list= client.getEnabledDatacenterConfigurations();
		if(list!=null){
			for (AppDatacenterConfig appDatacenterConfig : list) {
				if(appDatacenterConfig.getDatacenterId().equals(loginDatacenterId)){
					return appDatacenterConfig.getDatacenterDescription();
				}
			}
		}

		return null;
	}

	/* ===================================================================
	 * 'Set' Method template
	 * =================================================================== */
	//	ObjectFactory of = new ObjectFactory();
	//	QName portName =  new QName(getServiceNamespace(), "SetKKKK");
	//
	//	SetKKKK input = of.createSetKKKK();
	//	//input.set();
	//
	//	RequestMessage requestMessage = new RequestMessage();
	//	requestMessage.setBody(new JAXBElement<SetKKKK>(portName, SetKKKK.class, input));
	//	requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());
	//
	//	WsResult value = null;
	//	try {
	//		SetKKKKResponse wsResponse = invoke(portName, requestMessage, SetKKKKResponse.class);
	//
	//		//checkResponse(..) if necessary
	//		value = (WsResult) wsResponse.getSetKKKKResult().getValue();
	//	}
	//	catch (Throwable t) {
	//		invalidateCredentials();
	//	}
	//	if (value != null) {
	//		return new AppWsResult(value);
	//	}
	//	else {
	//		throw new WebServiceInvocationException();
	//	}


	/* ===================================================================
	 * 'Get' method template
	 * =================================================================== */
	//	ObjectFactory of = new ObjectFactory();
	//	QName portName =  new QName(getServiceNamespace(), "GetXXXX");
	//	GetXXXX input = of.createGetXXXX();
	//	//input.set();
	//	RequestMessage requestMessage = new RequestMessage();
	//	requestMessage.setBody(new JAXBElement<GetXXXX>(portName, GetXXXX.class, input));
	//	requestMessage.setSoapAction(getServiceNamespace() + "/IWsEndUser/" + portName.getLocalPart());
	//
	//	XXXX value = null;
	//	try {
	//		GetXXXXResponse wsResponse = invoke(portName, requestMessage, GetXXXXResponse.class);
	//
	//		//checkResponse(..) if necessary
	//		value = (XXXX) wsResponse.getGetXXXXResult().getValue().getValue().getValue();
	//	}
	//	catch (Throwable t) {
	//		invalidateCredentials();
	//	}
	//	if (value != null) {
	//		return new AppXXXX(value);
	//	}
	//	else {
	//		throw new WebServiceInvocationException();
	//	}


}
