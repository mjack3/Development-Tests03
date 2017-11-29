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



<display:table pagesize="5" name="bill" requestURI="${requestURI}" id="row" class="table table-over">


	<spring:message code="bill.amount" var="amountHeader" />
	<display:column property="amount" title="${amountHeader}"
		sortable="false" />

	<spring:message code="bill.date" var="dateHeader" />
	<display:column property="date" title="${dateHeader}"
		sortable="false" />

	<spring:message code="bill.paid" var="PaidHeader" />
	<display:column title="${PaidHeader}" sortable="false" > 
		<jstl:if test="${row.paid}">
			<spring:message code="bill.yes" />
		</jstl:if>
		<jstl:if test="${!row.paid}">
			<spring:message code="bill.no" />
		</jstl:if>
	</display:column>

	<spring:message code="bill.endorsed" var="endorsedsHeader" />
	<display:column  title="${endorsedsHeader}" >
		<jstl:if test="${row.endorsed}">
			<spring:message code="bill.yes" />
		</jstl:if>
		<jstl:if test="${!row.endorsed}">
			<spring:message code="bill.no" />
		</jstl:if>
	</display:column>
	
	<spring:message code="bill.receipt" var="receiptHeader" />
	<display:column title="${receiptHeader}">
		<jstl:if test="${!row.receipt.isEmpty()}">
			<a href="${row.receipt}">${receiptHeader}</a>
		</jstl:if>		
	</display:column>
	
	<spring:message code="bill.poll" var="pollHeader" />
	<display:column title="${pollHeader}">
		${row.poll.title} (${row.poll.ticket})
	</display:column>
	
	<spring:message code="bill.add" var="addHeader" />
	<display:column>
		<jstl:if test="${row.receipt.isEmpty()}">
			<a href="bill/poller/add.do?q=${row.id}"> <spring:message code="bill.add"/> </a>
		</jstl:if>
	</display:column>
	
	<security:authorize access="hasRole('ADMINISTRATOR')">
	<jstl:if test="${listPaid}">
	<display:column>
		<jstl:if test="${!row.endorsed}">
			<a href="bill/administrator/endorse.do?q=${row.id}"> <spring:message code="bill.addEndorsed"/> </a>
		</jstl:if>
	</display:column>
	</jstl:if>
	
	</security:authorize>

	
</display:table>









