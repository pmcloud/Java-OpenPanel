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

<%@page import="org.springframework.web.servlet.theme.CookieThemeResolver"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.ui.context.Theme"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">
			<img src='<c:url value="/resources/images/logo.png"/>'/>
		</td>
		<td width="70%" align="center">
		<c:if test="${cloudUser != null}">
			<table width="100%"  border="0">
				<tr>
					<td width="33%">&nbsp;</td>
					<td nowrap="nowrap"><fmt:message key="Datacenter"/>:&nbsp;${cloudUser.datacenterName}</td>
					<td><A href='<c:url value="/logout"/>' id="aChangeDc"><fmt:message key='Change'/></A></td>
					<td width="33%">&nbsp;</td>
					<td nowrap="nowrap"><fmt:message key="Welcome"/>:&nbsp;${cloudUser.username}</td>
					<td><A href='<c:url value="/logout"/>' id="aLogout"><fmt:message key='Logout'/></A></td>
				</tr>
			</table>
		</c:if>
		</td>
		<td width="20%" align="right" nowrap="nowrap">
			<a href="<c:url value='/changeLanguage?locale=it'/>"><img src="<c:url value='/resources/images/it_s1.jpg'/>" alt="Italiano" border="0"/></a>
			<a href="<c:url value='/changeLanguage?locale=en'/>"><img src="<c:url value='/resources/images/en_s1.jpg'/>" alt="English" border="0"/></a>
		</td>
	</tr>
</table>
<script type="text/javascript">
	$('#aChangeDc').button();
	$('#aLogout').button();
</script>