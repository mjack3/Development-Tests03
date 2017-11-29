<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<display:table pagesize="5" keepStatus="true" name="poller"
	requestURI="${requestURI}" id="row" class="table table-over">

	<!-- Attributes -->

	<spring:message code="poller.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}"
		sortable="false" />

	<spring:message code="poller.surname" var="surnameHeader" />
	<display:column property="surname" title="${surnameHeader}"
		sortable="false" />

	<spring:message code="poller.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}"
		sortable="false" />

	<spring:message code="poller.address" var="addressHeader" />
	<display:column property="address"  title="${addressHeader}"/>
	
	<security:authorize access="hasRole('ADMINISTRATOR')">
	
	<jstl:if test="${bannedPollers.contains(row) }">
		<display:column>
			<a href="poller/administrator/readmit.do?q=${row.id}"> <spring:message
					code="poller.readmit" />
			</a>
		</display:column>
	</jstl:if>

	<jstl:if test="${!bannedPollers.contains(row) }">
		<display:column>
			<a href="poller/administrator/banned.do?q=${row.id}"> <spring:message
					code="poller.banned" />
			</a>
		</display:column>
	</jstl:if>
	</security:authorize>

	
</display:table>









