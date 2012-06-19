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

<div class="cloudServers index">
	<h2><fmt:message key='Cloud.Server.List'/></h2>
	<form id='cloudServersForm' method='POST' action=''>
	<div style="width:100%" align="right">
		<button type='button' id="addServerBtn"><fmt:message key="New.Cloud.Server"/></button>
					<script type="text/javascript">
						$("#addServerBtn").button({
					            icons: {
					                primary: "ui-icon-circle-plus"
					            }
					            /*,disabled:true */
					    });
					    $("#addServerBtn").click(function(){postForm('cloudServersForm','addServerBtn','<c:url value="/newCloudServers/index"/>',null,false);});
					</script>
	</div>
	<div id="server-list">
		<display:table list="${servers}" id="appServer" cellpadding="0" cellspacing="0">
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
					    	postForm('cloudServersForm','manage_${appServer.serverId }','<c:url value="/cloudServers/view/${appServer.serverId}"/>',null, false);
					    	});
					</script>
			</display:column>
		</display:table>
	</div>
	</form>
</div>