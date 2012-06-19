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

<c:if test="${webUser != null}">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td nowrap="nowrap">&nbsp;<!-- <fmt:message key="${menuObserver.selectedItem}"/> --></td>
		<td nowrap="nowrap" style="text-align: right; vertical-align: bottom;" valign="bottom">
			&nbsp;&nbsp;<img title="Utente" alt="Utente" src='<c:url value="/resources/images/user.png"/>'>&nbsp;${webUser.username}
			&nbsp;&nbsp;<img title="Logout" alt="Logout" src='<c:url value="/resources/images/exit.png"/>'>&nbsp;
			<a style="vertical-align: bottom;" href='<c:url value="/logout/" />'>Logout</a>
		</td>
	</tr>
</table>
</c:if>