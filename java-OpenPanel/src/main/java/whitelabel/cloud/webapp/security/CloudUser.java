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
package whitelabel.cloud.webapp.security;

import whitelabel.cloud.entity.VDCResourceBoundsConfig;
import whitelabel.cloud.wsclient.enduser.WsEndUserClient;
import whitelabel.cloud.wsclient.enduser.WsEndUserVDCConfigClient;

public class CloudUser implements Authenticable {

	public final static String SESSION_NAME = "webUser";

	/***/
	private static final long serialVersionUID = 3693166181496091422L;


	private String username;
	private String password;
	private transient VDCResourceBoundsConfig vdcResourceBoundConfig;
	private Integer loginDatacenterId;
	private String dataCenterName=null;

	private transient WsEndUserClient wsEndUser;


	/**
	 * @param username
	 * @param password
	 */
	public CloudUser(String username, String password, Integer datacenterId) {
		this.username = username;
		this.password = password;
		this.loginDatacenterId=datacenterId;
	}


	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}


	@Override
	public boolean isEnabled() {
		return true;
	}


	public WsEndUserClient getWsEndUser() {
		return wsEndUser;
	}

	public void setWsEndUser(WsEndUserClient wsEndUser) {
		this.wsEndUser = wsEndUser;
	}


	protected final Integer getLoginDatacenterId() {
		return loginDatacenterId;
	}


	public VDCResourceBoundsConfig getVdcResourceBoundConfig() {
		return vdcResourceBoundConfig;
	}


	public void setVdcResourceBoundConfig(VDCResourceBoundsConfig vdcResourceBoundConfig) {
		this.vdcResourceBoundConfig = vdcResourceBoundConfig;
	}


	public String getDatacenterName(){
		if(dataCenterName==null && wsEndUser!=null){
			dataCenterName = wsEndUser.getDatacenterName(getLoginDatacenterId());
		}
		return dataCenterName;
	}




}
