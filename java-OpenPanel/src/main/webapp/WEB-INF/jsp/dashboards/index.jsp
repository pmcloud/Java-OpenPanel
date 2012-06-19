<%
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
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<div class="dashboards index">
	<h2><fmt:message key='Dashboard'/></h2>
	<form id='dashboardForm' method='POST' action=''>
	<div id="available-credit" style="text-align: right;">
		<span class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-primary ui-state-focus">
		&nbsp;<fmt:message key='Available.Credit'/>:&nbsp;&nbsp;<font size="12">&euro;<fmt:formatNumber value="${dashboard.credit.value}" pattern="###,###.00"/> </font>&nbsp;
		</span>
	</div>

	<div id="server-list">
		<h2><fmt:message key='Cloud.Server.List'/></h2>

		<display:table list="${dashboard.servers}" id="appServer" cellpadding="0" cellspacing="0">
			<display:column titleKey="Name">
				${appServer.name}&nbsp;
				<c:if test="${appServer.updating}">
					<img src='<c:url value="/resources/images/rounder.png"/>'title="<fmt:message key='Updating'/>" alt="<fmt:message key='Updating'/>" border="0"/>
				</c:if>
			</display:column>
			<display:column titleKey="Status">
				<span class="server-status-${appServer.serverStatus}"><fmt:message key="server.${appServer.serverStatus}"/>&nbsp;</span>
			</display:column>
			<display:column property="OSTemplateDescription" titleKey="Template" escapeXml="true"/>
			<display:column titleKey="Hypervisor">
				<fmt:message key='${appServer.hypervisorType}'/>
			</display:column>
			<display:column property="CPUQuantity" titleKey="CPU.core"/>
			<display:column property="RAMQuantity" titleKey="RAM.GB"/>
			<display:column property="totalDiskSize" titleKey="Disk.space.GB"/>
			<display:column titleKey="Actions" class="actions" >
					<button type='button' id="manage_${appServer.serverId }"><fmt:message key='Manage'/></button>
					<script type="text/javascript">
						$("#manage_${appServer.serverId }").button({
					            icons: {
					                primary: "ui-icon-wrench"
					            }
					    });
					    $("#manage_${appServer.serverId }").click(function(){
					    	postForm('dashboardForm','manage_${appServer.serverId }','<c:url value="/cloudServers/view/${appServer.serverId}"/>',null, false);
					    	});
					</script>
			</display:column>
		</display:table>
	</div>

	<div id="log-list">
		<br/>
		<h2><fmt:message key='Log.List'/></h2>
		<c:url value="/dashboards/index/" var="pagingUrl" />
		<display:table list="${dashboard.logs}" requestURI="${pagingUrl}" pagesize="20" id="appLog" cellpadding="0" cellspacing="0">
			<display:column property="operationName" titleKey="Command"/>
			<display:column titleKey="Status">
				<span class="log-status-${appLog.status}"><fmt:message key="log.${appLog.status}"/>&nbsp;</span>
			</display:column>
			<display:column property="targetResource" titleKey="Context"/>
			<display:column property="username" titleKey="User"/>
			<display:column property="startDatetime" titleKey="Begin.Date"/>
			<display:column property="lastUpdateDatetime" titleKey="End.Date"/>
		</display:table>
	</div>


	</form>
</div>