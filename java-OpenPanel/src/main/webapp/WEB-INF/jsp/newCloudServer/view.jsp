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


<s:hasBindErrors name="NewCloudServer">
<s:bind path="NewCloudServer.name">
	<c:forEach items="${status.errorCodes}" var="errCode">
		<div class="message"><fmt:message key='NewCloudServer.name'/>: <fmt:message key='${errCode}.NewCloudServer.name' /></div>
	</c:forEach>
</s:bind>
<s:bind path="NewCloudServer.password">
	<c:forEach items="${status.errorCodes}" var="errCode">
		<div class="message"><fmt:message key='NewCloudServer.password'/>: <fmt:message key='${errCode}.NewCloudServer.password' /></div>
	</c:forEach>
</s:bind>
<s:bind path="NewCloudServer.passwordChk">
	<c:forEach items="${status.errorCodes}" var="errCode">
		<div class="message"><fmt:message key='NewCloudServer.password.check'/>: <fmt:message key='${errCode}.NewCloudServer.passwordChk' /></div>
	</c:forEach>
</s:bind>
<s:bind path="NewCloudServer.selectedDiskSize[0]">
	<c:forEach items="${status.errorCodes}" var="errCode">
		<div class="message"><fmt:message key='NewCloudServer.vhds.0'/>: <fmt:message key='${errCode}' /></div>
	</c:forEach>
</s:bind>
<s:bind path="NewCloudServer.selectedDiskSize[1]">
	<c:forEach items="${status.errorCodes}" var="errCode">
		<div class="message"><fmt:message key='NewCloudServer.vhds.1'/>: <fmt:message key='${errCode}' /></div>
	</c:forEach>
</s:bind>
<s:bind path="NewCloudServer.selectedDiskSize[2]">
	<c:forEach items="${status.errorCodes}" var="errCode">
		<div class="message"><fmt:message key='NewCloudServer.vhds.2'/>: <fmt:message key='${errCode}' /></div>
	</c:forEach>
</s:bind>
<s:bind path="NewCloudServer.selectedDiskSize[3]">
	<c:forEach items="${status.errorCodes}" var="errCode">
		<div class="message"><fmt:message key='NewCloudServer.vhds.3'/>: <fmt:message key='${errCode}' /></div>
	</c:forEach>
</s:bind>
<s:bind path="NewCloudServer.eth02_IP">
	<c:forEach items="${status.errorCodes}" var="errCode">
		<div class="message"><fmt:message key='ETHERNET02'/> - <fmt:message key='NewCloudServer.IP'/>: <fmt:message key='${errCode}.NewCloudServer.eth02_IP' /></div>
	</c:forEach>
</s:bind>
<s:bind path="NewCloudServer.eth02_NM">
	<c:forEach items="${status.errorCodes}" var="errCode">
		<div class="message"><fmt:message key='ETHERNET02'/> - <fmt:message key='NewCloudServer.NETMASK'/>: <fmt:message key='${errCode}.NewCloudServer.eth02_NM' /></div>
	</c:forEach>
</s:bind>
<s:bind path="NewCloudServer.eth03_IP">
	<c:forEach items="${status.errorCodes}" var="errCode">
		<div class="message"><fmt:message key='ETHERNET03'/> - <fmt:message key='NewCloudServer.IP'/>: <fmt:message key='${errCode}.NewCloudServer.eth03_IP' /></div>
	</c:forEach>
</s:bind>
<s:bind path="NewCloudServer.eth03_NM">
	<c:forEach items="${status.errorCodes}" var="errCode">
		<div class="message"><fmt:message key='ETHERNET03'/> - <fmt:message key='NewCloudServer.NETMASK'/>: <fmt:message key='${errCode}.NewCloudServer.eth03_NM' /></div>
	</c:forEach>
</s:bind>
<s:bind path="NewCloudServer.vlan_eth02">
	<c:forEach items="${status.errorCodes}" var="errCode">
		<div class="message"><fmt:message key='ETHERNET02'/>: <fmt:message key='${errCode}.NewCloudServer.vlan_eth02' /></div>
	</c:forEach>
