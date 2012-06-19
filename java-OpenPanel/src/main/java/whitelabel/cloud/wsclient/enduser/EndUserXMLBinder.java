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
package whitelabel.cloud.wsclient.enduser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import whitelabel.cloud.wsclient.JaxbXMLBinder;



/**
 * @author luca
 *
 */
public class EndUserXMLBinder implements JaxbXMLBinder {

	public final static String JAXB_ENTITIES_PACKAGE = "whitelabel.cloud.remotews.jaxb.entity";
	
	private Logger logger = Logger.getLogger(getClass());

    private JAXBContext jaxbContext = null;
    private DocumentBuilderFactory dBuilderFactory = null;
    
    
    public EndUserXMLBinder() {
    	super();
    	jaxbInit();
    }
    
    
    private final void jaxbInit() {
        try {
            jaxbContext = JAXBContext.newInstance(JAXB_ENTITIES_PACKAGE);
        } catch (Throwable t) {
            logger.error("Errore inizializzazione JAXBContext per Marshalling.", t);
        }
        try {
            dBuilderFactory = DocumentBuilderFactory.newInstance();
            dBuilderFactory.setNamespaceAware(true);
        } catch (Exception e) {
            logger.error("Initialization error in DocumentBuilderFactory (jaxbInit()).", e);
        }
    }
    
	@Override
	public Document marshall(JAXBElement<?> elemento) throws ParserConfigurationException, JAXBException {
		
		 if (jaxbContext == null) {
	            throw new JAXBException("JAXB context not initialized.");
	        }

	        Document doc = (dBuilderFactory.newDocumentBuilder()).newDocument();

	        Marshaller marshaller = jaxbContext.createMarshaller();
	        marshaller.marshal(elemento, doc);
	        return doc;
	}

	@Override
	public Object unmarshall(Node node) throws ParserConfigurationException, JAXBException {
		
		if (jaxbContext == null) {
            throw new JAXBException("JAXB context not initialized.");
        }

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return unmarshaller.unmarshal(node);
	}

}
