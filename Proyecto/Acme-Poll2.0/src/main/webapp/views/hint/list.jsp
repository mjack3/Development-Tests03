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


<display:table pagesize="5" keepStatus="true" name="hint"
	requestURI="${requestURI}" id="row" class="table table-over">

	<!-- Attributes -->

	<spring:message code="hint.text" var="textHeader" />
	<display:column property="text" title="${textHeader}"
		sortable="false" />

	<spring:message code="hint.mark" var="markHeader" />
	<display:column property="mark" title="${markHeader}"
		sortable="false" />
		
		
	<spring:message code="hint.state" var="stateHeader" />
	<display:column title="${stateHeader}" sortable="false" >
		<a href="hint/score.do?q=${row.id}&p=${pollId}"> ${stateHeader} </a>
	</display:column>
	
	<security:authorize access="hasRole('ADMINISTRATOR')">	
		<spring:message code="acme.delete" var="removeHeader" />
		<display:column title="${removeHeader}" sortable="false" >
			<a href="hint/remove.do?q=${row.id}&p=${pollId}"> ${removeHeader} </a>
		</display:column>
	</security:authorize>

		
</display:table>

<br/>
<br/>

<a href="hint/create.do?q=${pollId}"> <spring:message code="hint.create" /> </a>






