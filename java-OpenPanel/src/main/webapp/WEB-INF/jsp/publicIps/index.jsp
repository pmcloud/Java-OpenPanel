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


<div class="iPAddresses index">
	<h2><fmt:message key='IP.Addresses'/></h2>

	<form id='ipFrom' method='POST' action=''>
	<div style="width:100%" align="right">
		<button type='button' id="addIpBtn"><fmt:message key='New.IP.Address'/></button>
					<script type="text/javascript">
						$("#addIpBtn").button({
					            icons: {
					                primary: "ui-icon-circle-plus"
					            }
					    });
					    $("#addIpBtn").click(function(){postForm('ipFrom','addIpBtn','<c:url value="/publicIps/add"/>','<fmt:message key="publicIps.add.confirm"/>',true);});
					</script>
	</div>
	<display:table list="${publicIps}" requestURI="index" pagesize="20" id="appIp" cellpadding="0"  cellspacing="0">
		<display:column titleKey="IP.Address" property="value"/>
		<display:column titleKey="Assigned">
			<c:choose>
				<c:when test="${appIp.assigned}">
					<fmt:message key="Yes"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="No"/>
				</c:otherwise>
			</c:choose>
		</display:column>
		<display:column titleKey="Cloud.Server" property="serverName"/>
		<display:column titleKey="Actions" class="actions">
			<button type='button' id="${appIp_rowNum}btn1"><fmt:message key='Delete'/></button>
					<script type="text/javascript">
						$("#${appIp_rowNum}btn1").button({
					            icons: {
					                primary: "ui-icon-trash"
					            }
					            <c:if test="${appIp.assigned}">
					    			,disabled:true
					    		</c:if>

					    });
					     <c:if test="${!appIp.assigned}">
					    	$("#${appIp_rowNum}btn1").click(function(){postForm('ipFrom','${appIp_rowNum}btn1','<c:url value="delete"/>/${appIp.resourceId}','<fmt:message key="publicIps.delete.confirm"><fmt:param value="${appIp.value}"/></fmt:message>',true)});
					    </c:if>
					</script>
		</display:column>
	</display:table>

	<%--
		<table cellpadding="0" cellspacing="0" border="0">

		<tr>
				<th><fmt:message key='IP Address'/></th>
				<th><fmt:message key='Assigned'/></th>
				<th><fmt:message key='Cloud Server'/></th>
				<th class="actions"><fmt:message key='Actions'/></th>
		</tr>
		<?php
		foreach ($publicIps as $iPAddress): $rowId='linkid'.$iPAddress->ResourceId ;?>
			<tr>
				<td><c:out value="${$iPAddress->Value}" escapeXml="true"/>&nbsp;</td>
				<td><?php if($iPAddress->isAssigned()){ $isAssigned=true; echo __('Yes');}else{$isAssigned=false; echo __('No');}; ?>&nbsp;</td>
				<td><c:out value="${$iPAddress->ServerName}" escapeXml="true"/>&nbsp;</td>
				<td>

					<button type='button' id="<?php echo $rowId.'btn1'?>"><fmt:message key='Delete'/></button>
					<script type="text/javascript">
						$("#<?php echo $rowId.'btn1' ?>").button({
					            icons: {
					                primary: "ui-icon-trash"
					            }
					            <?php if($isAssigned){ ?>
					    			,disabled:true
					    		<?php } ?>

					    });
					    <?php if(!$isAssigned){ ?>
					    	$("#<?php echo $rowId.'btn1' ?>").click(function(){postForm('ipFrom','<?php echo $rowId.'btn1' ?>','<?php echo $this->Form->url('delete/'.$iPAddress->ResourceId)?>','<?php echo __('Are you sure you want to delete  %s?', $iPAddress->Value) ?>',true)});
					    <?php } ?>
					</script>
				</td>
			</tr>
		<?php endforeach; ?>
		</table>
	 --%>
	</form>
</div>