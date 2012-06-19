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

<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div class="logins index">
<form action="j_spring_security_check" name="login" method="post">
	<fieldset>
		<legend><fmt:message key="Login"/></legend>
	</fieldset>
	<div id="login_username" style="width: 40%">
		<fmt:message key="Username"/><INPUT name="j_username" type="text" id="txtUserName" size="20" />
	</div>
	<div id="login_pwd" style="width: 40%">
		<fmt:message key="Password"/><INPUT name="j_password" type="password" id="txtPassword" size="20" />
	</div>
	<div id="login_vdcs" style="width: 40%">
		<fmt:message key="Datacenters"/>
		<select name="datacenterKey" id="LoginDatacenters">
			<option value="1::https://api.dc1.computing.cloud.it">DC1 - Arezzo - Italy</option>
			<option value="2::https://api.dc2.computing.cloud.it">DC2 - Arezzo - Italy</option>
			<option disabled="disabled" value="3::https://api.dc3.computing.cloud.it">DC3 - Repubblica Ceca</option>
		</select>
	</div>

	<div style="width: 40%; text-align: right;">
		<button type="submit" name="btnInvia" value="<fmt:message key="Login"/>" id="btnInvia" ><fmt:message key="Login"/></button>
	</div>
	<c:if test="${!empty loginKo}">
		<div style="width: 40%; text-align: left;">
			<b><fmt:message key="Login.KO"/></b>
		</div>
	</c:if>
</form>
</div>


	<!--<FORM action="j_spring_security_check" name="login" method="post">
       	<div align="center">
           <table border="0">
               <tr>
                   <td rowspan="2" colspan="2" valign="top"><img src="<c:url value="/resources/images/lockscreen.png" />"/></td>
               </tr>
               <tr>
                   <td valign="top">
                   <div >
	                   <TABLE align="center">
	                       <TR>
	                           <TD><SPAN id="Label1" class="mandatory">Username</SPAN></TD>
	                           <TD><INPUT name="j_username" type="text" id="txtUserName" size="20" /></TD>
	                       </TR>
	                       <TR>
	                           <TD><SPAN id="Label2" class="mandatory">Password</SPAN></TD>
	                           <TD><INPUT name="j_password" type="password" id="txtPassword" size="20" /></TD>
	                       </TR>
	                       <TR>
	                           <TD>&nbsp;</TD>
	                           <TD><button type="submit" name="btnInvia" value="Accedi" id="btnInvia" >Accedi</button></TD>
	                       </TR>
	                       <%if (request.getAttribute("loginKo")!=null) {// %>
	                       <TR>
	                       	   <TD>&nbsp;</TD>
	                           <TD align="left"><b>Login non riuscito</b></TD>
	                       </TR>
	                       <%}else{ %>
	                        <TR>
	                       	   <TD colspan="2">&nbsp;</TD>
	                       </TR>
	                       <%} %>

	                   </TABLE>
                   </div>
                   </td>
               </tr>
           </table>
        </div>
	</FORM>


-->
<script type="text/javascript">

		$('#btnInvia').button();

</script>


