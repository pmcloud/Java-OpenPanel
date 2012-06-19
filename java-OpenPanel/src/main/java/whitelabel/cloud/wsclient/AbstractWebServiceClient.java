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

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.log4j.Logger;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.w3c.dom.Document;

import whitelabel.cloud.exception.WebServiceInvocationException;

/**
 * @author inspect.it
 *
 */
public abstract class AbstractWebServiceClient {

    public final static JAXBElement<?> NULL_ELEMENT = null;

    private static final Logger LOG = Logger.getLogger(AbstractWebServiceClient.class);
    private SOAPProtocol protocol = SOAPProtocol.SOAP11;

    private String serviceNameSpace = null;
    private String serviceName = null;
    private String serviceEndPoint = null;    
    private JaxbXMLBinder binder = null;

    /**
     * @author luca
     *
     */
    public enum SOAPProtocol {

        SOAP11(SOAPBinding.SOAP11HTTP_BINDING), SOAP12(SOAPBinding.SOAP12HTTP_BINDING);

        private String value;

        private SOAPProtocol(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    // ------------------------------------------------------

    protected Dispatch<SOAPMessage> buildDispatcher(QName portName, RequestMessage requestMessage) {
    	
    	 Service service = Service.create(getServiceQualifiedName());
         service.addPort(portName, protocol.getValue(), getServiceEndPoint());

         Dispatch<SOAPMessage> messageDispatch = service.createDispatch(portName, SOAPMessage.class, Service.Mode.MESSAGE);
         if (requestMessage.getSoapAction() != null && requestMessage.getSoapAction().trim().length() > 0) {
        	 messageDispatch.getRequestContext().put(BindingProvider.SOAPACTION_USE_PROPERTY, true);
             messageDispatch.getRequestContext().put(BindingProvider.SOAPACTION_URI_PROPERTY, requestMessage.getSoapAction());
         }
         return messageDispatch;
    }
    
    protected SOAPMessage buildMessageRequest(Dispatch<SOAPMessage> messageDispatch, RequestMessage requestMessage) throws WebServiceException {
    	    	        
        MessageFactory messageFactory = ((SOAPBinding) messageDispatch.getBinding()).getMessageFactory();
        SOAPMessage request = null;
		try {
			request = messageFactory.createMessage();
	        if (requestMessage.getBody() != null) {
	            request.getSOAPBody().addDocument(getBinder().marshall(requestMessage.getBody()));
	        }        
	        if (requestMessage.getHeader() != null) {
	            Document doc = getBinder().marshall(requestMessage.getHeader());
	            doc.normalizeDocument();
	            SOAPElement soapElement = SOAPFactory.newInstance().createElement(doc.getDocumentElement());
	            request.getSOAPHeader().addChildElement(soapElement);
	        }	        
		} 
		catch (Throwable t) {
			throw new WebServiceException(t);			
		}		
		return request;
    }
    
    
    /**
     * @param <RESPONSE>
     * @param portName
     * @param requestMessage
     * @param responseClass
     * @return
     * @throws WebServiceInvocationException
     */
    protected final <RESPONSE> RESPONSE invoke(QName portName, RequestMessage requestMessage, Class<RESPONSE> responseClass) throws WebServiceInvocationException {

    	if (portName == null || requestMessage == null || responseClass == null) {
    		throw new WebServiceInvocationException("INVALID_PARAMETERS");
    	}
    	
        SOAPMessage request = null;
        SOAPMessage response = null;
        Throwable exception = null;
        try {
            Service service = Service.create(getServiceQualifiedName());
            service.addPort(portName, protocol.getValue(), getServiceEndPoint());

            Dispatch<SOAPMessage> messageDispatch = buildDispatcher(portName, requestMessage);            
            request = buildMessageRequest(messageDispatch, requestMessage);                        
            
            response = messageDispatch.invoke(request);
            
            return checkResponse(response, responseClass);
        } 
        catch (WebServiceException t) {
            LOG.error("Impossibile effettuare la chiamata SOAP", t);
            exception = t;
            throw t;
        }
        catch (Throwable t) {
            LOG.error("Impossibile effettuare la chiamata SOAP", t);
            exception = t;
            throw new WebServiceInvocationException("Impossibile effettuare la chiamata SOAP", t);
        } 
        finally {
            logSOAPRequest(request, response,requestMessage, exception);
        }
    }

    /**
     * @param <RESPONSE>
     * @param response
     * @param responseClass
     * @return
     * @throws WebServiceInvocationException
     */
    protected <RESPONSE> RESPONSE checkResponse(SOAPMessage response, Class<RESPONSE> responseClass) throws WebServiceInvocationException {
        try {
            Object resObj = getBinder().unmarshall(response.getSOAPBody().getFirstChild());
            if(resObj instanceof JAXBElement<?>) {
                return (RESPONSE)((JAXBElement<?>)resObj).getValue();
            }
            else{
                return (RESPONSE)(resObj);
            }
        }
        catch (WebServiceInvocationException t) {
            throw t;
        }
        catch (Throwable t) {
            throw new WebServiceInvocationException(t);
        }
    }

    /**
     * @param request
     * @param response
     * @param requestMessage
     * @param exception
     */
    protected void logSOAPRequest(SOAPMessage request, SOAPMessage response, RequestMessage requestMessage, Throwable exception) {
        try {
            if (request != null) {
                LOG.info("--------------------------------REQUEST---------------------------");
                LOG.info(prettyPrinting(request));
                LOG.info("--------------------------------END REQUEST---------------------------");
            }
            if (response != null) {
                LOG.info("--------------------------------RESPONSE---------------------------");
                LOG.info(prettyPrinting(response));
                LOG.info("--------------------------------END RESPONSE---------------------------");
            }
            if (exception != null) {
                LOG.info(exception);
            }
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    /**
     * @param soapMessage
     * @return
     * @throws Exception
     */
    protected String prettyPrinting(SOAPMessage soapMessage) throws Exception {
    	
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        soapMessage.writeTo(baos);
        String msgAsString = new String(baos.toByteArray());

        org.dom4j.Document document = DocumentHelper.parseText(msgAsString);
        OutputFormat prettyFormat = OutputFormat.createPrettyPrint();
        StringWriter prettyString = new StringWriter();
        XMLWriter xmlWriter = new XMLWriter(prettyString, prettyFormat);

        xmlWriter.write(document);

        return prettyString.toString();

    }

    public SOAPProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(SOAPProtocol protocol) {
        this.protocol = protocol;
    }

    
	protected final JaxbXMLBinder getBinder() {
		return this.binder;
	}
	
	protected final String getServiceNamespace() {		
		return this.serviceNameSpace;
	}
	
	protected final String getServiceName() {
		return this.serviceName;
	}
	
	protected final QName getServiceQualifiedName() {		
		return new QName(serviceNameSpace, serviceName);
	}
	
	protected final String getServiceEndPoint() {
		return this.serviceEndPoint;
	}

	protected final void setServiceNameSpace(String serviceNameSpace) {
		this.serviceNameSpace = serviceNameSpace;
	}

	protected final void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	protected final void setServiceEndPoint(String serviceEndPoint) {
		this.serviceEndPoint = serviceEndPoint;
	}

	protected final void setBinder(JaxbXMLBinder binder) {
		this.binder = binder;
	}

}
