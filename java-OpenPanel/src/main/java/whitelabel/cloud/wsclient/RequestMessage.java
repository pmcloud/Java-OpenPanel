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

import javax.xml.bind.JAXBElement;

public class RequestMessage {


    private String soapAction = null;
    private JAXBElement<?> body = null;
    private JAXBElement<?> header = null;

    
    public RequestMessage() {
    	super();
    }
    
    public RequestMessage(String soapAction, JAXBElement<?> body, JAXBElement<?> header) {
        super();
        this.soapAction = soapAction;
        this.body = body;
        this.header = header;
    }

    public JAXBElement<?> getBody() {
        return body;
    }
    public void setBody(JAXBElement<?> body) {
        this.body = body;
    }
    public JAXBElement<?> getHeader() {
        return header;
    }
    public void setHeader(JAXBElement<?> header) {
        this.header = header;
    }

    public String getSoapAction() {
        return soapAction;
    }

    public void setSoapAction(String soapAction) {
        this.soapAction = soapAction;
    }




}
