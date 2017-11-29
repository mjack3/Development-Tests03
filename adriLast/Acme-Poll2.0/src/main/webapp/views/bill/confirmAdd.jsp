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


<form:form action="bill/poller/saveAdd.do" modelAttribute="bill">


	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="amount" />
	<form:hidden path="date" />
	<form:hidden path="paid" />
	<form:hidden path="endorsed" />
	<form:hidden path="poll" />
	<form:hidden path="receipt" />


	<div class="form-group" style="width: 20%;">

		<h3><b> <spring:message code="bill.confirm" /> </b></h3>


	</div>

	<spring:message code="actor.save" var="actorSaveHeader" />
	<spring:message code="actor.cancel" var="actorCancelHeader" />
	<input type="submit" class="btn btn-primary"
		name="save" value="${actorSaveHeader}" />
	<input onclick="window.history.back()" class="btn btn-warning"
		type="button" name="cancel" value="${actorCancelHeader}" />


</form:form>