</s:bind>
<s:bind path="NewCloudServer.vlan_eth03">
	<c:forEach items="${status.errorCodes}" var="errCode">
		<div class="message"><fmt:message key='ETHERNET03'/>: <fmt:message key='${errCode}.NewCloudServer.vlan_eth03' /></div>
	</c:forEach>
</s:bind>
<s:bind path="NewCloudServer.createError">
	<c:forEach items="${status.errorMessages}" var="errCode">
		<div class="message">${errCode}</div>
	</c:forEach>
</s:bind>
</s:hasBindErrors>


<form:form id='NewCloudServersForm' method='POST' commandName="NewCloudServer" action=''>
	<h2><fmt:message key='NewCloudServer.definition'/></h2>

	<table class="no-alternate" cellpadding="2" cellspacing="0" width="100%" border="0">
		<tr class="no-alternate">
			<td nowrap="nowrap" class="no-alternate"><b><fmt:message key='NewCloudServer.name'/>:</b></td>
			<td class="no-alternate">
				<form:input path="name"/>
			</td>
			<td class="no-alternate" width="30%"><fmt:message key='NewCloudServer.name.info'/></td>
		</tr>
		<tr class="no-alternate">
			<td nowrap="nowrap" class="no-alternate"><b><fmt:message key='NewCloudServer.hypervisor'/>:</b></td>
			<td class="no-alternate">
				<form:select id="hypervisorSelect" path="selectedHypervisorType">
					<c:forEach var="entry" items="${NewCloudServer.hypervisorTypes}">
						<form:option value="${entry.key}" label="${entry.key}" />
					</c:forEach>
				</form:select>
			</td>
			<td class="no-alternate" width="30%"><fmt:message key='NewCloudServer.hypervisor.info'/></td>
		</tr>
		<tr class="no-alternate">
			<td nowrap="nowrap" class="no-alternate"><b><fmt:message key='NewCloudServer.ostemplate'/>:</b></td>
			<td class="no-alternate" colspan="2">
				<form:select id="templateSelect" path="selectedTemplateProdId">
					<c:forEach var="entry" items="${NewCloudServer.templateList}">
						<form:option value="${entry.key}" label="${entry.value}" />
					</c:forEach>
				</form:select>
			</td>
		</tr>
		<tr class="no-alternate">
			<td class="no-alternate">&nbsp;</td>
			<td class="no-alternate" colspan="2" align="center"><h3><fmt:message key='NewCloudServer.admpasswd.definition'/></h3></td>
		</tr>
		<tr class="no-alternate">
			<td nowrap="nowrap" class="no-alternate"><b><fmt:message key='NewCloudServer.password'/>:</b></td>
			<td class="no-alternate">
				<form:input type="password" id="password" path="password" size="20" maxlength="20"/>
			</td>
			<td class="no-alternate"><fmt:message key='NewCloudServer.password.info'/></td>
		</tr>
		<tr class="no-alternate">
			<td nowrap="nowrap" class="no-alternate"><b><fmt:message key='NewCloudServer.password.check'/>:</b></td>
			<td class="no-alternate">
				<form:input type="password" id="passwordChk" path="passwordChk" size="20" maxlength="20"/>
			</td>
			<td class="no-alternate"><fmt:message key='NewCloudServer.password.retype'/></td>
		</tr>
		<tr class="no-alternate">
			<td class="no-alternate">&nbsp;</td>
			<td class="no-alternate" colspan="2" align="center"><h3><fmt:message key='NewCloudServer.cpu_ram.definition'/></h3></td>
		</tr>
		<tr class="no-alternate">
			<td nowrap="nowrap" class="no-alternate"><b><fmt:message key='NewCloudServer.cpu'/>:</b></td>
			<td class="no-alternate">
			<form:select id="cpuSelect" path="selectedCPUNum">
				<c:forEach begin="${NewCloudServer.appCPUsBound.min}" end="${NewCloudServer.appCPUsBound.max}" var="counter">
					<form:option value="${counter}" label="${counter}"/>
				</c:forEach>
			</form:select>
			&nbsp;&nbsp;(<fmt:message key='NewCloudServer.core'/>)
			</td>
			<td class="no-alternate"><fmt:message key='NewCloudServer.cpu.info'/></td>
		</tr>
		<tr class="no-alternate">
			<td nowrap="nowrap" class="no-alternate"><b><fmt:message key='NewCloudServer.ram'/>:</b></td>
			<td class="no-alternate">
			<form:select id="ramSelect" path="selectedRAMNum">
				<c:forEach begin="${NewCloudServer.appRAMsBound.min}" end="${NewCloudServer.appRAMsBound.max}" var="counter">
					<form:option value="${counter}" label="${counter}"/>
				</c:forEach>
			</form:select>
			&nbsp;&nbsp;(<fmt:message key='NewCloudServer.gb'/>)
			</td>
			<td class="no-alternate"><fmt:message key='NewCloudServer.ram.info'/></td>
		</tr>
		<tr class="no-alternate">
			<td class="no-alternate">&nbsp;</td>
			<td class="no-alternate" colspan="2" align="center"><h3><fmt:message key='NewCloudServer.vhds.definition'/></h3></td>
		</tr>
		<tr class="no-alternate">
			<td nowrap="nowrap" class="no-alternate"><b><fmt:message key='NewCloudServer.vhds.0'/>:</b></td>
			<td class="no-alternate">
				<form:input id="diskSize_0"  path="selectedDiskSize[0]" size="3" maxlength="3"/>
				&nbsp;(<fmt:message key='NewCloudServer.gb'/>)&nbsp;&nbsp;
			</td>
			<td class="no-alternate">
				<span id="diskBounds0">
				<c:out value="Min: ${NewCloudServer.appHDxBound[0].min}  Default: ${NewCloudServer.appHDxBound[0].default} Max: ${NewCloudServer.appHDxBound[0].max}" />
				</span>
			</td>
		</tr>
		<tr class="no-alternate">
			<td nowrap="nowrap" class="no-alternate"><b><fmt:message key='NewCloudServer.vhds.1'/>:</b></td>
			<td class="no-alternate">
				<c:choose>
					<c:when test="${NewCloudServer.selectedDiskSize[1] > 0}">
						<form:input id="diskSize_1"  path="selectedDiskSize[1]" size="3" maxlength="3"/>
					</c:when>
					<c:otherwise>
						<form:input id="diskSize_1"  path="selectedDiskSize[1]" size="3" maxlength="3" disabled="true"/>
					</c:otherwise>
				</c:choose>
				&nbsp;(<fmt:message key='NewCloudServer.gb'/>)&nbsp;&nbsp;
				<button type="button" id='addHdBtn1'><fmt:message key='NewCloudServer.vhd.activate'/></button>
			</td>
			<td class="no-alternate">
				<span id="diskBounds1">
				<c:out value="Min: ${NewCloudServer.appHDxBound[1].min}  Default: ${NewCloudServer.appHDxBound[1].default} Max: ${NewCloudServer.appHDxBound[1].max}" />
				</span>
			</td>
		</tr>
		<tr class="no-alternate">
			<td nowrap="nowrap" class="no-alternate"><b><fmt:message key='NewCloudServer.vhds.2'/>:</b></td>
			<td class="no-alternate">
				<c:choose>
					<c:when test="${NewCloudServer.selectedDiskSize[2] > 0}">
						<form:input id="diskSize_2"  path="selectedDiskSize[2]" size="3" maxlength="3"/>
					</c:when>
					<c:otherwise>
						<form:input id="diskSize_2"  path="selectedDiskSize[2]" size="3" maxlength="3" disabled="true"/>
					</c:otherwise>
				</c:choose>
				&nbsp;(<fmt:message key='NewCloudServer.gb'/>)&nbsp;&nbsp;
				<button type="button" id='addHdBtn2'><fmt:message key='NewCloudServer.vhd.activate'/></button>
			</td>
			<td class="no-alternate">
				<span id="diskBounds2">
				<c:out value="Min: ${NewCloudServer.appHDxBound[2].min}  Default: ${NewCloudServer.appHDxBound[2].default} Max: ${NewCloudServer.appHDxBound[2].max}" />
				</span>
			</td>
		</tr>
		<tr class="no-alternate">
			<td nowrap="nowrap" class="no-alternate"><b><fmt:message key='NewCloudServer.vhds.3'/>:</b></td>
			<td class="no-alternate">
				<c:choose>
					<c:when test="${NewCloudServer.selectedDiskSize[3] > 0}">
						<form:input id="diskSize_3"  path="selectedDiskSize[3]" size="3" maxlength="3"/>
					</c:when>
					<c:otherwise>
						<form:input id="diskSize_3"  path="selectedDiskSize[3]" size="3" maxlength="3" disabled="true"/>
					</c:otherwise>
				</c:choose>
				&nbsp;(<fmt:message key='NewCloudServer.gb'/>)&nbsp;&nbsp;
				<button type="button" id='addHdBtn3'><fmt:message key='NewCloudServer.vhd.activate'/></button>
			</td>
			<td class="no-alternate">
				<span id="diskBounds3">
				<c:out value="Min: ${NewCloudServer.appHDxBound[3].min}  Default: ${NewCloudServer.appHDxBound[3].default} Max: ${NewCloudServer.appHDxBound[3].max}" />
				</span>
			</td>
		</tr>
	</table>
	<%@ include file="editNewNetworks.jspf" %>
	<br/>
	<div style="width:100%; text-align:right;">
			<button type="button" id="createServer"><fmt:message key='NewCloudServer.docreation'/></button>
	</div>
