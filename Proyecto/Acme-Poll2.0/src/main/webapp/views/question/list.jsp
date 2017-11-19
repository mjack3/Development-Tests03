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



<display:table pagesize="5" keepStatus="true" name="question"
	requestURI="${requestURI}" id="row" class="table table-over">

	<!-- Attributes -->

	<spring:message code="question.statment" var="statmentHeader" />
	<display:column property="statment" title="${statmentHeader}"
		sortable="false" />
		
	<spring:message code="question.number" var="numbertHeader" />
	<display:column property="number" title="${numbertHeader}"
		sortable="false" />


	<security:authorize access="isAnonymous()">


		<display:column>
			<a href="question/listChoice.do?q=${row.id}"> <spring:message
					code="question.choices" />
			</a>
		</display:column>

	</security:authorize>

	<security:authorize access="hasRole('POLLER')">

		<display:column>
			<a href="question/poller/listChoice.do?q=${row.id}"> <spring:message
					code="question.choices" />
			</a>
		</display:column>

	</security:authorize>



</display:table>









