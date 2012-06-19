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

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CloudWebAutenticationDetails extends WebAuthenticationDetails{



	private String dataCenterUrl;
	private Integer datacenterId;

	/**
	 *
	 */
	private static final long serialVersionUID = 2628231496849648817L;



	public CloudWebAutenticationDetails(HttpServletRequest request) {
		super(request);
		doPopulateAdditionalInformation(request);

	}

	//@Override
	protected void doPopulateAdditionalInformation(HttpServletRequest request) {
		//super.doPopulateAdditionalInformation(request);
		try {
			String datacenterKey = request.getParameter("datacenterKey");
			if(datacenterKey!=null){
				String parts[]=datacenterKey.split("::");
				datacenterId = Integer.parseInt(parts[0].trim());
				dataCenterUrl=parts[1].trim();
				if(!dataCenterUrl.endsWith("/")){
					dataCenterUrl+="/";
				}
			}
		} catch (Exception e) {
			datacenterId=-1;
			dataCenterUrl="Unknow";
		}


	}


	public String getDatacenterUrl() {
		return dataCenterUrl;
	}

	public Integer getDatacenterId() {
		return datacenterId;
	}



}
