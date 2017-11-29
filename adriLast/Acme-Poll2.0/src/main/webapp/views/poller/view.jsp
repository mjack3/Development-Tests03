
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<table>

<tr>
	<td><b><spring:message code="poller.name" var="nameHeader" /></b>
	</td>
	<b><jstl:out value="${nameHeader}" /></b>:
	<jstl:out value="${poller.name}" />
	<td>
<tr>
	<td><b><spring:message code="poller.surname" /></b>: <jstl:out
			value="${poller.surname}" /></td>
	<td>
<tr>
	<td><b><spring:message code="poller.email" /></b>: <jstl:out
			value="${poller.email}" /></td>
	<td>
<tr>
	<td><b><spring:message code="poller.phone" /></b>: <jstl:out
			value="${poller.phone}" /></td>
	<td>
<tr>
	<td><b><spring:message code="poller.address" /></b>: <jstl:out
			value="${poller.address}" /></td>

</table>

