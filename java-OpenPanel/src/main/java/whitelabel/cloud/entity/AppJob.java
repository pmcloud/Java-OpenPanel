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
import whitelabel.cloud.remotews.jaxb.entity.Job;
import whitelabel.cloud.remotews.jaxb.entity.JobStatus;


/**
 * @author luca
 *
 */
public class AppJob {

    protected XMLGregorianCalendar creationDate;
    protected Integer jobId;
    protected XMLGregorianCalendar lastUpdateDate;
    protected String operationName;
    protected Integer progress;
    protected Integer resourceId;
    protected String resourceValue;
    protected Integer serverId;
    protected String serverName;
    protected JobStatus status;
    protected Integer userId;
    protected String username;

    
    public AppJob(Job job) {
    	if (job != null) {
    		setCreationDate(job.getCreationDate());
    		setJobId(job.getJobId());
    		setLastUpdateDate(job.getLastUpdateDate());
    		setOperationName(job.getOperationName().getValue());
    		setProgress(job.getProgress());
    		setResourceId(job.getResourceId().getValue());
    		setResourceValue(job.getResourceValue().getValue());
    		setServerId(job.getServerId().getValue());
    		setServerName(job.getServerName().getValue());
    		setStatus(job.getStatus());
    		setUserId(job.getUserId());
    		setUsername(job.getUsername().getValue());
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
    
    public final boolean isInErrorState() {
    	return JobStatus.ERROR.equals(getStatus());
    }
    
    
    public final String getStartDatetime() {    	
    	XMLGregorianCalendar xgc = getCreationDate();
    	if (xgc != null) {
	    	DateFormatterExt dfe = new DateFormatterExt(DateFormatterExt.DATE_TIME_PATTERN);
	    	return dfe.print( xgc.toGregorianCalendar().getTime());
    	}
    	return "---";
    }
    
    public final String getLastUpdateDatetime() {
    	XMLGregorianCalendar xgc = getLastUpdateDate();
    	if (xgc != null) {
	    	DateFormatterExt dfe = new DateFormatterExt(DateFormatterExt.DATE_TIME_PATTERN);
	    	return dfe.print( xgc.toGregorianCalendar().getTime());
    	}
    	return "---";
    	
    }
    
    public XMLGregorianCalendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(XMLGregorianCalendar value) {
        this.creationDate = value;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer value) {
        this.jobId = value;
    }

    public XMLGregorianCalendar getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(XMLGregorianCalendar value) {
        this.lastUpdateDate = value;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String value) {
        this.operationName = value;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer value) {
        this.progress = value;
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
