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
package whitelabel.cloud.webapp.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import whitelabel.cloud.entity.VDCResourceBoundsConfig;
import whitelabel.cloud.exception.APPRuntimeException;
import whitelabel.cloud.exception.WsAuthenticationException;
import whitelabel.cloud.webapp.security.CloudUser;
import whitelabel.cloud.webapp.security.spring.UserDetailsImpl;
import whitelabel.cloud.wsclient.enduser.WsEndUserClient;

public class UserUtil {

	protected UserUtil() {

	}

	public static CloudUser getUser(){
		if(SecurityContextHolder.getContext()!=null && SecurityContextHolder.getContext().getAuthentication()!=null)
        {
			try {
	            UserDetailsImpl principal = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	            return (CloudUser) principal.getUser();
			} catch (Exception dontCare) {

			}

        }
		return  null;
	}

	public static WsEndUserClient getWsEndUserClient() throws APPRuntimeException{
		WsEndUserClient wsEndUserClient = null;
		try {
			CloudUser cu = getUser();
			if(cu!=null){
				wsEndUserClient = cu.getWsEndUser();
			}
		} catch (Exception e) {
			throw new WsAuthenticationException("cloudUser.error", e);
		}

		if(wsEndUserClient==null){
			throw new WsAuthenticationException("cloudUser.null");
		}

		return wsEndUserClient;

	}

	public static VDCResourceBoundsConfig getVDCResourceConfiguration() throws APPRuntimeException{
		VDCResourceBoundsConfig vdcResourceBoundsConfig = null;
		try {
			CloudUser cu = getUser();
			if(cu!=null){
				vdcResourceBoundsConfig = cu.getVdcResourceBoundConfig();
			}
		} catch (Exception e) {
			throw new APPRuntimeException("vdcResourceBoundsConfig.error", e);
		}

		if(vdcResourceBoundsConfig==null){
			throw new APPRuntimeException("vdcResourceBoundsConfig.null");
		}

		return vdcResourceBoundsConfig;

	}

}