</form:form>
</div>
<script type="text/javascript">
<!--
$('#createServer').button({icons:{primary:'ui-icon-plusthick'}});
$('#createServer').click(function() {
	postForm('NewCloudServersForm','createServer','<c:url value="/newCloudServers/createServer" />',null, false);
});
function liveAddHD(hdProg) {
	var disab = $('#diskSize_'+hdProg).attr('disabled');
	$('#diskSize_'+hdProg).attr('disabled', !disab);
	if (disab) {
		ajaxHDBoundsChange();
	}
	else {
		$('#diskSize_'+hdProg).val(-1);
	}
};

$('#addHdBtn1').button({text:false,icons:{primary:'ui-icon-cart'}});
$('#addHdBtn1').click(function() {
	liveAddHD(1);
});

$('#addHdBtn2').button({text:false,icons:{primary:'ui-icon-cart'}});
$('#addHdBtn2').click(function() {
	liveAddHD(2);
});

$('#addHdBtn3').button({text:false,icons:{primary:'ui-icon-cart'}});
$('#addHdBtn3').click(function() {
	liveAddHD(3);
});

function ajaxHDBoundsChange() {

	$.getJSON('<c:url value="/newCloudServers/loadHDBounds" />'
          	+"/"+ $('#hypervisorSelect').val()
          	+"/"+ $('#templateSelect').val()
          	, null
          	, function(jsonResp) {

          		var info = '';
          		for (var i = 0; i < jsonResp.length; i++) {

          			$('#diskBounds'+i).empty();
          			var currentSize = $('#diskSize_'+i).val();
          			var disabled = $('#diskSize_'+i).attr('disabled');
          			for (var k = 0; k < 3; k++) {
              			info += jsonResp[i][k].boundKey + " " + jsonResp[i][k].boundValue + " ";
          				if (!disabled && k == 0 && currentSize < jsonResp[i][k].boundValue) {
          					$('#diskSize_'+i).val(jsonResp[i][k].boundValue);
          				}
          				if (!disabled && k == 2 && currentSize > jsonResp[i][k].boundValue) {
          					$('#diskSize_'+i).val(jsonResp[i][k].boundValue);
          				}
          			}
              		$('#diskBounds' + i).html(info);
              		info = '';
          		}
		});
};

