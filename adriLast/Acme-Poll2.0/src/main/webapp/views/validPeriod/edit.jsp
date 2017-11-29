<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>



<form:form action="validPeriod/administrator/save.do" modelAttribute="validPeriod">

		
		<form:hidden path="id" />
		<form:hidden path="version" />


	    <div class="form-group" style="width: 20%;"> 
	    
		<label> <spring:message code="validPeriod.minimumPeriod"/> </label>
		<br />
		<input class="form-control" value="${validPeriod.minimumPeriod}" type="number" min="1" step="1" name="minimumPeriod"/>
		<form:errors cssClass="error" path="minimumPeriod" /> <br />
		
		</div>
		
		<spring:message code="acme.save" var="actorSaveHeader"/>
		<spring:message code="acme.cancel" var="actorCancelHeader"/>
		<input type="submit" class="btn btn-primary" name="save" value="${actorSaveHeader}" />
		<input onclick="window.history.back()" class="btn btn-warning" type="button" name="cancel" value="${actorCancelHeader}"/>
		
		
	</form:form>






