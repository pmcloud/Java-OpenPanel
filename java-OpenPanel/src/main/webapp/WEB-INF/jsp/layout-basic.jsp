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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="java.util.Locale"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.springframework.web.servlet.support.RequestContext"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<META HTTP-EQUIV="Pragma" content="Cache-Control:no-cache">

	<link href="<c:url value="/resources/display-tag/css/displaytag.css" />" rel="stylesheet"  type="text/css" />

	<%--  GESTIONE TEMA (via SPRING) --%>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %><s:theme code="theme.mainref"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/main-style.css" />"  />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %><s:theme code="theme.addition"/>" />

	<%-- inclusion of scripts js --%>
	<%@ include file="include-js.jspf" %>

	<%-- i18n --%>
	<fmt:setBundle basename="messages" />

	<title><tiles:insertAttribute name="pageTitle" ignore="true"/></title>
</head>

<body class="ui-widget-content">
	<div id="container">
		<div class="ui-widget-content ui-widget-header ui-corner-all">
			<div >
				<tiles:insertAttribute name="header" />
			</div>
		</div>
		<div id="content">
			<c:if test="${FLASH_MESSAGE != null}">
  				<div id="flashMessage" class="message">${FLASH_MESSAGE}</div>
			</c:if>
			<div><tiles:insertAttribute name="body" /></div>
			<c:if test="${webUser!=null}">
				<div class="ui-widget-content ui-widget-header ui-corner-all uiactions">
					<tiles:insertAttribute name="menu" />
				</div>
			</c:if>
		</div>
		<div  class="ui-widget-content">&nbsp;

		</div>
	</div>
</body>

</html>