function ajaxSelectChange(selectedId, ajaxUrl, ajaxParams) {

    $.getJSON(ajaxUrl, ajaxParams, function(jsonResp) {

    		$('#'+selectedId).empty();
            var options = ''; //'<option value="">...</option>';
            for (var i = 0; i < jsonResp.length; i++) {
                    options += '<option value="' + jsonResp[i].itemId + '">' + jsonResp[i].description + '</option>';
            }
            $('#'+selectedId).html(options);
    });
}

function hypervSelectChange() {

    $.getJSON('<c:url value="/newCloudServers/loadTemplates" />'+"/"+ $('#hypervisorSelect').val()
    	    , null
    	    , function(jsonResp) {
	    		$('#templateSelect').empty();
	            var options = '';
	            for (var i = 0; i < jsonResp.length; i++) {
	                    options += '<option value="' + jsonResp[i].itemId + '"';
	                    if (i == 0) {
		                    options += ' selected="selected" ';
	                    }
	                    options +=  '>' + jsonResp[i].description + '</option>';
	            }
	            $('#templateSelect').html(options);

	          	//---- change CPU select ---
	          	ajaxSelectChange("cpuSelect"
	    	          	,'<c:url value="/newCloudServers/loadCPUs" />'
	    	          	+"/"+ $('#hypervisorSelect').val()
	    	          	+"/"+ $('#templateSelect').val()
	    	          	,null);
	            //---- change RAM select ---
	            ajaxSelectChange("ramSelect"
	    	            ,'<c:url value="/newCloudServers/loadRAMs" />'
	    	            +"/"+ $('#hypervisorSelect').val()
	    	            +"/"+ $('#templateSelect').val()
	    	            ,null);
	            //---- change HDs bounds ---
	            ajaxHDBoundsChange();
    		}); // end of "main" json-callback
}

