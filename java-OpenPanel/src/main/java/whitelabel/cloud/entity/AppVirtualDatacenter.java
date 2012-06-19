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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import whitelabel.cloud.remotews.jaxb.entity.ArrayOfIPAddress;
import whitelabel.cloud.remotews.jaxb.entity.ArrayOfServerDetails;
import whitelabel.cloud.remotews.jaxb.entity.ArrayOfVLan;
import whitelabel.cloud.remotews.jaxb.entity.IPAddress;
import whitelabel.cloud.remotews.jaxb.entity.ServerDetails;
import whitelabel.cloud.remotews.jaxb.entity.VLan;
import whitelabel.cloud.remotews.jaxb.entity.VirtualDatacenter;


/**
 * @author luca
 *
 */
public class AppVirtualDatacenter {

    private Integer datacenterId;
    private List<AppIPAddress> ipAddresses;
    private List<AppServerDetails> servers;
    private List<AppVLan> vLans;
    //private JAXBElement<FTP> ftp;


    /**
     * @param vdc
     */
    public AppVirtualDatacenter(VirtualDatacenter vdc) {
    	if (vdc != null) {
    		setDatacenterId(vdc.getDatacenterId());
    		if (vdc.getServers() != null) {
    			setServers(vdc.getServers().getValue());
    		}
    		if (vdc.getIpAddresses() != null) {
    			setIpAddresses(vdc.getIpAddresses().getValue());
    		}
    		if (vdc.getVLans() != null) {
    			setVLans(vdc.getVLans().getValue());
    		}
    	}
    }

    public Integer getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(Integer value) {
        this.datacenterId = value;
    }

//    public JAXBElement<FTP> getFTP() {
//        return ftp;
//    }
//
//    public void setFTP(JAXBElement<FTP> value) {
//        this.ftp = ((JAXBElement<FTP> ) value);
//    }

    public List<AppIPAddress> getIpAddresses() {
        return ipAddresses;
    }

    private void setIpAddresses(ArrayOfIPAddress value) {
    	if (value != null) {
    		this.ipAddresses = new ArrayList<AppIPAddress>();
    		for (IPAddress ipa : value.getIPAddress()) {
    			AppIPAddress aip = new AppIPAddress(ipa);
    			AppServerDetails asd = getAppServerById(aip.getServerId());
    			if(asd!=null){
    				aip.setServerName(asd.getName());
    			}
    			this.ipAddresses.add(aip);
    		}
    	}
    }

    public List<AppServerDetails> getServers() {
        return servers;
    }

    private void setServers(ArrayOfServerDetails value) {
    	if (value != null) {
    		this.servers = new ArrayList<AppServerDetails>();
    		for (ServerDetails sd : value.getServerDetails()) {
    			this.servers.add(new AppServerDetails(sd));
    		}
    	}
    }

    public List<AppVLan> getVLans() {
        return vLans;
    }

    private void setVLans(ArrayOfVLan value) {
    	if (value != null) {
    		this.vLans = new ArrayList<AppVLan>();
    		for(VLan vl : value.getVLan()) {
    			AppVLan avl = new AppVLan(vl);
    			List<AppServerDetails> appServers = getAppServersById(avl.getServerIds());
    			if(appServers!=null){
    				StringBuilder names=new StringBuilder();
    				for (Iterator<AppServerDetails> iterator = appServers.iterator(); iterator.hasNext();) {
						AppServerDetails appServerDetails = iterator.next();
						names.append(appServerDetails.getName());
						if(iterator.hasNext()){
							names.append(", ");
						}
					}
    				avl.setServerNames(names.toString());
    			}
    			this.vLans.add(avl);
    		}
    	}
    }

    public List<AppServerDetails> getAppServersById(List<Integer> ids){
    	if(ids==null || ids.size()==0){
    		return new ArrayList<AppServerDetails>();
    	}
    	List<AppServerDetails> response = new ArrayList<AppServerDetails>();
    	if(getServers()!=null){
    		Set<Integer> needed = new TreeSet<Integer>(ids);
	    	for (AppServerDetails appSrvDetail : getServers()) {
				Integer id = appSrvDetail.getServerId();
				if(needed.contains(id)){
					response.add(appSrvDetail);
				}
			}
    	}
    	return response;
    }

    public AppServerDetails getAppServerById(Integer serverId){
    	if(serverId!=null){
    		List<AppServerDetails> tmp = getAppServersById(Arrays.asList(serverId));
    		if(tmp!=null && tmp.size()>0){
    			return tmp.get(0);
    		}
    	}
    	return null;

    }



}
