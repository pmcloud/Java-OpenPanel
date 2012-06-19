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
package whitelabel.cloud.exception;

import javax.xml.ws.WebServiceException;

/**
 * Eccezione usata per gestire tutti gli errori di chiamata a WS esterni al sistema
 *
 * @author luca
 *
 */
public class WebServiceInvocationException extends WebServiceException {

    public WebServiceInvocationException() {
        super();
    }

    public WebServiceInvocationException(String msg, Throwable arg1) {
        super(msg, arg1);
    }

    public WebServiceInvocationException(String msg) {
        super(msg);
    }

    public WebServiceInvocationException(Throwable arg0) {
        super(arg0);
    }


    private static final long serialVersionUID = -7079727746007575331L;

    private String errorCode;
    private String errorDesc;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    @Override
    public String getMessage() {
        StringBuffer result = new StringBuffer();
        if(getErrorCode()!=null){
            result.append("[ErrorNum:").append(getErrorCode()).append("] ");
        }
        if(getErrorDesc()!=null){
            result.append("[ErrorDesc:").append(getErrorDesc()).append("] ");
        }
        if(super.getMessage()!=null){
            result.append(super.getMessage());
        }
        return result.toString();
    }

}