function templateSelectChange() {

	//---- change CPU select ---
  	ajaxSelectChange("cpuSelect"
          	,'<c:url value="/newCloudServers/loadCPUs" />'
          	+"/"+ $('#hypervisorSelect').val()
          	+"/"+ $('#templateSelect').val()
          	,null);
    //---- change RAM select ---
    ajaxSelectChange("ramSelect"
            ,'<c:url value="/newCloudServers/loadRAMs" />'
            +"/"+ $('#hypervisorSelect').val()
            +"/"+ $('#templateSelect').val()
            ,null);
    //---- change HDs bounds ---
    ajaxHDBoundsChange();
}

$('#hypervisorSelect').change(function() {
	hypervSelectChange();
});

$('#templateSelect').change(function() {
	templateSelectChange();
});

$(document).ready(function() {
	var sel = $('#selectVLAN_ETH02 option:selected').attr('value');
	$('#ETH02_IP').attr('disabled', (sel == null || sel < 0));
	$('#ETH02_NM').attr('disabled', (sel == null || sel < 0));

	sel = $('#selectVLAN_ETH03 option:selected').attr('value');
	$('#ETH03_IP').attr('disabled', (sel == null || sel < 0));
	$('#ETH03_NM').attr('disabled', (sel == null || sel < 0));
});

$('#selectVLAN_ETH02').change(function() {
	var sel = $('#selectVLAN_ETH02 option:selected').attr('value');
	$('#ETH02_IP').attr('disabled', (sel < 0));
	$('#ETH02_NM').attr('disabled', (sel < 0));
});

$('#selectVLAN_ETH03').change(function() {
	var sel = $('#selectVLAN_ETH03 option:selected').attr('value');
	$('#ETH03_IP').attr('disabled', (sel < 0));
	$('#ETH03_NM').attr('disabled', (sel < 0));
});


//-->
</script>
