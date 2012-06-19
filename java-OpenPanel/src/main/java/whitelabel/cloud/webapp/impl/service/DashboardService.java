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
package whitelabel.cloud.webapp.impl.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import whitelabel.cloud.entity.AppCredit;
import whitelabel.cloud.entity.AppLog;
import whitelabel.cloud.entity.AppServerDetails;
import whitelabel.cloud.entity.AppVirtualDatacenter;
import whitelabel.cloud.webapp.impl.model.Dashboard;
import whitelabel.cloud.webapp.utils.UserUtil;
import whitelabel.cloud.wsclient.enduser.WsEndUserClient;

@Service
public class DashboardService extends AppService {

	public Dashboard load(){

		Dashboard dashboard = new Dashboard();

		WsEndUserClient wsClient = UserUtil.getWsEndUserClient();

		AppCredit credit = wsClient.getAvailableCredit();
		dashboard.setCredit(credit);

		AppVirtualDatacenter vdc = loadDatacenter();

		List<AppServerDetails> servers = null;
		List<AppLog> logs = null;

		if(vdc!=null){
			servers= vdc.getServers();
		}

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -7);

		List<AppLog> tmp = wsClient.getLogs(null, cal.getTime(), null);
		if(tmp!=null){
			Collections.reverse(tmp);
			logs=tmp;
		}


		if(servers==null){
			servers = new ArrayList<AppServerDetails>();
		}
		if(logs==null){
			logs = new ArrayList<AppLog>();
		}

		dashboard.setLogs(logs);
		dashboard.setServers(servers);
		return dashboard;
	}


}
