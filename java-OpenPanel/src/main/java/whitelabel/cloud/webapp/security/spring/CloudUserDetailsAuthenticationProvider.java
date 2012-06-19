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
package whitelabel.cloud.webapp.security.spring;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import whitelabel.cloud.entity.AppUserToken;
import whitelabel.cloud.webapp.security.CloudUser;
import whitelabel.cloud.wsclient.enduser.WsEndUserClient;
import whitelabel.cloud.wsclient.enduser.WsEndUserVDCConfigClient;



public class CloudUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

	@Autowired
	@Qualifier("wsEndUserEndpoint")
	private String wsEndUserEndpoint;

	@Autowired
	@Qualifier("wsEndUserNamespace")
	private String wsEndUserNamespace;

	@Autowired
	@Qualifier("wsEndUserServiceName")
	private String wsEndUserServiceName;



    protected final Log LOG = LogFactory.getLog(getClass());


    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }


    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    	CloudWebAutenticationDetails details = ((CloudWebAutenticationDetails) authentication.getDetails());

    	WsEndUserClient wsEndUser = new WsEndUserClient(wsEndUserNamespace, wsEndUserServiceName, details.getDatacenterUrl()+wsEndUserEndpoint);
    	AppUserToken utoken = null;
    	try {
    		utoken = wsEndUser.loginAs(username, authentication.getCredentials().toString());
		}
    	catch (Exception e) {
    		throw new UsernameNotFoundException("USERNAME_NOT_FOUND", e);
		}

    	if (utoken == null || !utoken.isValid()) {
    		throw new UsernameNotFoundException("USERNAME_NOT_FOUND");
    	}
    	// create new cloud-user
    	CloudUser cu = new CloudUser(username, authentication.getCredentials().toString(),details.getDatacenterId());
    	// set di wsEndUser to the user (so every ws-invoke use same authentication token)
    	cu.setWsEndUser(wsEndUser);

    	try {
    		//find VDCResourceConfiguration
        	WsEndUserVDCConfigClient wsEndUserVDCConfigClient = new WsEndUserVDCConfigClient(wsEndUserNamespace, wsEndUserServiceName, details.getDatacenterUrl()+wsEndUserEndpoint);
        	wsEndUserVDCConfigClient.setCredentials(utoken.getUserName(),utoken.getToken());
        	cu.setVdcResourceBoundConfig(wsEndUserVDCConfigClient.getVDCResourceConfiguration());
		} catch (Exception e) {
			throw new UsernameNotFoundException("VDC_CONFIG_NOT_FOUND", e);
		}



        return new UserDetailsImpl(cu);

    }



}
