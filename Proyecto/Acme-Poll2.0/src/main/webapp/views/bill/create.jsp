<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>




<form:form action="bill/administrator/save.do" modelAttribute="bill">

		
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="date" />
		<form:hidden path="paid" />
		<form:hidden path="endorsed" />
		<form:hidden path="receipt" />
		<form:hidden path="poll" />


	    <div class="form-group" style="width: 20%;"> 
	    
		<label> <spring:message code="bill.amount"/> </label>
		<br />
		<input class="form-control" value="${bill.amount}" type="number" min="0.1" step=".01" name="amount"/>
		<form:errors cssClass="error" path="amount" /> <br />
		
		</div>
		
		<spring:message code="actor.save" var="actorSaveHeader"/>
		<spring:message code="actor.cancel" var="actorCancelHeader"/>
		<input type="submit" class="btn btn-primary" name="save" value="${actorSaveHeader}" />
		<input onclick="window.history.back()" class="btn btn-warning" type="button" name="cancel" value="${actorCancelHeader}"/>
		
		
	</form:form>






