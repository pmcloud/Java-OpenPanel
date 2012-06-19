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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div>
		<div>&nbsp;</div>
		<c:set var="selectedLink" value="ui-accordion-header ui-helper-reset ui-state-active ui-corner-top"/>
		<c:set var="otherLink" value="ui-accordion-header ui-helper-reset ui-state-default ui-corner-all"/>
		<c:set var="legend" value="ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active"/>

		<div id="accordion" class="ui-accordion ui-widget ui-helper-reset ui-accordion-icons">
			<c:choose>
				<c:when test="${'dashboards'==menuObserver.selectedItem || 'home'==menuObserver.selectedItem}">
					<h3 class="${selectedLink}">
						<span class="ui-icon ui-icon-triangle-1-s"></span>
						<a href="<c:url value="/dashboards/index"/>"><fmt:message key="dashboard"/></a>
					</h3>
					<div class="${legend}">
						<span style="font-size:0.8em;"><fmt:message key="dashboard.legend"/></span>
					</div>
				</c:when>
				<c:otherwise>
					<h3 class="${otherLink}">
						<span class="ui-icon ui-icon-triangle-1-e"></span>
						<a href="<c:url value="/dashboards/index"/>"><fmt:message key="dashboard"/></a>
					</h3>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${'cloudServers'==menuObserver.selectedItem}">
					<h3 class="${selectedLink}">
						<span class="ui-icon ui-icon-triangle-1-s"></span>
						<a href="<c:url value="/cloudServers/index"/>"><fmt:message key="cloudServers"/></a>
					</h3>
					<div class="${legend}">
						<span style="font-size:0.8em;"><fmt:message key="cloudServers.legend"/></span>
					</div>
				</c:when>
				<c:otherwise>
					<h3 class="${otherLink}">
						<span class="ui-icon ui-icon-triangle-1-e"></span>
						<a href="<c:url value="/cloudServers/index"/>"><fmt:message key="cloudServers"/></a>
					</h3>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${'virtualSwitches'==menuObserver.selectedItem}">
					<h3 class="${selectedLink}">
						<span class="ui-icon ui-icon-triangle-1-s"></span>
						<a href="<c:url value="/virtualSwitches/index"/>"><fmt:message key="virtualSwitches"/></a>
					</h3>
					<div class="${legend}">
						<span style="font-size:0.8em;"><fmt:message key="virtualSwitches.legend"/></span>
					</div>
				</c:when>
				<c:otherwise>
					<h3 class="${otherLink}">
						<span class="ui-icon ui-icon-triangle-1-e"></span>
						<a href="<c:url value="/virtualSwitches/index"/>"><fmt:message key="virtualSwitches"/></a>
					</h3>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${'publicIps'==menuObserver.selectedItem}">
					<h3 class="${selectedLink}">
						<span class="ui-icon ui-icon-triangle-1-s"></span>
						<a href="<c:url value="/publicIps/index"/>"><fmt:message key="publicIps"/></a>
					</h3>
					<div class="${legend}">
						<span style="font-size:0.8em;"><fmt:message key="publicIps.legend"/></span>
					</div>
				</c:when>
				<c:otherwise>
					<h3 class="${otherLink}">
						<span class="ui-icon ui-icon-triangle-1-e"></span>
						<a href="<c:url value="/publicIps/index"/>"><fmt:message key="publicIps"/></a>
					</h3>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${'logAndJobs'==menuObserver.selectedItem}">
					<h3 class="${selectedLink}">
						<span class="ui-icon ui-icon-triangle-1-s"></span>
						<a href="<c:url value="/logAndJobs/index"/>"><fmt:message key="logAndJobs"/></a>
					</h3>
					<div class="${legend}">
						<span style="font-size:0.8em;"><fmt:message key="logAndJobs.legend"/></span>
					</div>
				</c:when>
				<c:otherwise>
					<h3 class="${otherLink}">
						<span class="ui-icon ui-icon-triangle-1-e"></span>
						<a href="<c:url value="/logAndJobs/index"/>"><fmt:message key="logAndJobs"/></a>
					</h3>
				</c:otherwise>
			</c:choose>
			<%--
				<h3 class="${otherLink}">
						<span class="ui-icon ui-icon-triangle-1-e"></span>
						<a href="<c:url value="/logout"/>"><fmt:message key="Logout"/></a>
				</h3>
			--%>
		</div>
		<span>&nbsp;</span>
</div>