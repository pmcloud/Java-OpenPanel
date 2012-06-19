
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

import java.lang.reflect.Method;

import javax.xml.bind.JAXBElement;

import whitelabel.cloud.remotews.jaxb.entity.ExceptionInfo;
import whitelabel.cloud.remotews.jaxb.entity.WsResult;


public class AppWsResult {

    private ExceptionInfo exceptionInfo;
    private Integer resultCode;
    private String resultMessage;
    private Boolean success;
    private Object value;


    public AppWsResult(WsResult wsResult) {
    	if (wsResult != null) {
    		setExceptionInfo(wsResult.getExceptionInfo().getValue());
    		setResultCode(wsResult.getResultCode());
    		setResultMessage(wsResult.getResultMessage().getValue());
    		setSuccess(wsResult.getSuccess());

    		try {
				Method method = (Method) wsResult.getClass().getMethod("getValue", null);
				if(method!=null){
					Object tmpVal = method.invoke(wsResult, null);

					if(tmpVal!=null && tmpVal instanceof JAXBElement<?>){
						 method = (Method) tmpVal.getClass().getMethod("getValue", null);
						 if(method!=null){
							 tmpVal = method.invoke(tmpVal, null);
						 }
					}
					setValue(tmpVal);
				}
			} catch (Exception e) {

			}
    	}
    }

    public ExceptionInfo getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(ExceptionInfo value) {
        this.exceptionInfo = value;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer value) {
        this.resultCode = value;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String value) {
        this.resultMessage = value;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean value) {
        this.success = value;
    }

    public final String getSuccessMessage() {
    	return (isSuccess() ? getResultMessage() : "");
    }

    public final Integer getSuccessCode() {
    	return (isSuccess() ? getResultCode() : null);
    }

    public final String getErrorMessage() {
    	return (!isSuccess() ? getResultMessage() : "");
    }

    public final Integer getErrorCode() {
    	return (!isSuccess() ? getResultCode() : null);
    }

    public final boolean hasException() {
    	return (getExceptionInfo() != null);
    }

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
