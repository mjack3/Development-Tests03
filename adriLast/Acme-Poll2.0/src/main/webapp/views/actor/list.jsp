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


<display:table pagesize="12" keepStatus="true" name="actor"
	requestURI="actor/list.do" id="row" class="table table-over">
	
	<spring:message code="actor.name" var="name" />
	<display:column property="name" title="${name}"
		sortable="false" />
	
	<spring:message code="actor.chirps" var="chirps" />
	<display:column title="${chirps}">
	
	<a href="chirp/actor/list.do?q=${row.id}"><jstl:out value="${chirps}"></jstl:out></a>
	
	</display:column>
	
	
</display:table>
<a href="chirp/actor/create.do"><spring:message code="chirp.create"></spring:message></a>

