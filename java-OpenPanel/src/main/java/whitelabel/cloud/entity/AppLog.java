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

import javax.xml.datatype.XMLGregorianCalendar;

import whitelabel.cloud.adapter.DateFormatterExt;
import whitelabel.cloud.remotews.jaxb.entity.JobStatus;
import whitelabel.cloud.remotews.jaxb.entity.Log;


/**
 * @author luca
 *
 */
public class AppLog {

    private Integer jobId;
    private XMLGregorianCalendar logDate;
    private Integer logId;
    private XMLGregorianCalendar logLastUpdateDate;
    private String message;
    private Integer messageId;
    private String operationName;
    private Integer resourceId;
    private String resourceValue;
    private Integer serverId;
    private String serverName;
    private JobStatus status;
    private Integer userId;
    private String username;

    
    public AppLog(Log log) {
    	if (log != null) {
    		setJobId(log.getJobId());
    		setLogId(log.getLogId());
    		setLogDate(log.getLogDate());
    		setLogLastUpdateDate(log.getLogLastUpdateDate());
    		setMessage(log.getMessage().getValue());
    		setMessageId(log.getMessageId().getValue());
    		setOperationName(log.getOperationName().getValue());
    		setResourceId(log.getResourceId().getValue());
    		setResourceValue(log.getResourceValue().getValue());
    		setServerId(log.getServerId().getValue());
    		setServerName(log.getServerName().getValue());
    		setStatus(log.getStatus());
    		setUsername(log.getUsername().getValue());
    	}
    }
    
    public final String getTargetResource() {
    	String resource = getServerName();
    	if (resource == null || resource.length() == 0) {
    		resource = getResourceValue();
    	}
    	if (resource == null ) {
    		resource = "---";
    	}
    	return resource;
    }
        
    
    public final String getStartDatetime() {    	
    	XMLGregorianCalendar xgc = getLogDate();
    	if (xgc != null) {
	    	DateFormatterExt dfe = new DateFormatterExt(DateFormatterExt.DATE_TIME_PATTERN);
	    	return dfe.print( xgc.toGregorianCalendar().getTime());
    	}
    	return "---";
    }
    
    public final String getLastUpdateDatetime() {
    	XMLGregorianCalendar xgc = getLogLastUpdateDate();
    	if (xgc != null) {
	    	DateFormatterExt dfe = new DateFormatterExt(DateFormatterExt.DATE_TIME_PATTERN);
	    	return dfe.print( xgc.toGregorianCalendar().getTime());
    	}
    	return "---";
    	
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer value) {
        this.jobId = value;
    }

    public XMLGregorianCalendar getLogDate() {
        return logDate;
    }

    public void setLogDate(XMLGregorianCalendar value) {
        this.logDate = value;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer value) {
        this.logId = value;
    }

    public XMLGregorianCalendar getLogLastUpdateDate() {
        return logLastUpdateDate;
    }

    public void setLogLastUpdateDate(XMLGregorianCalendar value) {
        this.logLastUpdateDate = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String value) {
        this.message = value;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer value) {
        this.messageId = value;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String value) {
        this.operationName = value;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer value) {
        this.resourceId = value;
    }

    public String getResourceValue() {
        return resourceValue;
    }

    public void setResourceValue(String value) {
        this.resourceValue = value;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer value) {
        this.serverId = value;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String value) {
        this.serverName = value;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus value) {
        this.status = value;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer value) {
        this.userId = value;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String value) {
        this.username = value;
    }

}
