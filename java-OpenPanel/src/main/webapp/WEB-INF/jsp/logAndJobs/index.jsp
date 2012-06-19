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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<div class="dashboards index">
	<h2>
		<fmt:message key='LogAndJobs' />
	</h2>
	<div align="right">
			<a id="refreshBtn" href="<c:url value="/logAndJobs/index"/>"><fmt:message key="Refresh"/></a>
	</div>
	<script type="text/javascript">
			$('#refreshBtn').button({text:true, icons:{primary:'ui-icon-refresh'}});
	</script>


		<div id="job-list">
			<br />
			<h2>
				<fmt:message key='Job.List' />
			</h2>
			<display:table list="${logAndJob.jobs}" id="appJob" cellpadding="0"
				cellspacing="0">
				<display:column property="operationName" titleKey="Command" />
				<display:column property="progress" titleKey="Progress.perc" />
				<display:column titleKey="Status">
					<span class="log-status-${appJob.status}"><fmt:message
							key="log.${appJob.status}" />&nbsp;</span>
				</display:column>
				<display:column property="resourceValue" titleKey="Context" />
				<display:column property="username" titleKey="User" />
				<display:column property="lastUpdateDatetime" titleKey="Begin.Date" />
			</display:table>
		</div>

		<div id="log-list">
			<br />
			<h2>
				<fmt:message key='Log.List' />
			</h2>
			<display:table list="${logAndJob.logs}" requestURI="index"
				pagesize="20" id="appLog" cellpadding="0" cellspacing="0">
				<display:column property="operationName" titleKey="Command" />
				<display:column titleKey="Status">
					<span class="log-status-${appLog.status}"><fmt:message
							key="log.${appLog.status}" />&nbsp;</span>
				</display:column>
				<display:column property="targetResource" titleKey="Context" />
				<display:column property="username" titleKey="User" />
				<display:column property="startDatetime" titleKey="Begin.Date" />
				<display:column property="lastUpdateDatetime" titleKey="End.Date" />
			</display:table>
		</div>

</div>