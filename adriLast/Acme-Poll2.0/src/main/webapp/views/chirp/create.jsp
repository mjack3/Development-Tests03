<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<spring:message code="chirp.text" var="text"></spring:message>




<form:form action="chirp/actor/save.do" modelAttribute="chirp">

	

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="momentWritten" />
		
		 <div class="form-group" style="width: 20%;"> 
	 
		
		<label> <spring:message code="chirp.text"/> </label><br />
		<form:textarea cols="30" rows="10" path="text" class="form-control"/>
		<form:errors cssClass="error" path="text" /> <br />
		
		
	<spring:message code="actor.save" var="actorSaveHeader"/>
		<spring:message code="actor.cancel" var="actorCancelHeader"/>
		<input type="submit" class="btn btn-primary" name="save" value="${actorSaveHeader}" />
		<input onclick="window.location='campaign/company/list.do'" class="btn btn-warning" type="button" name="cancel" value="${actorCancelHeader}"/>
		
		</div>
		
</form:form>
