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

<div class="CloudServers view">

<s:bind path="changeName.*">
	<c:forEach items="${status.errorMessages}" var="error">
		<div class="message">${error}</div>
	</c:forEach>
</s:bind>

<s:hasBindErrors name="diskSize">
	<s:bind path="diskSize.*">
		<c:forEach items="${status.errorMessages}" var="error">
			<div class="message">${error}</div>
		</c:forEach>
	</s:bind>
</s:hasBindErrors>
<s:bind path="changeCPU.*">
	<c:forEach items="${status.errorMessages}" var="error">
		<div class="message">${error}</div>
	</c:forEach>
</s:bind>
<s:bind path="changeRAM.*">
	<c:forEach items="${status.errorMessages}" var="error">
		<div class="message">${error}</div>
	</c:forEach>
</s:bind>

<form id='cloudServersForm' method='POST' action=''></form>
	<h2><fmt:message key='Manage.Cloud.Server'/></h2>
	<div>
		<a href='<c:url  value="/cloudServers/view/"/>${CloudServer.appServerDetail.serverId}' id="refreshBtn"><fmt:message key="Refresh"/></a>
	</div>
	<script type="text/javascript">
			$('#refreshBtn').button({text:true, icons:{primary:'ui-icon-refresh'}});
	</script>

		<table  border="0" cellpadding="2" cellspacing="0">
		<tr >
			<td nowrap="nowrap" ><b><fmt:message key='Cloud.Server.Name'/>:</b></td>
			<td>
				<span id="serverNameLbl"><c:out value="${CloudServer.appServerDetail.name}" escapeXml="true"/></span>
			</td>
			<td nowrap="nowrap">
				<%@ include file="editName.jspf"  %>
			</td>
			<td width="20%">&nbsp;</td>
			<td nowrap="nowrap"><b><fmt:message key='Cloud.Server.State'/>:</b></td>
			<td class="server-status-${CloudServer.appServerDetail.serverStatus}">
				<fmt:message key="server.${CloudServer.appServerDetail.serverStatus}"/>
				<c:if test="${CloudServer.appServerDetail.updating}">
					<img src='<c:url value="/resources/images/rounder.png" />' alt="<fmt:message key='Updating'/>"  title="<fmt:message key='Updating'/>" />
				</c:if>
			</td>
			<td>
				<c:if test="${CloudServer.appServerDetail.running}">
					<button type="button" id='poweroffBtn'><fmt:message key='Shutdown'/></button>
				</c:if>
				<c:if test="${CloudServer.appServerDetail.stopped}">
					<button id='startBtn'><fmt:message key='Start'/></button>
					<button id='archiveBtn'><fmt:message key='Archive'/></button>
				</c:if>
				<c:if test="${CloudServer.appServerDetail.archived}">
					<button id='restoreBtn'><fmt:message key='Restore'/></button>
				</c:if>
			</td>
		</tr>
		</table>

		<script type="text/javascript">
			$('#poweroffBtn').button({text:true, icons:{primary:'ui-icon-power'}});
			$('#startBtn').button({text:true, icons:{primary:'ui-icon-power'}});
			$('#archiveBtn').button({text:true, icons:{primary:'ui-icon-arrowreturnthick-1-s'}});
			$('#restoreBtn').button({text:true, icons:{primary:'ui-icon-arrowreturnthick-1-n'}});
			<c:if test="${CloudServer.appServerDetail.updating}">
				$('#poweroffBtn').button('disable');
				$('#startBtn').button('disable');
				$('#changeNameBtn').button('disable');
			</c:if>
			$("#startBtn").click(function(){
		    	postForm('cloudServersForm','startBtn','<c:url value="/cloudServers/start/${CloudServer.appServerDetail.serverId}"/>',null, true);
		    	});
			$("#poweroffBtn").click(function(){
				$('#srvStopDlg').dialog('open');
				});
			$("#archiveBtn").click(function(){
				postForm('cloudServersForm','archiveBtn','<c:url value="/cloudServers/archive/${CloudServer.appServerDetail.serverId}"/>',null, true);
		    	});
			$("#restoreBtn").click(function(){
				postForm('cloudServersForm','restoreBtn','<c:url value="/cloudServers/restore/${CloudServer.appServerDetail.serverId}"/>',null, true);
				});
		</script>

		<table  border="0" cellpadding="2" cellspacing="0">
		<tr>
			<td nowrap="nowrap" ><fmt:message key='Hypervisor'/>:</td>
			<td colspan="2"><b><fmt:message key='${CloudServer.appServerDetail.hypervisorType}'/></b></td>
			<td>&nbsp;</td>
			<td nowrap="nowrap" ><fmt:message key='HD0.Size'/>:</td>
			<c:set value="0" var="diskNum" scope="page"/>
			<%@ include file="editDisk.jspf" %>
		</tr>
		<tr>
			<td nowrap="nowrap" ><fmt:message key='S_O.template'/>:</td>
			<td colspan="2"><b><c:out value="${CloudServer.appServerDetail.OSTemplateDescription}" escapeXml="true"/></b></td>
			<td>&nbsp;</td>
			<td nowrap="nowrap" ><fmt:message key='HD1.Size'/>:</td>
			<c:set value="1" var="diskNum" scope="page"/>
			<%@ include file="editDisk.jspf" %>
		</tr>
		<tr>
			<td nowrap="nowrap" ><fmt:message key='Virtual.CPUs'/>:</td>
			<td nowrap="nowrap">
				<b>${CloudServer.appServerDetail.CPUQuantity}</b>
			</td>
			<td >
				<%@ include file="editCPUs.jspf" %>
			</td>
			<td >&nbsp;</td>
			<td nowrap="nowrap" ><fmt:message key='HD2.Size'/>:</td>
			<c:set value="2" var="diskNum" scope="page"/>
			<%@ include file="editDisk.jspf" %>
		</tr>
		<tr>
			<td nowrap="nowrap" ><fmt:message key='RAM'/>:</td>
			<td>
				<b>${CloudServer.appServerDetail.RAMQuantity}&nbsp;GB</b>
			</td>
			<td>
				<%@ include file="editRAMs.jspf" %>
			</td>
			<td>&nbsp;</td>
			<td nowrap="nowrap" ><fmt:message key='HD3.Size'/>:</td>
			<c:set value="3" var="diskNum" scope="page"/>
			<%@ include file="editDisk.jspf" %>
		</tr>
		</table>


	<%@ include file="editNetworks.jspf" %>

</div>

<c:if test="${CloudServer.appServerDetail.updating }">
	<script type="text/javascript">
		$(':button').button('disable');
	</script>
</c:if>

<%@ include file="serverStop.jspf" %>
