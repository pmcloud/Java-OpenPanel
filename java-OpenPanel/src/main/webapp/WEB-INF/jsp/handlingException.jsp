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

<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.StringWriter"%>
<%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="<c:url value="/resources/spring-errors/messages.css" />" rel="stylesheet"  type="text/css" />


<div class="index">

	<c:choose>
		<c:when test="${not empty resultMessage}">
			<div id="message" class="${resultMessage.type}">${resultMessage.text}</div>
		</c:when>
		<c:otherwise>
			<div id="message" class="error">${exception.message}</div>
		</c:otherwise>
	</c:choose>

	<%
	StringWriter sw = new StringWriter();
	if(exception!=null){
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
	}
	%>

	<div>
		<button type="button" id="backBtn" title="Indietro">Indietro</button>
		<button type="button" id="homeBtn" title="Home">Home</button>
		<c:if test="${exception !=null}">
			<button type="button" id="dettagliBtn" title="Dettagli">Dettagli</button>
		</c:if>
	</div>

	<c:if test="${exception !=null}">
		<div id="detailDiv"><%= sw.toString()%></div>
	</c:if>

	<script type="text/javascript">
		var i=0;
		$("#backBtn").click(function (){
			history.back();
		});

		$(function() {
			$( "#backBtn" ).button({
	            icons: {
	                primary: "ui-icon-arrowthickstop-1-w"
	            }

	        });
		});

		$("#homeBtn").click(function (){
			window.location="<%= request.getContextPath()%>/";
		});

		$(function() {
			$( "#homeBtn" ).button({
	            icons: {
	                primary: "ui-icon-home"
	            }

	        });
		});

		$(function() {
			$( "#detailDiv" ).hide();
			$( "#dettagliBtn" ).button({
	            icons: {
	                primary: "ui-icon-plus"
	            }

	        });
			$("#dettagliBtn").click(function (){
				if(i % 2 ==0){
					$( "#detailDiv" ).show("slow");
					$( "#dettagliBtn" ).button({
			            icons: {
			                primary: "ui-icon-minus"
			            }

			        });
				}else{
					$( "#detailDiv" ).hide("slow");
					$( "#dettagliBtn" ).button({
			            icons: {
			                primary: "ui-icon-plus"
			            }

			        });
				}
				i++;
			});
		});
	</script>
</div>