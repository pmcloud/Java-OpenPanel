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

<div class="virtualSwitches index">
	<h2><fmt:message key='Virtual.Switches'/></h2>

	<form id='vsFrom' method='POST' action=''>
	<div style="width:100%" align="right">
		<button type='button' id="addVsBtn"><fmt:message key='New.Virtual.Switch'/></button>
					<script type="text/javascript">
						$("#addVsBtn").button({
					            icons: {
					                primary: "ui-icon-circle-plus"
					            }
					    });
					    $("#addVsBtn").click(function(){$('#addDlg').dialog('open');});
					</script>
	</div>

		<display:table list="${virtualSwitches}" requestURI="index" pagesize="20" id="appVlan" cellpadding="0"  cellspacing="0">
		<display:column titleKey="Virtual.Switch" property="name"/>
		<display:column titleKey="Assigned">
			<c:choose>
				<c:when test="${appVlan.connected}">
					<fmt:message key="Yes"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="No"/>
				</c:otherwise>
			</c:choose>
		</display:column>
		<display:column titleKey="Cloud.Server" property="serverNames"/>
		<display:column titleKey="Actions" class="actions">
			<button type='button' id="${appVlan_rowNum}btn1"><fmt:message key='Delete'/></button>
					<script type="text/javascript">
						$("#${appVlan_rowNum}btn1").button({
					            icons: {
					                primary: "ui-icon-trash"
					            }
					            <c:if test="${appVlan.connected}">
					    			,disabled:true
					    		</c:if>

					    });
					     <c:if test="${!appVlan.connected}">
					    	$("#${appVlan_rowNum}btn1").click(function(){postForm('vsFrom','${appVlan_rowNum}btn1','<c:url value="delete"/>/${appVlan.resourceId}','<fmt:message key="virtualSwitches.delete.confirm"><fmt:param value="${appVlan.name}"/></fmt:message>',true)});
					    </c:if>
					</script>
		</display:column>
	</display:table>


	</form>

</div>

<div id="addDlg"  title="<fmt:message key='Add.Virtual.Switch'/>" class="form">
	<form:form id="addDlgForm" action="add" commandName="virtualSwitch">
	<fieldset>
		<div>
			<fmt:message key="Name"/>
			<form:input path="name"/>
			<form:errors path="name" cssClass="error"/>
		</div>
		<div align='right'><button type='button' id='addOkBtn'><fmt:message key="OK"/></button><button type='button' id='addKoBtn'><fmt:message key="Cancel"/></button></div>
	</fieldset>
	</form:form>
</div>


<script type="text/javascript">
	$('#addDlg').dialog({ autoOpen:false, modal:true, resizable:false });
	$('#addOkBtn').button({
	        icons: {
	             primary: "ui-icon-circle-check"
	        }
	});
	$('#addKoBtn').button({
	        icons: {
	             primary: "ui-icon-circle-close"
	        }
	});
	$('#addOkBtn').click(function(){
		postForm('addDlgForm','addOkBtn',null,null, true);
	});
	$('#addKoBtn').click(function(){$('#addDlg').dialog('close')});
	<c:if test='${showDlg==true}'>
		$('#addDlg').dialog('open');
	</c:if>
</script>