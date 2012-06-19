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
package whitelabel.cloud.wsclient;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPBinding;

import whitelabel.cloud.exception.WsAuthenticationException;

/**
 * @author luca - Inspect.it
 *
 */
public class SOAPHeaderAuthenticatedClient extends AbstractWebServiceClient {

	/**
	 * @author luca
	 *
	 */
	public enum AuthenticationMode {
		USERNAME_TOKEN,
		USERNAME_TOKEN_CRYPTO,
		BASIC_BINDING_PROPS;
	}


    private WebServiceAuthenticator authenticator;
    private AuthenticationMode mode;
    private String username = null;
    private String userpass = null;



	/**
	 * @param serviceNameSpace
	 * @param serviceName
	 * @param serviceEndPoint
	 * @param mode
	 * @param binder
	 */
	public SOAPHeaderAuthenticatedClient(String serviceNameSpace, String serviceName, String serviceEndPoint, AuthenticationMode mode, JaxbXMLBinder binder) {
		super();
		this.authenticator = new WebServiceAuthenticator();
		this.mode = mode;
		setBinder(binder);
        setServiceNameSpace(serviceNameSpace);
        setServiceName(serviceName);
        setServiceEndPoint(serviceEndPoint);
        if (serviceEndPoint != null && serviceEndPoint.toLowerCase().endsWith("soap11")) {
        	setProtocol(SOAPProtocol.SOAP11);
        }
        else {
        	setProtocol(SOAPProtocol.SOAP12);
        }
	}

	/**
	 * @param username
	 * @param userpass
	 */
	public final void setCredentials(String username, String userpass) {
		this.username = username;
		this.userpass = userpass;
	}



	/**
	 *
	 */
	public final void invalidateCredentials() throws WsAuthenticationException{
		setCredentials(null, null);
		throw new WsAuthenticationException();
	}

	@Override
	protected SOAPMessage buildMessageRequest(Dispatch<SOAPMessage> messageDispatch, RequestMessage requestMessage) throws WebServiceException {

		if (username == null || userpass == null) {
			throw new WebServiceException("INVALID_PARAMETER_FOR_AUTHENTICATION");
		}

        MessageFactory messageFactory = ((SOAPBinding) messageDispatch.getBinding()).getMessageFactory();
        SOAPMessage request = null;
		try {
			request = messageFactory.createMessage();
	        if (requestMessage.getBody() != null) {
	            request.getSOAPBody().addDocument(getBinder().marshall(requestMessage.getBody()));
	        }
	        // we use the 'autjenticator' to fill soap header..
	        switch (mode) {
				case USERNAME_TOKEN:
					authenticator.authenticateInClear(request, username, userpass);
					break;
				case USERNAME_TOKEN_CRYPTO:
					authenticator.authenticateWithDigest(request, username, userpass);
					break;
				case BASIC_BINDING_PROPS:
					//break;
				default:
					authenticator.authenticateBasic(messageDispatch, username, userpass);
					break;
			}
		}
		catch (Throwable t) {
			throw new WebServiceException(t);
		}
		return request;
    }

	public final void copyCredentials(SOAPHeaderAuthenticatedClient toCopy){
		if(toCopy!=null){
			toCopy.username=this.username;
			toCopy.userpass=this.userpass;
		}
	}

}
